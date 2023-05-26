package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Account {

	private Integer accountId;
	private String accountUsername;
	private String accountPassword;
	private User user;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ACCOUNT_ID")
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Basic
	@Column(name = "ACCOUNT_USERNAME")
	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	@Basic
	@Column(name = "ACCOUNT_PASSWORD")
	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(accountId, account.accountId) && Objects.equals(accountUsername, account.accountUsername) && Objects.equals(accountPassword, account.accountPassword);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountUsername, accountPassword);
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
