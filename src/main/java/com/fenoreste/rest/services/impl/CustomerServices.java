package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.dao.CustomerDAO;
import com.fenoreste.rest.dao.FacadeCustomer;
import com.fenoreste.rest.dto.siscoop.CustomerAccountDTO;
import com.fenoreste.rest.dto.siscoop.CustomerContactDetails;
import com.fenoreste.rest.dto.siscoop.CustomerDetailsDTO;
import com.fenoreste.rest.dto.siscoop.CustomerSearchDTO;
import com.fenoreste.rest.modelos.entidad.Persona;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/customer")
public class CustomerServices {

    @POST
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response BuscarSocio(String cadena) throws Throwable {
        CustomerDAO datos = new CustomerDAO();

        JsonObjectBuilder ObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayEsqueleto = Json.createArrayBuilder();        
        JsonObject JsonSocios = new JsonObject();        
        JsonObject Not_Found = new JsonObject() ;
        JSONObject datosEntrada = new JSONObject(cadena);
        System.out.println("Objeto Json:" + datosEntrada);
        System.out.println("cadena:" + cadena);

        JSONObject mainObject = new JSONObject(cadena);
        String cif = "";
        for (int i = 0; i < mainObject.length(); i++) {
            String fi = mainObject.getString("filters");
            JSONArray json = new JSONArray(fi);
            for (int x = 0; x < json.length(); x++) {
                JSONObject jsonO = new JSONObject(json.getJSONObject(x).toString());
                cif = jsonO.getString("value");
            }
        }

        try {
            List<CustomerSearchDTO> lista = datos.search(cif);
            CustomerSearchDTO cliente = null;
            System.out.println("Lista en servicios:" + lista);
            if (lista.size() > 0) {
                System.out.println("entro");                
                JsonSocios.put("customers",lista);
                return Response.status(Response.Status.OK).entity(JsonSocios).build();
            } else {
                Not_Found.put("type", "urn:vn:error-codes:VAL00003");
                Not_Found.put("title", "Socios no encontrados");
                datos.cerrar();
                return Response.status(Response.Status.NO_CONTENT).entity(Not_Found.toString()).build();
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            Not_Found.put("type", "urn:vn:error-codes:VAL00003");
            Not_Found.put("title", "Error en el proceso de escritura Json");
            datos.cerrar();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Not_Found.toString()).build();

        } finally {
            datos.cerrar();
        }
    }

    @POST
    @Path("/details")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getDetails(String cadena) throws Throwable {
        CustomerDAO datos = new CustomerDAO();
        
        JsonObject Not_Found = null;
        JsonObject Error = null;
        System.out.println("entro");
        JsonObject JsonSocios = new JsonObject();
        JsonObjectBuilder ObjectBuilder = Json.createObjectBuilder();
        try {
            JSONObject jsonE = new JSONObject(cadena);
            String customerId = jsonE.getString("customerId");
            CustomerDetailsDTO socio = datos.details(customerId);

            if (socio.getName() != null && socio.getCustomerId() != null) {
                System.out.println("entro");
                
                JsonObject clientes=new JsonObject();
                clientes.put("customerId", socio.getCustomerId());
                clientes.put("name", socio.getName());
                clientes.put("customerType", socio.getcustomerType());                
                JsonSocios.put("customer", clientes);
                return Response.status(Response.Status.ACCEPTED).entity(JsonSocios).build();

            } else {
              Not_Found.put("type", "urn:vn:error-codes:VAL00003");
              Not_Found.put("title", "Socios no encontrados");
              return Response.status(Response.Status.NO_CONTENT).entity(JsonSocios).build();
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            Not_Found.put("type", "urn:vn:error-codes:VAL00003");
            Not_Found.put("title", "Error en el proceso de escritura Json");
            datos.cerrar();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Not_Found.toString()).build();

        } finally {
            datos.cerrar();
        }

    }

    @POST
    @Path("/contactDetails")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response contactDetails(String cadena) throws Throwable {
        
        CustomerDAO datos = new CustomerDAO();
        JsonObjectBuilder ObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayEsqueleto = Json.createArrayBuilder();
        JsonObject MiddleContacts = new JsonObject();
        JsonObject Error = null;
        JSONObject datosEntrada = new JSONObject(cadena);
        String ogs=datosEntrada.getString("customerId");
        System.out.println("Objeto Json:" + datosEntrada);
        System.out.println("cadena:" + cadena);
        try{
        List<CustomerContactDetails>listaContacto = datos.ContactDetails(ogs);
        CustomerContactDetails detalle = new CustomerContactDetails();
        if(listaContacto.size() > 0 ){
           MiddleContacts.put("contactDetails",listaContacto);
           datos.cerrar();
           return Response.status(Response.Status.OK).entity(MiddleContacts).build();
                
        }else{
            Error.put("type", "urn:vn:error-codes:VAL00003");
            Error.put("title", "Datos no encontrados");
            datos.cerrar(); 
            return Response.status(Response.Status.NO_CONTENT).entity(Error).build(); 
        }      
        }catch(Exception e){
          
            datos.cerrar(); 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Error).build();   
        }

    }

    @POST
    @Path("/accounts")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAccounts(String cadena) throws Throwable {
        CustomerDAO datos = new CustomerDAO();
        javax.json.JsonObject datosOK =null;
        JsonObject Not_Found = new JsonObject();
        JsonObject Error = null;
        JsonArrayBuilder arraycuentas = Json.createArrayBuilder();
             
        try {            
        JSONObject mainObject = new JSONObject(cadena);
        String cif = mainObject.getString("customerId");
        System.out.println("Cif:"+cif);
        List<CustomerAccountDTO>cuentas = datos.Accounts(cif);
            System.out.println("Regeso:"+cuentas);
            if (cuentas.size() > 0) {
                System.out.println("si entro");
                
                for (int i=0;i<cuentas.size();i++) {
                    JsonObjectBuilder data = Json.createObjectBuilder();
                    CustomerAccountDTO cuenta=cuentas.get(i);
                    
                    
                    datosOK = data
                            .add("accountId",cuenta.getAccountId())
                            .add("accountNumber", cuenta.getAccountNumber())
                            .add("displayAccountNumber", cuenta.getDisplayAccountNumber())
                            .add("accountType", cuenta.getAccountTye())
                            .add("currencyCode", cuenta.getCurrencyCode())
                            .add("productCode", cuenta.getProductCode())
                            .add("status", cuenta.getStatus())
                            .add("restrictions", Json.createArrayBuilder()
                                    .build())
                            .add("customerRelations", Json.createArrayBuilder()
                                    .add(Json.createObjectBuilder()
                                            .add("relationCode", "SOW")
                                            .add("relationtype", "self")
                                            .build())
                                    .build())
                            .add("hasBalances", true)
                            .build();

                    arraycuentas.add(datosOK);

                }
                javax.json.JsonObject Found = Json.createObjectBuilder()
                        .add("accounts", arraycuentas)
                        .build();
                return Response.status(Response.Status.OK).entity(Found).build();
            } else {
                System.out.println("Aqui");
                Not_Found.put("type", "urn:vn:error-codes:VAL00003");
                Not_Found.put("title", "Sin registros para usuario:"+cif);
                return Response.status(Response.Status.NOT_FOUND).entity(Not_Found).build();

            }
         
        } catch (Exception e) {
            Error.put("type", "urn:vn:error-codes:SE00002");
            Error.put("title","parametros incorrectos");
            datos.cerrar();
            return Response.status(Response.Status.BAD_REQUEST).entity(Error.toString()).build();

        } finally {
            datos.cerrar();
        }
    }

    @POST
    @Path("/templates")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response res(String cadena) throws JSONException {
        JsonObject datosOk = new JsonObject();
        com.github.cliftonlabs.json_simple.JsonObject jsons=new com.github.cliftonlabs.json_simple.JsonObject();
        JsonObject datosError = new JsonObject();
        JsonObject jsito=new JsonObject();
        JsonObject NotFound = null;

        JSONObject datosEntrada = new JSONObject(cadena);
        String customerId = datosEntrada.getString("customerId").trim();
        CustomerServices custServices = new CustomerServices();
        System.out.println("customerId:"+customerId);
        try {
             if (!customerId.equals("")) {
            
            datosOk.put("templatesCodes:",Json.createArrayBuilder().add("Single-user-Template").add("Single-user for Apps Template").build());
            jsito.put("valueType", "string");
            jsito.put("value","0");
            datosOk.put("Templates",jsito); 
           }
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage()); 
        }
                  
                  
        return Response.status(Response.Status.OK).entity(datosOk).build();
    }

    @POST
    @Path("/detalles")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response detalles(String cadena) throws JSONException {
        JsonObject datosOk = null;
        JsonObject datosError = null;
        JsonObject NotFound = null;

        JSONObject datosEntrada = new JSONObject(cadena);
        String customerId = datosEntrada.getString("customerId").trim();
        CustomerServices custServices = new CustomerServices();
        CustomerDAO datos = new CustomerDAO();
        Persona persona = datos.detailss(customerId);
        FacadeCustomer f = null;

        return Response.status(Response.Status.OK).entity(datosOk).build();
    }
}
