package com.fenoreste.rest.dao;
import com.fenoreste.rest.impl.AbstractFacade;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
    

	public abstract class FacadeProductos<T>{
		private static EntityManagerFactory emf;
		private static final String PERSISTENCE_UNIT_NAME="conexion";
		
		  public FacadeProductos(Class<T> entityClass) {
			  emf =AbstractFacade.conexion();//Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);    
		  }
		  
		  public List<Object[]> getProducts(){
                    String s="";
		   EntityManager em=emf.createEntityManager();
		   Query query=null; 
		   System.out.println("Accediendo a consulta...");     
		   String consulta="select idproducto,(case when tipoproducto=2 then 'Prestamo' else 'Ahorro' end) as pr,nombre from productos";
                                       try { 
                        // s = new String(consulta.getBytes(),"ISO-8859-1");
                          s = new String(consulta.getBytes("ISO-8859-1"), "UTF-8");
                                           System.out.println("si:"+s);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(FacadeProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
		  List<Object[]>lista=null;
		   try {
		    query=em.createNativeQuery(s);
		         	lista=query.getResultList();
		         	System.out.println("Resultados obtenidos:"+lista.size());
		 } catch (Exception e) {
		  	System.out.println("No pudo obtener datos");
		 }finally {
		  em.clear();                                   
                                     }          
	                    return query.getResultList();
                                     }
		  
	               public List<Object[]> getProductsRate(String customerId,int productCode,String accountType){
	                 EntityManager em=emf.createEntityManager();
	                 Query query=null; 
	                 String consulta="select (select tasaio from productos pr where pr.idproducto=a.idproducto),"
		       		+ "(select plazomax from productos pr where pr.idproducto=a.idproducto)as pm,"
		       		+ "20.00 as smin,"
		       		+ "(select pr.nombre from productos pr where pr.idproducto=a.idproducto)as nombre"
		       		+ " from auxiliares a where replace((to_char(a.idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='"+customerId+"' AND a.idproducto="+productCode;//+" AND UPPER(pr.nombre) like '%"+accountType+"%'";
		       List<Object[]>lista=null;
			   try {				     
		         	query=em.createNativeQuery(consulta);
		         	lista=query.getResultList();
		       	} catch (Exception e) {
		            e.printStackTrace();
				}finally {
					em.clear();
				}          
			     return query.getResultList();
		    }
		    
		  
		  public void cerrar() throws Throwable {
			  emf.close();
		} 
         }

