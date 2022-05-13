package com.cmpe252.gicancers.model;

public class Treatment {
    private String treatment_id;
    private String title;
    private String invasive_lvl;
    private String stage;

    public String getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(String treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInvasive_lvl() {
        return invasive_lvl;
    }

    public void setInvasive_lvl(String invasive_lvl) {
        this.invasive_lvl = invasive_lvl;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "[treatment_id=" + treatment_id + ", title=" +
        title + ", invasive_lvl=" +
        invasive_lvl + ", stage=" +
        stage + "]";
    }
}
