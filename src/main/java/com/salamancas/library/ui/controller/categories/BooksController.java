package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.legacy.Book;
import com.salamancas.library.model.legacy.Copy;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

	@FXML
	private TableView<Book> tblBooks;
	@FXML
	private TableColumn<Book, String> bookTitle;
	@FXML
	private TableColumn<Book, String> bookAuthors;

	@FXML
	private TableView<Copy> tblCopies;
	@FXML
	private TableColumn<Copy, String> copyPublisher;
	@FXML
	private TableColumn<Copy, String> copySerial;
	@FXML
	private TableColumn<Copy, String> copyTitle;

	@FXML
	private TextField txfSearchBar;

	Options options;
	SQLUtils sqlUtils;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		options = Options.getInstance();
		sqlUtils = SQLUtils.getInstance();

		initBooks();
		initCopies();
	}

	private void initBooks() {
		bookTitle.setCellValueFactory(data -> data.getValue().titleProperty());
		bookAuthors.setCellValueFactory(data -> data.getValue().authorsProperty());

		ResultSet rs = sqlUtils.exequteSelectQuery("""
				select BOOK.BOOK_ID, A.AUTHOR_ID, BOOK_TITLE, AUTHOR_NAME
				from BOOK
				inner join AUTHOR_OF_BOOK AOB on BOOK.BOOK_ID = AOB.BOOK_ID
				inner join AUTHOR A on A.AUTHOR_ID = AOB.AUTHOR_ID;""");

		ObservableList<Book> list = FXCollections.observableArrayList(Book.fromResultSet(rs));
		FilteredList<Book> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getTitle().toLowerCase().contains(newValue.toLowerCase())
					|| data.getAuthors().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblBooks.setItems(filteredList);
	}

	private void initCopies() {
		copySerial.setCellValueFactory(data -> data.getValue().serialProperty());
		copyTitle.setCellValueFactory(data -> data.getValue().titleProperty());
		copyPublisher.setCellValueFactory(data -> data.getValue().publisherProperty());

		ResultSet rs = sqlUtils.exequteSelectQuery("""
				select COPY.COPY_ID, B.BOOK_ID, P.PUBLISHER_ID, COPY_SERIAL_NUMBER, BOOK_TITLE, PUBLISHER_NAME, COPY_ISBN, DATE_FROM, DATE_TO
				from COPY
				inner join BOOK B on B.BOOK_ID = COPY.BOOK_ID
				inner join PUBLISHER P on P.PUBLISHER_ID = COPY.PUBLISHER_ID
				left join BORROW on BORROW.COPY_ID = COPY.COPY_ID;""");
		ObservableList<Copy> list = FXCollections.observableArrayList(Copy.fromResultSet(rs));
		FilteredList<Copy> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getTitle().toLowerCase().contains(newValue.toLowerCase())
					|| data.getPublisher().toLowerCase().contains(newValue.toLowerCase())
					|| data.getSerial().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblCopies.setItems(filteredList);
	}
	@FXML
	private void bookAdd() {

	}

	@FXML
	private void bookEdit() {

	}

	@FXML
	private void bookDelete() {

	}

	@FXML
	private void copyAdd() {

	}

	@FXML
	private void copyEdit() {

	}

	@FXML
	private void copyDelete() {

	}

	@FXML
	private void copyDetails() {
		Copy copy = tblCopies.getSelectionModel().getSelectedItem();
		if(copy == null) {
			alert(Alert.AlertType.WARNING, "Warning", "Niste izabrali primerak", "Molimo Vas izaberite primerak");
			return;
		}
		alert(Alert.AlertType.INFORMATION, "Detalji", "Naslov: " + copy.getTitle(),
				"Izdavač: " + copy.getPublisher() +
				"\nDelovodni broj: " + copy.getSerial() +
				"\nISBN: " + copy.getIsbn());
	}

	private void alert(Alert.AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

}
