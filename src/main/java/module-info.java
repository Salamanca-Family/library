module com.salamancas.library {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires jakarta.persistence;
	requires java.naming;
	requires org.hibernate.orm.core;

	exports com.salamancas.library;
	exports com.salamancas.library.ui;
	opens com.salamancas.library.ui to javafx.fxml;
	exports com.salamancas.library.ui.controller;
	opens com.salamancas.library.ui.controller to javafx.fxml;
	exports com.salamancas.library.ui.controller.categories to javafx.fxml;
	opens com.salamancas.library.ui.controller.categories to javafx.fxml;
	opens com.salamancas.library.model.table to org.hibernate.orm.core;
	opens com.salamancas.library.model.view to org.hibernate.orm.core;
	exports com.salamancas.library.ui.controller.dialog to javafx.fxml;
}