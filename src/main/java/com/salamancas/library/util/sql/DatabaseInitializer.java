package com.salamancas.library.util.sql;

import java.io.File;

public class DatabaseInitializer {

	private static final String sqlBlock = """
				create table ACCOUNT (ACCOUNT_ID           INTEGER              not null, USER_ID              INTEGER, ACCOUNT_USERNAME     VARCHAR(25), ACCOUNT_PASSWORD     VARCHAR(25), primary key (ACCOUNT_ID), foreign key (USER_ID)    references USER (USER_ID) );
				create table AUTHOR (AUTHOR_ID            INTEGER              primary key autoincrement, AUTHOR_NAME          VARCHAR(30), AUTHOR_SURNAME          VARCHAR(30));
				create table BOOK (BOOK_ID              INTEGER              primary key autoincrement, BOOK_TITLE           VARCHAR(50), BOOK_ISBN            VARCHAR(13), BOOK_ISBN_OLD        VARCHAR(10));
				create table AUTHOR_OF_BOOK (AUTHOR_ID            INTEGER              not null, BOOK_ID              INTEGER              not null, primary key (AUTHOR_ID, BOOK_ID), foreign key (AUTHOR_ID)    references AUTHOR (AUTHOR_ID), foreign key (BOOK_ID)    references BOOK (BOOK_ID));
				create table COPY (COPY_ID              INTEGER              primary key autoincrement, BOOK_ID              INTEGER              not null, COPY_SERIAL_NUMBER   CHAR(10), foreign key (BOOK_ID)    references BOOK (BOOK_ID));create table "USER" (USER_ID              INTEGER              primary key autoincrement, USER_NAME            VARCHAR(30), USER_SURNAME         VARCHAR(30), USER_ADRESS          VARCHAR(45));
				create table BORROW (COPY_ID              INTEGER              not null, USER_ID              INTEGER              not null, DATE_FROM            DATE                 not null, DATE_TO              DATE, ACCOUNT_ID           INTEGER, NOTED                VARCHAR(150), primary key (COPY_ID, USER_ID, DATE_FROM), foreign key (COPY_ID)    references COPY (COPY_ID), foreign key (USER_ID)    references "USER" (USER_ID), foreign key (ACCOUNT_ID)    references ACCOUNT (ACCOUNT_ID));
				create table YEAR (YEAR_ID              INTEGER              primary key autoincrement, YEAR_NAME            VARCHAR(5)           not null );
				create table CLASS (CLASS_ID             INTEGER              primary key autoincrement, YEAR_ID              INTEGER              not null, CLASS_INDEX          VARCHAR(2), foreign key (YEAR_ID)    references YEAR (YEAR_ID));
				create table CLASS_ELDER (CLASS_ID             INTEGER              not null, USER_ID              INTEGER              not null, DATE_FROM            DATE                 not null, DATE_TO              DATE, primary key (CLASS_ID, USER_ID, DATE_FROM), foreign key (CLASS_ID)    references CLASS (CLASS_ID), foreign key (USER_ID)    references "USER" (USER_ID) );
				create table PUBLISHER (PUBLISHER_ID         INTEGER              primary key autoincrement, PUBLISHER_NAME       VARCHAR(100) );
				create table PUBLISHER_OF_BOOK (PUBLISHER_ID         INTEGER              not null, BOOK_ID              INTEGER              not null, primary key (PUBLISHER_ID, BOOK_ID), foreign key (PUBLISHER_ID)    references PUBLISHER (PUBLISHER_ID), foreign key (BOOK_ID)    references BOOK (BOOK_ID));
				create table STUDENT_IN_CLASS (CLASS_ID             INTEGER              not null, USER_ID              INTEGER              not null, DATE_FROM            DATE                 not null, DATE_TO              DATE, primary key (CLASS_ID, USER_ID, DATE_FROM), foreign key (CLASS_ID)    references CLASS (CLASS_ID), foreign key (USER_ID)    references "USER" (USER_ID));
				create table TOWN (TOWN_ID              INTEGER              not null, TOWN_NAME            VARCHAR(30), primary key (TOWN_ID));
				create table TOWN_OF_USER (USER_ID              INTEGER              not null, TOWN_ID              INTEGER              not null, DATE_FROM            DATE                 not null, DATE_TO              DATE, primary key (USER_ID, TOWN_ID, DATE_FROM), foreign key (USER_ID)    references "USER" (USER_ID), foreign key (TOWN_ID)    references TOWN (TOWN_ID));
				create table TYPE (TYPE_ID              INTEGER              not null, TYPE_NAME            VARCHAR(10), primary key (TYPE_ID));
				create table TYPE_OF_USER (TYPE_ID              INTEGER              not null, USER_ID              INTEGER              not null, DATE_FROM            DATE                 not null, DATE_TO              DATE,primary key (TYPE_ID, USER_ID, DATE_FROM), foreign key (TYPE_ID)    references TYPE (TYPE_ID), foreign key (USER_ID)    references "USER" (USER_ID));
				create table "USER" (USER_ID INTEGER primary key autoincrement, USER_NAME  VARCHAR(30), USER_SURNAME VARCHAR(30), USER_ADRESS VARCHAR(45));
				INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (1, 'Učenik');
				INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (2, 'Profesor');
				INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (3, 'Bibliotekar');
				INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (4, 'Admin');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (1, 'Bačka Palanka');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (2, 'Neštin');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (3, 'Vizić');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (4, 'Čelarevo');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (5, 'Gložan');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (6, 'Begeč');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (7, 'Futog');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (8, 'Veternik');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (9, 'Novi Sad');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (10, 'Karadjordjevo');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (11, 'Mladenovo');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (12, 'Obrovac');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (13, 'Tovariševo');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (14, 'Bač');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (15, 'Nova Gajdobra');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (16, 'Gajdobra');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (17, 'Silbaš');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (18, 'Despotovo');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (19, 'Parage');
				INSERT INTO TOWN (TOWN_ID, TOWN_NAME) VALUES (20, 'Pivnice');
				INSERT INTO USER (USER_NAME, USER_SURNAME, USER_ADRESS) VALUES ('Administrator', '', '');
				INSERT INTO USER (USER_NAME, USER_SURNAME, USER_ADRESS) VALUES ('Luka', 'Nedeljkov', 'Djordja Zličića 23');
				INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (4, 2, '2023-5-18', null);
				INSERT INTO TOWN_OF_USER (USER_ID, TOWN_ID, DATE_FROM, DATE_TO) VALUES (1, 2, '2023-5-18', null);
				INSERT INTO ACCOUNT (ACCOUNT_ID, USER_ID, ACCOUNT_USERNAME, ACCOUNT_PASSWORD) VALUES (1, 1, 'admin', password('admin');
			""";

	public static void initializeDatabase() {
		File file = new File("data.db");
		if(file.exists()) {
			return;
		}
		SQLUtils sqlUtils = SQLUtils.getInstance();
		String[] sqlQuerries = sqlBlock.split("\n");
		for(String sql : sqlQuerries) {
			sqlUtils.createTables(sql);
		}
	}

}
