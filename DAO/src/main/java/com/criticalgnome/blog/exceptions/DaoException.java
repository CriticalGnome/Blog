package com.criticalgnome.blog.exceptions;

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
}
