package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateReaderAction implements Action{

	private IMap<Long, Reader> readers;
	private ConsoleView view;
	
	@Override
	public void launch() {
		Reader r = new Reader();
		
		view.print("Podaj imiê");
		r.setName(view.read());
		
		view.print("Podaj nazwisko");
		r.setLastName(view.read());
		
		String line=getValidKey();
		Long key = Long.parseLong(line);
		
		readers.put(key, r);
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
		return "CreateReader";
	}

}
