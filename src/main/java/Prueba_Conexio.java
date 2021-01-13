
import com.fenoreste.rest.util.Fichero_Configuraciones;
import com.fenoreste.rest.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Prueba_Conexio {
    
    public static void main(String[] args) {
         conexion();
        
    }
    public static void conexion(){
           JPAUtil jp=new JPAUtil();
           Fichero_Configuraciones fc=new Fichero_Configuraciones();
           System.out.println("Direccion_Servidor:"+fc.getHost());
           System.out.println("Base_De_Datos:"+fc.getDatabase());
           String hs=String.valueOf(fc.getHost());
           String db=String.valueOf(fc.getDatabase());
           EntityManagerFactory emf=jp.getEntityManagerFactory(hs,db);
           EntityManager em=emf.createEntityManager();
           Query query=em.createNativeQuery("select nombre from personas limit 1");
           String nombre =(String) query.getSingleResult();
           emf.close();
           System.out.println("Nombre:"+nombre);
         
    }
   
     public static void leerArchivo(){
    
     }  
  
}
