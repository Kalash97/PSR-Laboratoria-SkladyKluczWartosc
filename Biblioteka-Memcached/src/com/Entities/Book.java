package com.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private int yearOfPublishment;
	
	@Getter
	@Setter
	private String isbn;
	
	@Getter
	private List<Author> authors = new ArrayList<Author>();

}
