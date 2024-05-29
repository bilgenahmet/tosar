package com.tosar.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 8434980226425245115L;

	@Id
	@GeneratedValue
	private int id;
	private String title;

	@Column(length = 3000)
	private String message;
	private Date date;
	private Date updateDate;
	private String category;

	@ManyToOne
	@JoinColumn(name = "ownerId", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "message",fetch=FetchType.EAGER)
	private Set<Comment> comments;
	
//	@OneToMany(mappedBy = "message",fetch=FetchType.EAGER)
	private int vote;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Message)) return false;
		Message other = (Message) obj;
		if (id != other.id) return false;
		if (owner == null) {
			if (other.owner != null) return false;
		} else if (!owner.equals(other.owner)) return false;
		return true;
	}
}
