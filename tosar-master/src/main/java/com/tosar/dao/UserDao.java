package com.tosar.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tosar.model.User;

@Repository
public class UserDao extends AbstractHibernateDAO<User> {
	public UserDao() {
		setClazz(User.class);
	}

	@Transactional
	public User getUserByEmail(String email) {
		Query query = getCurrentSession().createQuery("from User where email=:email");
		query.setString("email", email);
		return (User) query.uniqueResult();
	}

	@Transactional
	public User getUserByName(String name) {
		Query query = getCurrentSession().createQuery("from User where name=:name");
		query.setString("name", name);
		return (User) query.uniqueResult();
	}

	@Transactional
	public User getUserByUserName(String username) {
		Query query = getCurrentSession().createQuery("from User where username=:username");
		query.setString("username", username);
		return (User) query.uniqueResult();
	}

	@Transactional
	public boolean isUsernameExist(String username) {
		Query query = getCurrentSession().createQuery("from User where username=:username");
		query.setString("username", username);
		List<?> list = query.list();

		if (list.size() > 0) return true;
		else return false;
	}
}
