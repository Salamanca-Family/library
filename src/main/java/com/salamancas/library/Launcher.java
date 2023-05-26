package com.salamancas.library;

import com.salamancas.library.ui.LibraryApplication;
import com.salamancas.library.util.sql.DatabaseInitializer;
import com.salamancas.library.util.sql.HibernateUtil;

public class Launcher {

	public static void main(String[] args) {
		DatabaseInitializer.initializeDatabase();
		HibernateUtil.init();
		LibraryApplication.main(args);
	}

}
