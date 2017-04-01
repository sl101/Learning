package com.foxminded.zhevaha.task_10.dao;

import java.sql.SQLException;
import java.util.Set;

public interface GenericDao<T, E> {

	public Set<T> getAll() throws SQLException;

	public T getById(E id) throws SQLException;

	public T update(T entity) throws SQLException;

	public void delete(T id) throws SQLException;

	public void create(T entity) throws SQLException;

}
