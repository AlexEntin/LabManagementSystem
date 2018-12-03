package com.hotelkasani.pervaya.repository;

import com.hotelkasani.pervaya.model.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<StationEntity, Integer> {

    @Query (value = "select s from StationEntity s join s.labId lab where lab.id = :requestedLab")
    List<StationEntity> findAllByLabPlacement(@Param("requestedLab") Integer requestedLab);

    @Query (value = "select s from StationEntity s where (s.stationName =: requestedStation)")//and (s.labId =: requestedLab)
    List<StationEntity> findStationEntityByStationName(@Param("requestedStation") String requestedStation);

}
