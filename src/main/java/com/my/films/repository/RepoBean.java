package com.my.films.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import java.util.List;
import javax.inject.Inject;
import com.my.films.model.Film;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;


import com.my.films.repository.RepoLocal;  

@Singleton
public class RepoBean implements RepoLocal {
	
	private EntityManager entityManager;
	
	//@Produces
	@PostConstruct
	public void createEntityManager() {
		entityManager = Persistence
				.createEntityManagerFactory("mysql_persistence_unit")
				.createEntityManager();
	}
	
	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public List<Film> findAll() {
			String str = "select e from Film e";
			
			return entityManager.createQuery(str).getResultList();
	}
}