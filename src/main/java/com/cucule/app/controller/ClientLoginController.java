package com.cucule.app.controller;

import com.cucule.app.request.ClientLoginForm;
import com.cucule.common.annotation.NonAuth;
import com.cucule.common.logging.CuculeSessionManager;
import com.cucule.service.ClientAuthService;
import com.cucule.service.input.AuthServiceInput;
import com.cucule.service.output.AuthServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@NonAuth
@Controller
public class ClientLoginController extends ClientBaseController {
    @Autowired
    ClientAuthService authService;

    @RequestMapping(value = "/client/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) throws Exception {
        CuculeSessionManager.clearSession(request);
        return "client_login";
    }

    @RequestMapping(value = "/client/login", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute @Valid ClientLoginForm form, BindingResult bindingResult, HttpServletRequest httpRequest, Model model) throws Exception {

        if (hasValidation(bindingResult, model)) {
            model.addAttribute("form", form);
            return "client_login";
        }

        AuthServiceInput input = new AuthServiceInput();
        input.setLonginId(form.getLoginId());
        input.setLoginPassword(form.getLoginPassword());
        AuthServiceOutput output = authService.checkAuth(input);
        if (output.isWasApplied()) {
            CuculeSessionManager.saveSession(httpRequest, output.getLonginId(), output.getLole(), output.getClientId());
        } else {
            // loginに失敗のpage表示させる
        }

        return "redirect:/client/top";
    }

}
