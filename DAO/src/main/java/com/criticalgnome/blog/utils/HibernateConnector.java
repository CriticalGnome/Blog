package com.criticalgnome.blog.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class HibernateConnector {

    private static HibernateConnector me;
    private Configuration cfg;
    private SessionFactory factory;
    private StandardServiceRegistryBuilder builder;

    private HibernateConnector() throws HibernateException {

        cfg = new Configuration().configure();
        builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        factory = cfg.buildSessionFactory(builder.build());

    }

    public static synchronized HibernateConnector getInstance() throws HibernateException {
        if (me == null) {
            me = new HibernateConnector();
        }

        return me;
    }

    public Session getSession() throws HibernateException {
        Session session = factory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    public void releaseSession(Session session) throws HibernateException {
        if (session != null) {
            session.close();
        }
    }

    private void reconnect() throws HibernateException {
        this.factory = cfg.buildSessionFactory(builder.build());
    }
}