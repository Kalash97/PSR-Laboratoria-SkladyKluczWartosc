package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class UpdateAuthorAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		
		cv.print("Podaj klucz autora");
		String key = cv.read();
		
		Author a;
		
		try {
			a = (Author) client.get(key);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma autora o takim kluczu");
			return;
		}
			
		if(a==null) {
			cv.print("Nie ma autora o takim kluczu");
			return;
		}
		
		cv.print("Podaj nowe imi� (je�li nie zmieniasz to naci�nij ENTER)");
		String line = cv.read();
		if (!line.equals("")) {
			a.setName(line);
		}
		
		cv.print("Podaj nowe nazwisko (je�li nie zmieniasz to naci�nij ENTER)");
		line = cv.read();		
		if (!line.equals("")) {
			a.setLastName(line);
		}
		
		client.set(key, 2000, a);
	}

	@Override
	public String getName() {
		return "UpdateAuthor";
	}

}
