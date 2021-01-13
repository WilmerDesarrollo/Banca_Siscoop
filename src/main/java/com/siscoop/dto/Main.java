/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscoop.dto;

import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.Persona;
import com.fenoreste.rest.modelos.PersonasPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author root
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = AbstractFacade.conexion();
        EntityManager em = emf.createEntityManager();

        PersonasPK pk = new PersonasPK(30298, 25, 75681);

        System.out.println("czczczxc");
        Persona persona = em.find(Persona.class, pk);
        System.out.println("Nombre:" + persona);

    }
}
