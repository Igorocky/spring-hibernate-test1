package igye.springhibernate;

import igye.springhibernate.dao.MessageDao;
import igye.springhibernate.model.MessageWithAutoGeneratedId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static igye.springhibernate.TestUtils.SQL_DEBUG_LOGGER_NAME;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MessageDao.class)
public class MessageWithAutoGeneratedIdTest extends AbstractHibernateTest {
    private static final Logger logger = LogManager.getLogger(SQL_DEBUG_LOGGER_NAME);

    @Ignore
    @Test
    public void idShouldNotBeAssignedByPersist() {
        Session ses = getCurrentSession();
        MessageWithAutoGeneratedId msg = createNewMessage("msg900");

        logger.info("Before persist");
        ses.persist(msg);
        logger.info("After persist");
        Assert.assertNull(msg.getId());

        ses.flush();
        Assert.assertNotNull(msg.getId());
    }

    @Ignore
    @Test
    public void itShouldBePossibleToCreateFewRecordsUsingSingleInstanceOfAnObject() {
        Session ses = getCurrentSession();
        MessageWithAutoGeneratedId msg = createNewMessage("msg300");

        ses.save(msg);
        ses.flush();
        ses.evict(msg);

        msg.setText("msg400");
        ses.save(msg);
        ses.flush();
        ses.evict(msg);

        ses.flush();
        TestUtils.exploreDB(ses);
    }

    @Test
    public void messageShouldBeQueryableAfterPersist() {
        Session ses = getCurrentSession();
        MessageWithAutoGeneratedId msg = createNewMessage("msg300");
        ses.persist(msg);
        List<MessageWithAutoGeneratedId> res = ses
                .createQuery("select m from MessageAID m where m.text = 'msg300'").getResultList();
        Assert.assertEquals(1, res.size());
    }

    private MessageWithAutoGeneratedId createNewMessage(String text) {
        MessageWithAutoGeneratedId msg = new MessageWithAutoGeneratedId();
        msg.setText(text);
        return msg;
    }
}
