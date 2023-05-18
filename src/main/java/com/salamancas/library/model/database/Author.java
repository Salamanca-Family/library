package com.salamancas.library.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Author {

	private int id;
	private String name;
	private String surname;

	public Author(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public static ArrayList<Author> fromResultSet(ResultSet rs) {
		ArrayList<Author> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new Author(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
				));
			}
			return list;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}

}
