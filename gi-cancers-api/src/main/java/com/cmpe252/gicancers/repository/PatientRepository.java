package com.cmpe252.gicancers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmpe252.gicancers.model.Patient;

@Repository
public class PatientRepository {
    @Autowired
    private JdbcTemplate jdbc;

	public List<Patient> findAll() {
		String sql = "select * from patient";
		List<Patient> patients = jdbc.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class));
		return patients;
	}

    public Patient getPatientById(String patient_id) {
        String sql = "select * from patient where patient_id = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<Patient>(Patient.class), patient_id);
    }

    // When patient initiates conversation with Rasa Chatbot, patient gets created
    public int createPatient(String patient_id, String p_name, String cancer_name, String stage, String treatment_id) {
        String sql = "insert patient(patient_id, p_name, cancer_name, stage, treatment_id values (?, ?, ?, ?, ?)";
        return jdbc.update(sql, patient_id, p_name, cancer_name, stage, treatment_id); 
    }

    public List<Patient> getPatientsByTreatmentId(String treatment_id) {
        String sql = "select * from patient where treatment_id = ?";
        List<Patient> patients = jdbc.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class), treatment_id);
        return patients;
    }

    public List<Patient> getPatientsByDoctorId(String doctor_id) {
        String sql = "select * from patient where doctor_id = ?";
        List<Patient> patients = jdbc.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class), doctor_id);
        return patients;
    }

    public List<Patient> getPatientsByRasaId(String rasa_id) {
        String sql = "select * from patient where rasa_id = ?";
        List<Patient> patients = jdbc.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class), rasa_id);
        return patients;
    }

    /**
     * Editing Patient Details
     * 
     * @param patient_id
     * @param p_name
     * @param cancer_name
     * @param stage
     * @param treatment_id
     */
    public void updatePatientUsingPatientID(String patient_id, String p_name, String cancer_name, String stage, String treatment_id) {
        System.out.println("in the repo, about to prepare the statement");
        String sql = "update patient set patient_id = ?, p_name = ?, cancer_name = ?, stage = ?, treatment_id = ?";
        try {
            jdbc.update(sql, patient_id, p_name, cancer_name, stage, treatment_id);
        }
        catch (DataIntegrityViolationException e) {
            System.out.println("Error data integrity constraint violated when updating record for patient_id = " + patient_id);
        }
    }
}
