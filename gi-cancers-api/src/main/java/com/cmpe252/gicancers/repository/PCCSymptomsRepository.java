package com.cmpe252.gicancers.repository;

// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmpe252.gicancers.model.PCCSymptoms;

@Repository
public class PCCSymptomsRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public List<PCCSymptoms> findAll(String patient_id) {
        String sql = "SELECT * FROM pcc_symptoms WHERE patient_id = " + patient_id;
        return jdbc.query(sql, new BeanPropertyRowMapper<PCCSymptoms>(PCCSymptoms.class));
    }

    public PCCSymptoms getSymptomsByPatientId(String patient_id) {
        String sql = "select * from pcc_symptoms where patient_id = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<PCCSymptoms>(PCCSymptoms.class), patient_id);
    }

    public List<PCCSymptoms> getSymptomsByTreatmentId(String treatment_id) {
        String sql = "select * from pcc_symptoms where treatment_id = ?";
        List<PCCSymptoms> symptoms = jdbc.query(sql, new BeanPropertyRowMapper<PCCSymptoms>(PCCSymptoms.class), treatment_id);
        return symptoms;
    }

    public void createSymptoms(String patient_id, String p_name, String p_timestamp, 
    String bowelh_changes, boolean weakness, boolean fatigue, boolean rectal_bleeding, 
    boolean poop_blood, String ab_discomfort, boolean bowelne_feeling, 
    boolean weight_loss_ue) {
        String sql = "INSERT pcc_symptoms VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, patient_id, p_name, p_timestamp, bowelh_changes, weakness, fatigue, 
            rectal_bleeding, poop_blood, ab_discomfort, bowelne_feeling, weight_loss_ue);
    }
}
