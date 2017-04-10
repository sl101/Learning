package com.foxminded.zhevaha.task_10.dao;

import java.util.Set;

public interface GenericDao<T, E> {

	public Set<T> getAll() throws UniverException;

	public T getById(E id) throws UniverException;

	public T update(T entity) throws UniverException;

	public void delete(T id) throws UniverException;

	public void create(T entity) throws UniverException;

}
