package com.my.films.repository;

import java.util.List;
import java.util.concurrent.Future;

import com.my.films.model.Film;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;  

@Local
public interface RepoLocal {
	
	List<Film> findAllFilms();//Future<List<Film>>
	
	Film findFilm(@NotNull Long id);
	
	Film createFilm(@NotNull Film film);
	
	void deleteFilm(@NotNull Long id);
	
	Film updateFilm(@NotNull Film film);
}