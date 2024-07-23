package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vegetable.entities.OrderDetail;
import com.vegetable.entities.Pagination;
@Repository
public class OrderDetailImpl implements IDao<OrderDetail>{
	@Autowired(required = true)
	SessionFactory sessionFactory;
	
	@Override
	public List<OrderDetail> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<OrderDetail> data = session.createQuery("from OrderDetail order by desc").getResultList();
		session.getTransaction().commit();;
		session.close();
		return data;
	}

	@Override
	public OrderDetail getByName(String name) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
//			chuwa xu li on
			Query query = session.createQuery("from OrderDetail where name = :name");
			query.setParameter("name", name);
			OrderDetail detail =  (OrderDetail) query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return detail;			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public OrderDetail getBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		OrderDetail detail = session.get(OrderDetail.class, id);
		session.getTransaction().commit();
		session.close();
		return detail;
	}
	
	public List<OrderDetail> getByOrderId(int orderId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from OrderDetail where orderId = :orderId");
		query.setParameter("orderId", orderId);
		List<OrderDetail> data =  query.getResultList();
		session.getTransaction().commit();
		session.close();
		return data;
	}

	@Override
	public Pagination<OrderDetail> pagination(int page, int size, Map<String, String> dataMap) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from OrderDetail";
		
		if(dataMap.containsKey("q")) {
			sql += " where name like " + "'%"+ dataMap.get("q") + "%'"; 
		}
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<OrderDetail> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Pagination<OrderDetail> pagination = new Pagination<OrderDetail>(data, totalPage, size, page);
		session.getTransaction().commit();
		session.close();
		return pagination;
	}

	@Override
	public void insert(OrderDetail e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(OrderDetail e) {
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
		OrderDetail order = session.get(OrderDetail.class, id);
		session.delete(order);
		session.getTransaction().commit();
		session.close();
	}

}
