package com.kspt.model;

public class FormEr {
    private String id;

    private String formId;

    private String metadataId;

    private Long erType;

    private Long pageSize;

    private String gridOrder;

    private Long erLayer;

    private String parentErId;

    private Long orderIndex;

    private String gridTitle;

    private Long gridHeight;

    private String tableName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId == null ? null : metadataId.trim();
    }

    public Long getErType() {
        return erType;
    }

    public void setErType(Long erType) {
        this.erType = erType;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getGridOrder() {
        return gridOrder;
    }

    public void setGridOrder(String gridOrder) {
        this.gridOrder = gridOrder == null ? null : gridOrder.trim();
    }

    public Long getErLayer() {
        return erLayer;
    }

    public void setErLayer(Long erLayer) {
        this.erLayer = erLayer;
    }

    public String getParentErId() {
        return parentErId;
    }

    public void setParentErId(String parentErId) {
        this.parentErId = parentErId == null ? null : parentErId.trim();
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getGridTitle() {
        return gridTitle;
    }

    public void setGridTitle(String gridTitle) {
        this.gridTitle = gridTitle == null ? null : gridTitle.trim();
    }

    public Long getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(Long gridHeight) {
        this.gridHeight = gridHeight;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }
}