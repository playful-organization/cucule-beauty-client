package com.cucule.app.controller;

import com.cucule.common.annotation.NonAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@NonAuth
@Controller
public class ClientErrorController extends ClientBaseController {

    @RequestMapping(value = "/auth/error")
    public String authError() throws Exception {
        return "auth_error";
    }
}
