package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.impl.AbstractFacade;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
@PreMatching
public class Intersecter implements ContainerRequestFilter {

	
	@Override	
	public void filter(ContainerRequestContext request) throws IOException {
    	System.out.println("Lllegando a intesersecter...");	
	  String url=request.getUriInfo().getAbsolutePath().toString();
	  System.out.println("url"+url);
              /*  if(url.contains("/api/auth")){
	    return;
	    }else{
	        System.out.println("Accedido correctamente");		 
                        EntityManagerFactory emd=AbstractFacade.conexion();
	        EntityManager em=emd.createEntityManager();
	        String sql="SELECT token from jwt where id=1";
	        Query query=em.createNativeQuery(sql);
	        String tokenDB=(String) query.getSingleResult();	
		    
	       String head=request.getHeaders().toString();
	       String token = request.getHeaderString("Authorization");
	       System.out.println("url:"+head);
	       String jwt=tokenDB;
			
	      if(head.contains("Authorization")) {			 
	                if(!token.equals(jwt)) {
	                 JsonObject json=Json.createObjectBuilder()
		 .add("mensaje", "Credenciales incorrectas").build();
	     request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
	                   }
                           return;
                      }else {
	               JsonObject json=Json.createObjectBuilder()
	               .add("mensaje", "Credenciales necesarias").build();
                        request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
                             return;
	       }
	   }*/
	}
             }