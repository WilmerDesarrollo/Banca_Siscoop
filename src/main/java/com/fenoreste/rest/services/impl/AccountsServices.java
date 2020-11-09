package com.fenoreste.rest.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.fenoreste.rest.dao.AccountsDAO;
import com.google.gson.JsonArray;

@Path("/account")
public class AccountsServices {
	
/**********************Metodo para validar que exista el registro en la base de datos****************************/
	
    @POST
    @Path("/create/validate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getValidation(String cadena) throws Throwable { 

                  JsonObjectBuilder ObjectBuilder=Json.createObjectBuilder();
 	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;

        AccountsDAO aco=new AccountsDAO();
 
          try {
 	       JSONObject datosEntrada= new JSONObject(cadena);
 	       String customerId=datosEntrada.getString("customerId");
 	       int productCode=datosEntrada.getInt("productCode");
 	       String  accountType=datosEntrada.getString("accountType");
 	       System.out.println("customerID:"+customerId+"\n"
 				    	  +"productCode:"+productCode+"\n"
 				    	  +"accountType:"+accountType); 
 	       List<Object[]>lista_objetos=aco.getValidaton(customerId, productCode, accountType);
 	      System.out.println("listaaaaaaaaaaaaaaa:"+lista_objetos);
 	      if(lista_objetos!=null){
 	         JsonObjectBuilder desc=Json.createObjectBuilder();
                           for(Object[]convertidos:lista_objetos) {
 	         DatosOK=desc
    		                 .add("warnings",ObjectBuilder
    			                                                  .add("code","string")
                                                                                                        .add("message","string").build())
    				                                .add("validationId", convertidos[0].toString())
    				                                .add("fees",ObjectBuilder.add("feeCode",convertidos[1].toString())
   		                                                                    .add("amount",Json.createObjectBuilder()
   		                                		              .add("amount",convertidos[2].toString())
   		                                		              .add("currencyCode",convertidos[3].toString()).build())   		                                                                  
                                                                                                        .add("description",convertidos[4].toString()).build())
    		                  .add("executionDate",convertidos[5].toString())
    			.add("property1",ObjectBuilder.add("Validacion","Exitosa.").build())
    			.build();
    		  
    	         return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();  	
 	        }   
 	    }else{ 	    	  
 	        JsonNotFound=Json.createObjectBuilder()
 	                         		                                .add("type", "urn:vn:error-codes:VAL00003")
 	                                                                                      .add("title","The requested object could not be found").build();
 	        return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();
 	       }	
 	    
	    }catch (Exception e) {
	        JsonError=ObjectBuilder.add("type","urn:vn:error-codes")
	          	                                .add("title","validation failed on submitted data").build();		
	    }finally {
	      aco.cerrar();	    	
	   }
     	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();
	 
       }
	
    
/****************************************************Metodo para crear cuenta trae datos validados en el metodo anterior********************************************************/    
    @POST
    @Path("/create/execute")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response ponerCuentas(String cadena) throws Throwable {
        System.out.println("llegando");
                 AccountsDAO aco=new AccountsDAO();
        System.out.println("paso");
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;

     try {
                 JSONObject jsonEntrada=new JSONObject(cadena);
                 System.out.println("Json de entrada:"+jsonEntrada.getInt("validationId"));
                 int validationIdE=jsonEntrada.getInt("validationId");
                 // int originatorReferencedIdE=jsonEntrada.getInt("originatorReferencedId");
                System.out.println("validationid="+validationIdE);//+"\n originatorReferencedId:"+originatorReferencedIdE);
                 List<Object[]>cuentas=aco.setAccount(validationIdE);
                 String acountId="";
                 if(cuentas!=null) {
    	
        	for(Object[] cu:cuentas){
        		JsonObjectBuilder desc=Json.createObjectBuilder();
        		DatosOK=desc
	     		         .add("warnings",Json.createObjectBuilder()
	    		         .add("code","string")
	                                             .add("message","Cuenta creada con exito").build())
	    		         .add("status",cu[6].toString())
	    		         .add("executionId",cu[5].toString())
	    		         .add("accountId",cu[2].toString())
	    		         .add("accountNumber",cu[3].toString())
	    		         .add("displayAccountNumber",cu[4].toString())
	    		         .build();
        	acountId=String.valueOf(cu[2]);
                }
                 
        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
                 }else {
        	JsonNotFound=Json.createObjectBuilder()
        		                .add("type", "urn:vn:error-codes:VAL00003")
        	                                  .add("title","The account are registered")
                                                    .add("accountId",acountId).build();
        	return Response.status(Response.Status.CONFLICT).entity(JsonNotFound.toString()).build();

    	}
         } catch (Exception e) {
                   JsonError=Json.createObjectBuilder()
		        .add("type"    , "urn:vn:error-codes:SE00002")
		        .add("title"   , "Unexpected error")
		        .add("detail"  , e.getMessage()+" invalid param")
		        .build();
                  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();
        } finally {
    	 aco.cerrar();
     }      
 } 
	
	
/*************************Metodo para ver detalles de la cuenta************************************************/
    @POST
    @Path("/details")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response detailsAccount(String cadena) throws Throwable {
	AccountsDAO aco=new AccountsDAO();
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;

     try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
	    long accountIdE=jsonEntrada.getLong("accountId");
	    List<Object[]>detalles_cuenta=aco.detailsAccount(accountIdE);
       if(detalles_cuenta.size()>0) {
        	for(Object[] de_cu:detalles_cuenta){
        		JsonObjectBuilder desc=Json.createObjectBuilder();
        		 DatosOK=desc
	    	                            .add("warnings",Json.createObjectBuilder()
	    		                                             .add("code","string")
	                                                                                 .add("message","string").build())
	    		          .add("accountDetails",Json.createObjectBuilder()
	    			                                     .add("accountId",de_cu[0].toString())
                                           		           	                   .add("accountNumber",de_cu[1].toString())
	    				                   .add("displayAccountNumber",de_cu[2].toString())
	    				                   .add("accountType",de_cu[3].toString())
	    				                   .add("currencyCode",de_cu[4].toString())
	    				                   .add("status",de_cu[5].toString()).build())
	    		          .build();
        	}
        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
        }else {
        	JsonNotFound=Json.createObjectBuilder()
        		                 .add("type", "urn:vn:error-codes:VAL00003")
        	                                   .add("title","The requested object could not be found").build();
        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();
        }
    } catch (Exception e) {
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:SE00002")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , e.getMessage()+" invalid param")
				        .build();
                   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

     }
     finally {
    	 aco.cerrar();
     }      
  } 
	
	
	
/*************************AcountHistory************************************************************************/
	
	
	
	
	
	
	
/********************************Hold(Propietario de la cuenta) Pendiente**************************************/	
     @POST
     @Path("/hold")
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})
    public Response holdAccount(String cadena) throws Throwable {
	AccountsDAO aco=new AccountsDAO();
	
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
	      
           try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
                 long accountIdE=jsonEntrada.getLong("accountId");
                 String holders=aco.getHolders(accountIdE);
		    
		    
	 if(holders.equals("")) {
	        
                             JsonObjectBuilder desc=Json.createObjectBuilder();
	           DatosOK=desc
		  	  .add("warnings",Json.createObjectBuilder()
		    	                                     .add("code","string")
		                                                       .add("message","string").build())
		    	  .add("hold",Json.createObjectBuilder()
		    	                             .add("name","")
		    	                             .add("relationCode","string").build())
		                     .build();
	        	
	        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
	        }else {
	        	JsonNotFound=Json.createObjectBuilder()
	        		                .add("type", "urn:vn:error-codes:VAL00003")
	        	                                  .add("title","The requested object could not be found")
	        	                                  .build();
	        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();

	         }
	    } catch (Exception e) {
			  JsonError=Json.createObjectBuilder()
					        .add("type"    , "urn:vn:error-codes:VAL00001")
					        .add("title"   , "Unexpected error")
					        .add("detail"  , e.getMessage()+" invalid param")
					        .build();
			  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

	   }
	   finally {
	      	 aco.cerrar();
	     }      
    } 
		
	
	
/**********************************Holders(Titulares de la cuenta)*********************************************/
		

    @POST
    @Path("/holders")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response holderAccount(String cadena) throws Throwable {

                  AccountsDAO aco=new AccountsDAO();
	
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
      
     try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
	long accountIdE=jsonEntrada.getLong("accountId");
	String holders=aco.getHolders(accountIdE);
	    
	  
       if(!holders.equals("")) {
        		JsonObjectBuilder desc=Json.createObjectBuilder();
        		
        		 DatosOK=desc
	                                             .add("warnings",Json.createObjectBuilder()
	    		                                            .add("code","string")
	                                                                                .add("message","string").build())
	                                            .add("holders",Json.createArrayBuilder()
	    		                                        .add(Json.createObjectBuilder()
	    			                      .add("name",holders)
	    			                      .add("relationCode","string").build())
                                                              .build())
	    				  .add("property1","")
	    				  .add("property2","")
	    				  .build();
        	
        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
        }else {
        	JsonNotFound=Json.createObjectBuilder()
        			         .add("type", "urn:vn:error-codes:VAL00003")
        	                 .add("title","The requested object could not be found")
        	                 .build();
        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();

    	}
	    } catch (Exception e) {
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , e.getMessage()+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

		}
     finally {
    	 aco.cerrar();
     }      
	} 
	
   
	
	
/*************************************************Validate Internal Account*************************************/
	
	
	@POST
	@Path("/internal/validate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response internalValidateAccount(String cadena) throws Throwable {
	AccountsDAO aco=new AccountsDAO();
	
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
      
     try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
	    Long accountNumber=jsonEntrada.getLong("accountNumber");
	    String accountType=jsonEntrada.getString("accountType");
	    List<Object[]>lista_de_objetos=aco.getValidateInternalAccount(accountNumber, accountType);
	    
	    
       if(lista_de_objetos.size()>0) {
        	for(Object[] objetos:lista_de_objetos){
        		JsonObjectBuilder desc=Json.createObjectBuilder();
        		 DatosOK=desc
	    				  .add("warnings",Json.createObjectBuilder()
	    						     .add("code","string")
	                                 .add("message","string").build())
	    				  .add("accountId", objetos[0].toString())
	    				  .add("accountType",objetos[1].toString())	    				 
	    				  .add("holders",Json.createArrayBuilder()
				                    .add(Json.createObjectBuilder()
				                    		 .add("name",objetos[2].toString())
				                    		 .add("relationCode","string")
				                    		 .build())
		                            .build())
	    				  .add("displayAccountNumber",objetos[3].toString())
	    				  .build();
        	}
        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
        }else {
        	JsonNotFound=Json.createObjectBuilder()
        			         .add("type", "urn:vn:error-codes:VAL00003")
        	                 .add("title","The requested object could not be found")
        	                 .build();
        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();

    	}
	    } catch (Exception e) {
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , e.getMessage()+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

		}
        finally {
      	 aco.cerrar();
     }      
	} 
	
	
/****************************************validate change account status*****************************************************/
	
	@POST
	@Path("/status/change/validate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response validateChangeStatus(String cadena) throws Throwable {
    AccountsDAO aco=new AccountsDAO();
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
     try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
	    long accountId=jsonEntrada.getLong("accountId");
	    int statusCode=jsonEntrada.getInt("statusCode");
	    String reason=jsonEntrada.getString("reason");
	    
	    List<Object[]>objetos_validados=aco.getValidateChangeStatus(accountId, statusCode, reason);

       if(objetos_validados.size()>0){
          for( Object[] validados:objetos_validados) {
    	   JsonObjectBuilder desc=Json.createObjectBuilder();
           DatosOK=desc
	    			  .add("warnings",Json.createObjectBuilder()
	    						     .add("code","string")
	                                 .add("message","string").build())
	    			  .add("validationId",validados[0].toString())
	    			  .add("fees",Json.createObjectBuilder()
	    					          .add("feeCode",validados[1].toString())
	    					          .add("amount",Json.createObjectBuilder()
	    					        		             .add("amount",validados[2].toString())
	    					        		             .add("currencyCode",validados[3].toString())
	    					        		             .build())
	    					          .add("description","string")
	    					          .build())
	    			  .add("effectiveDate","09/07/2020")
	    			  .build();
        	
        	
           }
          return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
        }else {
        	JsonNotFound=Json.createObjectBuilder()
        			         .add("type", "urn:vn:error-codes:VAL00003")
        	                 .add("title","The requested object could not be found")
        	                 .build();
        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();

    	}
	    } catch (Exception e) {
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , e.getMessage()+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

		}
        finally {
      	 aco.cerrar();
     }      
	} 
	
	
	
/*****************************************************change status*********************************************************/
	
	@POST
	@Path("/status/change/execute")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response changeStatus(String cadena) throws Throwable {
    AccountsDAO aco=new AccountsDAO();
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
     try {
    	JSONObject jsonEntrada=new JSONObject(cadena);
	    long validationId=jsonEntrada.getLong("validationId");
	    //String originatorReferencedId=jsonEntrada.getString("originatorReferencedId");
	    List<Object[]> lista_obtenidos=aco.getChangeStatus(validationId,"");

       if(lista_obtenidos.size()>0){
        	for(Object[]objetos_validados:lista_obtenidos) {
        	 	JsonObjectBuilder desc=Json.createObjectBuilder();
        		DatosOK=desc
	    				  .add("warnings",Json.createObjectBuilder()
	    						     .add("code","string")
	                                 .add("message","string").build())
	    				  .add("statusCode", objetos_validados[0].toString())
	    				  .add("description",objetos_validados[1].toString())
	    				  .build();
        	}
        	return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
        }else {
        	JsonNotFound=Json.createObjectBuilder()
        			         .add("type", "urn:vn:error-codes:VAL00003")
        	                 .add("title","The requested object could not be found")
        	                 .build();
        	return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();

    	}
	    } catch (Exception e) {
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , e.getMessage()+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

		}
        finally {
      	 aco.cerrar();
     }      
	} 
	
	
	/***************************************Get Balances Pendieeeeeeeeeeeeeeeeeeeeeee*********************************************/
	

	@POST
	@Path("/balances")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response changesBalance(String[]cadena) throws Throwable {
	
	System.out.println(cadena);
	
	
		

    AccountsDAO aco=new AccountsDAO();
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
   
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , ""+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

	     
	} 
	
	
/***************************************Get Balances Pendieeeeeeeeeeeeeeeeeeeeeee*********************************************/
	

    @POST
    @Path("/api/timedeposit/create/create/validate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response changeBalance(String[]cadena) throws Throwable {
	
	System.out.println(cadena);
	
	
		

    AccountsDAO aco=new AccountsDAO();
	JsonObject DatosOK=null; 	
 	JsonObject JsonNotFound=null;
 	JsonObject JsonError=null;
   
		  JsonError=Json.createObjectBuilder()
				        .add("type"    , "urn:vn:error-codes:VAL00001")
				        .add("title"   , "Unexpected error")
				        .add("detail"  , ""+" invalid param")
				        .build();
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JsonError.toString()).build();

	     
	} 
	
	
	

}
