package com.hotelkasani.pervaya.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hotelkasani.pervaya.model.StationEntity;
import com.hotelkasani.pervaya.model.view.StationView;
import com.hotelkasani.pervaya.service.StationService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/api/station")
public class StationController {
    private final StationService stationService;
    private final Logger logger = LoggerFactory.getLogger(StationController.class);

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @CrossOrigin
    @JsonView(StationView.class)
    @GetMapping("/")
    public ResponseEntity<List<StationEntity>> getAllStations() { return stationService.getAllStations(); }

    @CrossOrigin
    @JsonView(StationView.class)
    @GetMapping("getStationByLabId")
    public ResponseEntity<List<StationEntity>> getStationByLabId (@RequestParam Integer labId)
    {
        return ResponseEntity.ok(stationService.getStationByLab(labId));
}

    @GetMapping("reserve")
    public ResponseEntity reserveSpecificStation (@RequestParam Integer id, @RequestParam String name) {
        return stationService.reserveStation(id, name);
    }

    @JsonView(StationView.class)
    @PostMapping("getStation")
    public ResponseEntity<StationEntity> getSpecificStation (@RequestParam Integer id) {
        ResponseEntity<StationEntity> stationById = stationService.getStation(id);
        return stationById;
    }

    @JsonView(StationView.class)
    @GetMapping("releaseStation")
    public ResponseEntity<StationEntity> releaseSpecificStation (@RequestParam Integer id) {
        return stationService.releaseStation(id);
    }
}
