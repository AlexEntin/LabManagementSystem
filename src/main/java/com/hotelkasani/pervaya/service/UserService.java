package com.hotelkasani.pervaya.service;

import com.hotelkasani.pervaya.model.UserEntity;
import com.hotelkasani.pervaya.repository.UserRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser() {
        List<UserEntity> all = userRepository.findAll();
        logger.info(String.valueOf(all));
        return null;
    }

    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();
        logger.info(String.valueOf(all));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<UserEntity> getOne(Integer id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        UserEntity result = byId.orElseGet(() -> {
            logger.error("");
            return null;
        });
        return ResponseEntity.ok(result);
    }

    public List<UserEntity> getOneByLab(String lab) {
        return Optional.ofNullable(lab)
                .map(labName -> userRepository.findAllByLab(lab))
                .orElseGet(() -> {
                    logger.warn("Please use method findOne");
                    return null;
                });
    }
}
