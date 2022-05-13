package com.cmpe252.gicancers.repository;


// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmpe252.gicancers.model.RGICancerArea;

@Repository
public class RGICancerAreaRepository {

	@Autowired
	private JdbcTemplate jdbc;

    public List<RGICancerArea> findAll() {
        System.out.println("Reached RGICancerAreaRepo");
        return jdbc.query("select * from r_gicancer_area", new BeanPropertyRowMapper<RGICancerArea>(RGICancerArea.class));
    }

    public List<RGICancerArea> findByRasaId(String rasa_id) {
        String sql = "select * from r_gicancer_area where rasa_id = ?";
        List<RGICancerArea> list = jdbc.query(sql, new BeanPropertyRowMapper<RGICancerArea>(RGICancerArea.class), rasa_id);
        return list;
    }

    public void addRGICancer(String rasa_id, String cancer_name) {
        String sql = "insert r_gicancer_area values (?, ?)";
        jdbc.update(sql, rasa_id, cancer_name);
    }
}
