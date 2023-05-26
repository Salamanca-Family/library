package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {

	private int userId;
	private String userName;
	private String userSurname;
	private String userBirthDate;
	private String userAddress;
	private Collection<Account> accounts;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "USER_SURNAME")
	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	@Basic
	@Column(name = "USER_BIRTH_DATE")
	public String getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	@Basic
	@Column(name = "USER_ADDRESS")
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@OneToMany(mappedBy = "user")
	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return userName + " " + userSurname;
	}

}
