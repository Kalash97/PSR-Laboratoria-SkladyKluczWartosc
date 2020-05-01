package com.Actions;

import com.Entities.Author;
import com.View.ConsoleView;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;

@AllArgsConstructor
public class CreateAuthorAction implements Action{

	private MemcachedClient client;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Author a = new Author();
		cv.print("Podaj imiê");
		a.setName(cv.read());

		cv.print("Podaj nazwisko");
		a.setLastName(cv.read());
		
		cv.print("Podaj klucz");
		String key = cv.read();
		
		client.set(key, 2000, a);
	}

	@Override
	public String getName() {
		return "CreateAuthor";
	}

}
