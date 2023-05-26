package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Copy {

	private Integer copyId;
	private String copySerialNumber;
	private String copyIsbn;
	private Book book;
	private Publisher publisher;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "COPY_ID")
	public Integer getCopyId() {
		return copyId;
	}

	public void setCopyId(Integer copyId) {
		this.copyId = copyId;
	}

	@Basic
	@Column(name = "COPY_SERIAL_NUMBER")
	public String getCopySerialNumber() {
		return copySerialNumber;
	}

	public void setCopySerialNumber(String copySerialNumber) {
		this.copySerialNumber = copySerialNumber;
	}

	@Basic
	@Column(name = "COPY_ISBN")
	public String getCopyIsbn() {
		return copyIsbn;
	}

	public void setCopyIsbn(String copyIsbn) {
		this.copyIsbn = copyIsbn;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Copy copy = (Copy) o;
		return Objects.equals(copyId, copy.copyId) && Objects.equals(copySerialNumber, copy.copySerialNumber) && Objects.equals(copyIsbn, copy.copyIsbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(copyId, copySerialNumber, copyIsbn);
	}

	@ManyToOne
	@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne
	@JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "PUBLISHER_ID")
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
