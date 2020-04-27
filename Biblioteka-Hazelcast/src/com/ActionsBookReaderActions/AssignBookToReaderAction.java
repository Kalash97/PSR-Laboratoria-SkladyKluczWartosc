package com.ActionsBookReaderActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Entities.Reader;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignBookToReaderAction implements Action{

	private IMap<Long, Reader> readers;
	private IMap<Long, Book> books;
	private ConsoleView view;
	
	@Override
	public void launch() {
		view.print("Podaj klucz ksi¹¿ki");
		String line = getValidKey();
		Long bookKey = Long.parseLong(line);
		
		Book b = books.get(bookKey);
		if (b == null) {
			view.print("Nie ma takiej ksi¹zki!");
			return;
		}
		
		view.print("Podaj klucz czytelnika");
		line = getValidKey();
		Long readerKey = Long.parseLong(line);
		
		Reader r = readers.get(readerKey);
		if(r == null) {
			view.print("Nie ma takiego czytelnika!");
			return;
		}
		
		r.getBooks().add(b);
		
		readers.put(readerKey, r);
	}

	private String getValidKey() {
		String line;
		do {
			view.print("Podaj klucz");
			line = view.read();	
		} while (!ValidUtil.isLongInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "AssignBookToReader";
	}

}
