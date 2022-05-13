package com.cmpe252.gicancers.repository;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// import com.cmpe252.gicancers.model.TGICancerArea;

@Repository
public class TGICancerAreaRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void addTGICancerName(String treatment_id, String cancer_name) {
        String sql = "insert t_gicancer_area values (?, ?)";
        jdbc.update(sql, treatment_id, cancer_name);
    }
}
