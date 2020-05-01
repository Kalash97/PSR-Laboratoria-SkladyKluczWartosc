package com.MemcachedInit;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedProvider {

	public static MemcachedClient getClient() {
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			return client;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
