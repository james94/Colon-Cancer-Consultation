package com.cmpe252.gicancers.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

import com.cmpe252.gicancers.model.Treatment;

@Repository
public class TreatmentRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Treatment> findByRasaId(String rasa_id) {
        String sql = "select * from treatment T where T.treatment_id in " +
            "(select R.treatment_id from researches R where R.rasa_id = " + rasa_id + ")";
        return jdbc.query(sql, this::mapRowToTreatment);
    }

    public List<Treatment> findByDoctorId(String doctor_id) {
        String sql = "select * from treatment T where T.treatment_id in " +
            "(select E.treatment_id from examines E where E.doctor_id = " + doctor_id + ")";
        return jdbc.query(sql, this::mapRowToTreatment);
    }
    public List<Treatment> findByPatientId(String patient_id) {
        String sql = "select * from treatment T where T.treatment_id in " +
            "(select P.treatment_id from patient P where P.treatment_id = " + patient_id + ")";
        return jdbc.query(sql, this::mapRowToTreatment);
    }
    public List<Treatment> findByCancerName(String cancer_name) {
        String sql = "select * from treatment T where T.treatment_id in (" +
            "select C.treatment_id from t_gicancer_area C where C.cancer_name = '" + cancer_name + "')";
        List<Treatment> res = jdbc.query(sql, this::mapRowToTreatment);
        return res;
    }

    public Treatment findByTreatmentId(String treatment_id) {
        String sql = "select * from treatment where treatment_id = " + treatment_id;
        List<Treatment> res  = jdbc.query(sql, this::mapRowToTreatment);
        
        if(res.size() == 0) {
            return null;
        }

        return res.get(0);
    }

    public void addTreatment(String rasa_id, String treatment_id, String title, String invasive_lvl, String stage) {
        String sql = "INSERT treatment VALUES (?, ?, ?, ?)";
        jdbc.update(sql, treatment_id, title, invasive_lvl, stage);

        String researches_sql = "INSERT researches VALUES (?, ?)";
        jdbc.update(researches_sql, rasa_id, treatment_id);
    }

    private Treatment mapRowToTreatment(ResultSet rs, int rowNum) throws SQLException {
        Treatment t = new Treatment();
        t.setTreatment_id(rs.getString("treatment_id"));
        t.setTitle(rs.getString("title"));
        t.setInvasive_lvl(rs.getString("invasive_lvl"));
        t.setStage(rs.getString("stage"));
        return t;
    }
}
