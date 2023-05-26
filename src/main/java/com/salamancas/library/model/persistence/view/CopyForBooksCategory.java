package com.salamancas.library.model.persistence.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.Immutable;

import java.util.Objects;

@Entity
@Immutable
@Table(name = "COPY_FOR_BOOKS_CATEGORY", schema = "main")
public class CopyForBooksCategory {

	private Integer copyId;
	private StringProperty copySerialNumber;
	private String copyIsbn;
	private StringProperty bookTitle;
	private StringProperty publisherName;

	public CopyForBooksCategory() {
		copySerialNumber = new SimpleStringProperty(this, "copySerialNumber");
		bookTitle = new SimpleStringProperty(this, "bookTitle");
		publisherName = new SimpleStringProperty(this, "publisherName");
	}

	@Id
	@Column(name = "COPY_ID", updatable = false, nullable = false)
	public Integer getCopyId() {
		return copyId;
	}

	public void setCopyId(Integer copyId) {
		this.copyId = copyId;
	}

	@Id
	@Column(name = "COPY_SERIAL_NUMBER")
	public String getCopySerialNumber() {
		return copySerialNumber.get();
	}

	public void setCopySerialNumber(String copySerialNumber) {
		this.copySerialNumber.set(copySerialNumber);
	}

	public StringProperty serialProperty() {
		return copySerialNumber;
	}

	@Id
	@Column(name = "COPY_ISBN")
	public String getCopyIsbn() {
		return copyIsbn;
	}

	public void setCopyIsbn(String copyIsbn) {
		this.copyIsbn = copyIsbn;
	}

	@Id
	@Column(name = "BOOK_TITLE")
	public String getBookTitle() {
		return bookTitle.get();
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle.set(bookTitle);
	}

	public StringProperty titleProperty() {
		return bookTitle;
	}

	@Id
	@Column(name = "PUBLISHER_NAME")
	public String getPublisherName() {
		return publisherName.get();
	}

	public void setPublisherName(String publisherName) {
		this.publisherName.set(publisherName);
	}

	public StringProperty publisherProperty() {
		return publisherName;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		CopyForBooksCategory that = (CopyForBooksCategory) o;
		return Objects.equals(copyId, that.copyId) && Objects.equals(copySerialNumber, that.copySerialNumber) && Objects.equals(bookTitle, that.bookTitle) && Objects.equals(publisherName, that.publisherName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(copyId, copySerialNumber, bookTitle, publisherName);
	}

}
