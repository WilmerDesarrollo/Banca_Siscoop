
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.fenoreste.rest.modelos.Persona;
import com.fenoreste.rest.modelos.PersonasPK;
import com.fenoreste.rest.modelos.cuentas_banca_movil;
import com.fenoreste.rest.util.JPAUtil;

public class pruebas {
	
	
	Persona pe=new Persona();	
	//huellas h=new huellas();
	public static void main (String args[]) {
		PersonasPK pk= new PersonasPK( 30298,25,75681);		
		EntityManagerFactory emf=new JPAUtil().getEntityManagerFactory("192.168.99.200","nuevomexico31dic20_movimientos");
		EntityManager em=emf.createEntityManager();
                cuentas_banca_movil cu=em.find(cuentas_banca_movil.class,29232);
                System.out.println("cu:"+cu.getAccountnumber());
                
                emf.close();
		/*Persona personas=em.find(Persona.class,pk);
		System.out.println("Nombre:"+personas.getNombre());
		*/
	
	}
}
