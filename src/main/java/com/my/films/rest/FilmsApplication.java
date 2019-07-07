package com.my.films.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.my.films.repository.RepoBean;

//import com.my.films.repository.EntityDao;
//import com.my.films.repository.BaseEntityDao;

@ApplicationPath("rest")
public class FilmsApplication extends ResourceConfig {

	public FilmsApplication() {
		packages("com.my.films.rest");
		
		register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(RepoBean.class)
                .to(RepoBean.class);
            }
        });
	}
}
