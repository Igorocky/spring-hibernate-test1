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
        Session session = sessionFactory.getCurrentSession();
        session.save(msg);
        return msg;
    }

    @Transactional
    public List<Message> loadAllMessages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select m from Message m").getResultList();
    }
}
