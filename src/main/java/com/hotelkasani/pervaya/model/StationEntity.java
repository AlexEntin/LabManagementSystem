package com.hotelkasani.pervaya.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.hotelkasani.pervaya.model.view.StationView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "station", schema = "lab", catalog = "")
public class StationEntity {
    private int id;
    private String stationName;
    private String environment;
    private String description;
    private String reservedBy;


    private LabEntity labId;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonView(StationView.class)
    @Basic
    @Column(name = "station_name")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @JsonView(StationView.class)
    @Basic
    @Column(name = "environment")
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @JsonView(StationView.class)
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(StationView.class)
    @Basic
    @Column(name = "reserved_by")
    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    @ManyToOne
    @JoinColumn (name = "lab_id")
    public LabEntity getLabId() {
        return labId;
    }

    public void setLabId(LabEntity labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return id == that.id &&
                Objects.equals(stationName, that.stationName) &&
                Objects.equals(environment, that.environment) &&
                Objects.equals(description, that.description) &&
                Objects.equals(reservedBy, that.reservedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationName, environment, description, reservedBy);
    }


}
