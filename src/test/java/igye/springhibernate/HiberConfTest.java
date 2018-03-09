package igye.springhibernate;

import igye.springhibernate.model.Item;
import igye.springhibernate.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@HibernateTestConfig
public class HiberConfTest {
    @Autowired
//    private EntityManagerFactory emf;
    private SessionFactory sessionFactory;

//    @Before
//    public void before() {
//        sessionFactory = emf.unwrap(SessionFactory.class);
//    }

    @Test
    public void sessionShouldBeAvailable() throws SQLException {
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

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
