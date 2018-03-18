package igye.springhibernate;

import igye.springhibernate.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static igye.springhibernate.TestUtils.SQL_DEBUG_LOGGER_NAME;

@RunWith(SpringRunner.class)
public class HiberConfTest extends AbstractHibernateTest {
    private static final Logger logger = LogManager.getLogger(SQL_DEBUG_LOGGER_NAME);

    @Test
    public void sessionShouldBeAvailable() {
        Session session = getCurrentSession();
        Item item = new Item();
        session.save(item);
        session.flush();
//        TestUtils.exploreDB(session);
    }

    @Test
    public void queryingSuperclass() {
        getCurrentSession().createQuery("select bd from BillingDetails bd").getResultList();
    }

    @Test
    public void savingAndReadingEmbeddableComponentWithAllFieldsNull() {
        //given
        Long id = transactionTemplate.execute(status -> {
            Session session = HiberConfTest.this.getCurrentSession();
            User user = new User();
            user.setHomeAddress(new Address());
            return (Long) session.save(user);
        });

        //when
        Address addr = transactionTemplate.execute(
                status -> getCurrentSession().load(User.class, id).getHomeAddress()
        );

        //then
        Assert.assertNull(addr);
    }

    @Test
    public void saveMethodShouldReturnTheSameId() {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("tttteeexxxttt");
        Long id1 = (Long) session.save(msg);
        Long id2 = (Long) session.save(msg);
        Assert.assertEquals(id1, id2);
    }

    @Test
    public void queryWillReturnTheSameObject() {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("text098");

        session.persist(msg);
        List<Message> res = session.createQuery("select m from Message m where m.text = :text")
                .setParameter("text", "text098")
                .getResultList();

        Assert.assertEquals(1, res.size());
        Assert.assertTrue(msg == res.get(0));
    }

    @Test
    public void queryWillReturnNewObjectIfTheObjectWasDetached() {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("text098");

        session.persist(msg);
        session.flush();
        session.detach(msg);
        List<Message> res = session.createQuery("select m from Message m where m.text = :text")
                .setParameter("text", "text098")
                .getResultList();

        Assert.assertEquals(1, res.size());
        Assert.assertTrue(msg != res.get(0));
    }

    @Test
    public void derivedFieldShouldNotBeSetInOneTransaction() {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("text098");

        session.persist(msg);
        logger.info("Before query");
        List<Message> res = session.createQuery("select m from Message m where m.text = :text")
                .setParameter("text", "text098")
                .getResultList();
        logger.info("After query");

        Assert.assertEquals(1, res.size());
        Assert.assertNull(res.get(0).getDerivedText());
    }

    @Test
    public void derivedFieldShouldBeCalculatedCorrectlyIfTheObjectWasDetached() {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("text098");

        session.persist(msg);
        session.flush();
        session.detach(msg);

        logger.info("Before query");
        Message res = (Message) session.createQuery("select m from Message m where m.text = :text")
                .setParameter("text", "text098")
                .getSingleResult();
        logger.info("After query");

        Assert.assertEquals("text098###", res.getDerivedText());
    }

    @Test
    public void subselectShouldReturnCorrectResult() {
        Session session = getCurrentSession();
        Message msg = new Message("t1", "a1");
        session.persist(msg);
        msg = new Message("t2", "a2");
        session.persist(msg);
        msg = new Message("t3", "a2");
        session.persist(msg);

        TextCount res = (TextCount) session
                .createQuery("select tc from TextCount tc where tc.author = :author")
                .setParameter("author", "a2")
                .getSingleResult();

        Assert.assertEquals(Long.valueOf(2), res.getNumberOfMessages());
//        TestUtils.exploreDB(session);
    }
}
