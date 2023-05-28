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
	private StringProperty bookTitle;
	private StringProperty authorName;

	public BookForBooksCategory() {
		bookTitle = new SimpleStringProperty(this, "bookTitle");
		authorName = new SimpleStringProperty(this, "authorName");
	}

	@Id
	@Column(name = "BOOK_ID", updatable = false, nullable = false)
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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
		return Objects.equals(bookId, that.bookId) && Objects.equals(bookTitle.get(), that.bookTitle.get()) && Objects.equals(authorName.get(), that.authorName.get());
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, bookTitle.get(), authorName.get());
	}

	@Override
	public String toString() {
		return "[" + bookId + " | " + bookTitle.get() + " | " + authorName.get() + "]";
	}

}
