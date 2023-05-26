package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.persistence.view.BookForBooksCategory;
import com.salamancas.library.model.persistence.view.CopyForBooksCategory;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.HibernateUtil;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

	@FXML
	private TableView<BookForBooksCategory> tblBooks;
	@FXML
	private TableColumn<BookForBooksCategory, String> bookTitle;
	@FXML
	private TableColumn<BookForBooksCategory, String> bookAuthors;

	@FXML
	private TableView<CopyForBooksCategory> tblCopies;
	@FXML
	private TableColumn<CopyForBooksCategory, String> copyPublisher;
	@FXML
	private TableColumn<CopyForBooksCategory, String> copySerial;
	@FXML
	private TableColumn<CopyForBooksCategory, String> copyTitle;

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
		bookTitle.setCellValueFactory(data -> data.getValue().bookTitleProperty());
		bookAuthors.setCellValueFactory(data -> data.getValue().authorNameProperty());

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObservableList<BookForBooksCategory> list = FXCollections.observableArrayList(session.createQuery("from BookForBooksCategory", BookForBooksCategory.class).list());
		session.close();

		for(int i = 1; i < list.size(); i++) {
			if(list.get(i - 1).getBookId().equals(list.get(i).getBookId())) {
				list.get(i).setAuthorName(list.get(i).getAuthorName() + ", " + list.get(i - 1).getAuthorName());
				list.remove(i-- - 1);
			}
		}

		FilteredList<BookForBooksCategory> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getBookTitle().toLowerCase().contains(newValue.toLowerCase())
					|| data.getAuthorName().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblBooks.setItems(filteredList);
	}

	private void initCopies() {
		copySerial.setCellValueFactory(data -> data.getValue().serialProperty());
		copyTitle.setCellValueFactory(data -> data.getValue().titleProperty());
		copyPublisher.setCellValueFactory(data -> data.getValue().publisherProperty());

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObservableList<CopyForBooksCategory> list = FXCollections.observableArrayList(session.createQuery("from CopyForBooksCategory", CopyForBooksCategory.class).list());
		session.close();

		FilteredList<CopyForBooksCategory> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getBookTitle().toLowerCase().contains(newValue.toLowerCase())
					|| data.getPublisherName().toLowerCase().contains(newValue.toLowerCase())
					|| data.getCopySerialNumber().toLowerCase().contains(newValue.toLowerCase());
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
		CopyForBooksCategory copy = tblCopies.getSelectionModel().getSelectedItem();
		if(copy == null) {
			alert(Alert.AlertType.WARNING, "Warning", "Niste izabrali primerak", "Molimo Vas izaberite primerak");
			return;
		}
		alert(Alert.AlertType.INFORMATION, "Detalji", "Naslov: " + copy.getBookTitle(),
				"Izdavač: " + copy.getPublisherName() +
				"\nDelovodni broj: " + copy.getCopySerialNumber() +
				"\nISBN: " + copy.getCopyIsbn());
	}

	private void alert(Alert.AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

}
