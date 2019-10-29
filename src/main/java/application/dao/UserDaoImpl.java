package application.dao;

import application.entity.People;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.providers.encoding.Md4PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    public JdbcTemplate jdbcTemplate;
    public BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Md4PasswordEncoder md4PasswordEncoder = new Md4PasswordEncoder();
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

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createId() {
        Criteria criteria = getSession().createCriteria(People.class).setProjection(Projections.max("id"));
        int newId = (int)criteria.uniqueResult() + 1;
        return newId;
    }

    @Override
    public boolean checkEntityInDatabase(People user) {
        final Session session = getSession();
        Object result = null;
        try {
            Criteria criteria = getSession().createCriteria(People.class);
            criteria.add(Restrictions.and(Restrictions.eq("email", user.getEmail()),
                    Restrictions.eq("password", user.getPassword())));
            result = criteria.uniqueResult();
        } finally {
            session.close();
            if (result != null) {
                return true;
            }
            return false;
        }
    }

//    private String hashPassword(String plainTextPassword){
//        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
//    }

    @Override
    public boolean checkEmailInDatabase(People user) {
        final Session session = getSession();
        Object result = null;
        try {
            Criteria criteria = getSession().createCriteria(People.class);
            criteria.add(Restrictions.eq("email", user.getEmail()));
            result = criteria.uniqueResult();
        } finally {
            session.close();
            if (result != null) {
                return true;
            }
            return false;
        }
    }

    @Override
    public void save(People user) {
        final Session session = getSession();
        try {
            session.beginTransaction();
            user.setId(createId());
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            session.save(user);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public People logIn(String email, String password) {
        final Session session = getSession();
        People user = null;
        People user2 = null;
        try {
            Criteria criteria2 = getSession().createCriteria(People.class);
            criteria2.add(Restrictions.eq("email", email));
            user2 = (People) criteria2.uniqueResult();
            if (passwordEncoder.matches(password,user2.getPassword())) {
                user = (People) criteria2.uniqueResult();
            }
            if (user == null && user2 != null) {
                user = user2;
            }
        } catch (NonUniqueResultException e) {
            throw e;
        } catch (Exception e) {
            user = null;
        } finally {
            session.close();
            return user;
        }
    }
}
