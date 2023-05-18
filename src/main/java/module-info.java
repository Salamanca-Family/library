module com.salamancas.library {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;


	opens com.salamancas.library to javafx.fxml;
	exports com.salamancas.library;
	exports com.salamancas.library.ui;
	opens com.salamancas.library.ui to javafx.fxml;
	exports com.salamancas.library.ui.controller;
	opens com.salamancas.library.ui.controller to javafx.fxml;
}