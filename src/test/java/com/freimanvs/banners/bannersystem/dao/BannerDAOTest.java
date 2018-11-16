package com.freimanvs.banners.bannersystem.dao;

import com.freimanvs.banners.bannersystem.data.Banner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerDAOTest {

    @Autowired
    BannerDAO bannerDAO;

    private Banner banner;
    private Long id;

    @Before
    public void before() {
        banner = new Banner();
        banner.setHeight(100);
        banner.setWidth(100);
        banner.setImgSrc("testSrc.jpg");
        banner.setTargetUrl("/testUrl");
        banner.setLangId(1);

        id = bannerDAO.add(banner);
        banner.setId(id);
    }

    @Test
    @Transactional
    @Rollback()
    public void getById() {
        Banner actual = bannerDAO.getById(id);
        assertEquals(banner, actual);
    }

    @Test
    @Transactional
    @Rollback()
    public void getByIdNull() {
        Banner banner = bannerDAO.getById(99999999L);
        assertNull(banner);
    }

    @Test
    @Transactional
    @Rollback()
    public void getAll() {
        List<Banner> list = bannerDAO.getAll();
        assertNotNull(list);
    }

    @Test
    @Transactional
    @Rollback()
    public void remove() {
        bannerDAO.remove(id);

        Banner actual = bannerDAO.getById(id);
        assertNull(actual);
    }

    @Test
    @Transactional
    @Rollback()
    public void update() {
        banner.setHeight(200);
        banner.setWidth(200);
        banner.setImgSrc("testSrc2.jpg");
        banner.setTargetUrl("/testUrl2");
        banner.setLangId(2);

        bannerDAO.update(id, banner);

        Banner actual = bannerDAO.getById(id);
        assertEquals(banner, actual);
    }

    @Test
    @Transactional
    @Rollback()
    public void add() {
        Banner actual = bannerDAO.getById(id);

        assertEquals(banner, actual);
    }

}