package igye.springhibernate.dao;

import igye.springhibernate.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Message saveMessage(Message msg) {
        Session session = getCurrentSession();
        session.save(msg);
        return msg;
    }

    @Transactional
    public Message persistMessage(Message msg) {
        Session session = getCurrentSession();
        session.persist(msg);
        return msg;
    }

    @Transactional
    public List<Message> loadMessagesByText(String text) {
        return getCurrentSession()
                .createQuery("select m from Message m where m.text like '%'||:text||'%'")
                .setParameter("text", text)
                .getResultList();
    }

    @Transactional
    public List<Message> loadAllMessages() {
        Session session = getCurrentSession();
        return session.createQuery("select m from Message m").getResultList();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
