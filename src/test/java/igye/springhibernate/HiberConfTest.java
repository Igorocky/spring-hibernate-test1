package igye.springhibernate;

import igye.springhibernate.model.Item;
import igye.springhibernate.model.Message;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
public class HiberConfTest extends AbstractHibernateTest {
    @Test
    public void sessionShouldBeAvailable() {
        Session session = getCurrentSession();
        Item item = new Item();
        session.save(item);
        session.flush();
    }

    @Test
    public void saveMethodShouldReturnTheSameId() throws SQLException {
        Session session = getCurrentSession();
        Message msg = new Message();
        msg.setText("tttteeexxxttt");
        Long id1 = (Long) session.save(msg);
        Long id2 = (Long) session.save(msg);
        Assert.assertEquals(id1, id2);
    }
}
