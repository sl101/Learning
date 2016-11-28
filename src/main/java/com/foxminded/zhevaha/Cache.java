package com.foxminded.zhevaha;

import java.util.HashMap;

public class Cache {

	private static HashMap<String, Entity> storage;
	private final static String HELLO = "Hello!";

	public Cache() {
		storage = new HashMap<String, Entity>();
	}

	public static void expire() {

		HashMap<String, Entity> storageClone = (HashMap<String, Entity>) storage
				.clone();

		for (String key : storage.keySet()) {
			if (storage.get(key).isExpired() && !key.equals(HELLO)) {
				storageClone.remove(key);
			}
		}
		storage.clear();
		storage = (HashMap<String, Entity>) storageClone.clone();

	}

	public static HashMap<String, Entity> getStorage() {
		return storage;
	}

	public static void setStorage(HashMap<String, Entity> storage) {
		Cache.storage = storage;
	}

	public static String getHello() {
		return HELLO;
	}

}
