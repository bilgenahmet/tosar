package com.tosar.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T extends Serializable> {
	protected Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	public void setClazz(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Transactional
	public T findOne(final int id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Transactional
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@Transactional
	public void save(final T entity) {
		getCurrentSession().persist(entity);
	}

	@Transactional
	public void saveOrUpdate(final T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public T update(final T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	@Transactional
	public void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(final int id) {
		final T entity = findOne(id);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
