package com.foxminded.zhevaha;

import java.util.HashMap;

public class Cache {

	private static HashMap<String, Entity> storage;
	private final static String HELLO_WORLD = "Hello World!";

	public Cache() {
		storage = new HashMap<String, Entity>();
	}

	public static void expire() {
		int beforeClean = storage.size();

		HashMap<String, Entity> storageClone = (HashMap<String, Entity>) storage
				.clone();

		for (String key : storage.keySet()) {
			if (storage.get(key).isExpired() && !key.equals(HELLO_WORLD)) {
				storageClone.remove(key);
			}
		}
		storage.clear();
		storage = (HashMap<String, Entity>) storageClone.clone();
		int afterClean = storage.size();
		if (afterClean < beforeClean) {

			System.out.println("cache size before / after clean: \n"
					+ beforeClean + " / " + afterClean);
		}

	}

	public static HashMap<String, Entity> getStorage() {
		return storage;
	}

	public static void setStorage(HashMap<String, Entity> storage) {
		Cache.storage = storage;
	}

	public static String getHelloWorld() {
		return HELLO_WORLD;
	}

}
