package com.hotelkasani.pervaya.repository;

import com.hotelkasani.pervaya.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findAllByDepartment(String department);

    @Query(value = "select u from UserEntity u join u.labs lab where lab.labName = :labName")
    List<UserEntity> findAllByLab(@Param("labName") String labName);
}
