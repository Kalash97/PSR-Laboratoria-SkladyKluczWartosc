package com.Main;

import java.util.ArrayList;
import java.util.List;

import com.Actions.Action;
import com.Actions.ExitAction;
import com.Actions.AuthorActions.CreateAuthorAction;
import com.Actions.AuthorActions.DeleteAuthorAction;
import com.Actions.AuthorActions.ReadAuthorByKeyAction;
import com.Actions.AuthorActions.UpdateAuthorAction;
import com.Actions.BookActions.CreateBookAction;
import com.Actions.BookActions.DeleteBookAction;
import com.Actions.BookActions.ReadBookByKeyAction;
import com.Actions.BookActions.UpdateBookAction;
import com.Actions.ReaderActions.CreateReaderAction;
import com.Actions.ReaderActions.DeleteReaderAction;
import com.Actions.ReaderActions.ReadReaderByKeyAction;
import com.Actions.ReaderActions.UpdateReaderAction;
import com.Entities.Book;
import com.MemcachedInit.MemcachedProvider;
import com.View.ConsoleView;

import net.spy.memcached.MemcachedClient;


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
		MemcachedClient client = MemcachedProvider.getClient();
		actions = new ArrayList<Action>();
		cv = new ConsoleView();
		
		//Test data init
		Book b = new Book();
		b.setTitle("Initial book");
		b.setIsbn("111-1111");
		b.setYearOfPublishment(2020);
		client.set("book1", 2000, b);
		
		actions.add(new CreateAuthorAction(client, cv));
		actions.add(new ReadAuthorByKeyAction(client, cv));
		actions.add(new DeleteAuthorAction(client, cv));
		actions.add(new UpdateAuthorAction(client, cv));
		
		actions.add(new CreateBookAction(client, cv));
		actions.add(new ReadBookByKeyAction(client, cv));
		actions.add(new DeleteBookAction(client, cv));
		actions.add(new UpdateBookAction(client, cv));
		
		actions.add(new CreateReaderAction(client, cv));
		actions.add(new ReadReaderByKeyAction(client, cv));
		actions.add(new DeleteReaderAction(client, cv));
		actions.add(new UpdateReaderAction(client, cv));
		
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
