package com.Actions.AuthorBookActions;

import com.Actions.Action;
import com.Entities.Author;
import com.Entities.Book;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class AssignAuthorToBookAction implements Action {

	private MemcachedClient client;
	private ConsoleView cv;

	@Override
	public void launch() {
		cv.print("Podaj klucz autora");
		String authorKey = cv.read();

		Author a;
		try {
			a = (Author) client.get(authorKey);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma autora o takim kluczu");
			return;
		}

		if (a == null) {
			cv.print("Nie ma autora o takim kluczu");
			return;
		}

		cv.print("Podaj klucz ksi¹¿ki");
		String bookKey = cv.read();

		Book b;

		try {
			b = (Book) client.get(bookKey);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma ksi¹¿ki o takim kluczu");
			return;
		}

		if (b == null) {
			cv.print("Nie ma ksi¹¿ki o takim kluczu");
			return;
		}
		
		b.getAuthors().add(a);
		a.getBooks().add(b);
		
		client.set(authorKey, 2000, a);
		client.set(bookKey, 2000, b);
	}
	
	

	@Override
	public String getName() {
		return "AssignAuthorToBook";
	}

}
