package com.salamancas.library.model.legacy;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;

public class User {

	private int id;
	private int classId;
	private int homeroomToId;
	private int typeId;
	private StringProperty name;
	private StringProperty surname;
	private String birthDate;
	private String address;
	private String town;
	private StringProperty type;
	private String year;
	private StringProperty schoolClass;

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
						rs.getString(9),
						rs.getString(10),
						rs.getString(11) == null ? rs.getString(12) : rs.getString(11),
						rs.getString(13) == null ? rs.getString(14) : rs.getString(13)
				));
			}
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	public User(int id, int classId, int homeroomToId, int typeId, String name, String surname, String birthDate, String address, String town, String type, String year, String schoolClass) {
		this.id = id;
		this.classId = classId;
		this.homeroomToId = homeroomToId;
		this.typeId = typeId;
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.birthDate = birthDate;
		this.address = address;
		this.type = new SimpleStringProperty(type);
		this.year = year;
		this.schoolClass = new SimpleStringProperty(schoolClass);
		classParser();
	}

	public User() {
		this.name = new SimpleStringProperty();
		this.surname = new SimpleStringProperty();
		this.birthDate = "";
		this.address = "";
		this.type = new SimpleStringProperty();
		this.year = "";
		this.schoolClass = new SimpleStringProperty();
	}

	private void classParser() {
		if(year.equals("") || schoolClass == null || schoolClass.getValue().equals("")) {
			return;
		}
		LocalDate currentDate = LocalDate.now();
		LocalDate start = LocalDate.of(Integer.parseInt(year), Month.SEPTEMBER, 1);
		StringBuilder classIndex = new StringBuilder();
		switch(Period.between(start, currentDate).getYears() + 1) {
			case 1 -> classIndex.append("I-").append(schoolClass.getValue());
			case 2 -> classIndex.append("II-").append(schoolClass.getValue());
			case 3 -> classIndex.append("III-").append(schoolClass.getValue());
			case 4 -> classIndex.append("IV-").append(schoolClass.getValue());
			default -> classIndex.append(year).append("-").append(schoolClass.getValue());
		}
		schoolClass.setValue(classIndex.toString());
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
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
		classParser();
	}

	public String getSchoolClass() {
		return schoolClass.get();
	}

	public StringProperty schoolClassProperty() {
		return schoolClass;
	}

	public void setSchoolClass(String schoolClass) {
		this.schoolClass.set(schoolClass);
		classParser();
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}

}
