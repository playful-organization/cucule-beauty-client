package com.cucule.app.request;

import lombok.Data;

@Data
public class ChangeClientScheduleForm {

    private String reservationId;

    private String priority;

    private String startTime;

    private String needTime;

    private String endTime;
}
