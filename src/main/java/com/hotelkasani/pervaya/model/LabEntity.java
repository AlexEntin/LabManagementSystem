package com.hotelkasani.pervaya.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.hotelkasani.pervaya.model.view.LabView;
import com.hotelkasani.pervaya.model.view.StationView;
import com.hotelkasani.pervaya.model.view.UserView;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lab")
@EqualsAndHashCode
public class LabEntity {
    private int id;
    private String labName;
    private String location;
    private String labDesc;


    private List<UserEntity> users = new ArrayList<>();

    //    @JsonManagedReference
    @ManyToMany(mappedBy = "labs")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @JsonView({UserView.class, LabView.class})
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("lab_fucking_name")
    @JsonView({UserView.class, StationView.class, LabView.class})
    @Basic
    @Column(name = "lab_name")
    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    @JsonView(LabView.class)
    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "lab_desc")
    public String getLabDesc() {
        return labDesc;
    }

    public void setLabDesc(String labDesc) {
        this.labDesc = labDesc;
    }
}
