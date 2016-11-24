package com.foxminded.zhevaha;

import java.util.HashMap;

public class Entity {
    private HashMap<String, Integer> value;
    private long birthTime;
    private final int LIFE_LIMIT = 1;

    public Entity(HashMap<String, Integer> value, long birthTime) {
        this.value = value;
        this.birthTime = birthTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > (birthTime+LIFE_LIMIT);
    }
    

	public HashMap<String, Integer> getValue() {
		return value;
	}

	public void setValue(HashMap<String, Integer> value) {
		this.value = value;
	}

	public long getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(long birthTime) {
		this.birthTime = birthTime;
	}
    
    
}
