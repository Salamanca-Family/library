package com.salamancas.library.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

@Entity
@Table(name = "USER_FOR_USERS_CATEGORY", schema = "main")
public class UserForUsersCategory {

	private Integer userId;
	private StringProperty userName;
	private StringProperty userSurname;
	private StringProperty typeName;
	private String studentYear;
	private StringProperty classIndex;
	private String homeroomYear;
	private StringProperty homeroomTeacher;

	public UserForUsersCategory() {
		userName = new SimpleStringProperty(this, "userName");
		userSurname = new SimpleStringProperty(this, "userSurname");
		typeName = new SimpleStringProperty(this, "typeName");
		classIndex = new SimpleStringProperty(this, "classIndex");
		homeroomTeacher = new SimpleStringProperty(this, "homeroomTeacher");
	}

	@Id
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName.get();
	}

	public void setUserName(String userName) {
		this.userName.set(userName);
	}

	public StringProperty nameProperty() {
		return userName;
	}

	@Column(name = "USER_SURNAME")
	public String getUserSurname() {
		return userSurname.get();
	}

	public void setUserSurname(String userSurname) {
		this.userSurname.set(userSurname);
	}

	public StringProperty surnameProperty() {
		return userSurname;
	}

	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName.get();
	}

	public void setTypeName(String typeName) {
		this.typeName.set(typeName);
	}

	public StringProperty typeProperty() {
		return typeName;
	}

	@Column(name = "STUDENT_YEAR")
	public String getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(String studentYear) {
		this.studentYear = studentYear;
	}

	@Column(name = "CLASS_INDEX")
	public String getClassIndex() {
		return classIndex.get();
	}

	public void setClassIndex(String classIndex) {
		this.classIndex.set(classIndex);
	}

	public StringProperty classIndexProperty() {
		return classIndex;
	}

	@Column(name = "HOMEROOM_YEAR")
	public String getHomeroomYear() {
		return homeroomYear;
	}

	public void setHomeroomYear(String homeroomYear) {
		this.homeroomYear = homeroomYear;
	}

	@Column(name = "HOMEROOM_TEACHER")
	public String getHomeroomTeacher() {
		return homeroomTeacher.get();
	}

	public void setHomeroomTeacher(String homeroomTeacher) {
		this.homeroomTeacher.set(homeroomTeacher);
	}

	public StringProperty homeroomTeacherProperty() {
		return homeroomTeacher;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		UserForUsersCategory that = (UserForUsersCategory) o;
		return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(userSurname, that.userSurname) && Objects.equals(typeName, that.typeName) && Objects.equals(studentYear, that.studentYear) && Objects.equals(classIndex, that.classIndex) && Objects.equals(homeroomYear, that.homeroomYear) && Objects.equals(homeroomTeacher, that.homeroomTeacher);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, userSurname, typeName, studentYear, classIndex, homeroomYear, homeroomTeacher);
	}

	@Override
	public String toString() {
		return userName.get() + " " + userSurname.get();
	}

}
