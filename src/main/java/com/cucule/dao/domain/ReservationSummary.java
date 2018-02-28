package com.cucule.dao.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by user on 2016/11/13.
 */
@Data
public class ReservationSummary {
    private String reservationId;
    private String clientId;
    private String staffId;
    private Integer priority;
    private String staffName;
    private Date startTime;
    private Integer needTime;
    private Date endTime;
    private String userLastNameKana;
    private String userFirstNameKana;
    private String userLastNameKanji;
    private String userFirstNameKanji;
}
