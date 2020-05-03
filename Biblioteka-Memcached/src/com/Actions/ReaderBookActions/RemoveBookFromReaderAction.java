package com.Actions.ReaderBookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Entities.Reader;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class RemoveBookFromReaderAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("Podaj klucz czytelnika");
		String readerKey = cv.read();
		
		Reader r;
		
		try {
			r = (Reader) client.get(readerKey);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma czytelnika o takim kluczu");
			return;
		}
			
		if(r==null) {
			cv.print("Nie ma czytelnika o takim kluczu");
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
		
		if(b==null) {
			cv.print("Nie ma ksi¹¿ki o takim kluczu");
			return;
		}
		
		for(Book book : r.getBooks()) {
			if(book.getTitle().equals(b.getTitle())) {
				r.getBooks().remove(book);
				break;
			}
		}
		
		client.set(readerKey, 2000, r);
	}

	@Override
	public String getName() {
		return "RemoveBookFromReader";
	}

}
