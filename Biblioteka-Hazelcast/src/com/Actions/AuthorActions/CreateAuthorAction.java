package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAuthorAction implements Action {

	private IMap<Long, Author> authors;
	private ConsoleView view;

	@Override
	public void launch() {
		Author a = new Author();
		view.print("Podaj imiê");
		a.setName(view.read());

		view.print("Podaj nazwisko");
		a.setLastName(view.read());

		String line = getValidKey();		
		Long key = Long.parseLong(line);
		authors.put(key, a);
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
		return "CreateAuthorAction";
	}

}
