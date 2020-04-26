package com.Actions.BookActions;

import com.Actions.Action;
import com.Entities.Book;
import com.Utils.ValidUtil;
import com.View.ConsoleView;
import com.hazelcast.map.IMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBookAction implements Action {

	private IMap<Long, Book> books;
	private ConsoleView view;

	@Override
	public void launch() {
		String line = getValidKey();
		Long key = Long.parseLong(line);
		Book b = books.get(key);
		if (b == null) {
			view.print("Nie ma takiej ksi¹¿ki!");
			return;
		}

		view.print("Podaj nowy tytu³ (jeœli nie zmieniasz to naciœnij ENTER)");
		line = view.read();
		if (!line.equals("")) {
			b.setTitle(line);
		}

		view.print("Podaj nowy ISBN (jeœli nie zmieniasz to naciœnij ENTER)");
		line = view.read();
		if (!line.equals("")) {
			b.setIsbn(line);
		}

		view.print("Podaj nowy rok wydania (jeœli nie zmieniasz to naciœnij ENTER)");
		line = view.read();
		if (!line.equals("")) {
			String year = getValidYear();
			if (!year.equals("")) {
				int yearOfPublishment = Integer.parseInt(year);
				b.setYearOfPublishment(yearOfPublishment);
			}
		}

		books.put(key, b);
	}

	private String getValidKey() {
		String line;
		do {
			view.print("Podaj klucz");
			line = view.read();
		} while (!ValidUtil.isLongInstance(line));
		return line;
	}

	private String getValidYear() {
		String line;
		do {
			view.print("Podaj rok wydania");
			line = view.read();
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
