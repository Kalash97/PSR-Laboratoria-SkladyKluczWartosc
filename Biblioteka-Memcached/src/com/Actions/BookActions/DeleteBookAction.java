package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class DeleteBookAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("Podaj klucz ksi¹zki");
		String key = cv.read();
		
		Book b;
		
		try {
			b = (Book) client.get(key);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma ksi¹¿ki o takim kluczu");
			return;
		}
		
		if(b==null) {
			cv.print("Nie ma ksi¹¿ki o takim kluczu");
			return;
		}
		
		client.delete(key);
	}

	@Override
	public String getName() {
		return "DeleteBook";
	}

}
