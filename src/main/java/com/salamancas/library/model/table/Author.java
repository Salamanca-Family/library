package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Author {

	private Integer authorId;
	private String authorName;
	private Collection<Book> books;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "AUTHOR_ID")
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Basic
	@Column(name = "AUTHOR_NAME")
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return Objects.equals(authorId, author.authorId) && Objects.equals(authorName, author.authorName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, authorName);
	}

	@ManyToMany(mappedBy = "authors")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

}
