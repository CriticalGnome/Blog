package com.criticalgnome.blog.exceptions;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class DaoException extends Exception {
    public DaoException(String msg) {
        super(msg);
    }
    public DaoException(String msg, Throwable e) {
        super(msg, e);
    }
    public DaoException(Class clazz, String msg, Throwable e) {
        super(msg, e);
        Logger logger = LogManager.getLogger(clazz);
        logger.log(Level.ERROR, msg, e);
    }
}
