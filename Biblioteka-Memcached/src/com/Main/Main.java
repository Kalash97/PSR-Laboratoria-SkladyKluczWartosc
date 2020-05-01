package com.Main;

import java.util.ArrayList;
import java.util.List;

import com.Actions.Action;
import com.Actions.CreateAuthorAction;
import com.Actions.ExitAction;
import com.Actions.ReadAuthorByKeyAction;
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
				
//			Author a = new Author();
//			a.setName("Heniek");
//			a.setLastName("B¹k");
//			client.set("Author1", 2000, a);
//			
//			Author cachedAuthor = (Author) client.get("Author1");
//			System.out.println("Autor: "+ cachedAuthor + "   " + cachedAuthor.getName()+" "+cachedAuthor.getLastName());
//			
//			/////////First tests
//			RandomObject r = new RandomObject();
//			r.setField("randomField");
//			client.set("1", 2000, r);
//		
//			RandomObject object = (RandomObject) client.get("1");
//			System.out.println(object.getField());		
	}
	
	public static void init() {
		MemcachedClient client = MemcachedProvider.getClient();
		actions = new ArrayList<Action>();
		cv = new ConsoleView();
		
		Book b = new Book();
		b.setTitle("ssss");
		
		client.set("book", 2000, b);
		
		actions.add(new CreateAuthorAction(client, cv));
		actions.add(new ReadAuthorByKeyAction(client, cv));
		
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
