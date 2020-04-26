package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteAuthorAction implements Action{

	private IMap<Long, Author> authors;
	private ConsoleView view;
	
	@Override
	public void launch() {
		String line = getValidKey();		
		Long key=Long.parseLong(line);
		authors.delete(key);
	}

	private String getValidKey() {
		String line;
		do {
			view.print("Podaj klucz");
			line = view.read();	
		} while (!ValidUtil.isLongInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "DeleteAuthor";
	}

}
