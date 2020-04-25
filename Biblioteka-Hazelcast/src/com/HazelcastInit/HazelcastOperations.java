package com.HazelcastInit;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastOperations {

	public static HazelcastInstance createHazelcastInstance() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		return instance;
	}
}
