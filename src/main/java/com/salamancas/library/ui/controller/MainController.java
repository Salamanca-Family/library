package com.salamancas.library.ui.controller;

import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	@FXML
	private ImageView userIcon;
	@FXML
	private ImageView bookIcon;
	@FXML
	private Label username;
	@FXML
	private ImageView usersIcon;
	@FXML
	private ImageView transactionIcon;
	@FXML
	private ImageView settingsIcon;
	@FXML
	private AnchorPane container;

	Options options;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		options = Options.getInstance();
		userIcon.setImage(Assets.userIcon);
		bookIcon.setImage(Assets.bookIcon);
		usersIcon.setImage(Assets.usersIcon);
		transactionIcon.setImage(Assets.transactionIcon);
		settingsIcon.setImage(Assets.settingsIcon);
		username.setText(options.getLoggedInUser().getName());
	}

	@FXML
	private void profile() {

	}

	@FXML
	private void signOut() {
		options.setLoggedInUser(null);
		options.switchStage();
	}

}