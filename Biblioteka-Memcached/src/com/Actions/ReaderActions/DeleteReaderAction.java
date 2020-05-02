package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class DeleteReaderAction implements Action{

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
		
		client.delete(key);
	}

	@Override
	public String getName() {
		return "DeleteReader";
	}

}
