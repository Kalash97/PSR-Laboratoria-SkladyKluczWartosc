package com.Actions;

import com.Entities.Author;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteAuthorAction implements Action{

	private IMap<Long, Author> authors;
	private Long key;
	
	@Override
	public void launch() {
		authors.delete(key);
	}

}
