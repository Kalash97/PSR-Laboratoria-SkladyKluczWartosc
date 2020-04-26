package com.Main;

import java.util.ArrayList;
import java.util.List;

import com.Actions.Action;
import com.Actions.ExitAction;
import com.Actions.AuthorActions.CreateAuthorAction;
import com.Actions.AuthorActions.DeleteAuthorAction;
import com.Actions.AuthorActions.ReadAllAuthors;
import com.Actions.AuthorActions.ReadAuthorByKeyAction;
import com.Actions.AuthorActions.UpdateAuthorAction;
import com.Actions.BookActions.CreateBookAction;
import com.Actions.BookActions.DeleteBookAction;
import com.Actions.BookActions.ReadAllBooksAction;
import com.Actions.BookActions.ReadBookByKeyAction;
import com.Actions.BookActions.UpdateBookAction;
import com.Actions.ReaderActions.CreateReaderAction;
import com.Actions.ReaderActions.DeleteReaderAction;
import com.Actions.ReaderActions.ReadAllReadersAction;
import com.Actions.ReaderActions.ReadReaderByKeyAction;
import com.Actions.ReaderActions.UpdateReaderAction;
import com.Entities.Author;
import com.Entities.Book;
import com.Entities.Reader;
import com.HazelcastInit.HazelcastProvider;
import com.View.ConsoleView;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {

	private static List<Action> actions;
	private static ConsoleView cv;
	
	public static void main(String[] args) {

		init();
		
		while (true) {
			cv.print("Lista dostêpnych akcji:");
			showActions();
			cv.print("");
			cv.print("Podaj akcjê");
			runAction(cv.read());
		}

	}
	
	public static void init() {
		HazelcastInstance instance = HazelcastProvider.getInstance();
		IMap<Long, Author> authors = instance.getMap("authors");
		IMap<Long, Book> books = instance.getMap("books");
		IMap<Long, Reader> readers = instance.getMap("readers");
		
		actions = new ArrayList<Action>();		
		cv = new ConsoleView();
		
		//Test data
		Author a = new Author();
		a.setName("Maniek");
		a.setLastName("B¹k");
		authors.put((long) 9, a);
		
		Book b = new Book();
		b.setTitle("Random title");
		b.setIsbn("1111-11-1111-111");
		b.setYearOfPublishment(1972);
		books.put((long) 9, b);
		
		Reader r = new Reader();
		r.setName("Krzysztof");
		r.setLastName("Jarzyna ze Szczecina");
		readers.put((long) 9, r);
		
		actions.add(new CreateAuthorAction(authors, cv));
		actions.add(new DeleteAuthorAction(authors, cv));
		actions.add(new UpdateAuthorAction(authors, cv));
		actions.add(new ReadAllAuthors(authors, cv));
		actions.add(new ReadAuthorByKeyAction(authors, cv));
		
		actions.add(new CreateBookAction(books, cv));
		actions.add(new DeleteBookAction(books, cv));
		actions.add(new UpdateBookAction(books, cv));
		actions.add(new ReadAllBooksAction(books, cv));
		actions.add(new ReadBookByKeyAction(books, cv));
		
		actions.add(new CreateReaderAction(readers, cv));
		actions.add(new DeleteReaderAction(readers, cv));
		actions.add(new UpdateReaderAction(readers, cv));
		actions.add(new ReadAllReadersAction(readers, cv));
		actions.add(new ReadReaderByKeyAction(readers, cv));
		
		actions.add(new ExitAction());
	}
	
	private static void runAction(String name) {
		for (Action a : actions) {
			if (name.equals(a.getName())) {
				launchAction(a);
				return;
			}
		}
		cv.print("Nie ma takiej akcji: " + name);
	}
	
	private static void launchAction(Action a) {
			a.launch();
	}
	
	private static void showActions() {
		for (Action a : actions) {
			cv.print(" " + a.getName());
		}
	}

}
