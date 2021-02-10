/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.dto.siscoop.ClearingCodeRulesDTO;
import com.fenoreste.rest.dto.siscoop.CountriesDTO;
import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.entidad.Paises_Siscoop;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
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
@Path("/GetCountries")
public class CountriesServices {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getCountries() {
        EntityManagerFactory emf = AbstractFacade.conexion();
        JsonObject json = new JsonObject();
        JsonObject jsonInfo = new JsonObject();
        try {
            EntityManager em = emf.createEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paises_Siscoop.class));
            List<Paises_Siscoop> lista = em.createQuery(cq).getResultList();
            List<CountriesDTO> ListaReal = new ArrayList<CountriesDTO>();
            for (int x = 0; x < lista.size(); x++) {
                CountriesDTO dto = new CountriesDTO(lista.get(x).getCode(),lista.get(x).getName());
                ListaReal.add(dto);
            }
            json.put("countries", ListaReal);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            System.out.println("Error en buscar paises:"+e.getMessage());
            jsonInfo.put("Error", "Datos no encontrados");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonInfo).build();
        } finally {
            emf.close();
        }

    }

    @GET
    @Path("/ClearingCodeRules")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getClearingCodeRules() {
        EntityManagerFactory emf = AbstractFacade.conexion();
        JsonObject json = new JsonObject();
        JsonObject jsonInfo = new JsonObject();
        try {
            EntityManager em = emf.createEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paises_Siscoop.class));
            List<Paises_Siscoop> lista = em.createQuery(cq).getResultList();
            List<ClearingCodeRulesDTO> ListaReal = new ArrayList<ClearingCodeRulesDTO>();
            for (int x = 0; x < lista.size(); x++) {
                ClearingCodeRulesDTO dto = new ClearingCodeRulesDTO(lista.get(x).getCode(), "numeric");
                ListaReal.add(dto);
            }
            json.put("clearingCodeRules", ListaReal);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            System.out.println("Error en ClearingCodeRules:"+e.getMessage());
            jsonInfo.put("Error", "Datos no encontrados");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonInfo).build();
        } finally {
            emf.close();
        }
    }

}
