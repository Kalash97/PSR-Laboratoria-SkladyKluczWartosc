package com.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Author implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	private List<Book> books= new ArrayList<Book>();
}
