package com.salamancas.library.model.persistence.view;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

@Entity
@Table(name = "COPY_FOR_TRANSACTION_CATEGORY", schema = "main")
public class CopyForTransactionCategory implements Externalizable {

	private Integer copyId;
	private StringProperty copySerialNumber;
	private StringProperty bookTitle;
	private StringProperty publisherName;
	private String dateFrom;
	private String dateTo;

	public CopyForTransactionCategory() {
		copySerialNumber = new SimpleStringProperty(this, "copySerialNumber");
		bookTitle = new SimpleStringProperty(this, "bookTitle");
		publisherName = new SimpleStringProperty(this, "publisherName");
	}

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
		return copySerialNumber.get();
	}

	public void setCopySerialNumber(String copySerialNumber) {
		this.copySerialNumber.set(copySerialNumber);
	}

	public StringProperty serialProperty() {
		return copySerialNumber;
	}

	@Basic
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

	@Basic
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

	@Basic
	@Column(name = "DATE_FROM")
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Basic
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
		CopyForTransactionCategory that = (CopyForTransactionCategory) o;
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getCopySerialNumber());
		out.writeObject(getBookTitle());
		out.writeObject(getPublisherName());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setCopySerialNumber((String) in.readObject());
		setBookTitle((String) in.readObject());
		setPublisherName((String) in.readObject());
	}

}
