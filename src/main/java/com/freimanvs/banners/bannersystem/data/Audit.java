package com.freimanvs.banners.bannersystem.data;

import java.sql.Timestamp;
import java.util.Objects;

public class Audit {
    private Long banner_id;
    private String action;
    private Timestamp time;
    private String username;

    public Audit() {
    }

    public Long getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(Long banner_id) {
        this.banner_id = banner_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return Objects.equals(banner_id, audit.banner_id) &&
                Objects.equals(action, audit.action) &&
                Objects.equals(time, audit.time) &&
                Objects.equals(username, audit.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banner_id, action, time, username);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "banner_id=" + banner_id +
                ", action='" + action + '\'' +
                ", time=" + time +
                ", username='" + username + '\'' +
                '}';
    }
}
