package com.cmpe252.gicancers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmpe252.gicancers.model.RasaGICancerVA;

@Repository
public class RasaGICancerVARepository {
    
    @Autowired
    private JdbcTemplate jdbc;

	public List<RasaGICancerVA> findAll() {
		String sql = "select * from rasa_gicancer_va";
		List<RasaGICancerVA> rasa_vas = jdbc.query(sql, new BeanPropertyRowMapper<RasaGICancerVA>(RasaGICancerVA.class));
		return rasa_vas;
	}

    public RasaGICancerVA getRasaById(String rasa_id) {
        String sql = "select * from rasa_gicancer_va where rasa_id = ?";
        try {
            return jdbc.queryForObject(sql, new BeanPropertyRowMapper<RasaGICancerVA>(RasaGICancerVA.class), rasa_id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // public List<RasaGICancerVA> getRasaVAByPatientId(String patient_id) {
    //     String sql = "select * from rasa_gicancer_va R where R.rasa_id in " +
    //         "(select ";
    // }

    public void createRasaGICancerVA(String rasa_id, String r_name) {
        String sql = "insert rasa_gicancer_va values (?, ?)";
        jdbc.update(sql, rasa_id, r_name);
    }
}
