package com.fenoreste.rest.dao;

import com.fenoreste.rest.dto.siscoop.ProductsDTO;
import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.entidad.CuentasSiscoop;
import com.fenoreste.rest.modelos.entidad.Persona;
import com.fenoreste.rest.modelos.entidad.Productos;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class FacadeProductos<T> {
    private static EntityManagerFactory emf;
    public FacadeProductos(Class<T> entityClass) {
        emf = AbstractFacade.conexion();//Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);    
    }
    
    public List<ProductsDTO> obtenerProductos(){
        EntityManagerFactory emf = AbstractFacade.conexion();
        EntityManager em = emf.createEntityManager();
        List<CuentasSiscoop> lista= new ArrayList<>();        
        List<ProductsDTO>ListaReal=new ArrayList<ProductsDTO>();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<CuentasSiscoop> consulta = cb.createQuery(CuentasSiscoop.class);                 
            Root<CuentasSiscoop> cuentas = consulta.from(CuentasSiscoop.class);             
            lista = em.createQuery(consulta).getResultList(); 
            for(int i=0;i<lista.size();i++){
                Productos pr=em.find(Productos.class,lista.get(i).getIdproducto());
                ProductsDTO dto=new ProductsDTO(
                        lista.get(i).getIdproducto(),lista.get(i).getTipocuenta(),pr.getNombre(),"MXN");
                ListaReal.add(dto);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar CuentasSiscoop:"+e.getMessage());
            e.printStackTrace();
        }
        return ListaReal;
    }
    
    
    public void seleccionarPersonas() {
        EntityManagerFactory emf = AbstractFacade.conexion();
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Persona> consulta = cb.createQuery(Persona.class);
                 
            Root<Persona> personas = consulta.from(Persona.class);
             
            Predicate p1 = null, p2 = null;
            p1 = cb.equal(personas.get("nombre"), "PEDRO");
            p2 = cb.equal(personas.get("appaterno"), "BELTRAN");
            
            Predicate pAnd = cb.and(p1,p2);
            Predicate nombreApellidos = cb.or(pAnd);
            consulta.select(personas).where(nombreApellidos);
            List<Persona> lista = em.createQuery(consulta).getResultList();
            lista.forEach((p) -> {
                System.out.println(p.getNombre() + "," + p.getAppaterno());
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getProducts() {
        String s = "";
        EntityManager em = emf.createEntityManager();
        Query query = null;
        System.out.println("Accediendo a consulta...");
        String consulta = "select idproducto,(case when tipoproducto=2 then 'Prestamo' else 'Ahorro' end) as pr,nombre from productos";
        try {
            // s = new String(consulta.getBytes(),"ISO-8859-1");
            s = new String(consulta.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("si:" + s);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FacadeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Object[]> lista = null;
        try {
            query = em.createNativeQuery(s);
            lista = query.getResultList();
            System.out.println("Resultados obtenidos:" + lista.size());
        } catch (Exception e) {
            System.out.println("No pudo obtener datos");
        } finally {
            em.clear();
        }
        return query.getResultList();
    }

    public List<Object[]> getProductsRate(String customerId, int productCode, String accountType) {
        EntityManager em = emf.createEntityManager();
        Query query = null;
        String consulta = "select (select tasaio from productos pr where pr.idproducto=a.idproducto),"
                + "(select plazomax from productos pr where pr.idproducto=a.idproducto)as pm,"
                + "20.00 as smin,"
                + "(select pr.nombre from productos pr where pr.idproducto=a.idproducto)as nombre"
                + " from auxiliares a where replace((to_char(a.idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "' AND a.idproducto=" + productCode;//+" AND UPPER(pr.nombre) like '%"+accountType+"%'";
        List<Object[]> lista = null;
        try {
            query = em.createNativeQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.clear();
        }
        return query.getResultList();
    }

    public void cerrar(){
        emf.close();
    }
}
