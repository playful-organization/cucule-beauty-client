package com.cucule.app.controller;

import com.cucule.common.annotation.ClientAuth;
import com.cucule.common.logging.CuculeSessionManager;
import com.cucule.service.ClientTopService;
import com.cucule.service.output.ClientTopServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ClientAuth
@Controller
public class ClientTopController extends ClientBaseController {

    @Autowired
    ClientTopService clientTopService;

    @RequestMapping(path = "/client/top", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView clientTop(HttpServletRequest request) throws Exception {

        ClientTopServiceOutput output = clientTopService.initClientTop(CuculeSessionManager.fetchClientId(request));

        return new ModelAndView("client_top", "output", output);
    }
}
