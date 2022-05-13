package com.cmpe252.gicancers.model;

public class TGICancerArea {
    private String treatment_id;
    private String cancer_name;

    public String getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(String treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getCancer_name() {
        return cancer_name;
    }

    public void setCancer_name(String cancer_name) {
        this.cancer_name = cancer_name;
    }

    @Override
    public String toString() {
        return "[treatment_id=" + treatment_id + ", cancer_name=" +
        cancer_name + "]";
    }
}
