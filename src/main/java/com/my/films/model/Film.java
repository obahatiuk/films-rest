package com.my.films.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlRootElement
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private Long id;
	
	@NotNull
	@XmlElement
	private String title;
	
	@XmlElement
	private String director;
	
	@NotNull
	@XmlElement
	private String description;
	
	public Long getId() {
		return id;
	}
	
	public String getIdTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}