import application.entity.People;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

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
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                Criteria criteria = getSession().createCriteria(People.class);
//                Projections
                criteria.add(Restrictions.eq("email", "pushka@gmail.com"));
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
                for (Object o : criteria.list()) {
                    People people = (People) o;
                    System.out.println("  " + people.getName() + " " + people.getSurname());
                }
            }
        } finally {
            session.close();
        }
    }
}
// TODO: 25.10.2019 1. Почитать про SessionFactory и DataSource
//  2. Почитать про Session
//  3. Прочитать про Критерию и переделать на нее все селекты
//  4. Restrictions, Projections и их использование в Criteria