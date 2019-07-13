package com.my.films.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.PathParam;

import com.my.films.model.Film;
import com.my.films.repository.RepoBean;

import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import javax.ws.rs.core.GenericEntity;

import javax.validation.constraints.Min;

import java.util.concurrent.Future;
import java.lang.InterruptedException;

@Path("films")
public class FilmsResource {
	
	@Inject
	private RepoBean filmRepo;
		
	@GET
	@Produces("application/json")
	public Response getFilms() {
		List<Film> films = filmRepo.findAllFilms();
		GenericEntity<List<Film>> entity = new GenericEntity<List<Film>>(films) {};
		return Response.status(200).entity(entity).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createFilm(Film film, @Context UriInfo uriInfo) {
		if(film.getId() != null)
			return Response.status(422).entity("film's id must be null as it is auto-generated.").build();
		
		film = filmRepo.createFilm(film);
		
		URI createdURI = uriInfo.getAbsolutePathBuilder().path(film.getId().toString()).build();
		return Response.status(201).location(createdURI).entity(film).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{id : \\d+}")
	public Response updateFilm(Film film, @PathParam("id") @Min(1) Long id, @Context UriInfo uriInfo) {
		boolean doesIdsMatch = film.getId() == null || id == film.getId();
		if(!doesIdsMatch) 
			return Response.status(422).entity("Mantioned in path id and entity's id doesn't match").build();
		
		boolean doesEntityExist = filmRepo.findFilm(id) != null;
		if(!doesEntityExist)
			return Response.status(404).entity("Entity not found").build();
		
		film = filmRepo.updateFilm(film);
		
		URI createdURI = uriInfo.getAbsolutePathBuilder().path(film.getId().toString()).build();
		return Response.status(200).entity(film).build();
	}
	
	@GET
	@Path("/{id : \\d+}")
	@Produces("application/json")
	public Response getFilm(@PathParam("id") @Min(1) Long id) {
		Film film = filmRepo.findFilm(id);
		
		return Response.status(200).entity(film).build();
	}
	
	
	@DELETE
	@Path("/{id : \\d+}")
	@Produces("application/json")
	public Response deleteFilm(@PathParam("id") @Min(1) Long id) {
		boolean doesEntityExist = filmRepo.findFilm(id) != null;
		if(!doesEntityExist)
			return Response.status(404).entity("Entity not found").build();
		
		filmRepo.deleteFilm(id);
		
		return Response.status(204).build();
	}
}