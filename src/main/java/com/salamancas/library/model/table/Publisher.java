package com.salamancas.library.model.table;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Publisher {

	private Integer publisherId;
	private String publisherName;
	private Collection<Copy> copies;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "PUBLISHER_ID")
	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	@Basic
	@Column(name = "PUBLISHER_NAME")
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Publisher publisher = (Publisher) o;
		return Objects.equals(publisherId, publisher.publisherId) && Objects.equals(publisherName, publisher.publisherName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(publisherId, publisherName);
	}

	@OneToMany(mappedBy = "publisher")
	public Collection<Copy> getCopies() {
		return copies;
	}

	public void setCopies(Collection<Copy> copies) {
		this.copies = copies;
	}

}
