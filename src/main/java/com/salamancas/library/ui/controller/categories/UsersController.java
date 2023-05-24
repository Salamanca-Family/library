package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.User;
import com.salamancas.library.ui.controller.dialog.UserController;
import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

	@FXML
	private TableColumn<User, String> name;
	@FXML
	private TableColumn<User, String> surname;
	@FXML
	private TableColumn<User, String> type;
	@FXML
	private TableColumn<User, String> schoolClass;
	@FXML
	private TableView<User> tblUsers;
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
		schoolClass.setCellValueFactory(data -> data.getValue().schoolClassProperty());

		ObservableList<User> list = FXCollections.observableArrayList(User.fromResultSet(sqlUtils.exequteSelectQuery("""
				select USER.USER_ID, SIC.CLASS_ID, HT.CLASS_ID, T2.TYPE_ID,
				       USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS, TOW.TOWN_NAME, TYPE_NAME, Y.YEAR_NAME, Y2.YEAR_NAME, C.CLASS_INDEX, C2.CLASS_INDEX
				from USER
				left join STUDENT_IN_CLASS SIC on USER.USER_ID = SIC.USER_ID
				left join CLASS C on C.CLASS_ID = SIC.CLASS_ID
				left join HOMEROOM_TEACHER HT on USER.USER_ID = HT.USER_ID
				left join CLASS C2 on C2.CLASS_ID = HT.CLASS_ID
				left join TYPE_OF_USER TOU on USER.USER_ID = TOU.USER_ID
				left join TYPE T2 on T2.TYPE_ID = TOU.TYPE_ID
				left join YEAR Y on Y.YEAR_ID = C.YEAR_ID
				left join YEAR Y2 on Y2.YEAR_ID = C2.YEAR_ID
				left join TOWN_OF_USER TOOU on TOOU.USER_ID = USER.USER_ID
				left join TOWN TOW on TOW.TOWN_ID = TOOU.TOWN_ID
				where HT.DATE_TO is null and SIC.DATE_TO is null and TOU.DATE_TO is null and T2.TYPE_ID != 4;""")));
		FilteredList<User> filteredList = new FilteredList<>(list);
		filteredList.setPredicate(data -> true);

		txfSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
			if(newValue.equals("")) {
				return true;
			}
			return data.getName().toLowerCase().contains(newValue.toLowerCase())
					|| data.getSurname().toLowerCase().contains(newValue.toLowerCase())
					|| data.getType().toLowerCase().contains(newValue.toLowerCase())
					|| data.getSchoolClass().toLowerCase().contains(newValue.toLowerCase());
		}));

		tblUsers.setItems(filteredList);
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
