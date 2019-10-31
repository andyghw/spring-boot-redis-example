package com.hanwen.model;

import java.io.Serializable;
import java.util.*;

public class Plan implements Serializable{
    private PlanCostShares planCostShares;
    private List<LinkedPlanServices> linkedPlanServices;
    private String _org;
    private String objectId;
    private String objectType;
    private String planType;
    private String creationDate;

    public PlanCostShares getPlanCostShares() {
        return this.planCostShares;
    }

    public void setPlanCostShares(PlanCostShares planCostShares) {
        this.planCostShares = planCostShares;
    }

    public List<LinkedPlanServices> getLinkedPlanServices() {
        return this.linkedPlanServices;
    }

    public void setLinkedPlanServices(List<LinkedPlanServices> linkedPlanServices) {
        this.linkedPlanServices = linkedPlanServices;
    }

    public String get_org() {
        return this._org;
    }

    public void set_org(String _org) {
        this._org = _org;
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

    public String getPlanType() {
        return this.planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}