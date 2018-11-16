package com.freimanvs.banners.bannersystem.rest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.freimanvs.banners.bannersystem.dao.BannerDAO;
import com.freimanvs.banners.bannersystem.data.Banner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BannerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BannerDAO bannerDAO;

    private Banner banner;
    private Long id;
    private String json;

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
        json = banner.toString();
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(username="user")
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/banners").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(startsWith("[")));
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(username="user")
    public void geById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/banners/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(json));
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(username="user")
    public void geByIdNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/banners/9999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(username="user")
    public void updateNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/banners/9999999").with(csrf()).contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\"id\": 0,\"imgSrc2\":\"imgSrc2\",\"width\":200,\"height\":200,\"targetUrl2\":\"targetUrl2\",\"langId\":2}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(username="user")
    public void deleteNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/banners/9999999").with(csrf()))
                .andExpect(status().isNotFound());
    }
}