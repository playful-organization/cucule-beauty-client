package com.cucule.common.logging;

import com.cucule.common.global.CuculeLole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CuculeSessionManager {
    private static final String LOGIN_ID = "loginId";
    private static final String LOLE = "lole";
    private static final String CLIENT_ID = "clientId";
    private static final String WAS_APPLIED = "wasApplied";

    public static void initSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(WAS_APPLIED) == null) {
            session.setAttribute(WAS_APPLIED, false);
        }
    }

    public static void saveSession(HttpServletRequest request, String loginId, CuculeLole lole, String clientId) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_ID, loginId);
        session.setAttribute(LOLE, lole);
        session.setAttribute(CLIENT_ID, clientId);
        session.setAttribute(WAS_APPLIED, true);

    }

    public static String fetchClientId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute(CLIENT_ID);

        return clientId;
    }

    public static CuculeLole fetchLole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CuculeLole lole = (CuculeLole) session.getAttribute(LOLE);

        return lole;
    }

    public static boolean wasApplied(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean wasApplied = (boolean) session.getAttribute(WAS_APPLIED);

        return wasApplied;
    }

    public static void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_ID);
        session.removeAttribute(LOLE);
        session.removeAttribute(CLIENT_ID);
        session.removeAttribute(WAS_APPLIED);
    }
}
