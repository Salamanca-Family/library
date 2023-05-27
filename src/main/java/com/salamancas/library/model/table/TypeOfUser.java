package com.salamancas.library.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "TYPE_OF_USER", schema = "main")
@IdClass(TypeOfUserPK.class)
public class TypeOfUser {

	private User user;
	private Type type;
	private String dateFrom;
	private String dateTo;

	@ManyToOne
	@Id
	public User getUser() {
		return user;
	}

	public void setUser(User userByUserId) {
		this.user = userByUserId;
	}

	@ManyToOne
	@Id
	public Type getType() {
		return type;
	}

	public void setType(Type typeByTypeId) {
		this.type = typeByTypeId;
	}

	@Id
	@Column(name = "DATE_FROM")
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Column(name = "DATE_TO")
	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

}
