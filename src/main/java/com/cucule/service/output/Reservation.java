package com.cucule.service.output;

import lombok.Data;

import java.util.List;

@Data
public class Reservation {

    private Integer priority;
    private String staffName;
    private  String staffId;
    private List<ReservationDetail> reservationDetailList;
}
