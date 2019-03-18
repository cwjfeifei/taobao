package com.xbrc.cerlibrary.taobao.bean;

public class TaoBaoTaoQiValueBean {

    /**
     * status : true
     * code : 1
     * data : {"isLogin":true,"taoScore":487}
     */

    private boolean status;
    private int code;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isLogin : true
         * taoScore : 487
         */

        private boolean isLogin;
        private int taoScore;

        public boolean isIsLogin() {
            return isLogin;
        }

        public void setIsLogin(boolean isLogin) {
            this.isLogin = isLogin;
        }

        public int getTaoScore() {
            return taoScore;
        }

        public void setTaoScore(int taoScore) {
            this.taoScore = taoScore;
        }
    }
}
