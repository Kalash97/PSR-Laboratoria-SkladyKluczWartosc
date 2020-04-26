package com.Actions.ReaderActions;

import com.Actions.Action;
import com.Entities.Reader;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteReaderAction implements Action{

	private IMap<Long, Reader> readers;
	private ConsoleView view;
	
	@Override
	public void launch() {
		String line = getValidKey();
		Long key = Long.parseLong(line );
		readers.delete(key);
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
		return "DeleteReader";
	}
	
}
