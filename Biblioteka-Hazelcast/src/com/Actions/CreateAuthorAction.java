package com.Actions;

import com.Entities.Author;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAuthorAction implements Action{

	private IMap<Long, Author> authors;
	private String name;
	private String lastName;
	private Long key;
	
	@Override
	public void launch() {
		Author a = new Author();
		a.setName(name);
		a.setLastName(lastName);
		authors.put(key, a);
	}
	
}
