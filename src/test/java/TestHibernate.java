import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestHibernate {
    private SessionFactory sessionFactory = null;

    @Test
    public void test() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        User user = new User();
        user.setName("小忠");
        user.setPassword("123456");
        user.setUsername("1477");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
