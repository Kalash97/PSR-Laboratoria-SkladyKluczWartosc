package com.Actions.ReaderActions;

import java.util.Set;

import com.Actions.Action;
import com.Entities.Reader;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadAllReadersAction implements Action{

	private IMap<Long, Reader> readers;
	private ConsoleView view;
	
	@Override
	public void launch() {
		Set<Long> keys = readers.localKeySet();
		for(Long k : keys) {
			view.print("Czytelnik o kluczu: "+k);
			view.print("Imiê: "+readers.get(k).getName());
			view.print("Nazwisko: "+readers.get(k).getLastName());
			view.print("");
		}
	}

	@Override
	public String getName() {
		return "ReadAllReaders";
	}

}
