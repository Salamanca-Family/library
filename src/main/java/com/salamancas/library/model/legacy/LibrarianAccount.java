package com.salamancas.library.model.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianAccount {

	private int id;
	private int userId;
	private String name;
	private String username;
	private String password;

	public LibrarianAccount(int id, int userId, String name, String username, String password) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public static LibrarianAccount fromResultSet(ResultSet rs) {
		try {
			if(!rs.next()) {
				return null;
			}
			return new LibrarianAccount(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3) + " " + rs.getString(4),
					rs.getString(5),
					rs.getString(6)
			);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return name;
	}

}
