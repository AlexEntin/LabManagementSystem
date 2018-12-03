package com.hotelkasani.pervaya.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hotelkasani.pervaya.model.LabEntity;
import com.hotelkasani.pervaya.model.view.LabView;
import com.hotelkasani.pervaya.service.LabService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/lab")
public class LabController {

    private final LabService labService;
    private static final Logger log = LoggerFactory.getLogger(LabController.class);

    public LabController(LabService labService) {
        this.labService = labService;
    }

    @Value("${messages.home:default-value}")
    private String message = "Hello World";

    @CrossOrigin
    @JsonView(LabView.class)
    @RequestMapping("/")
    public ResponseEntity<List<LabEntity>> getAllUsers() {
        return labService.getAllLabs();
    }
}
