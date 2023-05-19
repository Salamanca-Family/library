package com.salamancas.library;

import com.salamancas.library.ui.LibraryApplication;
import com.salamancas.library.util.sql.DatabaseInitializer;

public class Launcher {

	public static void main(String[] args) {
		DatabaseInitializer.initializeDatabase();
		LibraryApplication.main(args);
	}

}
