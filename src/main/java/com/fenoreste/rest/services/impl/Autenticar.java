package com.fenoreste.rest.services.impl;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fenoreste.rest.util.JPAUtil;
import com.fenoreste.rest.dao.UsuarioDao;
import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Path("/auth")
public class Autenticar {
         public String hasha="";
         @POST
         @Produces(MediaType.APPLICATION_JSON)
         @Consumes(MediaType.APPLICATION_JSON)		   
         public Response validar(Usuario usuario) {
         boolean status =UsuarioDao.validar(usuario.getUsername(), usuario.getPassword());
         try {
                  if(status){
	   String HASH=Token().toString();
                   hasha=HASH;
	   JsonObject json=Json.createObjectBuilder()
	                                    .add("token",HASH).build();
                  return Response.ok(json.toString()).build();
	   }  
          } catch (Exception e) {
                         e.getMessage();
                         
          }
         //String HASH="AHGC-12BD-1328-75HF-HD64";
         JsonObject json=Json.createObjectBuilder()
	                          .add("Mensaje","Datos incorrectos").build();	   
                   return Response.status(Response.Status.UNAUTHORIZED).entity(json.toString()).build();
		   
        }
     
         
         
         /*****************************    Creando el token que se regresa y almacena en la base de datos en la tabla jwt"     *******************************/
          EntityManagerFactory emd=AbstractFacade.conexion();
          EntityManager em=emd.createEntityManager();
          public String Token() {
	       String key="mi_clave";
		   long time=System.currentTimeMillis();
		   String jwt=Jwts.builder()
			          .signWith(SignatureAlgorithm.HS256,key)
			          .setSubject("www.fenoreste.com")
			          //.setIssuedAt(new Date(time))
			          //.setExpiration(new Date(time+120000))
			          .claim("email","programacion.fenoreste@gmail.com")
			          .compact();
		   String tokenizer="";  
		   try {
			   EntityTransaction et1 = em.getTransaction();	
			   if(eliminar()==true) {
				   tokenizer=jwt;
				   et1.begin();
				   Query sql=em.createNativeQuery("INSERT INTO jwt values(?,?)");
				   sql.setParameter(1,1);
				   sql.setParameter(2, tokenizer);
				   sql.executeUpdate();
				   et1.commit();
				   
			   }else {
				   tokenizer="Error: Contacte a su proveedor,su token no puedo ser actualizado";
			   }
			   System.out.println("Token Generado e insertado correctamente");
		} catch (Exception e) {
			   System.out.println("Error al generar token");
		}
	return tokenizer;
	}
	
	
           public boolean eliminar() {
	boolean bandera=false;
	try {
	     EntityTransaction et = em.getTransaction();
	     et.begin();
	     em.createNativeQuery("DELETE FROM jwt where id=1;").executeUpdate();
	     et.commit();
	     bandera=true;
	} catch (Exception e) {
	    e.getMessage();
	}
	return bandera;
	}
}