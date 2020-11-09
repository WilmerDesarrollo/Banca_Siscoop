package com.fenoreste.rest.util;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Wilmer
 */
public class JPAUtil{
      String  usuario = "saicoop";
      String pass = "slufpana?";
      String PU = "conexion";
    
    public EntityManagerFactory getEntityManagerFactory(String ip, String bd) {
        
        
        try {
        System.out.println("Lllego a jpa util");
        Properties properties = new Properties();
        properties.put("hibernate.jdbc.use_get_generated_keys" ,"false");
        properties.put("hibernate.connection.driver_class","org.postgresql.Driver");
        properties.put("hibernate.connection.url","jdbc:postgresql://"+ip+":5432/"+bd);
        properties.put("hibernate.connection.username",usuario);
        properties.put("hibernate.connection.password",pass);
        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.archive.autodetection","class");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
        properties.put("hbm2ddl.auto","update");
        properties.put("hibernate.c3p0.min_size","5");
        properties.put("hibernate.c3p0.max_size","30");
        properties.put("hibernate.c3p0.timeout","200");
        properties.put("hibernate.c3p0.max_statements","50");
        properties.put("hibernate.c3p0.idle_test_period","3000");
        EntityManagerFactory emf=Persistence.createEntityManagerFactory(PU,properties);    
            System.out.println("Listo");
         return emf;
        } catch (Throwable e) {
            System.err.println("Error al conectar a la persistencia" + e.getMessage());
       
        }
          return null;
    }

}


