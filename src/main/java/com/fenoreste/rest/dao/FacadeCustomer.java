package com.fenoreste.rest.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.fenoreste.rest.impl.AbstractFacade;

public abstract class FacadeCustomer<T>{
	  private static EntityManagerFactory emf;
	  //private static final String PERSISTENCE_UNIT_NAME="conexion";
	  List<Object[]>lista=null;
	  public FacadeCustomer(Class<T> entityClass) {
		    emf =  AbstractFacade.conexion();
	  }
          
  
                   public List<Object[]>search(String nombre){
                       EntityManager em=emf.createEntityManager();
                       
                       System.out.println("Lllego a buscar");
                       String consulta="SELECT replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','') as ogs,nombre||' '||appaterno||' '||apmaterno as nombrec,"
                              + "(case  when p.idgrupo=1  then 'Socio Contable'"
              	            + "when p.idgrupo=60 then 'Aval no socio'"
             	            + "when p.idgrupo=61 then 'Conyuge no socio'"
              	            + "when p.idgrupo=62 then 'Tutor no socio'"
              	            + "when p.idgrupo=63 then 'Beneficiario no socio'"
              	            + "when p.idgrupo=64 then 'Referencia personal no socio'"
              	            + "when p.idgrupo=8  then 'Prospecto a socio'"
              	            + "when p.idgrupo=9  then 'Aspirante'"
              	            + "when p.idgrupo=10 then 'Socio'"
              	            + "when p.idgrupo=40 then 'Socio castigado'"
              	            + "when p.idgrupo=41 then 'Socio dado de baja'"
              	            + "when p.idgrupo=42 then 'Aspirante dado de baja'"
              	            + "when p.idgrupo=43 then 'Prospecto dado de baja'"
              	            + "when p.idgrupo=44 then 'Menor dado de baja'"
              	            + "when p.idgrupo=45 then 'Ex-socio'"
              	            + "when p.idgrupo=46 then 'Ex-aspirante'"
              	            + "when p.idgrupo=47 then 'Ex-ahorrador menor'"
              	            + "when p.idgrupo=20 then 'Ahorrador menor'"
              	            + "when p.idgrupo=81 then 'Empleados'"
              	            + "when p.idgrupo=48 then 'Recursos no socio'"
              	            + "when p.idgrupo=88 then 'Personas bloqueadas cnbv'"
              	            + "when p.idgrupo=66 then 'Proveedores,personas fisicas'"
              	            + "when p.idgrupo=67 then 'Proveedores,personas morales'"
              	            + "when p.idgrupo=49 then 'Propietario real no socios'"
              	            + "when p.idgrupo=65 then 'Usuarios remesas' end) as tps from personas p WHERE nombre||' '||appaterno||' '||apmaterno LIKE '%"+nombre.toUpperCase()+"%'" ;
                       System.out.println("aun");
                       try {
                             System.out.println("en el try");
                           Query query=em.createNativeQuery(consulta);
                           lista=query.getResultList();
                           System.out.println("Salio:"+lista.size());
                       } catch (Exception e) {
                           System.out.println("Error al buscar cliente:"+e.getMessage());
                       }
                       System.out.println("lista");
                       return lista;
                   }
          
	  public List<Object[]>details(String customerId){
		 EntityManager em=emf.createEntityManager();
		 Query query=null; 
		 List<Object[]>lista=null;
                                     String consulta="SELECT replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','') as ogs,"
	            + "p.nombre||' '||p.appaterno||' '||p.apmaterno as nomb,"
                              + "p.curp,"
                              + "(case  when p.idgrupo=1  then 'Socio Contable'"
              	            + "when p.idgrupo=60 then 'Aval no socio'"
             	            + "when p.idgrupo=61 then 'Conyuge no socio'"
              	            + "when p.idgrupo=62 then 'Tutor no socio'"
              	            + "when p.idgrupo=63 then 'Beneficiario no socio'"
              	            + "when p.idgrupo=64 then 'Referencia personal no socio'"
              	            + "when p.idgrupo=8  then 'Prospecto a socio'"
              	            + "when p.idgrupo=9  then 'Aspirante'"
              	            + "when p.idgrupo=10 then 'Socio'"
              	            + "when p.idgrupo=40 then 'Socio castigado'"
              	            + "when p.idgrupo=41 then 'Socio dado de baja'"
              	            + "when p.idgrupo=42 then 'Aspirante dado de baja'"
              	            + "when p.idgrupo=43 then 'Prospecto dado de baja'"
              	            + "when p.idgrupo=44 then 'Menor dado de baja'"
              	            + "when p.idgrupo=45 then 'Ex-socio'"
              	            + "when p.idgrupo=46 then 'Ex-aspirante'"
              	            + "when p.idgrupo=47 then 'Ex-ahorrador menor'"
              	            + "when p.idgrupo=20 then 'Ahorrador menor'"
              	            + "when p.idgrupo=81 then 'Empleados'"
              	            + "when p.idgrupo=48 then 'Recursos no socio'"
              	            + "when p.idgrupo=88 then 'Personas bloqueadas cnbv'"
              	            + "when p.idgrupo=66 then 'Proveedores,personas fisicas'"
              	            + "when p.idgrupo=67 then 'Proveedores,personas morales'"
              	            + "when p.idgrupo=49 then 'Propietario real no socios'"
              	            + "when p.idgrupo=65 then 'Usuarios remesas' end) as tps," 
                      + "case when p.email='' then 'sin email' else p.email end,"
                      + "p.celular,"
                      + "(select (case when sc.estatusvivienda=0 then 'Sin datos' "
                      	 + "else (select descripcion from catalogo_menus cm where cm.menu='estatusvivienda' and cm.opcion=sc.estatusvivienda) end)"
                      + "from socioeconomicos sc where sc.idorigen=p.idorigen and sc.idgrupo=p.idgrupo and sc.idsocio=p.idsocio) "
                      + "as vivienda,"
                      + "(select codigopostal from colonias c where c.idcolonia=p.idcolonia) as cp,"
		              + "(select e.nombre from estados e inner  join municipios mn using(idestado) inner join colonias c using(idmunicipio) where c.idcolonia=p.idcolonia) as estado,"
		              + "p.numeroext from personas p where replace((to_char(p.idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='"+customerId+"'";
              
                try {
                query=em.createNativeQuery(consulta);
	    	    lista=query.getResultList();
	    	    System.out.println("total de objetos:"+lista.size());
                } catch (Exception e) {
	   System.out.println("Error obtenido:"+e.getMessage());
                }finally {
		     	em.clear();
                }           
                return lista;
         }
	  
	  
	  
         public List<Object[]>getAccounts(String customerId){
	 EntityManager em=emf.createEntityManager();
	 Query query=null; 
	 List<Object[]>lista=null;
	 String consulta="SELECT bm.*,(SELECT accounttype FROM aux_validaciones_cuenta WHERE validationid=bm.validationid)  FROM cuentas_banca_movil bm WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='"+customerId+"'";
	 try {
	      query=em.createNativeQuery(consulta);
	                   lista=query.getResultList();
		 System.out.println("total de objetos:"+lista.size());
	  } catch (Exception e) {
	                   e.printStackTrace();
		 System.out.println("Error al obtener cuentas:"+e.getMessage());
	   }finally {
	     	em.clear();
	   }           
	   return lista;
        }
         
         public List<Object[]>getTemplates(String customerId){
                   EntityManager em=emf.createEntityManager();
                   
             
             
             
             
             
             
             
          return null;
         }
         
         public List<Object[]>countries(){
          EntityManager em=emf.createEntityManager();
          Query query=em.createNativeQuery("SELECT * FROM paises");
          List<Object[]>lista=query.getResultList();
           return lista;
         }
         
	
	  
        public void finalize() throws Throwable {
		  emf.close();
		  super.finalize(); 
          } 
          
          

}
