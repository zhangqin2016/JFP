package com.kspt.model;

public class FormUiWithBLOBs extends FormUi {
    private String defaultValue;

    private String uiParam;

    private String uiHtml;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getUiParam() {
        return uiParam;
    }

    public void setUiParam(String uiParam) {
        this.uiParam = uiParam == null ? null : uiParam.trim();
    }

    public String getUiHtml() {
        return uiHtml;
    }

    public void setUiHtml(String uiHtml) {
        this.uiHtml = uiHtml == null ? null : uiHtml.trim();
    }
}