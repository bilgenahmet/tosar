package com.tosar.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tosar.model.Message;

@Repository
public class MessageDao extends AbstractHibernateDAO<Message> {
	public MessageDao() {
		setClazz(Message.class);
	}
	@Transactional
	public List<Message> getMessageByUserId(int ownerId) {
		Query query = getCurrentSession().createQuery("from Message where ownerId=:ownerId");
		query.setInteger("ownerId", ownerId);
		@SuppressWarnings("unchecked")
		List<Message> list = (List<Message>)query.list();
		return list;
	}

}
