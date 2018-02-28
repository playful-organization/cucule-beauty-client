package com.cucule.service.input;

import lombok.Data;

@Data
public class ChangeClientScheduleInput {
    private String clientId;
    private String reservationId;
    private Integer priority;
    private Float startTime;
    private Float endTime;
    private Integer needTime;

}
