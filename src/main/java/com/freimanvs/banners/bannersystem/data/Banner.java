package com.freimanvs.banners.bannersystem.data;

import java.io.Serializable;
import java.util.Objects;

public class Banner implements Serializable {
    private Long id;
    private String imgSrc;
    private Integer width;
    private Integer height;
    private String targetUrl;
    private Integer langId;

    public Banner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id) &&
                Objects.equals(imgSrc, banner.imgSrc) &&
                Objects.equals(width, banner.width) &&
                Objects.equals(height, banner.height) &&
                Objects.equals(targetUrl, banner.targetUrl) &&
                Objects.equals(langId, banner.langId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgSrc, width, height, targetUrl, langId);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"imgSrc\":\"" + imgSrc + "\"" +
                ",\"width\":" + width +
                ",\"height\":" + height +
                ",\"targetUrl\":\"" + targetUrl +  "\"" +
                ",\"langId\":" + langId +
                '}';
    }
}
