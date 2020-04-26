package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateAuthorAction implements Action {

	private IMap<Long, Author> authors;
	private ConsoleView view;

	@Override
	public void launch() {
		String line= getValidKey();
		Long key = Long.parseLong(line);
		Author a = authors.get(key);
		if (a == null) {
			view.print("Nie ma takiego autora!");
			return;
		}

		view.print("Podaj nowe imi� (je�li nie zmieniasz to naci�nij ENTER)");
		line = view.read();
		if (!line.equals("")) {
			a.setName(line);
		}
		
		view.print("Podaj nowe nazwisko (je�li nie zmieniasz to naci�nij ENTER)");
		line = view.read();		
		if (!line.equals("")) {
			a.setLastName(line);
		}
		
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
		return "UpdateAuthorAction";
	}

}
