package com.kspt.model;

public class OrgMembership {
    private String id;

    private String userAccount;

    private String deptId;

    private String roleId;

    private Short deptManager;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public Short getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(Short deptManager) {
        this.deptManager = deptManager;
    }
}