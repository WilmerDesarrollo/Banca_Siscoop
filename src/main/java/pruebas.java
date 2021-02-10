
import com.fenoreste.rest.dao.ProductsDAO;
import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.entidad.Paises;
import com.fenoreste.rest.modelos.entidad.Paises_Siscoop;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.fenoreste.rest.modelos.entidad.Persona;
import com.fenoreste.rest.modelos.entidad.PersonasPK;
import com.fenoreste.rest.modelos.entidad.cuentas_banca_movil;
import com.fenoreste.rest.modelos.entidad.CuentasSiscoop;
import javax.persistence.Persistence;

public class pruebas {
	
	
	Persona pe=new Persona();	
	//huellas h=new huellas();
	public static void main (String args[]) {
		//PersonasPK pk= new PersonasPK( 30298,25,75681);		
		EntityManagerFactory emf=AbstractFacade.conexion();
		EntityManager em=emf.createEntityManager();
                
                Paises_Siscoop pais=new Paises_Siscoop();
                pais=em.find(Paises_Siscoop.class,1);
                System.out.println("Pais:"+pais);
                
                String consulta="SELECT * FROM tablas WHERE idtabla='siscoop' AND idelemento=''banca_movil' AND dato1='prestamos'";
                String consulta1="SELECT dato2 from tablas WHERE idtabla='siscoop_banca_movil' AND idelemento='tipos_cuenta' AND dato1='prestamos'";
                /*Query query=em.createNativeQuery(consulta1);
                String cadena=(String)query.getSingleResult();
                System.out.println("Cadena:"+cadena);
                
                String[] textElements = cadena.split(",");
                System.out.println("TextElements:"+textElements[0]);
                for(int i=0; i<textElements.length; i++) {
                System.out.println("Element "+i+":"+textElements[i]); 
                }
                
                */
                ProductsDAO datos=new ProductsDAO();
                datos.seleccionarPersonas();
                emf.close();
	
	} 
}
