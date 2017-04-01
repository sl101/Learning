package com.foxminded.zhevaha.task_10.dao;

import java.sql.SQLException;

public class DaoException extends SQLException {

	public DaoException(String message) {
		super("\n" + message);
	}
}
