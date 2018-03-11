package igye.springhibernate;

import org.hibernate.Session;

public class TestUtils {
    public static void exploreDB(Session session) {
        session.doWork(connection -> org.h2.tools.Server.startWebServer(connection));
    }
}
