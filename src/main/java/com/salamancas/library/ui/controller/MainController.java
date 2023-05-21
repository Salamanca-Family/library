package com.salamancas.library.ui.controller;

import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	@FXML
	private ImageView userIcon;
	@FXML
	private Label username;

	@FXML
	private ImageView bookIcon;
	@FXML
	private Label booksButton;

	@FXML
	private ImageView usersIcon;
	@FXML
	private Label usersButton;

	@FXML
	private ImageView transactionIcon;
	@FXML
	private Label transactionsButton;

	@FXML
	private ImageView settingsIcon;
	@FXML
	private Label settingsButton;

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

		books();
	}

	@FXML
	private void profile() {

	}

	@FXML
	private void signOut() {
		options.setLoggedInUser(null);
		options.switchStage();
	}

	@FXML
	private void books() {
		FXMLLoader loader = new FXMLLoader(Assets.books);
		switchCategory(loader, booksButton);
	}

	@FXML
	private void users() {
		FXMLLoader loader = new FXMLLoader(Assets.users);
		switchCategory(loader, usersButton);
	}

	@FXML
	private void transactions() {
		FXMLLoader loader = new FXMLLoader(Assets.transaction);
		switchCategory(loader, transactionsButton);
	}

	private void switchCategory(FXMLLoader loader, Label transactionsButton) {
		try {
			ObservableList<Node> list = container.getChildren();
			if(list.size() == 0) {
				list.add(loader.load());
			} else {
				list.set(0, loader.load());
			}
			clearButtonColor();
			transactionsButton.getStyleClass().add("selected");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void clearButtonColor() {
		booksButton.getStyleClass().remove("selected");
		usersButton.getStyleClass().remove("selected");
		transactionsButton.getStyleClass().remove("selected");
		settingsButton.getStyleClass().remove("selected");
	}

}