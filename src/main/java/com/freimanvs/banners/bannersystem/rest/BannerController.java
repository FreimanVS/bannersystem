package com.freimanvs.banners.bannersystem.rest;

import com.freimanvs.banners.bannersystem.dao.BannerDAO;
import com.freimanvs.banners.bannersystem.data.Banner;
import com.freimanvs.banners.bannersystem.rest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/banners")
public class BannerController {

    @Autowired
    BannerDAO bannerDAO;

    @CrossOrigin
    @GetMapping(produces="application/json")
    public ResponseEntity<?> getAll() {
        List<Banner> list = bannerDAO.getAll();
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<?> getById(@PathVariable("id")Long id) {
        Banner obj = bannerDAO.getById(id);

        if (obj == null)
            throw new NotFoundException("there is no such banner to get by id");

        return ResponseEntity.ok(obj);
    }

    @CrossOrigin
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Banner o,
                                 HttpServletRequest request) {
        long id = bannerDAO.add(o);
        StringBuffer url = request.getRequestURL();
        url.append("/").append(id);
        return ResponseEntity.created(URI.create(url.toString())).build();
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> update(@PathVariable("id")Long id,
                                    @RequestBody Banner o,
                                    HttpServletRequest request) {
        boolean res = bannerDAO.update(id, o);

        if (!res)
            throw new NotFoundException("there is no such banner to update");

        StringBuffer url = request.getRequestURL();
        return ResponseEntity.created(URI.create(url.toString())).build();
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id")Long id) {
        boolean res = bannerDAO.remove(id);

        if (!res)
            throw new NotFoundException("there is no such banner to remove");

        return ResponseEntity.noContent().build();
    }
}
