package com.hotelkasani.pervaya.repository;

import com.hotelkasani.pervaya.model.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository <LabEntity, Integer> {
}
