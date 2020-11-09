package com.fenoreste.rest.services.impl;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fenoreste.rest.dao.UsuarioDao;
import com.fenoreste.rest.modelos.Usuario;

@Path("/pruebas")
public class prueba {
	
	public String hasha="";
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)		   
	   public Response validar() {
	  
		      String HASH="order by jackson";//Token().toString();
		      hasha=HASH;
		      JsonObject json=Json.createObjectBuilder()
				               .add("token",HASH)
				               .build();
		        
	   return Response.status(Response.Status.ACCEPTED)
			  	.entity(json.toString()).build();
	   
               }
     }