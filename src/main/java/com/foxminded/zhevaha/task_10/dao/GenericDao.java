package com.foxminded.zhevaha.task_10.dao;

import java.util.Set;

public interface GenericDao<T, E> {

	public Set<T> getAll();

	public T getById(E id);

	public T update(T entity);

	public void delete(T id);

	public void create(T entity);

}
