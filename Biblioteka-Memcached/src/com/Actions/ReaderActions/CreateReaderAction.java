package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class CreateReaderAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Reader r = new Reader();
		
		cv.print("Podaj imiê");
		r.setName(cv.read());

		cv.print("Podaj nazwisko");
		r.setLastName(cv.read());
		
		cv.print("Podaj klucz");
		String key = cv.read();
		
		client.set(key, 2000, r);
	}

	@Override
	public String getName() {
		return "CreateReader";
	}

}
