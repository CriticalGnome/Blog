package com.criticalgnome.blog.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Project Blog
 * Created on 09.04.2017.
 *
 * @author CriticalGnome
 */
public class HibernateUtil {
    private static HibernateUtil util;
    private SessionFactory sessionFactory;
    private final ThreadLocal <Session> sessions = new ThreadLocal<Session>();

    private HibernateUtil(){
        Configuration configuration = new Configuration();
        configuration.configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static HibernateUtil getInstance(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }

    public Session getSession(){
        Session session = sessions.get();
        if(session == null){
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void releaseSession(Session session){
        if(session != null){
            session.close();
            sessions.remove();
        }
    }

}
