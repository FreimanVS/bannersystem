package com.freimanvs.banners.bannersystem.data.mappers;

import com.freimanvs.banners.bannersystem.data.Banner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BannerMapper implements RowMapper<Banner> {

    public Banner mapRow(ResultSet resultSet, int i) throws SQLException {
        Banner banner = new Banner();
        banner.setId(resultSet.getLong("id"));
        banner.setImgSrc(resultSet.getString("imgSrc"));
        banner.setWidth(resultSet.getInt("width"));
        banner.setHeight(resultSet.getInt("height"));
        banner.setTargetUrl(resultSet.getString("targetUrl"));
        banner.setLangId(resultSet.getInt("langId"));
        return banner;
    }
}