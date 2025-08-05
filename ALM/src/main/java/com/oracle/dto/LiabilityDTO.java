package com.oracle.dto;

import com.oracle.enums.FlowType;
import com.oracle.enums.FlowStatus;
import com.oracle.enums.SourceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LiabilityDTO {

    @NotNull
    private String liabilityId;

    @Positive
    private double value;

    @NotNull
    private FlowType flowType;

    @NotNull
    private FlowStatus flowStatus;

    @NotNull
    private SourceType sourceType;
    
    // Getters and Setters

	public String getLiabilityId() {
		return liabilityId;
	}

	public void setLiabilityId(String liabilityId) {
		this.liabilityId = liabilityId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public FlowType getFlowType() {
		return flowType;
	}

	public void setFlowType(FlowType flowType) {
		this.flowType = flowType;
	}

	public FlowStatus getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(FlowStatus flowStatus) {
		this.flowStatus = flowStatus;
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

   
    
}