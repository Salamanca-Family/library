package com.salamancas.library.model.view;

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
@Table(name = "BOOK_FOR_BOOKS_CATEGORY", schema = "main")
public class BookForBooksCategory {

	private Integer bookId;
	private Integer authorId;
	private StringProperty bookTitle;
	private StringProperty authorName;

	public BookForBooksCategory() {
		bookTitle = new SimpleStringProperty(this, "bookTitle");
		authorName = new SimpleStringProperty(this, "authorName");
	}

	@Id
	@Column(name = "BOOK_ID")
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Id
	@Column(name = "AUTHOR_ID")
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Column(name = "BOOK_TITLE")
	public String getBookTitle() {
		return bookTitle.get();
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle.set(bookTitle);
	}

	public StringProperty bookTitleProperty() {
		return bookTitle;
	}

	@Column(name = "AUTHOR_NAME")
	public String getAuthorName() {
		return authorName.get();
	}

	public void setAuthorName(String authorName) {
		this.authorName.set(authorName);
	}

	public StringProperty authorNameProperty() {
		return authorName;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		BookForBooksCategory that = (BookForBooksCategory) o;
		return Objects.equals(bookId, that.bookId) && Objects.equals(authorId, that.authorId) && Objects.equals(bookTitle, that.bookTitle) && Objects.equals(authorName, that.authorName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, authorId, bookTitle, authorName);
	}

	@Override
	public String toString() {
		return "[" + bookId + "][" + authorId + "] " + bookTitle.get() + " " + authorName.get();
	}

}
