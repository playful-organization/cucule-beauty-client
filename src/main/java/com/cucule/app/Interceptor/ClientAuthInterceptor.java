package com.cucule.app.Interceptor;

import com.cucule.common.annotation.ClientAuth;
import com.cucule.common.annotation.NonAuth;
import com.cucule.common.annotation.UserAuth;
import com.cucule.common.exception.CuculeAuthException;
import com.cucule.common.global.CuculeLole;
import com.cucule.common.logging.CuculeLogger;
import com.cucule.common.logging.CuculeSessionManager;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ClientAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CuculeLogger.info("URL : " + request.getRequestURL().toString());
        CuculeLogger.info("URI : " + request.getRequestURI());
        CuculeLogger.info("QueryString : " + request.getQueryString());
        CuculeLogger.info("Method : " + request.getMethod());

        CuculeSessionManager.initSession(request);

        HandlerMethod hm = (HandlerMethod) handler;

        Class c = hm.getBean().getClass();
        NonAuth nonAuth = AnnotationUtils.findAnnotation(c, NonAuth.class);
        if (nonAuth != null) {
            CuculeLogger.info("Auth対象外");
            return true;
        }

        if (!CuculeSessionManager.wasApplied(request)) {
            throw new CuculeAuthException("Auth error.");
        }

        ClientAuth clientAuth = AnnotationUtils.findAnnotation(c, ClientAuth.class);
        if (clientAuth != null) {
            if (CuculeLole.Client.equals(CuculeSessionManager.fetchLole(request))) {
                CuculeLogger.info("Client認証完了");
                return true;
            } else {
                throw new CuculeAuthException("Auth error.");
            }
        }
        UserAuth userAuth = AnnotationUtils.findAnnotation(c, UserAuth.class);
        if (userAuth != null) {
            if (CuculeLole.User.equals(CuculeSessionManager.fetchLole(request))) {
                CuculeLogger.info("User認証完了");
                return true;
            } else {
                throw new CuculeAuthException("Auth error.");
            }
        }
        return true;
    }
}
