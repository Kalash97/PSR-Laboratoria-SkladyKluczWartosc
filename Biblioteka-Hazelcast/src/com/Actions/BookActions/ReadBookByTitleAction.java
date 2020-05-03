package com.Actions.BookActions;

import java.util.Set;

import com.Actions.Action;
import com.Entities.Book;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadBookByTitleAction implements Action {

	private IMap<Long, Book> books;
	private ConsoleView cv;

	@Override
	public void launch() {
		cv.print("Podaj tytu³ ksi¹¿ki");
		String title = cv.read();

		Set<Long> keys = books.localKeySet();
		for (Long k : keys) {
			if (books.get(k).getTitle().equals(title)) {
				cv.print("Ksi¹¿ka o kluczu: " + k);
				cv.print("Tytu³: " + books.get(k).getTitle());
				cv.print("ISBN: " + books.get(k).getIsbn());
				cv.print("Rok wydania: " + books.get(k).getYearOfPublishment());
				cv.print("");
			}
		}
	}

	@Override
	public String getName() {
		return "ReadBookByTitle";
	}

}
