package com.Actions.AuthorActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Entities.Book;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class ReadAuthorByKeyAction implements Action{

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
		
		cv.print("Imiê: "+a.getName());
		cv.print("Nazwisko: "+a.getLastName());
		cv.print("Ksi¹¿ki:: ");
		for(Book b : a.getBooks()) {
			cv.print("Tytu³: "+b.getTitle());
		}
		cv.print("");
	}

	@Override
	public String getName() {
		return "ReadAuthorByKey";
	}

}
