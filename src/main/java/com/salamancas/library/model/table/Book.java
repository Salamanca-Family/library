package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Book {

	private Integer bookId;
	private String bookTitle;
	private Collection<Author> authors;
	private Collection<Copy> copies;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "BOOK_ID")
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Basic
	@Column(name = "BOOK_TITLE")
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return Objects.equals(bookId, book.bookId) && Objects.equals(bookTitle, book.bookTitle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, bookTitle);
	}

	@ManyToMany()
	@JoinTable(
			name = "author_of_book",
			joinColumns = { @JoinColumn(name = "book_id") },
			inverseJoinColumns = { @JoinColumn(name = "author_id") }
	)
	public Collection<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<Author> authors) {
		this.authors = authors;
	}

	@OneToMany(mappedBy = "book")
	public Collection<Copy> getCopies() {
		return copies;
	}

	public void setCopies(Collection<Copy> copies) {
		this.copies = copies;
	}

}
