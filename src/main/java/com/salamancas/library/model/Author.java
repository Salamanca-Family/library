package com.salamancas.library.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Author {

	private int id;
	private String name;

	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ArrayList<Author> fromResultSet(ResultSet rs) {
		ArrayList<Author> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new Author(
						rs.getInt(1),
						rs.getString(2)
				));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
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

	@Override
	public String toString() {
		return name;
	}

}
