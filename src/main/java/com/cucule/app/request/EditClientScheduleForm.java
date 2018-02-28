package com.cucule.app.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.cucule.common.global.CuculeConstants.RAITEN_DATE_REGEX;

@Data
public class EditClientScheduleForm {

    private String reservationId;

    private String staffId;

    @NotEmpty(message = "{schedule.edit.raitendate.notempty}")
    @Pattern(regexp = RAITEN_DATE_REGEX, message = "{schedule.edit.raitendate.pattern}")
    private String raitenDate;

    private String startTime;

    private String needTime;

    private String endTime;

    // TODO 今の所名前のchenge許可していないからいらない。。。
    //    private String lastNameKana;
    //
    //    private String firstNameKana;
    //
    //    private String lastNameKanji;
    //
    //    private String firstNameKanji;

    private String displayName;
}
