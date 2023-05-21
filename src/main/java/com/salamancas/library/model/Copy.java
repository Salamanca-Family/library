package com.salamancas.library.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Copy {

	private int id;
	private int bookId;
	private int publisherId;
	private StringProperty serial;
	private StringProperty title;
	private StringProperty publisher;
	private StringProperty status;
	private String isbn;
	private String isbnOld;

	public static ArrayList<Copy> fromResultSet(ResultSet rs) {
		ArrayList<Copy> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new Copy(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8)
				));
			}
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	public Copy(int id, int bookId, int publisherId, String serial, String title, String publisher, String isbn, String isbnOld) {
		this.id = id;
		this.bookId = bookId;
		this.publisherId = publisherId;
		this.serial = new SimpleStringProperty(serial);
		this.title = new SimpleStringProperty(title);
		this.publisher = new SimpleStringProperty(publisher);
		this.isbn = isbn;
		this.isbnOld = isbnOld;
	}

	public int getId() {
		return id;
	}

	private void statusParser(){
		if(status.getValue() == null){
			status.setValue("nije na stanju");
		}
		else{
			status.setValue("na stanju");
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getSerial() {
		return serial.get();
	}

	public StringProperty serialProperty() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial.set(serial);
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

	public String getPublisher() {
		return publisher.get();
	}

	public StringProperty publisherProperty() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
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

	public String getStatus(){
		return status.get();
	}

	public StringProperty statusProperty() {
		return status;
	}
	public void setStatus(String status){this.status.set(status);}


	@Override
	public String toString() {
		return title + ", " + publisher + " [" + serial + "][" + (isbn == null ? isbnOld : isbn) + "]";
	}

}
