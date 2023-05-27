package com.salamancas.library.model.table;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class TypeOfUserPK implements Serializable {

	private Type type;
	private User user;
	private String dateFrom;

	public TypeOfUserPK() {
	}

	public TypeOfUserPK(Type type, User user, String dateFrom) {
		this.type = type;
		this.user = user;
		this.dateFrom = dateFrom;
	}

	@ManyToOne
	@JoinColumn(name = "TYPE_ID")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "DATE_FROM")
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

}
