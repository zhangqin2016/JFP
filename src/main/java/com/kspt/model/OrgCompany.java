package com.kspt.model;

public class OrgCompany {
    private String id;

    private String coCode;

    private String coName;

    private Long orderIndex;

    private String coMemo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCoCode() {
        return coCode;
    }

    public void setCoCode(String coCode) {
        this.coCode = coCode == null ? null : coCode.trim();
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName == null ? null : coName.trim();
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getCoMemo() {
        return coMemo;
    }

    public void setCoMemo(String coMemo) {
        this.coMemo = coMemo == null ? null : coMemo.trim();
    }
}