package com.cucule.service.input;

import lombok.Data;

import java.util.Date;

@Data
public class EditClientScheduleInput {
    private String reservationId;
    private String staffId;
    private Date startTime;
    private Date endTime;
    private Integer needTime;

}
