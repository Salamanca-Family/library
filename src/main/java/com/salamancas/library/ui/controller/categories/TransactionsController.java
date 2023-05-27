package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.table.User;
import com.salamancas.library.model.view.CopyForTransactionsCategory;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.HibernateUtil;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

	@FXML
	private TableView<CopyForTransactionsCategory> tblCopies;
	@FXML
	private TableColumn<CopyForTransactionsCategory, String> copySerial;
	@FXML
	private TableColumn<CopyForTransactionsCategory, String> copyTitle;
	@FXML
	private TableColumn<CopyForTransactionsCategory, String> copyStatus;
	@FXML
	private TableColumn<CopyForTransactionsCategory, String> copyPublisher;
	@FXML
	private ListView<User> lstUsers;
	@FXML
	private TextField txfCopiesSearchBar;
	@FXML
	private TextField txfUsersSearchBar;

	Options options;
	SQLUtils sqlUtils;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		options = Options.getInstance();
		sqlUtils = SQLUtils.getInstance();

		initCopies();
		initUsers();
	}

	private void initCopies() {
		copySerial.setCellValueFactory(data -> data.getValue().serialProperty());
		copyTitle.setCellValueFactory(data -> data.getValue().titleProperty());
		copyPublisher.setCellValueFactory(data -> data.getValue().publisherProperty());

		copyStatus.setCellFactory(copyStringTableColumn -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				this.setText(null);
				this.setGraphic(null);
				if(!empty) {
					CopyForTransactionsCategory copy = this.getTableRow().getItem();
					if(copy == null) {
						return;
					}
					if(copy.getDateFrom() == null && copy.getDateTo() == null || copy.getDateFrom() != null && copy.getDateTo() != null) {
						this.setText("Na stanju");
					} else {
						this.setText("Izdata");
					}
				}
			}
		});

		Session session = HibernateUtil.getSessionFactory().openSession();
		ObservableList<CopyForTransactionsCategory> list = FXCollections.observableArrayList(session.createQuery("from CopyForTransactionsCategory", CopyForTransactionsCategory.class).list());
		session.close();
		
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i - 1).getCopyId().equals(list.get(i).getCopyId())) {
				list.remove(i-- - 1);
			}
		}

		FilteredList<CopyForTransactionsCategory> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfCopiesSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getBookTitle().toLowerCase().contains(newValue.toLowerCase())
					|| data.getCopySerialNumber().toLowerCase().contains(newValue.toLowerCase())
					|| data.getCopySerialNumber().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblCopies.setItems(filteredList);
	}

	private void initUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		ObservableList<User> list = FXCollections.observableArrayList(session.createQuery("from User u inner join u.types tu on u = tu.user inner join Type t on tu.type = t where t.typeId != 4", User.class).list());
		session.close();

		FilteredList<User> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfUsersSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.toString().toLowerCase().contains(newValue.toLowerCase());
		}));

		lstUsers.setItems(filteredList);
	}
}
