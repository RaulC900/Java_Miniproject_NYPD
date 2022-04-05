package com.example.javaminiprojectnypd.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "CMPLNT_NUM")
    private Integer complaintID;

    @Column(name = "KY_CD")
    private Integer offenseCode;

    public Event() {
    }

    public Event(Integer complaintID, Integer offenseCode) {
        this.complaintID = complaintID;
        this.offenseCode = offenseCode;
    }

    public Integer getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(Integer complaintID) {
        this.complaintID = complaintID;
    }

    public Integer getOffenseCode() {
        return offenseCode;
    }

    public void setOffenseCode(Integer offenseCode) {
        this.offenseCode = offenseCode;
    }
}
