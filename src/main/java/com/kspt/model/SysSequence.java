package com.kspt.model;

public class SysSequence {
    private String sequenceName;

    private Long sequenceValue;

    private Short sequenceStep;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public Long getSequenceValue() {
        return sequenceValue;
    }

    public void setSequenceValue(Long sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    public Short getSequenceStep() {
        return sequenceStep;
    }

    public void setSequenceStep(Short sequenceStep) {
        this.sequenceStep = sequenceStep;
    }
}