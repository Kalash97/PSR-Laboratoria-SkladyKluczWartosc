package com.Actions.AuthorActions;

import java.util.Set;

import com.Actions.Action;
import com.Entities.Author;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadAllAuthors implements Action{

	private IMap<Long, Author> authors; 
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Set<Long> keys = authors.localKeySet();
		for(Long k : keys) {
			cv.print("Autor o kluczu: "+k);
			cv.print("Imiê: "+authors.get(k).getName());
			cv.print("Nazwisko: "+authors.get(k).getLastName());
			cv.print("");
		}
	}

	@Override
	public String getName() {
		return "ReadAllAuthors";
	}

}
