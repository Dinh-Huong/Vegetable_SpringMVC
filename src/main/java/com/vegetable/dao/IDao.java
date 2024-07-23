package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import com.vegetable.entities.Pagination;

public interface IDao<E> {
	public List<E> getAll();
	public E getByName(String name);
	public E getBySlug(String slug);
	public List<E> searchByName(String name);
	public E getById(int id);
	public Pagination<E> pagination(int page, int size, Map<String, String> dataMap);
	
	public void insert(E e);
	public void update(E e);
	public void delete(int id);
	
}
