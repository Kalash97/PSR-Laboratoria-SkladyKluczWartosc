package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadBookByKeyAction implements Action{

	private IMap<Long, Book> books;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		String line = getValidKey();
		Long key = Long.parseLong(line);
		
		Book b = books.get(key);
		if(b==null) {
			cv.print("Nie ma takiej ksi¹zki!");
			return;
		}
		
		cv.print("Tytu³: "+b.getTitle());
		cv.print("ISBN: "+ b.getIsbn());
		cv.print("Rok wydania: "+ b.getYearOfPublishment());
		cv.print("");
	}

	private String getValidKey() {
		String line;
		do {
			cv.print("Podaj klucz");
			line = cv.read();	
		} while (!ValidUtil.isLongInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "ReadBookByKey";
	}

}
