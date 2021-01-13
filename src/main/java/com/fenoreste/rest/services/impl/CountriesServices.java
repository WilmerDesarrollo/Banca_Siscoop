/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.dao.CustomerDAO;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author wilmer
 */
@Path("/countries")
public class CountriesServices {
    
    
         @GET
         @Produces({MediaType.APPLICATION_JSON})
         @Consumes({MediaType.APPLICATION_JSON})
    
         public Response getCountries(){   
                     CustomerDAO datos=new CustomerDAO();
                     JsonObject datosB=null;
                     JsonObject datosOK=null;
                     JsonObjectBuilder ObjectBuilder=Json.createObjectBuilder();
                     JsonArrayBuilder  arrayEsqueleto=Json.createArrayBuilder();
                     JsonArrayBuilder  paises=Json.createArrayBuilder();
                     JsonObject ArrayPaises=null;
                     List<Object[]>lista=datos.countries();
             
          try{
              
                   
             for(Object[]obj:lista){
                datosB=Json.createObjectBuilder()
                                       .add("code",obj[0].toString())
                                       .add("name",obj[1].toString())
                        .build();      
                arrayEsqueleto.add(datosB);
            }
                ArrayPaises=ObjectBuilder.add("Clientes",arrayEsqueleto).build();
                datosOK=ObjectBuilder
                                                                  .add("totalRecords",lista.size())
                                                                  .add("",ArrayPaises).build();
           
          }catch(Exception e){
              System.out.println("Error al buscar paises:"+e.getMessage());
          }
              
            return Response.status(Response.Status.OK).entity(datosOK.toString()).build();
                     
         }
           
           
}
