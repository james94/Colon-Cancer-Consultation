package com.cmpe252.gicancers.model;

public class RGICancerArea {
    
    public String rasa_id;
    public String cancer_name;

    public String getRasa_id() {
        return rasa_id;
    }

    public void setRasa_id(String rasa_id) {
        this.rasa_id = rasa_id;
    }

    public String getCancer_name() {
        return cancer_name;
    }

    public void setCancer_name(String cancer_name) {
        this.cancer_name = cancer_name;
    }

    @Override
    public String toString() {
        return "RGICancerArea [rasa_id=" + rasa_id + 
        ", cancer_name=" + cancer_name + "]";
    }
}
