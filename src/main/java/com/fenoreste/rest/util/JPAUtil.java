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
        System.out.println("Ip:"+ip+", Base:"+bd);
        
        try {
        System.out.println("Lllego a jpa util");
        Properties properties = new Properties();
        properties.put("hibernate.jdbc.use_get_generated_keys","true");
        properties.put("hibernate.temp.use_jdbc_metadata_defaults","false");
        properties.put("hibernate.connection.driver_class","org.postgresql.Driver");
        properties.put("hibernate.connection.url","jdbc:postgresql://"+ip+":5432/"+bd);
        properties.put("hibernate.connection.username",usuario);
        properties.put("hibernate.connection.password",pass);
        return Persistence.createEntityManagerFactory(PU,properties);   
         
        } catch (Throwable e) {
            System.err.println("Error al conectar a la persistencia" + e.getMessage());
       
        }
          return null;
    }

}


