package com.my.films.test;

import org.glassfish.jersey.test.JerseyTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.core.Response;

import com.my.films.rest.FilmsResource;
import com.my.films.rest.FilmsApplication;

import com.my.films.model.Film;
import javax.ws.rs.core.GenericType;

import javax.ws.rs.client.Entity;

public class FilmsResourceTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new FilmsApplication();
	}
	
	@Test
	public void getFilms_OkResponseStatusNotEmptyList() {
		Response response = target("/films").request().get();
		
		assertTrue(response.getStatus() == 200);
		
		List<Film> films = response.readEntity(new GenericType<List<Film>>() {});
		
		assertTrue(films.size() > 0);
	}
	
	@Test
	public void createFilm_IdNotNull_UnprocessableEntityResponseStatus() {
				
		Film film  = new Film();
		film.setId(8L);
		film.setTitle("test title");
		film.setDescription("test description");
		film.setDirector("test director");
		
		Response response = target("/films").request().post(Entity.json(film));
		
		assertTrue(response.getStatus() == 422);

	}
	
	@Test
	public void createFilm_IdNull_CreatedResponseStatus() {
				
		Film film  = new Film();

		film.setTitle("test title");
		film.setDescription("test description");
		film.setDirector("test director");
		
		Response response = target("/films").request().post(Entity.json(film));
		
		assertTrue(response.getStatus() == 201);

	}
	
}