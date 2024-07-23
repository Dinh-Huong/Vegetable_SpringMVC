package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vegetable.entities.Category;
import com.vegetable.entities.Pagination;
import com.vegetable.entities.Users;

@Repository
public class AccountImpl implements IDao<Users>{
	@Autowired(required = true)
	SessionFactory sessionFactory;
	
	@Override
	public List<Users> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Users> result = session.createQuery("from Users order by id desc").getResultList();
		session.getTransaction().commit();
		session.close(); 
		return result;
	}

	@Override
	public Users getByName(String name) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Users where name = :name");
			query.setParameter("name", name);
			Users users = (Users) query.getSingleResult();	
			session.getTransaction().commit();
			session.close(); 
			return users;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Users getByEmail(String email) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Users where email = :email");
			query.setParameter("email", email);
			Users users = (Users) query.getSingleResult();	
			session.getTransaction().commit();
			session.close();
			return users;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Users getBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Users users = session.get(Users.class, id);
		session.getTransaction().commit();
		session.close(); 
		return users;
	}

	@Override
	public Pagination<Users> pagination(int page, int size, Map<String, String> dataMap) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from Users";	
		if(dataMap.get("role").equals("1")) sql += "where role = 1";
		else sql += "where role = 0";
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<Users> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Pagination<Users> pagination = new Pagination<Users>(data, totalPage, size, page);
		session.getTransaction().commit();
		return pagination;
	}

	@Override
	public void insert(Users e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(Users e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close(); 
		
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Users users = session.get(Users.class, id);
		session.delete(users);
		session.getTransaction().commit();
		session.close(); 
		
	}

}
