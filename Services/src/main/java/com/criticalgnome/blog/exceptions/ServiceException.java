package com.criticalgnome.blog.exceptions;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public class ServiceException extends Exception {
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
    public ServiceException(Class clazz, String msg, Throwable e) {
        super(msg, e);
        Logger logger = LogManager.getLogger(clazz);
        logger.log(Level.ERROR, msg, e);
    }
}
