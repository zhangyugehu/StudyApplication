package com.thssh.appskin.factory;

/**
 * 换肤时需要更改的内容
 *
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class SkinItem {

    /*
    * android:background="@drawable/ic_launcher"
    * android:[attrName] = "@[attrType/attrValue](rId)"
    */

    private String attrName;
    private int rId;
    private String attrValue;
    private String attrType;

    public SkinItem(String attrName, int rId, String attrType, String attrValue) {
        this.attrName = attrName;
        this.rId = rId;
        this.attrType = attrType;
        this.attrValue = attrValue;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrName() {
        return attrName;
    }

    public int getrId() {
        return rId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public String getAttrType() {
        return attrType;
    }

    @Override
    public String toString() {
        return "SkinItem{" +
                "attrName='" + attrName + '\'' +
                ", rId=" + rId +
                ", attrValue='" + attrValue + '\'' +
                ", attrType='" + attrType + '\'' +
                '}';
    }
}
