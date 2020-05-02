package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class UpdateReaderAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		cv.print("Podaj klucz czytelnika");
		String key = cv.read();
		
		Reader r;
		
		try {
			r = (Reader) client.get(key);
		} catch (ClassCastException | IllegalArgumentException e) {
			cv.print("Nie ma czytelnika o takim kluczu");
			return;
		}
			
		if(r==null) {
			cv.print("Nie ma czytelnika o takim kluczu");
			return;
		}
		
		cv.print("Podaj nowe imiê (jeœli nie zmieniasz to naciœnij ENTER)");
		String line = cv.read();
		if (!line.equals("")) {
			r.setName(line);
		}
		
		cv.print("Podaj nowe nazwisko (jeœli nie zmieniasz to naciœnij ENTER)");
		line = cv.read();		
		if (!line.equals("")) {
			r.setLastName(line);
		}
		
		client.set(key, 2000, r);
	}

	@Override
	public String getName() {
		return "UpdateReader";
	}

}
