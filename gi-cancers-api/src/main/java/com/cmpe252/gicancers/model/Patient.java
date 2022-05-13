package com.cmpe252.gicancers.model;

public class Patient {
    private String patient_id;
    private String p_name;
    private String cancer_name;
    private String stage;
    private String treatment_id;
    private String p_username;
    private String p_password;
    private String doctor_id;
    private String rasa_id;
    private Treatment treatment;
    private Doctor doctor;
    private RasaGICancerVA rasaGiCancerVa;

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

	public String getCancer_name() {
		return cancer_name;
	}

	public void setCancer_name(String cancer_name) {
		this.cancer_name = cancer_name;
	}

    public String getStage() {
        return stage;
    }   

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTreatment_id() {
        return treatment_id;
    }   

    public void setTreatment_id(String treatment_id) {
        this.treatment_id = treatment_id;
    }
    public String getP_username() {
        return p_username;
    }   

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }
    public String getP_password() {
        return p_password;
    }   

    public void setP_password(String p_password) {
        this.p_password = p_password;
    }
    public String getDoctor_id() {
        return doctor_id;
    }   

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getRasa_id() {
        return rasa_id;
    }

    public void setRasa_id(String rasa_id) {
        this.rasa_id = rasa_id;
    }

    public Treatment getTreatment() {
        return treatment;
    }   

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    public Doctor getDoctor() {
        return doctor;
    }   

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public RasaGICancerVA getRasaGiCancerVa() {
        return rasaGiCancerVa;
    }

    public void setRasaGiCancerVA(RasaGICancerVA rasaGiCancerVa) {
        this.rasaGiCancerVa = rasaGiCancerVa;
    }

    @Override
    public String toString() {
        return "PCCSymptoms[patient_id=" +
        patient_id + ", p_name=" +
        p_name + ", cancer_name=" +
        cancer_name + ", stage=" +
        stage + ", treatment_id=" +
        treatment_id + ", p_username=" +
        p_username + ", p_password=" +
        p_password + ", doctor_id=" +
        doctor_id + "]";
    }
}
