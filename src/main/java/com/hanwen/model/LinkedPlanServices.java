package com.hanwen.model;

import java.io.Serializable;

public class LinkedPlanServices implements Serializable{
    private LinkedService linkedService;
    private PlanserviceCostShares planserviceCostShares;
    private String _org;
    private String objectId;
    private String objectType;

    public LinkedService getLinkedService() {
        return this.linkedService;
    }

    public void setLinkedService(LinkedService linkedService) {
        this.linkedService = linkedService;
    }

    public PlanserviceCostShares getPlanserviceCostShares() {
        return this.planserviceCostShares;
    }

    public void setPlanserviceCostShares(PlanserviceCostShares planserviceCostShares) {
        this.planserviceCostShares = planserviceCostShares;
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



    class LinkedService implements Serializable{
        private String _org;
        private String objectId;
        private String objectType;
        private String name;

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

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class PlanserviceCostShares implements Serializable{
        private long deductible;
        private String _org;
        private long copay;
        private String objectId;
        private String objectType;

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
}