package com.foxminded.zhevaha.task_10.domain;

public class Room {
	private long id;
	private String name;

	public Room(String name) {
		this.name = name;
	}

	public class RootRoom {
		Room room;

		public void setRoomId(Long id) {
			room.setId(id);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

}
