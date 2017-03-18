package com.foxminded.zhevaha.task_10.dao;

import java.util.Set;

public interface DaoFactory<T, E> {

	public Set<T> getAll();

	public T getEntityById(E id);

	public T update(T entity);

	public void delete(T id);

	public void create(T entity);

}
