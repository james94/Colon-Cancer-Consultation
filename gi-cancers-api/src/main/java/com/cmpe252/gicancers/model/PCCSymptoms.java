package com.cmpe252.gicancers.model;

public class PCCSymptoms {
    private String patient_id;
    private String p_name;
    private String p_timestamp;
    private String bowelh_changes;
    private boolean weakness;
    private boolean fatigue;
    private boolean rectal_bleeding;
    private boolean poop_blood;
    private String ab_discomfort;
    private boolean bowelne_feeling;
    private boolean weight_loss_ue;
    private Patient patient;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_timestamp() {
        return p_timestamp;
    }

    public void setP_timestamp(String p_timestamp) {
        this.p_timestamp = p_timestamp;
    }

    public String getBowelh_changes() {
        return bowelh_changes;
    }

    public void setBowelh_changes(String bowelh_changes) {
        this.bowelh_changes = bowelh_changes;
    }

    public boolean getWeakness() {
        return weakness;    
    }

    public void setWeakness(boolean weakness) {
        this.weakness = weakness;    
    }

    public boolean getFatigue() {
        return fatigue;
    }

    public void setFatigue(boolean fatigue) {
        this.fatigue = fatigue;
    }

    public boolean getRectal_bleeding() {
        return rectal_bleeding;
    }

    public void setRectal_bleeding(boolean rectal_bleeding) {
        this.rectal_bleeding = rectal_bleeding;
    }

    public boolean getPoop_blood() {
        return poop_blood;
    }

    public void setPoop_blood(boolean poop_blood) {
        this.poop_blood = poop_blood;
    }

    public String getAb_discomfort() {
        return ab_discomfort;
    }

    public void setAb_discomfort(String ab_discomfort) {
        this.ab_discomfort = ab_discomfort;
    }

    public boolean getBowelne_feeling() {
        return bowelne_feeling;
    }

    public void setBowelne_feeling(boolean bowelne_feeling) {
        this.bowelne_feeling = bowelne_feeling;
    }

    public boolean getWeight_loss_ue() {
        return weight_loss_ue;
    }

    public void setWeight_loss_ue(boolean weight_loss_ue) {
        this.weight_loss_ue = weight_loss_ue;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "PCCSymptoms[patient_id=" +
        patient_id + ", p_name=" +
        p_name + ", p_timestamp=" +
        p_timestamp + ", bowelh_changes=" +
        bowelh_changes + ", weakness=" +
        weakness + ", fatigue=" +
        fatigue + ", rectal_bleeding=" +
        rectal_bleeding + ", poop_blood=" +
        poop_blood + ", ab_discomfort=" +
        ab_discomfort + ", bowelne_feeling=" +
        bowelne_feeling + ", weight_loss_ue=" +
        weight_loss_ue + "]";
    }
}
