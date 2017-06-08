package com.thssh.httpprocessor;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class DNResult {
    private String name;
    private String network;
    private String creater;
    private String cratedate;
    private String shoolname;
    private String part;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCratedate() {
        return cratedate;
    }

    public void setCratedate(String cratedate) {
        this.cratedate = cratedate;
    }

    public String getShoolname() {
        return shoolname;
    }

    public void setShoolname(String shoolname) {
        this.shoolname = shoolname;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "DNResult{" +
                "name='" + name + '\'' +
                ", network='" + network + '\'' +
                ", creater='" + creater + '\'' +
                ", cratedate='" + cratedate + '\'' +
                ", shoolname='" + shoolname + '\'' +
                ", part='" + part + '\'' +
                '}';
    }
}
