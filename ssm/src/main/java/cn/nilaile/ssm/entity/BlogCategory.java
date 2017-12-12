package cn.nilaile.ssm.entity;

public class BlogCategory {
    private Integer cid;

    private String cname;

    private Integer csort;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getCsort() {
        return csort;
    }

    public void setCsort(Integer csort) {
        this.csort = csort;
    }
}