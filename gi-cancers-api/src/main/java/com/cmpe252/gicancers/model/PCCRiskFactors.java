package com.cmpe252.gicancers.model;

public class PCCRiskFactors {
    private String patient_id;
    private String p_name;
    private String p_timestamp;
    private int age;
    private String inherited_syndrome;
    private boolean sedentary_lifestyle;
    private boolean lfiber_hfat_diet;
    private boolean african_american;
    private boolean diabetes;
    private boolean obesity;
    private boolean smoking;
    private boolean alcohol;
    private boolean colon_cancer_hist;
    private boolean colon_polyp_hist;
    private boolean radiation_therap;
    private boolean inflam_intest_cond;
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
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getInherited_syndrome() {
        return inherited_syndrome;
    }

    public void setInherited_syndrome(String inherited_syndrome) {
        this.inherited_syndrome = inherited_syndrome;
    }
    public boolean getSedentary_lifestyle() {
        return sedentary_lifestyle;
    }

    public void setsedentary_lifestyle(boolean sedentary_lifestyle) {
        this.sedentary_lifestyle = sedentary_lifestyle;
    }
    public boolean getLfiber_hfat_diet() {
        return lfiber_hfat_diet;
    }

    public void setLfiber_hfat_diet(boolean lfiber_hfat_diet) {
        this.lfiber_hfat_diet = lfiber_hfat_diet;
    }
    public boolean getAfrican_american() {
        return african_american;
    }

    public void setAfrican_american(boolean african_american) {
        this.african_american = african_american;
    }
    public boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }
    public boolean getObesity() {
        return obesity;
    }

    public void setObesity(boolean obesity) {
        this.obesity = obesity;
    }
    public boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
    public boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }
    public boolean getColon_cancer_hist() {
        return colon_cancer_hist;
    }

    public void setColon_cancer_hist(boolean colon_cancer_hist) {
        this.colon_cancer_hist = colon_cancer_hist;
    }
    public boolean getColon_polyp_hist() {
        return colon_polyp_hist;
    }

    public void setColon_polyp_hist(boolean colon_polyp_hist) {
        this.colon_polyp_hist = colon_polyp_hist;
    }
    public boolean getRadiation_therap() {
        return radiation_therap;
    }

    public void setRadiation_therap(boolean radiation_therap) {
        this.radiation_therap = radiation_therap;
    }
    public boolean getInflam_intest_cond() {
        return inflam_intest_cond;
    }

    public void setInflam_intest_cond(boolean inflam_intest_cond) {
        this.inflam_intest_cond = inflam_intest_cond;
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
        p_timestamp + ", age=" +
        age + ", inherited_syndrome=" +
        inherited_syndrome + ", sedentary_lifestyle=" +
        sedentary_lifestyle + ", lfiber_hfat_diet=" +
        lfiber_hfat_diet + ", african_american=" +
        african_american + ", diabetes=" +
        diabetes + ", obesity=" +
        obesity + ", smoking=" +
        smoking + ", alcohol=" +
        alcohol + ", colon_cancer_hist=" +
        colon_cancer_hist + ", colon_polyp_hist=" +
        colon_polyp_hist + ", radiation_therap=" +
        radiation_therap + ", inflam_intest_cond=" +
        inflam_intest_cond + "]";
    }
}
