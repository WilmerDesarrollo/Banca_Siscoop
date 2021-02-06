package com.fenoreste.rest.services.impl;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.fenoreste.rest.dao.ProductsDAO;



@Path("/products")
public class ProductsServices {	  
       @POST
       @Produces({MediaType.APPLICATION_JSON})
       @Consumes({MediaType.APPLICATION_JSON})
        public Response getProducts(String cadena) throws Throwable { 
        JsonObjectBuilder ObjectBuilder=Json.createObjectBuilder();
        JsonArrayBuilder  arrayEsqueleto=Json.createArrayBuilder();
        JsonObject ArrayProductos=null;
        JsonObject Warnings=null;
        JsonObject JsonRegreso=null;   	
        JSONObject datosEntrada= new JSONObject(cadena);
        System.out.println("Accedio al metodo con:"+datosEntrada);
	           
        ProductsDAO pr=new ProductsDAO();
         try {
	String cadenaE=datosEntrada.getString("property");
	if(cadenaE.equals("productos")) {
	  List<Object[]>ListaProductos=pr.getProducts();
	  System.out.println("Fue a validar y se encontro:"+ListaProductos.size()+" registros");
	  if(ListaProductos.size()>0){
	  for(Object[] prod:ListaProductos) {
	       JsonObjectBuilder desc=Json.createObjectBuilder();
	       JsonObject DatosProd=desc
                                                                            .add("productCode",String.valueOf(prod[0].toString()))
				    .add("accountType",String.valueOf(prod[1].toString()))
				    .add("description",String.valueOf(prod[2].toString()))
				    .add("currencyCode","str").build();
                          arrayEsqueleto.add(DatosProd);
	   }
	        Warnings=ObjectBuilder.add("code","string")
	    		        .add("message","string").build();
                          ArrayProductos=ObjectBuilder.add("Products",arrayEsqueleto).build();
	        JsonRegreso=ObjectBuilder.add("warnings",Warnings)
                                                                          .add("",ArrayProductos).build();
                     }
	return Response.status(Response.Status.OK).entity(JsonRegreso.toString()).build();
                   } else {
	     JsonRegreso=ObjectBuilder.add("type","urn:vn:error-codes:"+cadena)
	                                                     .add("tittle","The requested object could not be found").build();
                  return Response.status(Response.Status.NOT_FOUND).entity(JsonRegreso.toString()).build();
	}
                }catch (Exception e) {
	   JsonRegreso=ObjectBuilder.add("type","urn:vn:error-codes")
	                                                   .add("title","validation failed on submitted data").build();
                  return Response.status(Response.Status.BAD_REQUEST).entity(JsonRegreso.toString()).build();
                 }finally {
	pr.cerrar();
	}
   }
	    
	    
        @POST
        @Path("/product/rate")
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})
        public Response getProductsRate(String cadena) throws Throwable { 
                  JsonObjectBuilder ObjectBuilder=Json.createObjectBuilder();
    	JsonObject DatosOK=null; 	
    	JsonObject JsonNotFound=null;
    	JsonObject JsonError=null;
    	JSONObject datosEntrada= new JSONObject(cadena);
    	ProductsDAO productosD=new ProductsDAO();
         try {
                  String customerId=datosEntrada.getString("customerId");
                  int productCode=datosEntrada.getInt("productCode");            
         	String acountType=datosEntrada.getString("accountType");         	
         	System.out.println("CustomerID:"+customerId);
         	System.out.println("ProductCode:"+productCode);
         	System.out.println("AcountType:"+acountType+"\n\n\n\n\n");
    	List<Object[]>ListaProductos=productosD.getProductsRate(customerId,productCode,acountType);
    		if(ListaProductos.size()>0){
		   for(Object[] prod:ListaProductos) {
		        JsonObjectBuilder desc=Json.createObjectBuilder();
		       DatosOK=desc
		                                 .add("warnings",ObjectBuilder.add("code","string")
			                                                                 .add("message","string").build())
			               .add("interestRate",prod[0].toString())
			               .add("maturityDate",prod[1].toString())
			               .add("minInitialDepositAmount",ObjectBuilder.add("amount",prod[2].toString())
                                                                                                                                                  .add("currecyCode","code").build())
                                                                     .add("property1", ObjectBuilder.add("description",prod[3].toString()).build())
		                                 .add("property2", ObjectBuilder.add("description",prod[3].toString()).build()).build();
				} 
                   return Response.status(Response.Status.OK).entity(DatosOK.toString()).build(); 
                  }else {
	           JsonNotFound= ObjectBuilder.add("type","urn:vn:error-codes:"+customerId+","+productCode+","+acountType)
		                                             .add("tittle","The requested object could not be found").build();
                   return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();	 
		   } 	
	}catch (Exception e) {
	           JsonError=ObjectBuilder.add("type","urn:vn:error-codes")
		                                   .add("title","validation failed on submitted data").build();
	return Response.status(Response.Status.BAD_REQUEST).entity(JsonError.toString()).build();
	}finally {
	productosD.cerrar();
	}
 }
	    
	    
	    
        
    
}
	
	
	

