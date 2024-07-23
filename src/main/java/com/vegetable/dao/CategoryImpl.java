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

@Repository
public class CategoryImpl implements IDao<Category>{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Category> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category> result = session.createQuery("from Category order by id desc").getResultList();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public Category getByName(String name) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Category where name = :name");
			query.setParameter("name", name);
			Category category = (Category) query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return category;
		} catch (Exception e) {
			return null;
		}
	}
	public List<Category> getByStatus(int status) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Category where status = :status");
			query.setParameter("status", status);
			List<Category> categorys = query.getResultList();
			session.getTransaction().commit();
			session.close();
			return categorys;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Category getBySlug(String slug) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Category where slug = :slug");
			query.setParameter("slug", slug);
			Category category = (Category) query.getSingleResult();
			session.getTransaction();
			session.close();
			return category;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> searchByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Category where name like :name");
		query.setParameter("name", "%"+name+"%");
		List<Category> data = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}

	@Override
	public Category getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Category category = session.get(Category.class, id);
		session.getTransaction().commit();
		session.close();
		return category;
	}

	@Override
	public void insert(Category e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(Category e) {
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
		Category category = session.get(Category.class, id);
		session.delete(category);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Pagination<Category> pagination(int page, int size, Map<String, String> dataMap) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from Category";
		
		if(dataMap.containsKey("q")) {
			sql += " where name like " + "'%"+ dataMap.get("q") + "%'"; 
		}
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<Category> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Pagination<Category> pagination = new Pagination<Category>(data, totalPage, size, page);
		session.getTransaction().commit();
		return pagination;
	}

}
