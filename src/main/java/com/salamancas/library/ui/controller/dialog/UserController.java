package com.salamancas.library.ui.controller.dialog;

import com.salamancas.library.model.legacy.User;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class UserController {

	@FXML
	private TextField name;
	@FXML
	private TextField surname;
	@FXML
	private DatePicker birthDate;
	@FXML
	private TextField address;
	@FXML
	private ChoiceBox<String> town;
	@FXML
	private ChoiceBox<String> type;
	@FXML
	private ChoiceBox<String> schoolClass;

	Options options = Options.getInstance();
	SQLUtils sqlUtils = SQLUtils.getInstance();

	private User user;

	public User getUser() {
		user.setName(name.getText());
		user.setSurname(surname.getText());
		user.setBirthDate(birthDate.getValue() == null ? "" : birthDate.getValue().toString());
		user.setAddress(address.getText());
		user.setTown(town.getValue());
		user.setTown(type.getValue());
		user.setSchoolClass(schoolClass.getValue());
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		name.setText(user.getName());
		surname.setText(user.getSurname());
		birthDate.setValue(user.getBirthDate().equals("") ? null : LocalDate.parse(user.getBirthDate()));
		address.setText(user.getAddress());
		town.getSelectionModel().select(user.getTown());
		type.getSelectionModel().select(user.getType());
		schoolClass.getSelectionModel().select(user.getSchoolClass());
	}

}
