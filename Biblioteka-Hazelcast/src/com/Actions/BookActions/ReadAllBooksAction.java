package com.Actions.BookActions;

import java.util.Set;

import com.Actions.Action;
import com.Entities.Book;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadAllBooksAction implements Action{

	private IMap<Long, Book> books;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Set<Long> keys = books.localKeySet();
		for(Long k : keys) {
			cv.print("Ksi¹¿ka o kluczu: "+k);
			cv.print("Tytu³: "+books.get(k).getTitle());
			cv.print("ISBN: "+ books.get(k).getIsbn());
			cv.print("Rok wydania: "+ books.get(k).getYearOfPublishment());
			cv.print("");
		}
	}

	@Override
	public String getName() {
		return "ReadAllBooks";
	}

}
