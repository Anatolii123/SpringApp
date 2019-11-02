import application.entity.People;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;
import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;

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
//        System.out.println(Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
//        SecureRandom secureRandom = new SecureRandom();
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        System.out.println(keyPair.getPublic());
//        System.out.println((long) (Math.random() * 1000000000000000000L));

//        BigInteger result = BigInteger.valueOf(0);
//        for (int i = 1000; i > 1; i--) {
//            BigInteger en = BigInteger.valueOf(i).pow(982);
//            result = en.mod(BigInteger.valueOf(983));
//            if (result.equals(BigInteger.valueOf(1))) {
//                System.out.println(i);
//                break;
//            }
//        }
        String s = "9dd4e461268c8034f5c8564e155c67a6";

        BigInteger num = new BigInteger(s,16);
        BigInteger num2 = num.xor(BigInteger.valueOf(669));
        System.out.println(num);
        System.out.println(num2);
        String s2 = num2.toString(16);
        System.out.println(s2);
        String s3 = num2.xor(BigInteger.valueOf(669)).toString(16);
        System.out.println(s3);
//        Integer[] arr = new Integer[32];
//        String[] arr2 = new String[32];
//        for (int i = 0; i < s.length(); i++) {
//            arr[i] = Integer.parseInt(s.substring(i,i+1),16);
//            System.out.print(arr[i] + " ");
//            arr[i] = arr[i] ^ 669;
//            arr2[i] = Integer.toHexString(arr[i]);
//        }
//        System.out.println();
//        for (int i = 0; i < s.length(); i++) {
//            System.out.print(arr2[i] + " ");
//        }
//        System.out.println();
//        StringBuilder builder = new StringBuilder();
//        for(String str : arr2) {
//            builder.append(str);
//        }
//        s = builder.toString();
//        System.out.println(s);
//
//        BigInteger key = BigInteger.valueOf(669);
//
//        int len = key.toString().length();
//        System.out.println(key.intValue());
//
//        String[] arr3 = new String[s.length()/3];
//        Integer[] arr4 = new Integer[s.length()/3];
//        for (int i = 0; i < s.length()/3; i++) {
//            arr3[i] = s.substring(i*3,i*3+3);
//            arr4[i] = Integer.parseInt(arr3[i],16) ^ 669;
//        }
//        for (int i = 0; i < s.length()/3; i++) {
//            arr3[i] = Integer.toHexString(arr4[i]);
//        }
//        StringBuilder builder2 = new StringBuilder();
//        for(String str : arr3) {
//            builder2.append(str);
//        }
//        s = builder2.toString();



//        for (var i = 0; i < s.length/3; i++) {
//            s = s.replace("a","A").replace("b","B").replace("c","C").replace("d","D").replace("e","E").replace("f","F");
//            arr2[i] = s.substr(i*3,3);
//            arr2[i] = parseInt(arr2[i],16) ^ 669;
//            arr2[i] = arr2[i].toString(16);
//        }
//        s = arr2.join("");
//        alert(s);
    }
}
