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
import javax.ejb.Stateless;
import javax.ejb.Asynchronous;

import com.my.films.repository.RepoLocal;  
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;

import javax.validation.constraints.NotNull;


@Stateless
public class RepoBean implements RepoLocal {
	
	private EntityManager entityManager;
	
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
	
	//@Asynchronous
	public List<Film> findAllFilms() {//Future<List<Film>>
			String str = "select e from Film e";
			//List<Film> result = entityManager.createQuery(str).getResultList();new AsyncResult<List<Film>>(result);
			return entityManager.createQuery(str).getResultList();
	}
	
	public Film findFilm(@NotNull Long id) {
		return entityManager.find(Film.class, id);
	}
	
	public Film createFilm(@NotNull Film film) {
		entityManager.getTransaction().begin();
		entityManager.persist(film);
		entityManager.getTransaction().commit();
		return film;
	}
	
	public void deleteFilm(@NotNull Long id) {		
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(Film.class, id));
		entityManager.getTransaction().commit();
	}
	
	public Film updateFilm(@NotNull Film film) {
		entityManager.getTransaction().begin();
		entityManager.merge(film);
		entityManager.getTransaction().commit();
		return film;
	}
}