package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteBookAction implements Action{

	private IMap<Long, Book> books;
	private ConsoleView view;
	
	@Override
	public void launch() {
		String line=getValidKey();
		Long key = Long.parseLong(line);
		books.delete(key);
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
		return "DeleteBook";
	}

}
