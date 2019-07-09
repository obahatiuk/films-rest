package com.my.films.test;

import org.glassfish.jersey.test.JerseyTest;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.core.Response;

import com.my.films.rest.FilmsResource;
import com.my.films.rest.FilmsApplication;


public class FilmsResourceTest extends JerseyTest {
	@Override
	protected Application configure() {
	return new FilmsApplication();
	}
	
	@Test
	public void getFilms() {
		Response response = target("/films").request().get();
		assertNotNull(response);
		
	}
}