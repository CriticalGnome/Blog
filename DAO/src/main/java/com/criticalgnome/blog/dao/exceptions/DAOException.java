package com.criticalgnome.blog.dao.exceptions;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class DAOException extends Exception {
    public DAOException(String msg) {
        super(msg);
    }
    public DAOException(String msg, Throwable e) {
        super(msg, e);
    }
}
