package com.tosar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 8719973078147080036L;

	@Id
	@GeneratedValue
	public int id;
	public String comment;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	public User user;

	@ManyToOne
	@JoinColumn(name = "messageId", nullable = false)
	public Message message;

	public Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Comment)) return false;
		Comment other = (Comment) obj;
		if (id != other.id) return false;
		if (user == null) {
			if (other.user != null) return false;
		} else if (!user.equals(other.user)) return false;
		return true;
	}
}
