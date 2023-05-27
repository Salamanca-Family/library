package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Type {

	private Integer typeId;
	private String typeName;
	private Collection<TypeOfUser> users;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TYPE_ID")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Basic
	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(mappedBy = "type")
	public Collection<TypeOfUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<TypeOfUser> users) {
		this.users = users;
	}

}
