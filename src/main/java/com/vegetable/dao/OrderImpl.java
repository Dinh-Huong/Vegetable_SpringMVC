package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vegetable.entities.Order;
import com.vegetable.entities.Pagination;
import com.vegetable.entities.Product;

@Repository
public class OrderImpl implements IDao<Order>{
	@Autowired(required = true)
	SessionFactory sessionFactory;
	
	@Override
	public List<Order> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Order> data = session.createQuery("from Order order by createdAt desc").getResultList();
		session.getTransaction().commit();;
		session.close();
		return data;
	}

	@Override
	public Order getByName(String name) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
//			chuwa xu li on
			Query query = session.createQuery("from Order where name = :name");
			query.setParameter("name", name);
			Order order = (Order) query.getSingleResult();
			session.getTransaction().commit();
			return order;			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Order getBySlug(String slug) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Order where slug = :slug");
			query.setParameter("slug", slug);
			Order order = (Order) query.getSingleResult();
			session.getTransaction();
			session.close();
			return order;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Order> searchByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Order where name like :name");
		query.setParameter("name", "%"+name+"%");
		List<Order> data = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}

	@Override
	public Order getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Order order = session.get(Order.class, id);
		session.getTransaction().commit();
		session.close();
		return order;
	}
	
	public Order getByUserId(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Order where userId= : userId");
		query.setParameter("userId", userId);
		Order order = (Order) query.getSingleResult();
		session.getTransaction().commit();
		session.close();
		return order;
	}
 
	
	public Order getByStatusAndUserId(int status, Integer userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Order where status = :status and userId = :userId");
		query.setParameter("status", status);
		query.setParameter("userId", userId);
		Order order = query.getResultList().size() != 0 ? (Order) query.getResultList().get(0) : null;
		
		session.getTransaction().commit();
		session.close();
		return order;
	}

	@Override
	public Pagination<Order> pagination(int page, int size, Map<String, String> dataMap) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from Order where status <> 0";
		
		if(dataMap.containsKey("q")) {
			sql += " and name like " + "'%"+ dataMap.get("q") + "%'"; 
		}
		System.out.print(sql);
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<Order> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Pagination<Order> pagination = new Pagination<Order>(data, totalPage, size, page);
		session.getTransaction().commit();
		session.close();
		return pagination;
	}

	@Override
	public void insert(Order e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(Order e) {
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
		Order order = session.get(Order.class, id);
		session.delete(order);
		session.getTransaction().commit();
		session.close();
		
	}

}
