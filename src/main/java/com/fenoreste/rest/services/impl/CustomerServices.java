package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.dao.CustomerDAO;
import com.fenoreste.rest.dao.FacadeCustomer;
import com.fenoreste.rest.modelos.Persona;
import com.siscoop.dto.customerDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        JsonObject ArrayProductos = null;
        JsonObject Warnings = null;
        JsonObject JsonRegreso = null;
        JsonObject datosOK = null;
        JsonObject Not_Found = null;
        JsonObject Error = null;
        JSONObject datosEntrada = new JSONObject(cadena);

        System.out.println("cadena:" + cadena);
        String nombre = datosEntrada.getString("name").trim();
        System.out.println("nombre:" + nombre);
        try {
            List<Object[]> customerSearch = datos.search(nombre);

            // add("totalRecords",String.valueOf(customerSearch.size()))
            for (Object[] cus_search : customerSearch) {
                JsonObjectBuilder data = Json.createObjectBuilder();
                JsonObject clientes = data
                        .add("customerId", cus_search[0].toString())
                        .add("name", cus_search[1].toString())
                        .add("customerType", cus_search[2].toString()).build();
                arrayEsqueleto.add(clientes);
            }
            if (arrayEsqueleto.toString().length() > 0) {
                Warnings = ObjectBuilder.add("code", "string")
                        .add("message", "string").build();
                ArrayProductos = ObjectBuilder.add("Clientes", arrayEsqueleto).build();
                datosOK = ObjectBuilder.add("Warnings", Warnings)
                        .add("totalRecords", customerSearch.size())
                        .add("", ArrayProductos).build();
            } else {
                datosOK = Json.createObjectBuilder()
                        .add("title", "No se encontraron registros:")
                        .build();
            }

            return Response.status(Response.Status.OK).entity(datosOK.toString()).build();
        } catch (Exception e) {
            Not_Found = Json.createObjectBuilder()
                    .add("type", "urn:vn:error-codes:VAL00003")
                    .add("title", "Error en el proceso de escritura Json")
                    .build();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(Not_Found.toString()).build();
        } finally {
            datos.finalize();
        }
    }

    @POST
    @Path("/details")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getDetails(String cadena) throws Throwable {
        CustomerDAO datos = new CustomerDAO();
        JsonObject datosOK = null;
        JsonObject Not_Found = null;
        JsonObject Error = null;
        System.out.println("entro");
        try {
            JSONObject datosEntrada = new JSONObject(cadena);
            String customerIdE = datosEntrada.getString("customerId").trim();
            System.out.println("CustomerId:" + customerIdE);
            List<Object[]> customerDetails = datos.details(customerIdE);
            if (customerDetails.size() > 0) {
                for (Object[] cus_detail : customerDetails) {
                    JsonObjectBuilder data = Json.createObjectBuilder();
                    datosOK = data.add("warnings", Json.createObjectBuilder()
                            .add("code", "string").build())
                            .add("customer", Json.createObjectBuilder()
                                    .add("customerId", cus_detail[0].toString())
                                    .add("name", cus_detail[1].toString())
                                    .add("taxId", cus_detail[2].toString())
                                    .add("customerType", cus_detail[3].toString())
                                    .add("contactDetails", Json.createObjectBuilder()
                                            .add("emails", Json.createObjectBuilder()
                                                    .add("value", "")//cus_detail[4].toString())
                                                    .add("type", "personal").build())
                                            .add("phones", Json.createObjectBuilder()
                                                    .add("value", ""))//cus_detail[5].toString())
                                            .add("type", "mobile").build())
                                    .add("addresses", Json.createObjectBuilder()
                                            .add("type", "")//cus_detail[6].toString())
                                            .add("countryCode", "")//cus_detail[7].toString())
                                            .add("state", "")//cus_detail[8].toString())
                                            .add("country", "string")
                                            .add("city", "string")
                                            .add("zip", "string")
                                            .add("district", "string")
                                            .add("street", "string")
                                            .add("number", "string")
                                            .add("name", "string")
                                            .add("apartament", "string").build()))
                            .add("property1", Json.createObjectBuilder()
                                    .add("description", "string").build())
                            .add("property2", Json.createObjectBuilder()
                                    .add("description", "string").build())
                            .build();
                }

            } else {
                datosOK = Json.createObjectBuilder()
                        .add("type", "urn:vn:error-codes:VAL00003")
                        .add("title", "The requested object could not be found:" + customerIdE).build();
            }
            return Response.status(Response.Status.ACCEPTED).entity(datosOK.toString()).build();
        } catch (Exception e) {
            Error = Json.createObjectBuilder()
                    .add("type", "urn:vn:error-codes:SE00002")
                    .add("title", "Unexpected error")
                    .add("detail", "parameter not found")
                    .add("instance", "").build();
            return Response.status(Response.Status.BAD_REQUEST).entity(Error.toString()).build();
        } finally {
            datos.finalize();
        }
    }

    @POST
    @Path("/accounts")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAccounts(String cadena) throws Throwable {
        CustomerDAO datos = new CustomerDAO();
        JsonObject datosOK = null;
        JsonObject Not_Found = null;
        JsonObject Error = null;
        JsonArrayBuilder arraycuentas = Json.createArrayBuilder();
        try {
            JSONObject datosEntrada = new JSONObject(cadena);
            String customerIdE = datosEntrada.getString("customerId").trim();
            System.out.println("CustomerId:" + customerIdE);
            List<Object[]> customerAccounts = datos.getAccounts(customerIdE);

            if (customerAccounts.size() > 0) {
                for (Object[] cus_accounts : customerAccounts) {
                    JsonObjectBuilder data = Json.createObjectBuilder();
                    datosOK = data
                            .add("accountId", cus_accounts[2].toString())
                            .add("accountNumber", cus_accounts[3].toString())
                            .add("displayAccountNumber", cus_accounts[4].toString())
                            .add("accountType", cus_accounts[14].toString())
                            .add("currencyCode", cus_accounts[11].toString())
                            .add("productCode", cus_accounts[11].toString())
                            .add("status", cus_accounts[6].toString())
                            .add("restrictions", Json.createArrayBuilder()
                                    .add("credits").build())
                            .add("customerRelations", Json.createArrayBuilder()
                                    .add(Json.createObjectBuilder()
                                            .add("relationCode", "")
                                            .add("relationtype", "")
                                            .build())
                                    .build())
                            .add("hasBalances", true)
                            .add("property1", Json.createObjectBuilder()
                                    .add("description", "string").build())
                            .add("property2", Json.createObjectBuilder()
                                    .add("description", "string").build())
                            .build();

                    arraycuentas.add(datosOK);

                }
                JsonObject Found = Json.createObjectBuilder()
                        .add("warnings", Json.createObjectBuilder()
                                .add("code", "string")
                                .add("message", "string").build())
                        .add("accounts", arraycuentas)
                        .add("property1", Json.createObjectBuilder()
                                .add("description", "string").build())
                        .add("property2", Json.createObjectBuilder()
                                .add("description", "string").build())
                        .build();
                return Response.status(Response.Status.OK).entity(Found.toString()).build();
            } else {
                Not_Found = Json.createObjectBuilder()
                        .add("type", "urn:vn:error-codes:VAL00003")
                        .add("title", "The requested object could not be found:" + customerIdE).build();
                return Response.status(Response.Status.NOT_FOUND).entity(Not_Found.toString()).build();

            }
        } catch (Exception e) {
            Error = Json.createObjectBuilder()
                    .add("type", "urn:vn:error-codes:SE00002")
                    .add("title", "Unexpected error")
                    .add("detail", "parameter not found" + e.getMessage())
                    .add("instance", e.getMessage())
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(Error.toString()).build();

        } finally {
            datos.finalize();
        }
    }

    @POST
    @Path("/templates")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response res(String cadena) throws JSONException {
        JsonObject datosOk = null;
        JsonObject datosError = null;
        JsonObject NotFound = null;

        JSONObject datosEntrada = new JSONObject(cadena);
        String customerId = datosEntrada.getString("customerId").trim();
        CustomerServices custServices = new CustomerServices();

        if (!customerId.equals("")) {
            datosOk = Json.createObjectBuilder()
                    .add("warnings:", Json.createObjectBuilder()
                            .add("code", "string")
                            .add("message", "string").build())
                    .add("templateCodes:", "string")
                    .add("property1", Json.createObjectBuilder()
                            .add("description", "string").build())
                    .add("property1", Json.createObjectBuilder()
                            .add("description", "string").build())
                    .build();
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
