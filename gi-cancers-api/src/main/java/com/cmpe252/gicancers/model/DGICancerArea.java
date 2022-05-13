package com.cmpe252.gicancers.model;

public class DGICancerArea {
    private String doctor_id;
    private String cancer_name;

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getCancer_name() {
        return cancer_name;
    }

    public void setCancer_name(String cancer_name) {
        this.cancer_name = cancer_name;
    }

    @Override
    public String toString() {
        return "[doctor_id=" + doctor_id + ", cancer_name=" +
        cancer_name + "]";
    }
}
