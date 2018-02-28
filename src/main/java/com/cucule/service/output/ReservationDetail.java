package com.cucule.service.output;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDetail {
    private String reservationId;
    private String userFullName;
    private String userLastNameKana;
    private String userFirstNameKana;
    private String userLastNameKanji;
    private String userFirstNameKanji;
    private Date startTime;
    private Date endTime;
    private Integer needTime;

}
