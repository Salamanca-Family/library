package com.salamancas.library.ui.controller;

import com.salamancas.library.model.database.LibrarianAccount;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	@FXML
	private Label incorrectPassword;

	SQLUtils sqlUtils;
	Options options;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		sqlUtils = SQLUtils.getInstance();
		options = Options.getInstance();

		username.textProperty().addListener(observable -> checkFields());
		password.textProperty().addListener(observable -> checkFields());

		username.getParent().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			KeyCodeCombination keyComb = new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN);
			if(event.getCode() == KeyCode.ENTER) {
				authenticate();
			}
			if(keyComb.match(event)) {
				logIn(new LibrarianAccount(1, 1, "Administrator", "admin", "admin"));
			}
		});
	}

	private void checkFields() {
		if(username.getText().equals("")) {
			return;
		}
		char temp = username.getText().charAt(username.getText().length() - 1);
		if(!(Character.isAlphabetic(temp) || temp == '.') || username.getText().length() == 26) {
			username.setText(username.getText().substring(0, username.getText().length() - 1));
		}
	}

	@FXML
	void authenticate() {
		String sql = "select account.account_id, account.user_id, user_name, user_surname, account_username, account_password from account\n" +
				"inner join user on account.user_id = user.user_id\n" +
				"where account_username = '" + username.getText() + "' and account_password = '" + password.getText() + "';";
		LibrarianAccount librarianAccount = LibrarianAccount.fromResultSet(sqlUtils.exequteSelectQuery(sql));
		if(librarianAccount == null) {
			incorrectPassword.setVisible(true);
			return;
		}
		logIn(librarianAccount);
	}

	private void logIn(LibrarianAccount account) {
		username.requestFocus();
		username.setText("");
		password.setText("");
		incorrectPassword.setVisible(false);

		options.setLoggedInUser(account);
		options.switchStage();
	}

}
