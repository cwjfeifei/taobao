package com.xbrc.cerlibrary.taobao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;//昵称
    private String phoneNo;//手机号码
    private String realName;//正式姓名
    private String sex;//性别
    private String birthday;//生日
    private String idCard;//身份证
    private String loginEmail;//登录邮箱
    private int fastRefundAmt;//极速退款金额
    private String headerUrl;//头像地址
    private int vipCount;//成长值
    private int vipLevel;//VIP等级
    private int taoScore;//淘气值
    private String authStatus;//是否实名认证
    private List<TaoBaoAddress> addressList = new ArrayList<>();//淘宝地址
    List<TaoBaoOrderListBean> monthOrderList = new ArrayList<TaoBaoOrderListBean>();//订单详情

    private String ali_account;//支付宝账号
    private String ali_yue;//账户余额
    private String ali_yuebao;//余额宝余额
    private String ali_income;//累计收益

    public String getAli_income() {
        return ali_income;
    }

    public void setAli_income(String ali_income) {
        this.ali_income = ali_income;
    }

    public String getAli_yue() {
        return ali_yue;
    }

    public void setAli_yue(String ali_yue) {
        this.ali_yue = ali_yue;
    }

    public String getAli_yuebao() {
        return ali_yuebao;
    }

    public void setAli_yuebao(String ali_yuebao) {
        this.ali_yuebao = ali_yuebao;
    }

    public List<TaoBaoOrderListBean> getMonthOrderList() {
        return monthOrderList;
    }

    public void setMonthOrderList(List<TaoBaoOrderListBean> monthOrderList) {
        this.monthOrderList = monthOrderList;
    }

    public List<TaoBaoAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<TaoBaoAddress> addressList) {
        this.addressList = addressList;
    }

    public static class TaoBaoAddress {
        private String receiptName;
        private String receiptPhone;
        private String receiptAddress;
        private String receiptDetailAddress;
        private String receiptPostCode;
        private boolean isDefault = false;

        public String getReceiptName() {
            return receiptName;
        }

        public void setReceiptName(String receiptName) {
            this.receiptName = receiptName;
        }

        public String getReceiptPhone() {
            return receiptPhone;
        }

        public void setReceiptPhone(String receiptPhone) {
            this.receiptPhone = receiptPhone;
        }

        public String getReceiptAddress() {
            return receiptAddress;
        }

        public void setReceiptAddress(String receiptAddress) {
            this.receiptAddress = receiptAddress;
        }

        public String getReceiptDetailAddress() {
            return receiptDetailAddress;
        }

        public void setReceiptDetailAddress(String receiptDetailAddress) {
            this.receiptDetailAddress = receiptDetailAddress;
        }

        public String getReceiptPostCode() {
            return receiptPostCode;
        }

        public void setReceiptPostCode(String receiptPostCode) {
            this.receiptPostCode = receiptPostCode;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public void setDefault(boolean aDefault) {
            isDefault = aDefault;
        }

        @Override
        public String toString() {
            return "TaoBaoAddress{" +
                    "receiptName='" + receiptName + '\'' +
                    ", receiptPhone='" + receiptPhone + '\'' +
                    ", receiptAddress='" + receiptAddress + '\'' +
                    ", receiptDetailAddress='" + receiptDetailAddress + '\'' +
                    ", receiptPostCode='" + receiptPostCode + '\'' +
                    ", isDefault=" + isDefault +
                    '}';
        }
    }

    public String getAli_account() {
        return ali_account;
    }

    public void setAli_account(String ali_account) {
        this.ali_account = ali_account;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public int getTaoScore() {
        return taoScore;
    }

    public void setTaoScore(int taoScore) {
        this.taoScore = taoScore;
    }

    public int getVipCount() {
        return vipCount;
    }

    public void setVipCount(int vipCount) {
        this.vipCount = vipCount;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }


    public int getFastRefundAmt() {
        return fastRefundAmt;
    }

    public void setFastRefundAmt(int fastRefundAmt) {
        this.fastRefundAmt = fastRefundAmt;
    }

    @Override
    public String toString() {
        return "BasicInfo{" +
                "userName='" + userName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", idCard='" + idCard + '\'' +
                ", loginEmail='" + loginEmail + '\'' +
                ", fastRefundAmt=" + fastRefundAmt +
                ", headerUrl='" + headerUrl + '\'' +
                ", vipCount=" + vipCount +
                ", vipLevel=" + vipLevel +
                ", taoScore=" + taoScore +
                ", authStatus='" + authStatus + '\'' +
                ", addressList=" + addressList +
                ", monthOrderList=" + monthOrderList +
                ", ali_account='" + ali_account + '\'' +
                ", ali_yue='" + ali_yue + '\'' +
                ", ali_yuebao='" + ali_yuebao + '\'' +
                ", ali_income='" + ali_income + '\'' +
                '}';
    }
}
