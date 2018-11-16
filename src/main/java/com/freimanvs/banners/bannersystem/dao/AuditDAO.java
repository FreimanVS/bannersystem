package com.freimanvs.banners.bannersystem.dao;

import com.freimanvs.banners.bannersystem.data.Audit;
import com.freimanvs.banners.bannersystem.data.mappers.AuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String TABLE = "audit";

    private final String SQL_GET_ALL = "SELECT * FROM " + TABLE;
    private final String SQL_ADD = "INSERT INTO " + TABLE + " (banner_id, action, time, username) values(?,?,?,?)";
    private final String FIND_ALL_BY_USERNMANE = "SELECT * FROM " + TABLE + " WHERE username = ?";

    public List<Audit> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new AuditMapper());
    }

    public long add(Audit o) {
        boolean res = jdbcTemplate.update(SQL_ADD,
                o.getBanner_id(), o.getAction(), o.getTime(), o.getUsername()) > 0;

        return res ? 1 : 0;
    }

    public List<Audit> getAllByUsername(String username) {
        return jdbcTemplate.query(FIND_ALL_BY_USERNMANE, new Object[] {username}, new AuditMapper());
    }
}
