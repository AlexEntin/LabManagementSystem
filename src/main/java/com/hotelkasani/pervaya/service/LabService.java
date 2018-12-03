package com.hotelkasani.pervaya.service;

import com.hotelkasani.pervaya.model.LabEntity;
import com.hotelkasani.pervaya.model.UserEntity;
import com.hotelkasani.pervaya.repository.LabRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class LabService {

    private final Logger logger = LoggerFactory.getLogger(LabService.class);
    private  final LabRepository labRepository;

    public LabService(LabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public ResponseEntity<List<LabEntity>> getAllLabs() {
        List<LabEntity> all = labRepository.findAll();
        logger.info(String.valueOf(all));
        return ResponseEntity.ok(all);
    }
}
