package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.legacy.User;
import com.salamancas.library.model.persistence.view.UserForUserCategory;
import com.salamancas.library.ui.controller.dialog.UserController;
import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.HibernateUtil;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

	@FXML
	private TableColumn<UserForUserCategory, String> name;
	@FXML
	private TableColumn<UserForUserCategory, String> surname;
	@FXML
	private TableColumn<UserForUserCategory, String> type;
	@FXML
	private TableColumn<UserForUserCategory, String> schoolClass;
	@FXML
	private TableColumn<UserForUserCategory, String> homeroomTeacher;
	@FXML
	private TableView<UserForUserCategory> tblUsers;
	@FXML
	private TextField txfSearchBar;

	Options options;
	SQLUtils sqlUtils;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		options = Options.getInstance();
		sqlUtils = SQLUtils.getInstance();

		name.setCellValueFactory(data -> data.getValue().nameProperty());
		surname.setCellValueFactory(data -> data.getValue().surnameProperty());
		type.setCellValueFactory(data -> data.getValue().typeProperty());
		schoolClass.setCellValueFactory(data -> data.getValue().classIndexProperty());
		homeroomTeacher.setCellValueFactory(data -> data.getValue().homeroomTeacherProperty());

		schoolClass.setCellFactory(usersStringTableColumn -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				this.setText(null);
				this.setGraphic(null);
				if(!empty) {
					UserForUserCategory user = this.getTableRow().getItem();
					if(user == null) {
						return;
					}
					String userYear = user.getStudentYear();
					if(userYear == null) {
						this.setText("/");
						return;
					}
					String userClass = user.getClassIndex();
					this.setText(formatClassLabel(userYear, userClass));
				}
			}
		});
		homeroomTeacher.setCellFactory(usersStringTableColumn -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				this.setText(null);
				this.setGraphic(null);
				if(!empty) {
					UserForUserCategory user = this.getTableRow().getItem();
					if(user == null) {
						return;
					}
					String userYear = user.getHomeroomYear();
					if(userYear == null) {
						this.setText("/");
						return;
					}
					String userClass = user.getHomeroomTeacher();
					this.setText(formatClassLabel(userYear, userClass));
				}
			}
		});

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObservableList<UserForUserCategory> list = FXCollections.observableArrayList(session.createQuery("from UserForUserCategory", UserForUserCategory.class).list());
		session.close();

		FilteredList<UserForUserCategory> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getUserName().toLowerCase().contains(newValue.toLowerCase())
					|| data.getUserSurname().toLowerCase().contains(newValue.toLowerCase())
					|| data.getTypeName().toLowerCase().contains(newValue.toLowerCase())
					|| data.getClassIndex().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblUsers.setItems(filteredList);
	}

	private String formatClassLabel(String userYear, String userClass) {
		LocalDate currentDate = LocalDate.now();
		LocalDate start = LocalDate.of(Integer.parseInt(userYear), Month.SEPTEMBER, 1);
		StringBuilder classIndex = new StringBuilder();
		switch(Period.between(start, currentDate).getYears() + 1) {
			case 1 -> classIndex.append("I-").append(userClass);
			case 2 -> classIndex.append("II-").append(userClass);
			case 3 -> classIndex.append("III-").append(userClass);
			case 4 -> classIndex.append("IV-").append(userClass);
			default -> classIndex.append(userYear).append("-").append(userClass);
		}
		return classIndex.toString();
	}

	@FXML
	private void userAdd() throws IOException {
		FXMLLoader loader = new FXMLLoader(Assets.userDialog);
		DialogPane root = loader.load();
		UserController controller = loader.getController();
		controller.setUser(new User());

		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Novi korisnik");
		dialog.setDialogPane(root);

		Optional<ButtonType> result = dialog.showAndWait();
		if(result.isPresent()) {
			System.out.println(controller.getUser());
		}
	}

	@FXML
	private void userDelete() {

	}

	@FXML
	private void userDetails() {

	}

	@FXML
	private void userEdit() {

	}

}
