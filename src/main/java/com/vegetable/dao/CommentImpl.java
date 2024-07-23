package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vegetable.entities.Comment;
import com.vegetable.entities.Pagination;

@Repository
public class CommentImpl implements IDao<Comment>{
	@Autowired(required = true)
	SessionFactory sessionFactory;
	
	@Override
	public List<Comment> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Comment> data = session.createQuery("from Comment order by id desc").getResultList();
		session.getTransaction().commit();
		return data;
	}

	@Override
	public Comment getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Comment comment = session.get(Comment.class, id);
		session.getTransaction().commit();
		return comment;
	}

	@Override
	public Pagination<Comment> pagination(int page, int size, Map<String, String> dataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Comment e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		
	}

	@Override
	public void update(Comment e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Comment comment = session.get(Comment.class, id);
		session.delete(comment);
		session.getTransaction().commit();
		
	}

}
