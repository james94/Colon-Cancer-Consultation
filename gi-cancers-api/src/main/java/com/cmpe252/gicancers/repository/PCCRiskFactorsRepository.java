package com.cmpe252.gicancers.repository;

// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmpe252.gicancers.model.PCCRiskFactors;

@Repository
public class PCCRiskFactorsRepository {
    @Autowired
    private JdbcTemplate jdbc;

    public List<PCCRiskFactors> findAll(String patient_id) {
        String sql = "SELECT * FROM pcc_riskfactors WHERE patient_id = " + patient_id;
        return jdbc.query(sql, new BeanPropertyRowMapper<PCCRiskFactors>(PCCRiskFactors.class));
    }

    public PCCRiskFactors getRiskFactorsByPatientId(String patient_id) {
        String sql = "select * from pcc_riskfactors where patient_id = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<PCCRiskFactors>(PCCRiskFactors.class), patient_id);
    }

    public List<PCCRiskFactors> getRiskFactorsByTreatmentId(String treatment_id) {
        String sql = "select * from pcc_riskfactors where treatment_id = ?";
        List<PCCRiskFactors> riskFactors = jdbc.query(sql, new BeanPropertyRowMapper<PCCRiskFactors>(PCCRiskFactors.class), treatment_id);
        return riskFactors;
    }

    public void createRiskFactors(String patient_id, String p_name, String p_timestamp, int age, 
        String inherited_syndrome, boolean sedentary_lifestyle, boolean lfiber_hfat_diet, boolean african_american, 
        boolean diabetes, boolean obesity, boolean smoking, boolean alcohol, boolean colon_cancer_hist, 
        boolean colon_polyp_hist, boolean radiation_therap, boolean inflam_intest_cond) {
            String sql = "INSERT pcc_riskfactors VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbc.update(sql, patient_id, p_name, p_timestamp, age, inherited_syndrome, 
                sedentary_lifestyle, lfiber_hfat_diet, african_american, diabetes, obesity, smoking, alcohol, 
                colon_cancer_hist, colon_polyp_hist, radiation_therap, inflam_intest_cond);
    }
}
