package com.cucule.common.exception;

import com.cucule.common.global.ClientPageName;
import lombok.Data;

@Data
public class ErrorModel {
    private String errorMassage;
    private ClientPageName validationErrorPageName;
}
