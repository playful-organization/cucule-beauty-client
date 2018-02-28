package com.cucule.app.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.cucule.common.global.CuculeConstants.FIRST_NAME_KANA_REGEX;
import static com.cucule.common.global.CuculeConstants.LAST_NAME_KANA_REGEX;
import static com.cucule.common.global.CuculeConstants.RAITEN_DATE_REGEX;

@Data
public class RegisterClientScheduleForm {

    private String staffId;

    @NotEmpty(message = "{schedule.register.raitendate.notempty}")
    @Pattern(regexp = RAITEN_DATE_REGEX, message = "{schedule.register.raitendate.pattern}")
    private String raitenDate;

    private String startTime;

    private String needTime;

    private String endTime;

    @NotEmpty(message = "{schedule.register.lastnamekana.notempty}")
    @Pattern(regexp = LAST_NAME_KANA_REGEX, message = "{schedule.register.lastnamekana.pattern}")
    private String lastNameKana;

    @Pattern(regexp = FIRST_NAME_KANA_REGEX, message = "{schedule.register.firstnamekana.pattern}")
    private String firstNameKana;

    @Size(max = 32, message = "{schedule.register.lastnamekanji.size}")
    private String lastNameKanji;

    @Size(max = 32, message = "{schedule.register.firstnamekanji.size}")
    private String firstNameKanji;

    private String displayName;
}
