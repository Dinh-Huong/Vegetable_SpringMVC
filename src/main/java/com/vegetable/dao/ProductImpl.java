package com.vegetable.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vegetable.entities.Pagination;
import com.vegetable.entities.Product;

@Repository
public class ProductImpl implements IDao<Product>{
	@Autowired(required = true)
	SessionFactory sessionFactory;
	
	@Override
	public List<Product> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> data = session.createQuery("from Product order by id desc").getResultList();
		session.getTransaction().commit();
		return data;
	}

	@Override
	public Product getByName(String name) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Product where name = :name");
			query.setParameter("name", name);
			Product product = (Product) query.getSingleResult();
			session.getTransaction().commit();
			return product;			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getBySlug(String slug) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Product where slug = :slug");
			query.setParameter("slug", slug);
			Product product = (Product) query.getSingleResult();
			session.getTransaction();
			session.close();
			return product;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> searchByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Product where name like :name");
		query.setParameter("name", "%"+name+"%");
		List<Product> data = query.getResultList();
		session.getTransaction().commit();
		return data;
	}

	@Override
	public Product getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product product = session.get(Product.class, id);
		session.getTransaction().commit();
		return product;
	}
	
	public List<Product> getByStatus(int status) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Product where status = :status").setMaxResults(5);
			query.setParameter("status", status);
			List<Product> products = query.getResultList();
			session.getTransaction().commit();
			session.close();
			return products;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Product> getByType(int status, int top){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//from Product pro join Category cate on pro.categoryId = cate.id where cate.status = :status
		Query query = session.createQuery("select pro from Product pro join pro.category cate where cate.status = :status").setMaxResults(top);
		query.setParameter("status", status);
		List<Product> listProduct = query.getResultList();
		session.getTransaction().commit();
		return listProduct;
	}
	
	public List<Product> getByCategory(String value, int top) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		from Product pro join Category cate on pro.categoryId = cate.id where cate.name = :value
		Query query = session.createQuery("from Product pro join Category cate on pro.categoryId = cate.id where cate.name = :value").setMaxResults(top);
		query.setParameter("value", value);
		List<Product> listProduct = query.getResultList();
		session.getTransaction().commit();
		return listProduct;
	}
	
	@Override
	public Pagination<Product> pagination(int page, int size, Map<String, String> dataMap) {
		boolean hasCondition = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sql = "from Product pro where";
		
		if(dataMap.containsKey("q")) {
			sql += " name like " + "'%"+ dataMap.get("q") + "%' and "; 
			hasCondition = true;
		}
		if(dataMap.containsKey("cate")) {
			sql += " categoryId =" + dataMap.get("cate") + " and "; 
			hasCondition = true;
		}
		if(dataMap.containsKey("fruits")) {
			sql += " pro.category.status = 0 and "; 
			hasCondition = true;
		} 
		if(dataMap.containsKey("vegetables")) {
			sql += " pro.category.status = 1 and "; 
			hasCondition = true;
		} 
		if(hasCondition != true) {
			sql = "from Product";
		} else 
			sql = sql.substring(0, sql.length() - 4);
		
		System.out.print(sql);
		Query query = session.createQuery(sql);
		int totalPage = (int) Math.ceil((double)query.getResultList().size()/size);
		List<Product> data = query.setFirstResult((page-1)*size).setMaxResults(size).getResultList();
		Pagination<Product> pagination = new Pagination<Product>(data, totalPage, size, page);
		session.getTransaction().commit();
		return pagination;
	}

	@Override
	public void insert(Product e) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(Product e) {
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
		Product product = session.get(Product.class, id);
		session.delete(product);
		session.getTransaction().commit();
		session.close();
	}

}
