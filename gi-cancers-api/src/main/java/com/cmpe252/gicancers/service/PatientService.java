package com.cmpe252.gicancers.service;

import com.cmpe252.gicancers.model.Patient;

public interface PatientService {
    /**
     * For Rasa Chatbot or Doctor using app, can get any patient
     * using patient id
     * 
     * @param patient_id
     * @return
     */
    Patient getPatientById(String patient_id);

    /**
     * For Doctor using app, can get any patient
     * using patient id
     * 
     * @param patient_id
     * @return
     */
    // Patient getPatientById(String doctor_id, String patient_id);

    /**
     * For Rasa Chatbot using app, can get any patient
     * using patient id
     * 
     * @param patient_id
     * @return
     */
    Patient getPatientById(String rasa_id, String patient_id);

    void createPatient(String p_name, String cancer_name, String stage, String treatment_id, String rasa_id);

    void updatePatientUsingPatientID(String patient_id, String p_name, String cancer_name, String stage, String treatment_id);
}
