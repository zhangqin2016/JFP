package com.kspt.model;

public class FormUi {
    private String id;

    private String formErId;

    private String metadataMapId;

    private String uiTitle;

    private Long isNull;

    private String isDisplay;

    private String uiType;

    private String uiLength;

    private Long orderIndex;

    private String uiName;

    private Long isEdit;

    private String uiLengthType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFormErId() {
        return formErId;
    }

    public void setFormErId(String formErId) {
        this.formErId = formErId == null ? null : formErId.trim();
    }

    public String getMetadataMapId() {
        return metadataMapId;
    }

    public void setMetadataMapId(String metadataMapId) {
        this.metadataMapId = metadataMapId == null ? null : metadataMapId.trim();
    }

    public String getUiTitle() {
        return uiTitle;
    }

    public void setUiTitle(String uiTitle) {
        this.uiTitle = uiTitle == null ? null : uiTitle.trim();
    }

    public Long getIsNull() {
        return isNull;
    }

    public void setIsNull(Long isNull) {
        this.isNull = isNull;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay == null ? null : isDisplay.trim();
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType == null ? null : uiType.trim();
    }

    public String getUiLength() {
        return uiLength;
    }

    public void setUiLength(String uiLength) {
        this.uiLength = uiLength == null ? null : uiLength.trim();
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName == null ? null : uiName.trim();
    }

    public Long getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Long isEdit) {
        this.isEdit = isEdit;
    }

    public String getUiLengthType() {
        return uiLengthType;
    }

    public void setUiLengthType(String uiLengthType) {
        this.uiLengthType = uiLengthType == null ? null : uiLengthType.trim();
    }
}