package com.salamancas.library.model.legacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Town {

	private int id;
	private String name;

	public static ArrayList<Town> fromResultSet(ResultSet rs) {
		ArrayList<Town> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new Town(
						rs.getInt(1),
						rs.getString(2)
				));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Town(int id, String name) {
		this.id = id;
		this.name = name;
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
		return "[" + id + "] " + name;
	}

}
