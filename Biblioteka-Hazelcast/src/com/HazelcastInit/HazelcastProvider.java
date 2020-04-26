package com.HazelcastInit;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastProvider {

	public static HazelcastInstance getInstance() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		return instance;
	}
}
