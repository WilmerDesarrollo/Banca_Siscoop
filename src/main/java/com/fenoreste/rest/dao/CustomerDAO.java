package com.fenoreste.rest.dao;

import com.fenoreste.rest.modelos.entidad.Persona;

public class CustomerDAO extends FacadeCustomer<Persona> {

	 public CustomerDAO() {
	     super(Persona.class);
  }     
}
