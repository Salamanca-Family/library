package com.salamancas.library.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Book {

	private int id;
	private String title;
	private String isbn;
	private String isbnOld;

	public static ArrayList<Book> fromResultSet(ResultSet rs) {
		ArrayList<Book> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
				));
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Book(int id, String title, String isbn, String isbnOld) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.isbnOld = isbnOld;
	}

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbnOld() {
		return isbnOld;
	}

	public void setIsbnOld(String isbnOld) {
		this.isbnOld = isbnOld;
	}

	@Override
	public String toString() {
		return title + " [" + (isbn.equals("") ? isbnOld : isbn) + "]";
	}

}
