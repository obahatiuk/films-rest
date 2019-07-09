package com.my.films.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.my.films.model.Film;
import com.my.films.repository.RepoBean;

import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

@Path("films")
public class FilmsResource {
	
	@Inject
	private RepoBean filmRepo;
		
	@GET
	@Produces("application/json")
	public Response getHello() {
		List<Film> films = filmRepo.findAll();
		GenericEntity<List<Film>> entity = new GenericEntity<List<Film>>(films) {};
		return Response.status(200).entity(entity).build();
	}
}