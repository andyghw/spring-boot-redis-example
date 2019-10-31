package com.hanwen.model;

import java.io.Serializable;

public class PlanCostShares implements Serializable{
    private long deductible;
    private String _org;
    private long copay;
    private String objectId;
    private String objectType;

    public PlanCostShares(int deductible, String _org, int copay, String objectId, String objectType) {
        this.deductible = deductible;
        this._org = _org;
        this.copay = copay;
        this.objectId = objectId;
        this.objectType = objectType;
    }

	public long getDeductible() {
		return this.deductible;
	}

	public void setDeductible(long deductible) {
		this.deductible = deductible;
	}

	public String get_org() {
		return this._org;
	}

	public void set_org(String _org) {
		this._org = _org;
	}

	public long getCopay() {
		return this.copay;
	}

	public void setCopay(long copay) {
		this.copay = copay;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}