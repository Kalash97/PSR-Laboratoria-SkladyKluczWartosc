package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBookAction implements Action{
	
	private IMap<Long, Book> books;
	private ConsoleView view;
	
	@Override
	public void launch() {
		Book b = new Book();
		
		view.print("Podaj tytu³");
		b.setTitle(view.read());
		
		view.print("Podaj ISBN");
		b.setIsbn(view.read());
		
		String line = getValidYear();
		int yearOfPublishment = Integer.parseInt(line);
		b.setYearOfPublishment(yearOfPublishment);
		
		line = getValidKey();		
		Long key = Long.parseLong(line);
		
		books.put(key, b);
	}

	private String getValidKey() {
		String line;
		do {
			view.print("Podaj klucz");
			line = view.read();	
		} while (!ValidUtil.isLongInstance(line));
		return line;
	}
	
	private String getValidYear() {
		String line;
		do {
			view.print("Podaj rok wydania");
			line = view.read();
		}while(!ValidUtil.isIntInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "CreateBookAction";
	}

}
