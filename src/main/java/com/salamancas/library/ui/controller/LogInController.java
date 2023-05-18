package com.salamancas.library.ui.controller;

import com.salamancas.library.model.database.LibrarianAccount;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.SQLUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController {

	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	@FXML
	private Label incorrectPassword;

	SQLUtils sqlUtils;
	Options options;

	@FXML
	void logIn() {
		sqlUtils = SQLUtils.getInstance();
		options = Options.getInstance();
		String sql = "select account.account_id, user_name, user_surname, account_username, account_password from account\n" +
				"inner join user on account.account_id = user.account_id\n" +
				"where account_username = '" + username.getText() + "' and account_password = '" + password.getText() + "';";
		LibrarianAccount librarianAccount = LibrarianAccount.fromResultSet(sqlUtils.exequteSelectQuery(sql));
		if(librarianAccount == null) {
			incorrectPassword.setVisible(true);
			return;
		}
		options.setLoggedInUser(librarianAccount);
		password.setText("");
		options.switchStage();
	}

}
