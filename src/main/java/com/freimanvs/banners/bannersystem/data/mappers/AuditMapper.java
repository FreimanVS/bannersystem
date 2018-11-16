package com.freimanvs.banners.bannersystem.data.mappers;

import com.freimanvs.banners.bannersystem.data.Audit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditMapper implements RowMapper<Audit> {

    public Audit mapRow(ResultSet resultSet, int i) throws SQLException {
        Audit audit = new Audit();
        audit.setBanner_id(resultSet.getLong("banner_id"));
        audit.setAction(resultSet.getString("action"));
        audit.setTime(resultSet.getTimestamp("time"));
        audit.setUsername(resultSet.getString("username"));
        return audit;
    }
}