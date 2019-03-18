package com.xbrc.cerlibrary.taobao.bean;

import java.util.List;

public class TaoBaoVipGrounpValueBean {

    /**
     * ret : ["SUCCESS"]
     * data : {"msg":"success","server":"vipmanager011011085007.center.na62","code":1,"stats":true,"bizData":{"vipLevel":0,"upgrade":{"isNeed":true,"targetLevel":2},"userNick":"tb11890871","needCount":-5125,"isInWhite":true,"userAvatar":"//wwc.alicdn.com/avatar/getAvatar.do?userId=1746398584&width=160&height=160&type=sns","activation":{"isNeed":false,"targetLevel":2},"firstVisit":true,"vipCount":6125}}
     * v : 1.0
     * api : mtop.taobao.vip.privilege.getVipInfo
     */

    private DataBean data;
    private String v;
    private String api;
    private List<String> ret;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<String> getRet() {
        return ret;
    }

    public void setRet(List<String> ret) {
        this.ret = ret;
    }

    public static class DataBean {
        /**
         * msg : success
         * server : vipmanager011011085007.center.na62
         * code : 1
         * stats : true
         * bizData : {"vipLevel":0,"upgrade":{"isNeed":true,"targetLevel":2},"userNick":"tb11890871","needCount":-5125,"isInWhite":true,"userAvatar":"//wwc.alicdn.com/avatar/getAvatar.do?userId=1746398584&width=160&height=160&type=sns","activation":{"isNeed":false,"targetLevel":2},"firstVisit":true,"vipCount":6125}
         */

        private String msg;
        private String server;
        private int code;
        private boolean stats;
        private BizDataBean bizData;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isStats() {
            return stats;
        }

        public void setStats(boolean stats) {
            this.stats = stats;
        }

        public BizDataBean getBizData() {
            return bizData;
        }

        public void setBizData(BizDataBean bizData) {
            this.bizData = bizData;
        }

        public static class BizDataBean {
            /**
             * vipLevel : 0
             * upgrade : {"isNeed":true,"targetLevel":2}
             * userNick : tb11890871
             * needCount : -5125
             * isInWhite : true
             * userAvatar : //wwc.alicdn.com/avatar/getAvatar.do?userId=1746398584&width=160&height=160&type=sns
             * activation : {"isNeed":false,"targetLevel":2}
             * firstVisit : true
             * vipCount : 6125
             */

            private int vipLevel;
            private UpgradeBean upgrade;
            private String userNick;
            private int needCount;
            private boolean isInWhite;
            private String userAvatar;
            private ActivationBean activation;
            private boolean firstVisit;
            private int vipCount;

            public int getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public UpgradeBean getUpgrade() {
                return upgrade;
            }

            public void setUpgrade(UpgradeBean upgrade) {
                this.upgrade = upgrade;
            }

            public String getUserNick() {
                return userNick;
            }

            public void setUserNick(String userNick) {
                this.userNick = userNick;
            }

            public int getNeedCount() {
                return needCount;
            }

            public void setNeedCount(int needCount) {
                this.needCount = needCount;
            }

            public boolean isIsInWhite() {
                return isInWhite;
            }

            public void setIsInWhite(boolean isInWhite) {
                this.isInWhite = isInWhite;
            }

            public String getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(String userAvatar) {
                this.userAvatar = userAvatar;
            }

            public ActivationBean getActivation() {
                return activation;
            }

            public void setActivation(ActivationBean activation) {
                this.activation = activation;
            }

            public boolean isFirstVisit() {
                return firstVisit;
            }

            public void setFirstVisit(boolean firstVisit) {
                this.firstVisit = firstVisit;
            }

            public int getVipCount() {
                return vipCount;
            }

            public void setVipCount(int vipCount) {
                this.vipCount = vipCount;
            }

            public static class UpgradeBean {
                /**
                 * isNeed : true
                 * targetLevel : 2
                 */

                private boolean isNeed;
                private int targetLevel;

                public boolean isIsNeed() {
                    return isNeed;
                }

                public void setIsNeed(boolean isNeed) {
                    this.isNeed = isNeed;
                }

                public int getTargetLevel() {
                    return targetLevel;
                }

                public void setTargetLevel(int targetLevel) {
                    this.targetLevel = targetLevel;
                }
            }

            public static class ActivationBean {
                /**
                 * isNeed : false
                 * targetLevel : 2
                 */

                private boolean isNeed;
                private int targetLevel;

                public boolean isIsNeed() {
                    return isNeed;
                }

                public void setIsNeed(boolean isNeed) {
                    this.isNeed = isNeed;
                }

                public int getTargetLevel() {
                    return targetLevel;
                }

                public void setTargetLevel(int targetLevel) {
                    this.targetLevel = targetLevel;
                }
            }
        }
    }
}
