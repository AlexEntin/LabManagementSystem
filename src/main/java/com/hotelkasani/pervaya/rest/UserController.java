package com.hotelkasani.pervaya.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hotelkasani.pervaya.model.UserEntity;
import com.hotelkasani.pervaya.model.view.UserView;
import com.hotelkasani.pervaya.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(UserView.class)
    @GetMapping("getAll")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userService.getAllUsers();
    }

    @JsonView(UserView.class)
    @GetMapping("get/:id")
    public ResponseEntity<UserEntity> getOne(@PathVariable Integer id) {
        return userService.getOne(id);
//        return userService.getOneByLab(id, lab);
    }

    @JsonView(UserView.class)
    @GetMapping("getByLab")
    public List<UserEntity> getByLab(@RequestParam(required = false) String lab) {
        return userService.getOneByLab(lab);
    }



    public ModelAndView someMethod(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

}
