package cn.nilaile.ssm.entity;

import java.util.Date;

public class BlogLogReq {
    private Integer id;

    private Date reqtime;

    private String reqip;

    private String reqhead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReqtime() {
        return reqtime;
    }

    public void setReqtime(Date reqtime) {
        this.reqtime = reqtime;
    }

    public String getReqip() {
        return reqip;
    }

    public void setReqip(String reqip) {
        this.reqip = reqip == null ? null : reqip.trim();
    }

    public String getReqhead() {
        return reqhead;
    }

    public void setReqhead(String reqhead) {
        this.reqhead = reqhead == null ? null : reqhead.trim();
    }
}