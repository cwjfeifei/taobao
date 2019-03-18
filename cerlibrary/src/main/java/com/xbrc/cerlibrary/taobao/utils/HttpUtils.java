package com.xbrc.cerlibrary.taobao.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtils {
    private static final String DEFAULT_CHARSET = "UTF-8"; // 默认字符集
    private static final String _GET = "GET"; // GET
    private static final String _POST = "POST";// POST

    public static final Pattern INNERTEXTPATTERN = Pattern.compile(">([^<>]+?)<");

    /**
     * 初始化http请求参数
     */
    private static HttpURLConnection initHttp(String url, String method, Map<String, String> headers)
            throws IOException {
        URL _url = new URL(url);
        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod(method);
        http.setInstanceFollowRedirects(false);
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if(!method.equals(_GET)) {
            http.setDoOutput(true);
            http.setUseCaches(false);
        }
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * 初始化http请求参数
     */
    private static HttpsURLConnection initHttps(String url, String method, Map<String, String> headers)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new TrustAnyHostnameVerifier());
        http.setSSLSocketFactory(ssf);
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod(method);
        http.setInstanceFollowRedirects(false);
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if(!method.equals(_GET)) {
            http.setDoOutput(true);
            http.setUseCaches(false);
        }
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * get请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers, Charset charset) {
        StringBuffer buffer = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(initParams(url, params), _GET, headers);
            } else {
                http = initHttp(initParams(url, params), _GET, headers);
            }
            if(http.getResponseCode() == 200) {
                 /*Pattern pattern = Pattern.compile("charset=\\S*");
                Matcher matcher = pattern.matcher(http.getContentType());
               if (matcher.find()) {
                    charset = Charset.forName(matcher.group().replace("charset=", ""));
                }*/
                InputStream in = http.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in, charset.name()));
                buffer = new StringBuffer();
                String line = "";
                while ((line = read.readLine()) != null){
                    buffer.append(line);
                }
                in.close();
                if (http != null) {
                    http.disconnect();// 关闭连接
                }
                return buffer.toString();
            } else if (http.getResponseCode() == 302 || http.getResponseCode() == 301) {
                String url2 = http.getHeaderField("Location");
                URL newUrl = new URL(url2);
                String newHost = newUrl.getHost();
                headers.put("Host", newHost);
                return get(url2, params, headers, charset);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        return  get(url, params, headers, Charset.defaultCharset());
    }

    /**
     * get请求
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * get请求
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null, Charset.defaultCharset());
    }

    /**
     * get请求
     */
    public static String get(String url, Map<String, String> headers, Charset charset) {
        return get(url, null, headers, charset);
    }

    /**
     * post请求
     */
    public static String post(String url, String params, Map<String, String> headers, Charset charset) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, _POST, headers);
            } else {
                http = initHttp(url, _POST, headers);
            }
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(http.getOutputStream());
            dos.writeBytes(params);
            dos.flush();
            dos.close();

            int respCode = http.getResponseCode();
            if(HttpURLConnection.HTTP_OK == respCode) {
                InputStream in = http.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in, charset.name()));
                String valueString = null;
                bufferRes = new StringBuffer();
                while ((valueString = read.readLine()) != null) {
                    bufferRes.append(valueString);
                }
                in.close();
                if (http != null) {
                    http.disconnect();// 关闭连接
                }
                return bufferRes.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, map2Url(params), null, Charset.defaultCharset());
    }

    /**
     * post请求
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(url, map2Url(params), headers, Charset.defaultCharset());
    }

    /**
     * post请求
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers, Charset charset) {
        return post(url, map2Url(params), headers, charset);
    }

    public static String initParams(String url, Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     */
    public static String map2Url(Map<String, String> paramToMap) {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Map.Entry<String, String> entry : paramToMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (null != value && !"".equals(value.trim())) {
                try {
                    url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return url.toString();
    }

    /**
     * 检测是否https
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * 不进行主机名确认
     */
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 信任所有主机 对于任何证书都不做SSL检测
     * 安全验证机制，而Android采用的是X509验证
     */
    private static class MyX509TrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    public static String replRtnSpace(String html)
    {
        if (html == null)
        {
            return "";
        }
        return decode(Util.removeComment(html.replaceAll("[\t\r\n]", "").replaceAll(">\\s+?<", "><").replace("&nbsp;", " "))).trim();
//        return Util.removeComment(html.replaceAll("[\t\r\n]", "").replaceAll(">\\s+?<", "><").replace("&nbsp;", " "));
    }

    public static String getValue(String str, Pattern p) throws Exception
    {
        Matcher m = p.matcher(str);
        if (m.find())
        {
            if (m.groupCount() == 0)
            {
                return m.group().trim();
            }
            return m.group(1).trim();
        }
        return "";
    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5)
                        && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
                        .charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(
                                unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String getValue(String str, String pattern) throws Exception
    {
        Pattern p = Pattern.compile(pattern);
        return getValue(str, p);
    }

    public static List<String[]> getListArray(String html, Pattern p)
    {
        List<String[]> list = new ArrayList<String[]>();
        Matcher m = p.matcher(html);
        while (m.find())
        {
            String[] array = new String[m.groupCount()];
            for (int i = 0; i < m.groupCount(); i++)
            {
                array[i] = m.group(i + 1).trim();
            }
            list.add(array);
        }
        return list;
    }

    public static List<String[]> getListArray(String html, String pattern) throws Exception
    {
        return getListArray(html, Pattern.compile(pattern));
    }

    public static void print(List<String[]> list) throws Exception
    {
        print(list, false);
    }

    public static void print(List<String[]> list, Boolean innerText) throws Exception
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (innerText)
            {
                for (int j = 0; j < list.get(i).length; j++)
                {
                    list.get(i)[j] = getInnerText(list.get(i)[j]);
                }
            }
            StringBuffer sb = new StringBuffer();
            for(String item : list.get(i)) {
                sb.append(item + "\t" );
            }
            Log.i("信息：", sb.toString());
        }
    }

    public static String getInnerText(String html) throws Exception
    {
        if (html.contains("<"))
        {
            html = HttpUtils.getValue(html, INNERTEXTPATTERN);
        }
        return html;
    }

}
