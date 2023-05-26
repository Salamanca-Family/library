package com.salamancas.library.ui.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

}
