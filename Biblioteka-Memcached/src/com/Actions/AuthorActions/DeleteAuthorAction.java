package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class DeleteAuthorAction implements Action{

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
		
		client.delete(key);
	}

	@Override
	public String getName() {
		return "DeleteAuthor";
	}

}
