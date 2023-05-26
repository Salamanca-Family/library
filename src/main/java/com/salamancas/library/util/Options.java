package com.salamancas.library.util;

import com.salamancas.library.model.table.Account;
import com.salamancas.library.ui.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Options {

	private static Options instance;

	private Stage logIn;
	private Stage library;

	private Options() {}

	public static Options getInstance() {
		if(instance == null) {
			System.out.println("Options must be initialized first");
			return null;
		}
		return instance;
	}

	public static void initialize(Stage logIn) {
		if(instance != null) {
			return;
		}
		instance = new Options();
		instance.logIn = logIn;
	}

	public void newLibrary(Account account) {
		try {
			library = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(Assets.library);
			Scene scene = new Scene(fxmlLoader.load());
			((MainController) fxmlLoader.getController()).setLoggedIn(account);
			scene.getStylesheets().add(Options.class.getResource("/com/salamancas/library/assets/css/main.css").toExternalForm());
			library.setScene(scene);
			library.setResizable(false);
			library.setTitle("Library");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void logInStage() {
		library.hide();
		library = null;
		logIn.show();
	}

	public void libraryStage(Account account) {
		if(library == null) {
			newLibrary(account);
		}
		logIn.hide();
		library.show();
	}

}
