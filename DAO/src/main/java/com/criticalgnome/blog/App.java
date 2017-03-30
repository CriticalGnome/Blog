package com.criticalgnome.blog;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.utils.HibernateConnector;
import org.hibernate.Session;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class App {
    public static void main(String[] args) {
        Session session = HibernateConnector.getInstance().getSession();
        session.beginTransaction();
        Role role = new Role(null, "Test");
        session.save(role);
        session.getTransaction().commit();
        session.close();
        session.getSessionFactory().close();
    }
}
