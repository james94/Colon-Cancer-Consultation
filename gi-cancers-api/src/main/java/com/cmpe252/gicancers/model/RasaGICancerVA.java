package com.cmpe252.gicancers.model;

import java.util.ArrayList;
import java.util.List;

public class RasaGICancerVA {
    private String rasa_id;
    private String r_name;
    private List<RGICancerArea> RGICancerAreas = new ArrayList<RGICancerArea>();
    private List<Patient> patients = new ArrayList<Patient>();
    private List<Treatment> treatments = new ArrayList<Treatment>();

    public String getRasa_id() {
        return rasa_id;
    }

    public void setRasa_id(String rasa_id) {
        this.rasa_id = rasa_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public List<RGICancerArea> RGICancerArea() {
        return RGICancerAreas;
    }

    public void setRGICancerArea(List<RGICancerArea> rGICancerAreas) {
        RGICancerAreas = rGICancerAreas;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    @Override
    public String toString() {
        return "[rasa_id=" + rasa_id + ", r_name=" + r_name + 
        ", RGICancerAreas=" + RGICancerAreas + ", patients=" + patients + 
        ", treatments=" + treatments + "]";
    }
}
