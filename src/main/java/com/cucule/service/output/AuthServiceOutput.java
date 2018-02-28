package com.cucule.service.output;

import com.cucule.common.global.CuculeLole;
import lombok.Data;

@Data
public class AuthServiceOutput {
    private String longinId;
    private String loginPassword;
    private String clientId;
    private CuculeLole lole;
    private boolean wasApplied;
}
