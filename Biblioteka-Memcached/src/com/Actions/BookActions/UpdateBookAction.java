package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class UpdateBookAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("Podaj klucz ksi��ki");
		String key = cv.read();
		
		Book b;
		
		try {
			b = (Book) client.get(key);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma ksi��ki o takim kluczu");
			return;
		}
			
		if(b==null) {
			cv.print("Nie ma ksi�zki o takim kluczu");
			return;
		}
		
		cv.print("Podaj nowy tytu� (je�li nie zmieniasz to naci�nij ENTER)");
		String line = cv.read();
		if (!line.equals("")) {
			b.setTitle(line);
		}
		
		cv.print("Podaj nowy ISBN (je�li nie zmieniasz to naci�nij ENTER)");
		line = cv.read();
		if (!line.equals("")) {
			b.setIsbn(line);
		}
		
		cv.print("Podaj nowy rok wydania (je�li nie zmieniasz to naci�nij ENTER)");
		line = cv.read();
		if (!line.equals("")) {
			String year = getValidYear();
			if (!year.equals("")) {
				int yearOfPublishment = Integer.parseInt(year);
				b.setYearOfPublishment(yearOfPublishment);
			}
		}
		
		client.set(key, 2000, b);
	}

	private String getValidYear() {
		String line;
		do {
			cv.print("Podaj rok wydania");
			line = cv.read();
			if (line.equals("")) {
				return line;
			}
		} while (!ValidUtil.isIntInstance(line));
		return line;
	}
	
	@Override
	public String getName() {
		return "UpdateBook";
	}

}
