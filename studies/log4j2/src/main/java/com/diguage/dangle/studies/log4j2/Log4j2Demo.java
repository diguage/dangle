package com.diguage.dangle.studies.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Calendar;

/**
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-21 20:56
 */
public class Log4j2Demo {
    //    private static final Logger logger = LogManager.getLogger(Log4j2Demo.class);
//    private static final Logger logger = LogManager.getFormatterLogger(Log4j2Demo.class);

    @Test
    public void testLog() {
        Logger logger = LogManager.getFormatterLogger(Log4j2Demo.class);

        logger.debug("Logging in user %s with birthday %s", "D瓜哥", Calendar.getInstance());
        logger.debug("Logging in user %1$s with birthday %2$tm %2$te,%2$tY", "D瓜哥", Calendar.getInstance());
        logger.debug("Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
        logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE);
    }

    @Test
    public void testFormatterLogger() {
        Logger logger = LogManager.getFormatterLogger(Log4j2Demo.class);

        logger.debug("Logging in user %s with birthday %s", "D瓜哥", Calendar.getInstance());
        logger.debug("Logging in user %1$s with birthday %2$tm %2$te,%2$tY", "D瓜哥", Calendar.getInstance());
        logger.debug("Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
        logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE);
    }
}
