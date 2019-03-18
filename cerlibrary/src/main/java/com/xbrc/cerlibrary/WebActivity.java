package com.xbrc.cerlibrary;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.xbrc.cerlibrary.taobao.DiffuseView;
import com.xbrc.cerlibrary.taobao.bean.BasicInfo;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoAddressBean;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoAdressItemBean;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoOrderInfoListBean;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoOrderListBean;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoTaoQiValueBean;
import com.xbrc.cerlibrary.taobao.bean.TaoBaoVipGrounpValueBean;
import com.xbrc.cerlibrary.taobao.utils.ExecuteTask;
import com.xbrc.cerlibrary.taobao.utils.GsonUtil;
import com.xbrc.cerlibrary.taobao.utils.HttpUtils;
import com.xbrc.cerlibrary.taobao.utils.LogUtil;
import com.xbrc.cerlibrary.taobao.utils.MD5Util;
import com.xbrc.cerlibrary.taobao.utils.TaoBaoHandlerUtil;
import com.xbrc.cerlibrary.taobao.utils.TaobaoHeaderUtil;
import com.xuexiang.xqrcode.XQRCode;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String loginUrl = "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&goto=https://h5.m.taobao.com/mlapp/mytaobao.html";
    private static final String afterLoginUrl = "https://h5.m.taobao.com/mlapp/mytaobao.html";
    private static final String afterLoginUrl2 = "https://m.taobao.com/tbopen/index.html";
    private static final String afterLoginUrl3 = "https://i.taobao.com/my_taobao.htm?";
    private static final String afterLoginUrl4 = "https://www.taobao.com";
    private static final String afterLoginUrl5 = "https://login.taobao.com/member/loginByIm.do";

    private static final String basicInfo1Url = "https://i.taobao.com/my_taobao.htm";
    private static final String basicInfo2Url = "https://member1.taobao.com/member/fresh/account_security.htm";
    private static final String basicInfo3Url = "https://member1.taobao.com/member/fresh/certify_info.htm";
    private static final String basicInfo4Url = "https://member1.taobao.com/member/fresh/account_profile.htm";
    private static final String basicInfo5Url = "https://vip.taobao.com/ajax/getGoldUser.do";
    private static final String basicInfo6Url = "https://h5.m.taobao.com/vip/home.html";
    private static final String addressUrl = "https://member1.taobao.com/member/fresh/deliver_address.htm";
    private static final String consumeUrl = "https://buyertrade.taobao.com/trade/itemlist/asyncBought.htm";// 所有订单链接


    private static final String aliUrl2 = "https://my.alipay.com/portal/i.htm";
    private static final String alipayInfo1Url = "https://member1.taobao.com/member/fresh/account_management.htm";
    private static final String huabeiUrl = "https://my.alipay.com/portal/huabeiWithDynamicFont.json";

    private String aliUrl = "";

    private static final Pattern alip = Pattern.compile("href=\"([^<>]*?)\" >我的支付宝</a>");
    private static final Pattern header_url = Pattern.compile("<img src=\"(.*?)&type=sns");
    private static final Pattern basicInfo1 = Pattern.compile("<input id=\"mtb-nickname\"[^>]*?value=\"([^<>]*?)\"/><input id=\"mtb-userid\"[^>]*?value=\"([^<>]*?)\"/>");
    private static final Pattern basicInfo2 = Pattern.compile("<span class=\"t\">登 录 邮 箱：</span><span class=\"default grid-msg \">([^<>]*?)</span>.*?<span class=\"t\">绑 定 手 机：</span><span class=\"default grid-msg\">([^<>]*?)</span>");
    private static final Pattern basicInfo11 = Pattern.compile("<div class=\"s-icon-main\" >极速退款</div>.*?<em>([^<>]*?)</em>");
    private static final Pattern basicInfo3 = Pattern.compile("<div class=\"msg-tit msg-vm\">([^<>]*?)</div>.*?<span>姓名：</span><div class=\"left\">([^<>]*?)</div></div><div class=\"explain-info\"><span>18位身份证号：</span><div class=\"left\">([^<>]*?)</div>");
    private static final Pattern basicInfo4 = Pattern.compile("<span class=\"label-like\">真实姓名：</span><strong>([^<>]*?)</strong>");
    private static final Pattern basicInfo5 = Pattern.compile("checked.*?/><label>([^<>]*?)</label>");
    private static final Pattern basicInfo6 = Pattern.compile("<input type=\"text\" name=\"_fm.ed._0.b\" value=\"([^<>]*?)\" class=\"inputtext input-year\" readonly=\"readonly\"/>.*?<input type=\"text\" name=\"_fm.ed._0.bi\" value=\"([^<>]*?)\" class=\"inputtext input-date\" readonly=\"readonly\" />.*?<input type=\"text\" name=\"_fm.ed._0.bir\" value=\"([^<>]*?)\" class=\"inputtext input-date\" readonly=\"readonly\" />");

    private static final Pattern tbAlipyInfo1 = Pattern.compile("<h3 class=\"ui-tipbox-title\">已绑定支付宝账户: <span class=\"red\">([^<>]*?)</span>");
    private static final Pattern huabeibalp = Pattern.compile("creditAmountStr.*?>([^<>]*?)<span.*?>([^<>]*?)</span>.*?availableAmountStr.*?>([^<>]*?)<span.*?>([^<>]*?)</span>");

    private BasicInfo basicInfo = new BasicInfo();


    private TextView tv_title_middle;
    private ViewGroup container;
    private LinearLayout rl_auth;
    private NumberProgressBar number_progress_bar;
    private AgentWeb mAgentWeb;
    private ImageView iv_title_back, iv_title_right;
    public AppCompatActivity mContext;
    private DiffuseView diffuseView;
    private String img_url = "";
    private String mCtoken = "";
    private boolean isCert = false;
    List<TaoBaoOrderListBean> monthOrderList = new ArrayList<TaoBaoOrderListBean>();//订单详情

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_web);
        StatusBarUtil.setColorForSwipeBack(this, getResources().getColor(R.color.task_ing), 0);
        mContext = this;
        LogUtil.logInit(true);
        tv_title_middle = findViewById(R.id.tv_title_middle);
        container = findViewById(R.id.container);
        rl_auth = findViewById(R.id.rl_auth);
        iv_title_back = findViewById(R.id.iv_title_back);
        iv_title_right = findViewById(R.id.iv_title_right);
        iv_title_back.setOnClickListener(this);
        iv_title_right.setOnClickListener(this);
        diffuseView = (DiffuseView) findViewById(R.id.diffuseView);
        diffuseView.start();

        number_progress_bar = (NumberProgressBar)findViewById(R.id.number_progress_bar);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
//                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
//                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setWebLayout(new WebLayout(this))
//                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
//                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(loginUrl);


        tv_title_middle.setText("淘宝认证");
        mAgentWeb.getAgentWebSettings().getWebSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(false);// 设置此属性，可任意比例缩放
        mAgentWeb.getAgentWebSettings().getWebSettings().setUserAgentString(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        mAgentWeb.getWebCreator().getWebView().addJavascriptInterface(this, "android");

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_title_back) {
            if (isCert) {
                showDialog();
            } else {
                finish();
            }
        } else if (i == R.id.iv_title_right) {
            mAgentWeb.getUrlLoader().reload();
        }
    }

    @JavascriptInterface
    public void goAuth() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
                mAgentWeb.getWebCreator().getWebView().evaluateJavascript("document.querySelector(\".qrcode-img img\").src", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        img_url = value;
                        goTaoBaoApp();
                    }
                });
            }
        });
    }

    private void goTaoBaoApp() {
        LogUtil.loge("返回的图片地址===" + img_url);
//        https://img.alicdn.com/tfscom/TB130tSLNnaK1RjSZFtwu2C2VXa.png
        OkGo.<File>get(img_url.replace("\"", "")).tag(this).execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                String tabao_login_url = XQRCode.analyzeQRCode(response.body().getPath());
                LogUtil.loge("淘宝===解析到的二维码数据为=====" + tabao_login_url);
                if (TaoBaoHandlerUtil.isPkgInstalled(mContext, "com.taobao.taobao")) {
                    TaoBaoHandlerUtil.gotoShop(mContext, tabao_login_url.replace("https", "taobao"));
                } else {
                    Toast.makeText(mContext, "您还没有安装淘宝客户端！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                LogUtil.loge("OkGo===onError==" + response.getException().toString());
            }
        });
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (url.startsWith(loginUrl)) {
                container.setVisibility(View.VISIBLE);
                rl_auth.setVisibility(View.GONE);
            } else {
                container.setVisibility(View.GONE);
                rl_auth.setVisibility(View.VISIBLE);
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(final WebView webView, String url) {
            if (webView.getProgress() < 100) return;
            LogUtil.loge("加载的网页为======" + url);
            // 获取cookie
            final String cokieStr = AgentWebConfig.getCookiesByUrl(webView.getUrl());
            LogUtil.loge("获取到的Cookie==" + cokieStr);
            if (url.startsWith(loginUrl)) {
                container.setVisibility(View.VISIBLE);
                rl_auth.setVisibility(View.GONE);
            } else {
                container.setVisibility(View.GONE);
                rl_auth.setVisibility(View.VISIBLE);
            }

            if (cokieStr != null && (url.equals(afterLoginUrl) || url.startsWith(afterLoginUrl2) || url.startsWith(afterLoginUrl3) || url.startsWith(afterLoginUrl4))) {
                isCert = true;
                ExecuteTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing())
                            return;
                        captureData(cokieStr);
                    }
                });
            } else if (url.startsWith(aliUrl2)) {
                mAgentWeb.getWebCreator().getWebView().evaluateJavascript("document.querySelector(\"#J-income-num\").textContent", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        basicInfo.setAli_income(value);
                        LogUtil.loge("获取到的累计收益为==" + value);
                        mAgentWeb.getUrlLoader().loadUrl("https://shenghuo.alipay.com/transfercore/withdraw/apply.htm");
                    }
                });
//                final String ali_cokieStr = AgentWebX5Config.getCookiesByUrl(url);
//                ExecuteTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            boolean getalipayInfo2 = getalipayInfo2(huabeiUrl, ali_cokieStr);
//                            if (getalipayInfo2) {
//                                addProgress(5);// +5%
//                            }
//                            addProgress(5);
//                        } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                });
            } else if (url.startsWith("https://shenghuo.alipay.com/transfercore/withdraw/apply.htm")) {
                mAgentWeb.getWebCreator().getWebView().evaluateJavascript("document.querySelector(\".number\").textContent", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        basicInfo.setAli_yue(value);
                        LogUtil.loge("获取到的账户余额为==" + value);
                        mAgentWeb.getUrlLoader().loadUrl("https://yebprod.alipay.com/yeb/mfWithdraw.htm");
                    }
                });
            } else if (url.startsWith("https://yebprod.alipay.com/yeb/mfWithdraw.htm")) {
                mAgentWeb.getWebCreator().getWebView().evaluateJavascript("document.querySelector(\".ft-orange\").textContent", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        basicInfo.setAli_yuebao(value);
                        LogUtil.loge("获取到的余额宝总金额为==" + value);
                        LogUtil.loge("获取到的所有数据为===" + basicInfo.toString());
                    }
                });
            }
            super.onPageFinished(webView, url);
        }

//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if (url.startsWith(afterLoginUrl) || url.startsWith(afterLoginUrl2) || url.startsWith(afterLoginUrl3) || url.startsWith(afterLoginUrl4) || url.startsWith(afterLoginUrl5)) {
//                container.setVisibility(View.GONE);
//                rl_auth.setVisibility(View.VISIBLE);
//            }
//            return true;
//        }

//        @Override
//        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//            sslErrorHandler.proceed();
//        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(final WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i >= 100 && webView.getUrl().startsWith(loginUrl)) {
                webView.loadUrl("javascript:" + TaoBaoConstant.taobao_login_hide);
            }
        }
    };

    private void captureData(String cookie) {
        mCtoken = getCtoken(cookie);
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo1 = getbasicInfo1(basicInfo1Url, cookie);// 获取淘宝id,淘宝账号,极速退款额度
        if (basicInfo1) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo2 = getbasicInfo2(basicInfo2Url, cookie);// 获取登陆邮箱和电话号码
        if (basicInfo2) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo3 = getbasicInfo3(basicInfo3Url, cookie);// 获取真实姓名和身份证
        if (basicInfo3) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo4 = getbasicInfo4(basicInfo4Url, cookie);// 获取真实姓名
        if (basicInfo4) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo5 = getbasicInfo5(basicInfo5Url, cookie);// 获取淘气值
        if (basicInfo5) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean basicInfo6 = getbasicInfo6(basicInfo6Url, cookie);// 获取VIP等级 成长值
        if (basicInfo6) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean alipayInfo1 = getalipayInfo1(alipayInfo1Url, cookie);// 获取支付宝账户
        if (alipayInfo1) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        boolean address = getAddress(addressUrl, cookie);// 获取收货地址
        if (address) {
            addProgress(5);
        }
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        int pages = getConsume0(consumeUrl, cookie);// 3个月有几页
        if (pages > 0) {
            for (int i = 1; i <= pages; i++) {
                if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
                boolean consume = getConsume(consumeUrl, cookie, i);// 淘宝订单数据
                if (consume) {
                    addProgress(2);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        basicInfo.setMonthOrderList(monthOrderList);
        //支付宝登录
        initAliPayLogin();
    }

    //支付宝登录
    @SuppressLint("SetJavaScriptEnabled")
    private void initAliPayLogin() {
        mAgentWeb.getUrlLoader().loadUrl(aliUrl);
    }

    // 获取3个月的淘宝订单页数
    private int getConsume0(String url, String cookie) {
        try {
            Map<String, String> heades = new HashMap<String, String>();
            heades.put("Accept", "application/json, text/javascript, */*; q=0.01");
            heades.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            heades.put("Cache-Control", "no-cache");
            heades.put("Connection", "keep-alive");
            heades.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            heades.put("Cookie", cookie);
            heades.put("Host", "buyertrade.taobao.com");
            heades.put("Pragma", "no-cache");
            heades.put("Referer", "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
            heades.put("X-Requested-With", "XMLHttpRequest");
            heades.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");

            Map<String, String> params = new HashMap<String, String>();

            params.put("_input_charset", "utf8");
            params.put("action", "itemlist/BoughtQueryAction");
            params.put("event_submit_do_query", String.valueOf(1));
//            params.put("dateBegin", String.valueOf(beginTime));
//            params.put("dateEnd", String.valueOf(endTime));
            params.put("pageNum", String.valueOf(1));
            params.put("pageSize", String.valueOf(15));
            params.put("prePageNo", String.valueOf(1));

            String result = HttpUtils.post(url, params, heades, Charset.forName("GBK"));

            if (TextUtils.isEmpty(result)) {
                return -1;
            } else {
                TaoBaoOrderInfoListBean TaoBaoOrderInfoListBean = GsonUtil.changeJsonToBean(result, TaoBaoOrderInfoListBean.class);
                if (TaoBaoOrderInfoListBean != null) {
                    if (TaoBaoOrderInfoListBean.getPage() != null) {
                        return TaoBaoOrderInfoListBean.getPage().getTotalPage();
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    // 获取淘宝订单列表
    private boolean getConsume(String url, String cookie, int page) {
        try {
            Map<String, String> heades = new HashMap<String, String>();
            heades.put("Accept", "application/json, text/javascript, */*; q=0.01");
            heades.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            heades.put("Cache-Control", "no-cache");
            heades.put("Connection", "keep-alive");
            heades.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            heades.put("Cookie", cookie);
            heades.put("Host", "buyertrade.taobao.com");
            heades.put("Pragma", "no-cache");
            heades.put("Referer", "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
            heades.put("X-Requested-With", "XMLHttpRequest");
            heades.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");

            Map<String, String> params = new HashMap<String, String>();

            params.put("_input_charset", "utf8");
            params.put("action", "itemlist/BoughtQueryAction");
            params.put("event_submit_do_query", String.valueOf(1));
//            params.put("dateBegin", String.valueOf(beginTime));
//            params.put("dateEnd", String.valueOf(endTime));
            params.put("pageNum", String.valueOf(page));
            params.put("pageSize", String.valueOf(15));

            String result = HttpUtils.post(url, params, heades, Charset.forName("GBK"));

            if (TextUtils.isEmpty(result)) {
                return false;
            } else {
                TaoBaoOrderInfoListBean taoBaoOrderInfoListBean = GsonUtil.changeJsonToBean(result, TaoBaoOrderInfoListBean.class);
                if (taoBaoOrderInfoListBean.getMainOrders() != null && taoBaoOrderInfoListBean.getMainOrders().size() > 0) {
                    List<TaoBaoOrderInfoListBean.MainOrdersBean> mainOrders = taoBaoOrderInfoListBean.getMainOrders();
                    for (int i = 0; i < mainOrders.size(); i++) {
                        TaoBaoOrderListBean taoBaoOrderListBean = new TaoBaoOrderListBean();
                        taoBaoOrderListBean.setOrderInfo_createTime(mainOrders.get(i).getOrderInfo().getCreateTime());
                        taoBaoOrderListBean.setOrderInfo_id(mainOrders.get(i).getOrderInfo().getId());
                        taoBaoOrderListBean.setPayInfo_actualFee(mainOrders.get(i).getPayInfo().getActualFee());
                        taoBaoOrderListBean.setSeller_shopName(mainOrders.get(i).getSeller().getShopName());
                        taoBaoOrderListBean.setStatusInfo_text(mainOrders.get(i).getStatusInfo().getText());
                        taoBaoOrderListBean.setItemBeanList(mainOrders.get(i).getSubOrders());
                        monthOrderList.add(taoBaoOrderListBean);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取淘宝订单列表异常" + e);
            return false;
        }
    }


    // 获取花呗额度
    private boolean getalipayInfo2(String url, String cookie) {
        try {
            Map<String, String> heades = new HashMap<String, String>();
            heades.put("Host", "my.alipay.com");
            heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
            heades.put("Accept", "*/*");
            heades.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            heades.put("Referer", "https://my.alipay.com/portal/i.htm");
            heades.put("X-Requested-With", "XMLHttpRequest");
            heades.put("Cookie", cookie);
            heades.put("Connection", "keep-alive");
            heades.put("Pragma", "no-cache");
            heades.put("Cache-Control", "no-cache");

            Map<String, String> params = new HashMap<String, String>();

            params.put("className", "huabeiWithDynamicFontClass");
            params.put("encrypt", "true");
            params.put("_input_charset", "utf-8");
            params.put("ctoken", mCtoken);
            params.put("_output_charset", "utf-8");

            String result = HttpUtils.get(url, params, heades, Charset.defaultCharset());

            String html = HttpUtils.replRtnSpace(result);
            List<String[]> huabeiList = HttpUtils.getListArray(html, huabeibalp);
            if (huabeiList != null && huabeiList.size() > 0) { // 与是否开通花呗有关
                String available = huabeiList.get(0)[2] + huabeiList.get(0)[3];
            } else {
                LogUtil.loge("获取花呗额度失败");
                return false;
            }
            return true;

        } catch (Exception e) {
            LogUtil.loge("获取花呗额度异常");
            return false;
        }
    }

    // 获取收货地址
    private boolean getAddress(String url, String cookie) {
        String data = "{}";
        long time = System.currentTimeMillis();
        String sign = sign(getCookiesH5tk(cookie).split("_")[0], "12574478", data, time);
        String address_url = String.format("https://h5api.m.taobao.com/h5/mtop.taobao.mbis.getdeliveraddrlist/1.0/?jsv=2.4.2&appKey=12574478&t=%s&sign=%s&api=mtop.taobao.mbis.getDeliverAddrList&v=1.0&ecode=1&needLogin=true&dataType=jsonp&type=jsonp&callback=mtopjsonp4&data={}", time, sign);
        try {
            String result = HttpUtils.get(address_url, TaobaoHeaderUtil.getAddressHeader(cookie), Charset.forName("UTF-8"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取收货地址失败");
                return false;
            } else {
                String success_data = result.replace("mtopjsonp4(", "").replace(")", "");
                TaoBaoAddressBean taoBaoAddressBean = GsonUtil.changeJsonToBean(success_data, TaoBaoAddressBean.class);
                Gson gson = new Gson();
                List<TaoBaoAdressItemBean> adressList = gson.fromJson(taoBaoAddressBean.getData().getReturnValue().replaceAll("\\\\", ""), new TypeToken<List<TaoBaoAdressItemBean>>() {
                }.getType());
                if (adressList.size() == 0) return false;
                List<BasicInfo.TaoBaoAddress> addressList = new ArrayList<>();
                for (TaoBaoAdressItemBean taoBaoAdressItemBean : adressList) {
                    BasicInfo.TaoBaoAddress taoBaoAddress = new BasicInfo.TaoBaoAddress();
                    taoBaoAddress.setDefault(taoBaoAdressItemBean.isDefaultAddress());
                    taoBaoAddress.setReceiptAddress(taoBaoAdressItemBean.getFullAddress());
                    taoBaoAddress.setReceiptDetailAddress(taoBaoAdressItemBean.getAddressDetail());
                    taoBaoAddress.setReceiptName(taoBaoAdressItemBean.getFullName());
                    taoBaoAddress.setReceiptPhone(taoBaoAdressItemBean.getMobile());
                    taoBaoAddress.setReceiptPostCode(taoBaoAdressItemBean.getPost());
                    addressList.add(taoBaoAddress);
                }
                basicInfo.setAddressList(addressList);
                LogUtil.loge("获取收货地址成功=======" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取收货地址异常");
            return false;
        }
    }

    // 获取支付宝账户
    private boolean getalipayInfo1(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getAlipayHeader(cookie), Charset.forName("GBK"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取支付宝账户失败");
                return false;
            } else {
                String html = HttpUtils.replRtnSpace(result);
                String account = HttpUtils.getValue(html, tbAlipyInfo1);
                basicInfo.setAli_account(account);
                LogUtil.loge("获取支付宝账户成功==" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取支付宝账户异常");
            return false;
        }
    }

    // 获取淘宝id,淘宝账号,极速退款额度
    private boolean getbasicInfo1(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getBaseInfoHeader(cookie), Charset.forName("UTF-8"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取淘宝id,淘宝账号,极速退款额度失败");
                return false;
            } else {
                String html = HttpUtils.replRtnSpace(result);
                aliUrl = "https:" + HttpUtils.getValue(html, alip);
                String header_list = HttpUtils.getValue(html, header_url);
                if (!header_list.isEmpty()) {
                    basicInfo.setHeaderUrl("https:" + header_list.replace("<img src=\"", "").replace("\">", "") + "&type=sns");
                }
                List<String[]> list = HttpUtils.getListArray(html, basicInfo1);
                if (list != null && list.size() > 0) {
                    basicInfo.setUserName(list.get(0)[0]);
                }
                String tbValue = HttpUtils.getValue(html, basicInfo11);
                if (!tbValue.isEmpty()) {
                    basicInfo.setFastRefundAmt(Integer.parseInt(tbValue));
                }
                LogUtil.loge("获取淘宝id,淘宝账号,极速退款额度成功==" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取淘宝id,淘宝账号,极速退款额度获取异常");
            return false;
        }

    }

    // 获取登陆邮箱和电话号码
    private boolean getbasicInfo2(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getBaseInfo1Header(cookie), Charset.forName("GBK"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取登陆邮箱和电话号码失败");
                return false;
            } else {
                String html = HttpUtils.replRtnSpace(result);
                List<String[]> list = HttpUtils.getListArray(html, basicInfo2);
                if (list != null && list.size() > 0) {
                    basicInfo.setLoginEmail(list.get(0)[0]);
                    basicInfo.setPhoneNo(list.get(0)[1]);
                }
                LogUtil.loge("获取登陆邮箱和电话号码成功==" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取登陆邮箱和电话号码异常");
            return false;
        }
    }

    // 获取真实姓名、性别
    private boolean getbasicInfo4(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getBaseInfo3Header(cookie), Charset.forName("GBK"));

            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取真实姓名和身份证失败");
                return false;
            } else {
                String html = HttpUtils.replRtnSpace(result);
                List<String[]> list2 = HttpUtils.getListArray(html, basicInfo6);
                if (list2 != null && list2.size() > 0) {
                    basicInfo.setBirthday(list2.get(0)[0] + "-" + list2.get(0)[1] + "-" + list2.get(0)[2]);
                }
                List<String[]> list1 = HttpUtils.getListArray(html, basicInfo5);
                if (list1 != null && list1.size() > 0) {
                    basicInfo.setSex(list1.get(0)[0]);
                }
                List<String[]> list = HttpUtils.getListArray(html, basicInfo4);
                if (list != null && list.size() > 0) {
                    basicInfo.setRealName(list.get(0)[0]);
                }
                LogUtil.loge("获取到的真实姓名、性别成功=====" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取真实姓名和身份证异常");
            return false;
        }
    }

    // 获取真实姓名和身份证
    private boolean getbasicInfo3(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getBaseInfo2Header(cookie), Charset.forName("GBK"));

            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取真实姓名和身份证失败");
                return false;
            } else {
                String html = HttpUtils.replRtnSpace(result);
                List<String[]> list = HttpUtils.getListArray(html, basicInfo3);
                if (list != null && list.size() > 0) {
                    basicInfo.setRealName(list.get(0)[1]);
                    basicInfo.setIdCard(list.get(0)[2]);
                    basicInfo.setAuthStatus(list.get(0)[0]);
                }
                LogUtil.loge("获取真实姓名和身份证成功=====" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取真实姓名和身份证异常");
            return false;
        }
    }

    // 获取VIP等级 成长值
    private boolean getbasicInfo6(String url, String cookie) {
        String data = "{}";
        long time = System.currentTimeMillis();
        String sign = sign(getCookiesH5tk(cookie).split("_")[0], "12574478", data, time);
        String vip_url = "https://api.m.taobao.com/rest/h5ApiUpdate.do?" + String.format("callback=mtopjsonp1&type=jsonp&api=mtop.taobao.vip.privilege.getVipInfo&v=1.0&data={}&ecode=0&type=get&dataType=jsonp&appKey=12574478&t=%s&sign=%s", time, sign);
        try {
            String result = HttpUtils.get(vip_url, TaobaoHeaderUtil.getBaseInfo5Header(cookie), Charset.forName("UTF-8"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取VIP等级 成长值失败");
                return false;
            } else {
                String success_data = result.replace("mtopjsonp1(", "").replace(")", "");
                TaoBaoVipGrounpValueBean taoBaoVipGrounpValueBean = GsonUtil.changeJsonToBean(success_data, TaoBaoVipGrounpValueBean.class);
                int vipCount = taoBaoVipGrounpValueBean.getData().getBizData().getVipCount();//成长值
                int vipLevel = taoBaoVipGrounpValueBean.getData().getBizData().getVipLevel();//VIP等级
                basicInfo.setVipCount(vipCount);
                basicInfo.setVipLevel(vipLevel);
                LogUtil.loge("获取VIP等级 成长值成功=======" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取VIP等级 成长值异常");
            return false;
        }
    }

    // 获取淘气值
    private boolean getbasicInfo5(String url, String cookie) {
        try {
            String result = HttpUtils.get(url, TaobaoHeaderUtil.getBaseInfo4Header(cookie), Charset.forName("GBK"));
            if (TextUtils.isEmpty(result)) {
                LogUtil.loge("获取淘气值失败");
                return false;
            } else {
                TaoBaoTaoQiValueBean taoBaoTaoQiValueBean = GsonUtil.changeJsonToBean(result, TaoBaoTaoQiValueBean.class);
                int taoScore = taoBaoTaoQiValueBean.getData().getTaoScore();
                basicInfo.setTaoScore(taoScore);
                LogUtil.loge("获取到的淘气值成功=======" + basicInfo.toString());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.loge("获取淘气值异常");
            return false;
        }
    }

    public String getCookiesH5tk(String cookie) {
        String cookieValue = "";
        if (!TextUtils.isEmpty(cookie)) {
            String[] cookieArray = cookie.split(";");// 多个Cookie是使用分号分隔的
            for (int i = 0; i < cookieArray.length; i++) {
                int position = cookieArray[i].indexOf("=");// 在Cookie中键值使用等号分隔
                String cookieName = cookieArray[i].substring(0, position);// 获取键
                if (cookieName.contains("_m_h5_tk")) {
                    cookieValue = cookieArray[i].substring(position + 1);// 获取值
                    break;
                }
            }
        }
        return cookieValue;
    }

    public String sign(String h5tk, String appKey, String data, long time) {
        String s = h5tk + "&" + time + "&" + appKey + "&" + data;
        return MD5Util.encodeMD5(s).toLowerCase();
    }

    private String getCtoken(String cookie) {
        String ctoken = "";
        if (!TextUtils.isEmpty(cookie)) {
            String[] cookieArray = cookie.split(";");// 多个Cookie是使用分号分隔的
            for (int i = 0; i < cookieArray.length; i++) {
                int position = cookieArray[i].indexOf("=");// 在Cookie中键值使用等号分隔
                String cookieName = cookieArray[i].substring(0, position);// 获取键
                if (cookieName.contains("ctoken")) {
                    ctoken = cookieArray[i].substring(position + 1);// 获取值
                    break;
                }
            }
        }
        addProgress(5);// 登录成功+5%
        return ctoken;
    }

    private void addProgress(final int diff) {
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) return;
        runOnUiThread(new Runnable() {
            public void run() {
                number_progress_bar.incrementProgressBy(diff);
            }
        });
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb != null) {
            mAgentWeb.clearWebCache();
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        diffuseView.stop();
        clearWebViewCache();
    }

    public void clearWebViewCache() {
        // 清除cookie即可彻底清除缓存
        CookieSyncManager.createInstance(mContext);
        CookieManager.getInstance().removeAllCookie();
    }

    private AlertDialog mAlertDialog;

    private void showDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("离开将导致认证失败，确定吗?")
                    .setTitle("温馨提示")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                        }
                    })//
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                            finish();
                        }
                    }).create();
        }
        mAlertDialog.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isCert) {
                showDialog();
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
