package com.salamancas.library.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

	private int id;
	private int classId;
	private int homeroomToId;
	private int typeId;
	private StringProperty name;
	private StringProperty surname;
	private StringProperty type;
	private String year;
	private StringProperty schoolClass;
	private StringProperty homeroomTo;

	public static ArrayList<User> fromResultSet(ResultSet rs) {
		ArrayList<User> list = new ArrayList<>();
		try {
			while(rs.next()) {
				list.add(new User(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9) == null ? "" : rs.getString(9),
						rs.getString(10) == null ? "" : rs.getString(10)
				));
			}
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	public User(int id, int classId, int homeroomToId, int typeId, String name, String surname, String type, String year, String schoolClass, String homeroomTo) {
		this.id = id;
		this.classId = classId;
		this.homeroomToId = homeroomToId;
		this.typeId = typeId;
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.type = new SimpleStringProperty(type);
		this.year = year;
		this.schoolClass = new SimpleStringProperty(schoolClass);
		this.homeroomTo = new SimpleStringProperty(homeroomTo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getHomeroomToId() {
		return homeroomToId;
	}

	public void setHomeroomToId(int homeroomToId) {
		this.homeroomToId = homeroomToId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getSurname() {
		return surname.get();
	}

	public StringProperty surnameProperty() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public String getType() {
		return type.get();
	}

	public StringProperty typeProperty() {
		return type;
	}

	public void setType(String type) {
		this.type.set(type);
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSchoolClass() {
		return schoolClass.get();
	}

	public StringProperty schoolClassProperty() {
		return schoolClass;
	}

	public void setSchoolClass(String schoolClass) {
		this.schoolClass.set(schoolClass);
	}

	public String getHomeroomTo() {
		return homeroomTo.get();
	}

	public StringProperty homeroomToProperty() {
		return homeroomTo;
	}

	public void setHomeroomTo(String homeroomTo) {
		this.homeroomTo.set(homeroomTo);
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}

}
