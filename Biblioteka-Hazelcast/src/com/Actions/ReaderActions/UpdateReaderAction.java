package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateReaderAction implements Action{

	private IMap<Long, Reader> readers;
	private ConsoleView view;
	
	@Override
	public void launch() {
		String line=getValidKey();
		Long key = Long.parseLong(line);
		Reader r = readers.get(key);
		if (r == null) {
			view.print("Nie ma takiego czytelnika!");
			return;
		}
		
		view.print("Podaj nowe imiê (jeœli nie zmieniasz to naciœnij ENTER)");
		line = view.read();
		if (!line.equals("")) {
			r.setName(line);
		}
		
		view.print("Podaj nowe nazwisko (jeœli nie zmieniasz to naciœnij ENTER)");
		line = view.read();		
		if (!line.equals("")) {
			r.setLastName(line);
		}
		
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
		return "UpdateReaderAction";
	}

}
