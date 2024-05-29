package com.tosar.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tosar.model.Comment;
import com.tosar.model.User;

@Repository
public class CommentDao extends AbstractHibernateDAO<Comment> {
	public CommentDao() {
		setClazz(Comment.class);
	}
	@Transactional
	public User getUserByUserId(int userId) {
		Query query = getCurrentSession().createQuery("from Comment where userId=:userId");
		query.setInteger("username", userId);
		return (User) query.uniqueResult();
	}
}
