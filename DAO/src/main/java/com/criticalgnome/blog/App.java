package com.criticalgnome.blog;

import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.utils.HibernateConnector;
import org.hibernate.Session;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class App {
    public static void main(String[] args) {
        Session session = HibernateConnector.getInstance().getSession();
        List<Record> records = session.createCriteria(Record.class).list();
        for (Record record : records) { int i = record.getTags().size(); }
        session.close();
        session.getSessionFactory().close();
        System.out.println(records);
    }
}
