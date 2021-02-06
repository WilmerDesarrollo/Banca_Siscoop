
import com.fenoreste.rest.modelos.entidad.Productos;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class App {

    private static SessionFactory sessionFactory;

    public static final SessionFactory sessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.URL, "postgresql://localhost:5432/sagrada310320");
                properties.put(Environment.USER, "desarrollo");
                properties.put(Environment.PASS, "desarrollo");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                properties.put(Environment.SHOW_SQL, true);
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                properties.put(Environment.C3P0_MIN_SIZE,"20");
                properties.put(Environment.C3P0_TIMEOUT, "300");
                properties.put(Environment.C3P0_MAX_STATEMENTS, "50");
                properties.put(Environment.C3P0_IDLE_TEST_PERIOD,"3000");
                properties.put(Environment.HBM2DDL_AUTO,"create-drop"); 
                configuration.setProperties(properties);
                
                //configuration.addAnnotatedClass(Productos.class);
                
                ServiceRegistry service=new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
                
                sessionFactory = configuration.buildSessionFactory(service);
            } catch (Exception e) {
                System.out.println("Error:"+e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        Configuration cfg = new Configuration();

        cfg.configure("hibernate.cfg.xml");
        Properties properties = new Properties();
        properties.put(Environment.USE_GET_GENERATED_KEYS,false);
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "postgresql://192.168.99.200:5432/sannicolas30nov20_movimientos");
        properties.put(Environment.USER, "saicoop");
        properties.put(Environment.PASS, "slufpana?");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");/*
        properties.put("hibernate.jdbc.use_get_generated_keys", "false");
        properties.put("hibernate.c3p0.min_size", "5");
        properties.put("hibernate.c3p0.max_size", "20");
        properties.put("hibernate.c3p0.timeout", "300");
        properties.put("hibernate.c3p0.max_statements", "50");
        properties.put("hibernate.c3p0.idle_test_period", "3000");*/
        cfg.setProperties(properties);

        SessionFactory session = cfg.buildSessionFactory();
        System.out.println("Session:" + session);
        //SessionFactory session=cfg.buildSessionFactory();
        sessionFactory.openSession();
        EntityManager em = session.createEntityManager();
        Query q = em.createNativeQuery("SELECT nombre FROM personas limit 1");
        System.out.println("Nombre:" + (String) q.getSingleResult());

    }
}
