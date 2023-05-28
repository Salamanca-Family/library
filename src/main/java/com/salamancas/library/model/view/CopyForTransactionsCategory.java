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
@Table(name = "COPY_FOR_TRANSACTIONS_CATEGORY", schema = "main")
public class CopyForTransactionsCategory {

	private Integer copyId;
	private StringProperty copySerialNumber;
	private StringProperty bookTitle;
	private StringProperty publisherName;
	private String dateFrom;
	private String dateTo;

	public CopyForTransactionsCategory() {
		copySerialNumber = new SimpleStringProperty(this, "copySerialNumber");
		bookTitle = new SimpleStringProperty(this, "bookTitle");
		publisherName = new SimpleStringProperty(this, "publisherName");
	}

	@Id
	@Column(name = "COPY_ID")
	public Integer getCopyId() {
		return copyId;
	}

	public void setCopyId(Integer copyId) {
		this.copyId = copyId;
	}

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

	@Column(name = "DATE_FROM")
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Column(name = "DATE_TO")
	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		CopyForTransactionsCategory that = (CopyForTransactionsCategory) o;
		return Objects.equals(copyId, that.copyId) && Objects.equals(copySerialNumber.get(), that.copySerialNumber.get()) && Objects.equals(bookTitle.get(), that.bookTitle.get()) && Objects.equals(publisherName.get(), that.publisherName.get()) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(copyId, copySerialNumber.get(), bookTitle.get(), publisherName.get(), dateFrom, dateTo);
	}

	@Override
	public String toString() {
		return copyId.toString() + " " + dateFrom + " " + dateTo;
	}

}
