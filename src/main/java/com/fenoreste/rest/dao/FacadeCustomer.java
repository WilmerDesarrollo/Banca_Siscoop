package com.fenoreste.rest.dao;

import com.fenoreste.rest.dto.siscoop.CustomerContactDetails;
import com.fenoreste.rest.dto.siscoop.CustomerDetailsDTO;
import com.fenoreste.rest.dto.siscoop.CustomerSearchDTO;
import com.fenoreste.rest.dto.siscoop.CustomerAccountDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.entidad.Auxiliares;
import com.fenoreste.rest.modelos.entidad.AuxiliaresPK;
import com.fenoreste.rest.modelos.entidad.Persona;
import com.fenoreste.rest.modelos.entidad.PersonasPK;
import com.fenoreste.rest.modelos.entidad.tiposCuentaSiscoop;
import java.util.ArrayList;

public abstract class FacadeCustomer<T> {

    private static EntityManagerFactory emf;
    //private static final String PERSISTENCE_UNIT_NAME="conexion";
    List<Object[]> lista = null;

    public FacadeCustomer(Class<T> entityClass) {
        emf = AbstractFacade.conexion();
    }

    public List<CustomerSearchDTO> search(String ogs) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Lllego a buscar");
        List<CustomerSearchDTO> lista = new ArrayList<>();
        CustomerSearchDTO client = null;
        try {
            String consulta = "SELECT replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','') as ogs,"
                    + "nombre||' '||appaterno||' '||apmaterno as name,"
                    + "CASE WHEN curp !=' ' then curp ELSE 'SIN REGISTROS' END as taxId,"
                    + "CASE WHEN to_char(fechanacimiento,'YYYY/MM/dd')::::text != ' ' THEN to_char(fechanacimiento,'dd-MM-YYYY')::::text ELSE 'SIN REGISTROS' END  as birthDate,"
                    + "(CASE WHEN TRIM(razon_social) IS NOT NULL AND TRIM(razon_social) <> '' THEN 'grupal' ELSE 'individual' end) as customerType"
                    + " from personas p WHERE replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','')='" + ogs + "'";
            System.out.println("aun");

            System.out.println("en el try");
            Query query = em.createNativeQuery(consulta);
            List<Object[]> ListaObjetos = query.getResultList();
            System.out.println("Salio");

            List<Object[]> SocioEncontrados = query.getResultList();

            if (SocioEncontrados.size() > 0) {
                for (Object[] objetos : SocioEncontrados) {
                    client = new CustomerSearchDTO(objetos[0].toString(), objetos[1].toString(), objetos[2].toString(), objetos[3].toString(), objetos[4].toString());
                    lista.add(client);
                }
            }

            System.out.println("Socios:" + lista);
            return lista;
        } catch (Exception e) {
            System.out.println("Error al buscar cliente:" + e.getMessage());
            cerrar();
        }

        return null;
    }

    public CustomerDetailsDTO details(String customerId) {
        EntityManager em = emf.createEntityManager();
        Query query = null;
        List<Object[]> ListaObjetos = null;
        String consulta = "SELECT p.nombre||' '||p.appaterno||' '||p.apmaterno as name,"
                + "(CASE WHEN TRIM(razon_social) IS NOT NULL AND TRIM(razon_social) <> '' THEN 'grupal' ELSE 'individual' END) as customerType"
                + " FROM personas p WHERE replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','')='" + customerId + "'";
        CustomerDetailsDTO cliente = new CustomerDetailsDTO();
        try {
            query = em.createNativeQuery(consulta);
            ListaObjetos = query.getResultList();
            for (Object[] objetos : ListaObjetos) {
                cliente = new CustomerDetailsDTO(customerId, objetos[0].toString(), objetos[1].toString());
            }
            System.out.println("Socio:" + cliente);
        } catch (Exception e) {
            System.out.println("Error al obtener detalles del socio:" + e.getMessage());
            cerrar();
        }
        return cliente;
    }

    public List<CustomerContactDetails> ContactDetails(String customerId) {
        EntityManager em = emf.createEntityManager();
        Query query = null;
        List<Object[]> ListaObjetos = null;
        String consulta = "SELECT CASE WHEN p.telefono != '' THEN p.telefono ELSE '0' END as phone,"
                + " CASE WHEN p.celular != '' THEN p.celular ELSE '0' END as cellphone,"
                + " CASE WHEN p.email != '' THEN  p.email ELSE '0' END as email FROM personas p WHERE replace(to_char(p.idorigen,'099999')||to_char(p.idgrupo,'09')||to_char(p.idsocio,'099999'),' ','')='" + customerId + "'";
        CustomerContactDetails contactsPhone = new CustomerContactDetails();
        CustomerContactDetails contactsCellphone = new CustomerContactDetails();
        CustomerContactDetails contactsEmail = new CustomerContactDetails();
        List<CustomerContactDetails> ListaContactos = new ArrayList<>();
        try {
            query = em.createNativeQuery(consulta);
            ListaObjetos = query.getResultList();
            System.out.println("Size:"+ListaObjetos.size());
            for (Object[] obj : ListaObjetos) {
                System.out.println("Ok:"+obj[0].toString());
                if (!obj[0].toString().equals("0")) {
                    System.out.println("Entro");
                    contactsPhone.setCustomerContactId(customerId);
                    contactsPhone.setCustomerContactType("phone");
                    contactsPhone.setPhoneNumber(obj[0].toString());
                    ListaContactos.add(contactsPhone);
                    
                }
                if(!obj[1].toString().equals("0")){
                   contactsCellphone.setCustomerContactId(customerId);
                    contactsCellphone.setCustomerContactType("cellphone");
                    contactsCellphone.setCellphoneNumber(obj[1].toString());
                    ListaContactos.add(contactsCellphone);  
                }
                if(!obj[2].toString().equals("0")){
                    contactsEmail.setCustomerContactId(customerId);
                    contactsEmail.setCustomerContactType("email");
                    contactsEmail.setEmail(obj[2].toString());
                    ListaContactos.add(contactsEmail);  
                }
                
            }
            System.out.println("ListaContactos:"+ListaContactos);

            cerrar();
        } catch (Exception e) {
            System.out.println("Error al obtener detalles del socio:" + e.getMessage());
            cerrar();
        }
        
        return ListaContactos;
    }

    public List<CustomerAccountDTO> Accounts(String customerId) {
        EntityManager em = emf.createEntityManager();
        Query query = null;
        List<CustomerAccountDTO>ListaProductos=new ArrayList<>();
        String consulta = "SELECT * FROM auxiliares a WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "'";
        CustomerAccountDTO producto=new CustomerAccountDTO();
        try {
            
            query = em.createNativeQuery(consulta,Auxiliares.class);
            List<Auxiliares>ListaProd= query.getResultList();
            String status="";
            String accountType = "";
            Object[]arr ={};
            Object[]arr1 ={"relationCode","SOW"};
            List<CustomerAccountDTO> listaDeCuentas=new ArrayList<>();
            Query query1=em.createNativeQuery("SELECT dato2 FROM tablas WHERE idtabla='siscoop_banca_movil' AND idelemento='tipo_cuenta_ahorro'");
            String ahorros=(String)query1.getSingleResult();
            String[]arrAhorros=ahorros.split(",");
            
            Query query2=em.createNativeQuery("SELECT dato2 FROM tablas WHERE idtabla='siscoop_banca_movil' AND idelemento='tipo_cuenta_prestamo'");
            String prestamos=(String)query1.getSingleResult();
            String[]arrPrestamos=prestamos.split(",");
            
            
            
           int idproducto=0;
           int prod=0;
            for(int k=0;k<1;k++){
            for(int i=0;i<ListaProd.size();i++){
                Auxiliares a=ListaProd.get(i);            
                System.out.println("IdproductoA:"+a.getAuxiliaresPK().getIdproducto());
                try {
                    tiposCuentaSiscoop tp=em.find(tiposCuentaSiscoop.class, a.getAuxiliaresPK().getIdproducto());
                accountType=String.valueOf(tp.getTipocuenta());
                } catch (Exception e) {
                }
                
                
                
                if(a.getEstatus()==2){
                    status="OPEN";
                }else if(a.getEstatus()==3){
                    status="CLOSED";
                }else{
                    status="INACTIVE";
                }
                producto=new CustomerAccountDTO(
                customerId,a.getAuxiliaresPK().getIdorigenp()+""+a.getAuxiliaresPK().getIdproducto()+""+a.getAuxiliaresPK().getIdauxiliar(),
                           a.getAuxiliaresPK().getIdorigenp()+""+a.getAuxiliaresPK().getIdproducto()+""+a.getAuxiliaresPK().getIdauxiliar(),
                           accountType,"","",status,arr,arr1);
               listaDeCuentas.add(producto);
               accountType="";
            } 
            }
            System.out.println("Lista de cuentas:"+listaDeCuentas);
            return listaDeCuentas;
            
        } catch (Exception e) {

            System.out.println("Error al obtener cuentas:" + e.getMessage());
        } finally {
            em.clear();
        }
        return null;
    }

    public List<Object[]> getTemplates(String customerId) {
        EntityManager em = emf.createEntityManager();

        return null;
    }

    public List<Object[]> countries() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM paises");
        List<Object[]> lista = query.getResultList();
        return lista;
    }

    public void cerrar() {
        emf.close();
    }

    public Persona detailss(String customerId) {

        System.out.println("Alderson22");

        System.out.println("Lllego:" + customerId);
        EntityManager em = emf.createEntityManager();
        System.out.println("dfdsfdsf");

        System.out.println("sdfsdf");
        try {
            PersonasPK pk = new PersonasPK(30298, 25, 75681);

            System.out.println("czczczxc");
            Persona persona = em.find(Persona.class, pk);
            System.out.println("Nombre:" + persona);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        /*    
                try {
               
	    	     System.out.println("Nombre:"+yo.getNombre());
                            
	    	    System.out.println("total de objetos:"+lista.size());
                } catch (Exception e) {
	   System.out.println("Error obtenido:"+e.getMessage());
                }finally {
		     	
                }*/
        return null;
    }

}
