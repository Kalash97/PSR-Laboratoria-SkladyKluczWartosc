package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class CreateBookAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Book b = new Book();
		cv.print("Podaj tytu³");
		b.setTitle(cv.read());
		
		cv.print("Podaj ISBN");
		b.setIsbn(cv.read());
		
		String line = getValidYear();
		int yearOfPublishment = Integer.parseInt(line);
		b.setYearOfPublishment(yearOfPublishment);
		
		cv.print("Podaj klucz");
		String key = cv.read();
		
		client.set(key, 2000, b);
	}

	private String getValidYear() {
		String line;
		do {
			cv.print("Podaj rok wydania");
			line = cv.read();
		}while(!ValidUtil.isIntInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "CreateBook";
	}

}
