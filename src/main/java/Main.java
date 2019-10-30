import application.entity.People;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                Criteria criteria = getSession().createCriteria(People.class);
//                criteria.add(Restrictions.eq("email", "pushka@gmail.com"));
//                for (Object o : criteria.list()) {
//                    People people = (People) o;
//                    System.out.println("  " + people.getName() + " " + people.getSurname());
//                }
//            }
//        } finally {
//            session.close();
//        }

    }
}
