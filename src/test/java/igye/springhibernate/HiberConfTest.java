package igye.springhibernate;

import igye.springhibernate.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Database;
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
import java.sql.SQLException;
import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource("classpath:log.properties")
public class HiberConfTest {
    @Autowired
    private EntityManagerFactory emf;

    private SessionFactory sessionFactory;

    @Before
    public void before() {
        sessionFactory = emf.unwrap(SessionFactory.class);
    }

    @Test
    public void checkSession() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Item item = new Item();
        session.save(item);
        session.flush();
    }

    @Configuration
    @EnableTransactionManagement
    public static class Conf {

        @Bean
        public EmbeddedDatabase dataSource() {
            EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                    .generateUniqueName(true)
                    .setType(H2)
                    .setScriptEncoding("UTF-8")
                    .ignoreFailedDrops(true)
//                    .addScript("schema.sql")
//                    .addScripts("user_data.sql", "country_data.sql")
                    .build();

            return db;
        }

        @Bean
        public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
            LocalSessionFactoryBean res = new LocalSessionFactoryBean();
            res.setDataSource(dataSource);
            res.setPackagesToScan("igye.springhibernate.model");
            Properties props = new Properties();
            props.put("hibernate.dialect", Database.H2);
            props.put("hibernate.format_sql", "true");
            props.put("hibernate.use_sql_comments", "true");
//            props.put("hibernate.show_sql", "true");
            props.put("hibernate.hbm2ddl.auto", "create-drop");
            res.setHibernateProperties(props);
            return res;
        }

        @Bean
        public HibernateTransactionManager transactionManager(DataSource dataSource) {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(sessionFactory(dataSource).getObject());
            return transactionManager;
        }
    }


}
