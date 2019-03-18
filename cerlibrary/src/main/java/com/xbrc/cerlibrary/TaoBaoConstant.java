package com.xbrc.cerlibrary;

public class TaoBaoConstant {
    static final String taobao_login_hide =
            "document.getElementById(\"J_Quick2Static\").remove();\n" +
                    "document.getElementsByClassName('poptip')[0].remove();\n" +
                    "document.getElementsByClassName('poptip')[0].remove();\n" +
                    "document.getElementsByClassName('login-links')[0].remove();\n" +
                    "document.getElementsByClassName('login-links')[0].remove();document.querySelectorAll(\".login-title\").forEach(function (x) {\n" +
                    "  return x.remove();\n" +
                    "}); document.querySelector(\".qrcode-desc\").innerHTML = '<span id=\"mx-auth-button2\">手机淘宝一键授权<span>', document.querySelector(\".qrcode-desc\").setAttribute(\"style\", \"background-color: #f6351d;height: 36px;margin:auto;font-size: 16px;color: #fff;line-height: 36px;border-radius: 5px;margin-top: 10px;text-align: center;margin-top:30px;\"), document.querySelector(\".qrcode-desc\").addEventListener(\"click\", function() {window.android.goAuth();" +
                    "                                                                                                                                                                                                                                                                                                                                                                                                                                            });var t = document.createElement(\"div\");\n" +
                    "t.innerHTML = '<div id=\"mx-auth-qrcode-tips\">温馨提示：<div>1.需要手机已安装手机淘宝APP，并已经完成登录。</div><div>2.点击“手机淘宝一键登录”，按指引完成授权认证</div><div>3.如果出现“非法请求”或“非法表单提交”，请返回并重试一键授权</div></div>', t.setAttribute(\"style\", \"font-size:14px;margin-top:20px;\"), document.querySelector(\".qrcode-mod\").appendChild(t)";
    static final String taobao_height = "document.getElementsByClassName(\"quick-form\").style.height=50+\"px\";\n";

}
