package com.cmpe252.gicancers.model;

public class Doctor {
    private String doctor_id;
    private String d_name;

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String toString() {
        return "[doctor_id=" + doctor_id + ", d_name=" +
        d_name + "]";
    }
}
