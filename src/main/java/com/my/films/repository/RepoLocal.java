package com.my.films.repository;

import java.util.List;
import com.my.films.model.Film;

import javax.ejb.Local;  

@Local
public interface RepoLocal {
	List<Film> findAll();
}