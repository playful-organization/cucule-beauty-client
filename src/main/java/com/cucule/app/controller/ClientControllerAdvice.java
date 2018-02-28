package com.cucule.app.controller;

import com.cucule.common.exception.CuculeAuthException;
import com.cucule.common.logging.CuculeLogger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public ModelAndView exceptionHandler(Exception e) {
        CuculeLogger.error(e.getMessage(), e);
        // とりあえずエラーページにトレース出しておく
        return new ModelAndView("error_page", "stackTrace", e.getStackTrace());
    }

    @ExceptionHandler(CuculeAuthException.class)
    public String authExceptionHandler(CuculeAuthException e) {
        CuculeLogger.info(e.getStackTrace().toString());
        return "redirect:/auth/error";
    }
    // TODO もっと細かくExceptionHandler定義する
    // TODO デフォルトの404のwhite page overrideする
}
