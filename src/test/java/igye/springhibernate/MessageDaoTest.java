package igye.springhibernate;

import igye.springhibernate.dao.MessageDao;
import igye.springhibernate.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MessageDao.class)
public class MessageDaoTest extends AbstractHibernateTest {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MessageDao dao;

    @Test
    public void messageDaoShouldSaveAndLoadMessages() {
        Session session = getCurrentSession();
        //given
        logger.info("0TRANS - {}", session.getTransaction().getStatus());
        transactionTemplate.execute(status -> {
            logger.info("1TRANS - {}", status);
            dao.saveMessage(createNewMessage("msg1"));
            dao.saveMessage(createNewMessage("msg2"));
            return null;
        });

        //when
        List<Message> messages = transactionTemplate.execute(status -> {
            logger.info("2TRANS - {}", status);
            return dao.loadAllMessages();
        });

        //then
        Assert.assertEquals(2, messages.size());
        logger.info("00TRANS - {}", session.getTransaction().getStatus());

//        TestUtils.exploreDB(session);
    }

    @Test
    public void messageShouldBeUpdatableInDifferentTransactions() {
        transactionTemplate.execute(status -> {
            dao.saveMessage(createNewMessage("msg10"));
            dao.saveMessage(createNewMessage("msg20"));
            return null;
        });

        transactionTemplate.execute(status -> {
            List<Message> msgs = dao.loadMessagesByText("10");
            msgs.forEach(msg -> msg.setText(msg.getText() + "-updated"));
            return null;
        });

        List<Message> messages = transactionTemplate.execute(status -> dao.loadMessagesByText("-updated"));

        Assert.assertEquals(1, messages.size());
        Assert.assertEquals("msg10-updated", messages.get(0).getText());
    }

    private Message createNewMessage(String text) {
        Message msg = new Message();
        msg.setText(text);
        return msg;
    }
}
