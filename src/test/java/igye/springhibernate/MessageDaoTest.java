package igye.springhibernate;

import igye.springhibernate.dao.MessageDao;
import igye.springhibernate.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@HibernateTestConfig
@ContextConfiguration(classes = MessageDao.class)
public class MessageDaoTest {
    private static final Logger logger = LogManager.getLogger(MessageDaoTest.class);

    @Autowired
    private MessageDao dao;
    @Autowired
    private SessionFactory sessionFactory;


    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @Before
    public void before() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    @Test
    public void messageDaoShoulSaveAndLoadMessages() {
        Session session = sessionFactory.getCurrentSession();
        //given
        logger.info("0TRANS - {}", session.getTransaction().getStatus());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                logger.info("1TRANS - {}", status);
                dao.saveMessage(createNewMessage("msg1"));
                dao.saveMessage(createNewMessage("msg2"));
            }

            private Message createNewMessage(String text) {
                Message msg = new Message();
                msg.setText(text);
                return msg;
            }
        });

        //when
        List<Message> messages = transactionTemplate.execute(new TransactionCallback<List<Message>>() {
            @Override
            public List<Message> doInTransaction(TransactionStatus status) {
                logger.info("2TRANS - {}", status);
                return dao.loadAllMessages();
            }
        });

        //then
        Assert.assertEquals(2, messages.size());
        logger.info("00TRANS - {}", session.getTransaction().getStatus());

    }
}
