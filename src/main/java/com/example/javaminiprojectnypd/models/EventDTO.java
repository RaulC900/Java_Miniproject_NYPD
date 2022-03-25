package com.example.javaminiprojectnypd.models;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "event")
public class EventDTO {

    @Id
    @Column(name = "CMPLNT_NUM")
    @NotNull
    @Min(value = 100000000, message = "complaintID must be a 9-digit number")
    @Max(value = 999999999, message = "complaintID must be a 9-digit number")
    private Integer complaintID;

    @Column(name = "KY_CD")
    @NotNull
    @Min(value = 100, message = "offenseCode must be a 3-digit number")
    @Max(value = 999, message = "offenseCode must be a 3-digit number")
    private Integer offenseCode;

    public EventDTO() {
    }

    public EventDTO(Integer complaintID, Integer offenseCode) {
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
