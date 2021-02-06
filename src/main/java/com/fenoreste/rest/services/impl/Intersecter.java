package com.fenoreste.rest.services.impl;

import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.entidad.JWT;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
        /*System.out.println("Lllegando a intesersecter...");
        String url = request.getUriInfo().getAbsolutePath().toString();
        System.out.println("La URL QUE SE ESTA CONSUMIENDO:" + url);

        URL url1= new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        

        if (url.contains("/api/auth")) {
            return;
        } else {
            System.out.println("Accedido correctamente");
            EntityManagerFactory emd = AbstractFacade.conexion();
            EntityManager em = emd.createEntityManager();
            String sql = "SELECT * from jwt where id=1";
            Query query = em.createNativeQuery(sql, JWT.class);
            boolean bandera = false;
            try {
                JWT jwts = (JWT) query.getSingleResult();
                System.out.println("jwt token:" + jwts.getToken());
                bandera = true;
            } catch (Exception e) {
                System.out.println("No existen registros");
                bandera = false;
            }

            String head = request.getHeaders().toString();
            String token = request.getHeaderString("Authorization");

            if (head.contains("Authorization")) {
                if (!token.equals("")) {
                    JsonObject json = Json.createObjectBuilder()
                            .add("mensaje", "Credenciales incorrectas").build();
                    request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
                }
                return;
            } else {
                JsonObject json = Json.createObjectBuilder()
                        .add("mensaje", "Credenciales necesarias").build();
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
                return;
            }
        }*/
    }
}
