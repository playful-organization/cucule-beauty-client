package com.cucule.service.input;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterClientScheduleInput {
    private String clientId;
    private String staffId;
    private Date startTime;
    private Integer needTime;
    private Date endTime;
    private String lastNameKana;
    private String firstNameKana;
    private String lastNameKanji;
    private String firstNameKanji;
}
