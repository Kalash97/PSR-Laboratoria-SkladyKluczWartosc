package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class ReadBookByKeyAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("Podaj klucz ksi��ki");
		String key = cv.read();
		
		Book b;
		
		try {
			b = (Book) client.get(key);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma ksi��ki o takim kluczu");
			return;
		}
		
		if(b==null) {
			cv.print("Nie ma ksi��ki o takim kluczu");
			return;
		}
		
		cv.print("Tytu�: "+b.getTitle());
		cv.print("ISBN: "+b.getIsbn());
		cv.print("Rok wydania: "+b.getYearOfPublishment());
	}
	@Override
	public String getName() {
		return "ReadBookByKey";
	}
	
}
