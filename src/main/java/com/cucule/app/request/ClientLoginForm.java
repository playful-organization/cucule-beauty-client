package com.cucule.app.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.cucule.common.global.CuculeConstants.LOGIN_ID_REGEX;
import static com.cucule.common.global.CuculeConstants.LOGIN_PASSWORD_REGEX;

@Data
public class ClientLoginForm {

    @NotEmpty(message = "{login.id.notempty}")
    @Pattern(regexp = LOGIN_ID_REGEX, message = "{login.id.pattern}")
    private String loginId;

    @NotEmpty(message = "{login.password.notempty}")
    @Pattern(regexp = LOGIN_PASSWORD_REGEX, message = "{login.password.pattern}")
    private String loginPassword;
}
