package com.salamancas.library.ui;

import com.salamancas.library.util.Assets;
import com.salamancas.library.util.Options;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {
	@Override
	public void start(Stage logIn) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Assets.logIn);
		logIn.setScene(new Scene(fxmlLoader.load()));
		logIn.setTitle("Library");
		logIn.show();

		Options.initialize(logIn);
	}

	public static void main(String[] args) {
		launch();
	}
}