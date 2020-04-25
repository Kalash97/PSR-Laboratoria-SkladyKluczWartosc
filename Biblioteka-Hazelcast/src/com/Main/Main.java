package com.Main;

import java.util.Set;

import com.Actions.CreateAuthorAction;
import com.Actions.DeleteAuthorAction;
import com.Entities.Author;
import com.HazelcastInit.HazelcastOperations;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {

	public static void main(String[] args) {

		HazelcastInstance instance = HazelcastOperations.createHazelcastInstance();
		
		IMap<Long, Author> authors = instance.getMap("authors");
		
		CreateAuthorAction caa = new CreateAuthorAction(authors, "Zdzichu", "Sztacheta", (long) 1);
		caa.launch();
		
		CreateAuthorAction caa1 = new CreateAuthorAction(authors, "maniek", "boczek", (long) 2);
		caa1.launch();
		
		DeleteAuthorAction daa = new DeleteAuthorAction(authors, (long) 1);
		daa.launch();
		
		Set<Long> keys = authors.localKeySet();
		for(Long k : keys) {
			System.out.println("Autor o kluczu: "+k);
			System.out.println("Imiê: "+authors.get(k).getName());
			System.out.println("Nazwisko: "+authors.get(k).getLastName());
			System.out.println("");
		}
		
//		TEST
//		HazelcastInstance client = Hazelcast.newHazelcastInstance();
//		
//		IMap<Long, RandomEntity> map = client.getMap("Test");
//		IMap<Long, RandomEntity> map3333 = client.getMap("iuy");
//		
//		RandomEntity rnd = new RandomEntity();
//		rnd.setField1("someEntity");
//		rnd.setField2(1);
//		
//		RandomEntity rnd2 = new RandomEntity();
//		rnd2.setField1("someDifrentEntity");
//		rnd2.setField2(1);
//		
//		map.put((long) 1, rnd);
//		map3333.put((long) 2, rnd2);
//		
////		for(Entry<Long, RandomEntity> e : map.entrySet()) {
////			System.out.println(e.getKey()+" -> " + e.getValue());
////		}
//		
//		Set<Long> keys = map.localKeySet();
//		Set<Long> keys2 = map3333.localKeySet();
//		for(Long k : keys) {
//			System.out.println(k+"->"+map.get(k)+" Field1:"+map.get(k).getField1()+ " Field2:"+map.get(k).getField2());
//		}
//		
//		for(Long k : keys2) {
//			System.out.println(k+"->"+map3333.get(k)+" Field1:"+map3333.get(k).getField1()+ " Field2:"+map3333.get(k).getField2());
//		}
//		
		System.exit(1);
	}

}
