package com.freimanvs.banners.bannersystem.controllers;

import com.freimanvs.banners.bannersystem.dao.AuditDAO;
import com.freimanvs.banners.bannersystem.data.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    AuditDAO auditDAO;

    @CrossOrigin
    @GetMapping(produces="application/json")
    public ResponseEntity<?> getAll() {
        List<Audit> list = auditDAO.getAll();
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping(value = {"/{username}"}, produces="application/json")
    public ResponseEntity<?> getAllByUsername(@PathVariable("username") String username) {
        List<Audit> list = auditDAO.getAllByUsername(username);
        return ResponseEntity.ok(list);
    }
}
