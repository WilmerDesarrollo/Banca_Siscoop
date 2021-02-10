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
import com.fenoreste.rest.dto.siscoop.ProductsDTO;
import javax.ws.rs.GET;

@Path("/Products")
public class ProductsServices {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getProducts() {
        javax.json.JsonObject datosOK =null;
        JsonArrayBuilder arraycuentas = Json.createArrayBuilder();
        ProductsDAO pr = new ProductsDAO();
        try {
            List<ProductsDTO> ListaProductos = pr.obtenerProductos();
            if(ListaProductos != null){
            for (int i=0;i<ListaProductos.size();i++) {
                    JsonObjectBuilder data = Json.createObjectBuilder();
                    ProductsDTO prod=ListaProductos.get(i);  
                    datosOK = data
                            .add("productCode",prod.getProductCode())
                            .add("accountType",prod.getAccountType())
                            .add("description",prod.getDescription())
                            .add("currencyCode", prod.getCurrencyCode())
                            .add("frecuency",Json.createObjectBuilder().add("value",90).add("valueType","integer").add("isSensitive","false").build())
                            .add("period",Json.createObjectBuilder().add("value","D").add("valueType","string").add("isSensitive","false").build())
                            .build();
                    arraycuentas.add(datosOK);
            }
            javax.json.JsonObject Found = Json.createObjectBuilder()
                        .add("accounts", arraycuentas)
                        .build();
           return Response.status(Response.Status.OK).entity(Found).build();
        }else{
         com.github.cliftonlabs.json_simple.JsonObject Not_Found = new com.github.cliftonlabs.json_simple.JsonObject();
         Not_Found.put("Error","Datos no encontrados");
         Response.status(Response.Status.NO_CONTENT).entity(Not_Found).build();
        }
        } catch (Exception e) {
         com.github.cliftonlabs.json_simple.JsonObject Error = new com.github.cliftonlabs.json_simple.JsonObject(); 
         Error.put("Error","Interno en el servidor");
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Error).build();
        }finally{
         pr.cerrar();
        }
        return null;
    }

    @POST
    @Path("/product/rate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getProductsRate(String cadena) throws Throwable {
        JsonObjectBuilder ObjectBuilder = Json.createObjectBuilder();
        JsonObject DatosOK = null;
        JsonObject JsonNotFound = null;
        JsonObject JsonError = null;
        JSONObject datosEntrada = new JSONObject(cadena);
        ProductsDAO productosD = new ProductsDAO();
        try {
            String customerId = datosEntrada.getString("customerId");
            int productCode = datosEntrada.getInt("productCode");
            String acountType = datosEntrada.getString("accountType");
            System.out.println("CustomerID:" + customerId);
            System.out.println("ProductCode:" + productCode);
            System.out.println("AcountType:" + acountType + "\n\n\n\n\n");
            List<Object[]> ListaProductos = productosD.getProductsRate(customerId, productCode, acountType);
            if (ListaProductos.size() > 0) {
                for (Object[] prod : ListaProductos) {
                    JsonObjectBuilder desc = Json.createObjectBuilder();
                    DatosOK = desc
                            .add("warnings", ObjectBuilder.add("code", "string")
                                    .add("message", "string").build())
                            .add("interestRate", prod[0].toString())
                            .add("maturityDate", prod[1].toString())
                            .add("minInitialDepositAmount", ObjectBuilder.add("amount", prod[2].toString())
                                    .add("currecyCode", "code").build())
                            .add("property1", ObjectBuilder.add("description", prod[3].toString()).build())
                            .add("property2", ObjectBuilder.add("description", prod[3].toString()).build()).build();
                }
                return Response.status(Response.Status.OK).entity(DatosOK.toString()).build();
            } else {
                JsonNotFound = ObjectBuilder.add("type", "urn:vn:error-codes:" + customerId + "," + productCode + "," + acountType)
                        .add("tittle", "The requested object could not be found").build();
                return Response.status(Response.Status.NOT_FOUND).entity(JsonNotFound.toString()).build();
            }
        } catch (Exception e) {
            JsonError = ObjectBuilder.add("type", "urn:vn:error-codes")
                    .add("title", "validation failed on submitted data").build();
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonError.toString()).build();
        } finally {
            productosD.cerrar();
        }
    }
}
