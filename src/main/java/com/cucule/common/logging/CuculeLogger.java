package com.cucule.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuculeLogger {
    private static final Logger logger = LoggerFactory.getLogger(CuculeLogger.class);

    public static void info(String msg, Exception e) {
        logger.info(msg, e);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void debug(String msg, Exception e) {
        logger.debug(msg, e);
    }

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void error(String msg, Exception e) {
        logger.error(msg, e);
    }

    public static void error(String msg) {
        logger.error(msg);
    }
}
