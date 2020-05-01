package com.Main;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.Entities.Author;

import net.spy.memcached.MemcachedClient;

public class Main {

	public static void main(String[] args) {
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			
			Author a = new Author();
			a.setName("Heniek");
			a.setLastName("B¹k");
			
			client.set("Author1", 2000, a);
			
			Author cachedAuthor = (Author) client.get("Author1");
			System.out.println("Autor: "+ cachedAuthor + "   " + cachedAuthor.getName()+" "+cachedAuthor.getLastName());
			
//			/////////First tests
//			RandomObject r = new RandomObject();
//			r.setField("randomField");
//			client.set("1", 2000, r);
//		
//			RandomObject object = (RandomObject) client.get("1");
//			System.out.println(object.getField());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
