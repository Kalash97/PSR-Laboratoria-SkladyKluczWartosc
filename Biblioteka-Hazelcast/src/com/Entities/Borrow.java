package com.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Borrow implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Date dateOfEnd;

	@Getter
	@Setter
	private Reader reader;
	
	@Getter
	private List<Book> books = new ArrayList<Book>();
}
