package com.fenoreste.rest.dto;

	import java.lang.Object;
	import java.util.Set;
	import javax.json.Json;
	import javax.json.JsonObject;
	import javax.json.JsonObjectBuilder;
	import javax.validation.ConstraintViolation;
	import javax.validation.Validation;
	import javax.validation.Validator;
	import javax.validation.ValidatorFactory;

import org.codehaus.jackson.annotate.JsonIgnore;

	public abstract class DTOFacade<T> {
	 /* @JsonIgnore
	  public Set<ConstraintViolation<Object>> getViolaciones() {
	    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    final Validator validator = factory.getValidator();
	    return validator.validate(this);
	  }

	  @JsonIgnore
	  private JsonObject getErrors() {
	    final JsonObjectBuilder builder = Json.createObjectBuilder();
	    this.getViolaciones().stream().forEach(x -> {
	        builder.add(x.getPropertyPath().toString(), x.getMessage());
	    });
	    return builder.build();
	  }

	  public void validate(){
	    final JsonObject errors = this.getErrors();
	    if(!errors.isEmpty()){
	       //throw new ServicesException(Response.Status.BAD_REQUEST, errors.toString());
	    }}*/
	}


