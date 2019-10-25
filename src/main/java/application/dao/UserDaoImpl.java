package application.dao;

import application.entity.People;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    public JdbcTemplate jdbcTemplate;

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

//    final Metamodel metamodel = session.getSessionFactory().getMetamodel();

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(People user) {
        final Session session = getSession();
        try {
            session.beginTransaction();
            People people = new People();
            Criteria criteria = getSession().createCriteria(People.class).setProjection(Projections.max("id"));
            int newId = (int)criteria.uniqueResult() + 1;
            people.setId(newId);
            people.setName(user.getName());
            people.setSurname(user.getSurname());
            people.setEmail(user.getEmail());
            people.setPassword(user.getPassword());
            people.setDateOfBirth(user.getDateOfBirth());
            people.setGender(user.getGender());
            people.setBug(user.getBug());
            people.setComments(user.getComments());
            session.save(people);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public People logIn(String email, String password) {
        final Session session = getSession();
        People user = new People();
        try {
            Criteria criteria = getSession().createCriteria(People.class);
            criteria.add(Restrictions.and(Restrictions.eq("email", email),
                    Restrictions.eq("password", password)));
            for (Object o : criteria.list()) {
                user = (People) o;
            }
        } catch (Exception e) {
            user = null;
        } finally {
            session.close();
            return user;
        }
//        String sql = "SELECT * FROM PEOPLE WHERE EMAIL = ? AND PASSWORD = ?";
//        try {
//            user =
//            user = jdbcTemplate.queryForObject(sql, new UserMapper(), email, password);
//        } catch (Exception e) {
//            user = null;
//        }
//        return user;
    }

    @Override
    public void update(People user) {

    }


    @Override
    public void logOut() {

    }
}
