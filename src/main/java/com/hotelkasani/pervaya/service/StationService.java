package com.hotelkasani.pervaya.service;

import com.hotelkasani.pervaya.model.StationEntity;
import com.hotelkasani.pervaya.repository.StationRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class StationService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    private final StationRepository stationRepository;
    private final EmailService emailService;

    public StationService(StationRepository stationRepository, EmailService emailService) {
        this.stationRepository = stationRepository;
        this.emailService = emailService;
    }

    public ResponseEntity<List<StationEntity>> getAllStations() {
        List<StationEntity> all = stationRepository.findAll();
        logger.info(String.valueOf(all));
        return ResponseEntity.ok(all);
    }

    public List<StationEntity> getStationByLab(int lab) {
        return Optional.ofNullable(lab)
                .map(labName -> stationRepository.findAllByLabPlacement(lab))
                .orElseGet(() -> {
                    logger.warn("getStationByLab warning");
                    return null;
                });
    }

    /*public StationEntity getStationByName (int lab, String name) {
        return Optional.ofNullable(name).filter(s -> !s.isEmpty())
                .map(stationName -> stationRepository.findStationEntityByStationName(name, lab))
                .orElseGet(() -> {
                    logger.warn("Please check station at the current lab");
                    return null;
                });
    }*/

    public ResponseEntity<StationEntity> getStation(Integer id) {
        Optional<StationEntity> byId = stationRepository.findById(id);
        StationEntity result = byId.orElseGet(() -> {
            logger.error("Station not found with id: " + id);
            return null;
        });
        return ResponseEntity.ok(result);
    }

    public ResponseEntity reserveStation(Integer id, String name) {
        return Optional.ofNullable(getStation(id))
                .map(station -> {
                    station.getBody().setReservedBy(name);
                    stationRepository.save(station.getBody());
                    logger.info(String.format("Station %s is reserved by %s", id, name));
                    emailService.sendSimpleMessage("sanya.entin@gmail.com", "shalom", "Hello, I reserved a station");
                    return new ResponseEntity(HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("Couldn't reserve station with id: " + id);
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                });
    }

    public ResponseEntity releaseStation(Integer id) {
//        ResponseEntity<StationEntity> station = getStation(id);
        return Optional.ofNullable(getStation(id))
                .map(station -> {
                    station.getBody().setReservedBy("");
                    stationRepository.save(station.getBody());
                    logger.info(String.format("Station %s is released", id));
                    return new ResponseEntity(HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("Couldn't reserve station with id: " + id);
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                });
    }
}
