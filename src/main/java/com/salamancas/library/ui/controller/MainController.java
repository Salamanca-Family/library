package com.salamancas.library.ui.controller;

import com.salamancas.library.model.database.LibrarianAccount;
import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	@FXML
	private ImageView icon;
	@FXML
	private Label username;

	Options options;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		options = Options.getInstance();
		icon.setImage(Assets.userIcon);
		username.setText(options.getLoggedInUser().getName());
	}

}