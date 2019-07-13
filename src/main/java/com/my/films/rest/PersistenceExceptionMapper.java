package com.my.films.rest;

import javax.ws.rs.ext.*;

import javax.ws.rs.core.Response;

import javax.persistence.PersistenceException;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
	
	 @Override
	public Response toResponse(PersistenceException e) {
		return Response.status(500).entity("Oops, something went wrong").build();
	}
	
}