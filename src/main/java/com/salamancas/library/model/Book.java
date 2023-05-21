package com.salamancas.library.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Book {

	private int id;
	private ArrayList<Integer> authorIds;
	private StringProperty title;
	private StringProperty authors;

	public static ArrayList<Book> fromResultSet(ResultSet rs) {
		ArrayList<Book> list = new ArrayList<>();
		Book currentBook = null;
		int currentId = 0;
		try {
			while(rs.next()) {
				if(currentId == rs.getInt(1)) {
					currentBook.setAuthors(currentBook.getAuthors() + ", " + rs.getString(4));
					currentBook.getAuthorIds().add(rs.getInt(2));
					continue;
				}
				currentId = rs.getInt(1);
				currentBook = new Book(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4)
				);
				list.add(currentBook);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Book(int id, int authorId, String title, String authors) {
		this.id = id;
		this.authorIds = new ArrayList<>();
		authorIds.add(authorId);
		this.title = new SimpleStringProperty(title);
		this.authors = new SimpleStringProperty(authors);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getAuthorIds() {
		return authorIds;
	}

	public String getTitle() {
		return title.get();
	}

	public StringProperty titleProperty() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getAuthors() {
		return authors.get();
	}

	public StringProperty authorsProperty() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors.set(authors);
	}

	@Override
	public String toString() {
		return title.getValue();
	}

}
