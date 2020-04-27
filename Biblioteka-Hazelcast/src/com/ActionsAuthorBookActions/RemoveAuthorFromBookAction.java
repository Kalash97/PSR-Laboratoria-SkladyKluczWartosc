package com.ActionsAuthorBookActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveAuthorFromBookAction implements Action{

	private IMap<Long, Author> authors;
	private IMap<Long, Book> books;
	private ConsoleView view;
	
	@Override
	public void launch() {
		view.print("Podaj klucz autora");
		String line = getValidKey();
		Long authorKey = Long.parseLong(line);
		
		Author a = authors.get(authorKey);
		if (a == null) {
			view.print("Nie ma takiego autora!");
			return;
		}
		
		view.print("Podaj klucz ksi¹zki");
		line = getValidKey();
		Long bookKey = Long.parseLong(line);
		
		Book b = books.get(bookKey);
		if (b == null) {
			view.print("Nie ma takiej ksi¹zki!");
			return;
		}
		
		for(Book book : a.getBooks()) {
			if(book.getTitle().equals(b.getTitle())) {
				a.getBooks().remove(book);
				b.getAuthors().remove(a);
				break;
			}
		}
		
		authors.put(authorKey, a);
		books.put(bookKey, b);
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
		return "RemoveAuthorFromBook";
	}

}
