package com.salamancas.library.model.persistence.view;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.Immutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@Entity
@Immutable
public class Users implements Externalizable {

	private Integer userId;
	private StringProperty userName = new SimpleStringProperty(this, "userName");
	private StringProperty userSurname = new SimpleStringProperty(this, "userSurname");
	private StringProperty typeName = new SimpleStringProperty(this, "typeName");
	private StringProperty classIndex = new SimpleStringProperty(this, "classIndex");
	private StringProperty homeroomTeacher = new SimpleStringProperty(this, "homeroomTeacher");
	private String studentYear;
	private String homeroomYear;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Basic
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

	@Basic
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

	@Basic
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

	@Basic
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

	@Basic
	@Column(name = "HOMEROOM_TEACHER", nullable = true, length = -1)
	public String getHomeroomTeacher() {
		return homeroomTeacher.get();
	}

	public void setHomeroomTeacher(String homeroomTeacher) {
		this.homeroomTeacher.set(homeroomTeacher);
	}

	public StringProperty homeroomTeacherProperty() {
		return homeroomTeacher;
	}

	@Basic
	@Column(name = "STUDENT_YEAR")
	public String getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(String studentYear) {
		this.studentYear = studentYear;
	}

	@Basic
	@Column(name = "HOMEROOM_YEAR")
	public String getHomeroomYear() {
		return homeroomYear;
	}

	public void setHomeroomYear(String homeroomYear) {
		this.homeroomYear = homeroomYear;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Users users = (Users) o;
		return Objects.equals(userId, users.userId) && Objects.equals(userName, users.userName) && Objects.equals(userSurname, users.userSurname) && Objects.equals(classIndex, users.classIndex) && Objects.equals(typeName, users.typeName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, userSurname, classIndex, typeName);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(getUserId());
		out.writeObject(getUserName());
		out.writeObject(getUserSurname());
		out.writeObject(getClassIndex());
		out.writeObject(getHomeroomTeacher());
		out.writeObject(getTypeName());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setUserId(in.readInt());
		setUserName((String) in.readObject());
		setUserSurname((String) in.readObject());
		setClassIndex((String) in.readObject());
		setHomeroomTeacher((String) in.readObject());
		setTypeName((String) in.readObject());
	}

}
