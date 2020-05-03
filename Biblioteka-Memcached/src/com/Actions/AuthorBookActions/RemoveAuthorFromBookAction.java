package com.Actions.AuthorBookActions;

import java.util.Iterator;

import com.Actions.Action;
import com.Entities.Author;
import com.Entities.Book;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class RemoveAuthorFromBookAction implements Action{

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
			
		for(Iterator<Author> itA = b.getAuthors().iterator();itA.hasNext();) {
			Author next = itA.next();
			if(next.getName().equals(a.getName()) && next.getLastName().equals(a.getLastName())) {
				itA.remove();
			}
		}
		
		for(Iterator<Book> itB = a.getBooks().iterator();itB.hasNext();) {
			Book next = itB.next();
			if(next.getTitle().equals(b.getTitle())) {
				itB.remove();
			}
		}
		
		client.set(authorKey, 2000, a);
		client.set(bookKey, 2000, b);
		
	}

	@Override
	public String getName() {
		return "RemoveAuthorFromBook";
	}

}
