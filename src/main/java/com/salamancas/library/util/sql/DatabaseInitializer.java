package com.salamancas.library.util.sql;

import java.io.File;

public class DatabaseInitializer {

	private static final String sqlBlock = """
			drop table if exists ACCOUNT;
			drop table if exists AUTHOR;
			drop table if exists AUTHOR_OF_BOOK;
			drop table if exists BOOK;
			drop table if exists BORROW;
			drop table if exists CLASS;
			drop table if exists HOMEROOM_TEACHER;
			drop table if exists COPY;
			drop table if exists PUBLISHER;
			drop table if exists STUDENT_IN_CLASS;
			drop table if exists TOWN;
			drop table if exists TOWN_OF_USER;
			drop table if exists TYPE;
			drop table if exists TYPE_OF_USER;
			drop table if exists USER;
			drop table if exists YEAR;
			drop view if exists BOOK_FOR_BOOKS_CATEGORY;
			drop view if exists COPY_FOR_BOOKS_CATEGORY;
			drop view if exists USER_FOR_USERS_CATEGORY;
			drop view if exists COPY_FOR_TRANSACTIONS_CATEGORY;
			   
			--==============================================================
			-- Table: USER
			--==============================================================
			create table USER (
			USER_ID              INTEGER              primary key autoincrement,
			USER_NAME            TEXT,
			USER_SURNAME         TEXT,
			USER_BIRTH_DATE      TEXT,
			USER_ADDRESS          TEXT
			);
			   
			--==============================================================
			-- Table: ACCOUNT
			--==============================================================
			create table ACCOUNT (
			ACCOUNT_ID           INTEGER              primary key,
			USER_ID              INTEGER,
			ACCOUNT_USERNAME     TEXT,
			ACCOUNT_PASSWORD     TEXT,
			foreign key (USER_ID)
			      references USER (USER_ID)
			);
			   
			--==============================================================
			-- Table: AUTHOR
			--==============================================================
			create table AUTHOR (
			AUTHOR_ID            INTEGER              primary key autoincrement,
			AUTHOR_NAME          TEXT
			);
			   
			--==============================================================
			-- Table: PUBLISHER
			--==============================================================
			create table PUBLISHER (
			PUBLISHER_ID         INTEGER              primary key autoincrement,
			PUBLISHER_NAME       TEXT
			);
			   
			--==============================================================
			-- Table: BOOK
			--==============================================================
			create table BOOK (
			BOOK_ID              INTEGER              primary key autoincrement,
			BOOK_TITLE           TEXT
			);
			   
			--==============================================================
			-- Table: AUTHOR_OF_BOOK
			--==============================================================
			create table AUTHOR_OF_BOOK (
			AUTHOR_ID            INTEGER              not null,
			BOOK_ID              INTEGER              not null,
			primary key (AUTHOR_ID, BOOK_ID),
			foreign key (AUTHOR_ID)
			      references AUTHOR (AUTHOR_ID),
			foreign key (BOOK_ID)
			      references BOOK (BOOK_ID)
			);
			   
			--==============================================================
			-- Table: COPY
			--==============================================================
			create table COPY (
			COPY_ID              INTEGER              primary key autoincrement,
			BOOK_ID              INTEGER,
			PUBLISHER_ID         INTEGER,
			COPY_SERIAL_NUMBER   TEXT,
			COPY_ISBN            TEXT,
			foreign key (BOOK_ID)
			      references BOOK (BOOK_ID),
			foreign key (PUBLISHER_ID)
			      references PUBLISHER (PUBLISHER_ID)
			);
			   
			--==============================================================
			-- Table: BORROW
			--==============================================================
			create table BORROW (
			COPY_ID              INTEGER              not null,
			USER_ID              INTEGER              not null,
			DATE_FROM            TEXT                 not null,
			DATE_TO              TEXT,
			ACCOUNT_ID           INTEGER,
			primary key (COPY_ID, USER_ID, DATE_FROM),
			foreign key (COPY_ID)
			      references COPY (COPY_ID),
			foreign key (USER_ID)
			      references USER (USER_ID),
			foreign key (ACCOUNT_ID)
			      references ACCOUNT (ACCOUNT_ID)
			);
			   
			--==============================================================
			-- Table: YEAR
			--==============================================================
			create table YEAR (
			YEAR_ID              INTEGER              primary key autoincrement,
			YEAR_NAME            TEXT
			);
			   
			--==============================================================
			-- Table: CLASS
			--==============================================================
			create table CLASS (
			CLASS_ID             INTEGER              primary key autoincrement,
			YEAR_ID              INTEGER,
			CLASS_INDEX          TEXT,
			foreign key (YEAR_ID)
			      references YEAR (YEAR_ID)
			);
			   
			--==============================================================
			-- Table: HOMEROOM_TEACHER
			--==============================================================
			create table HOMEROOM_TEACHER (
			CLASS_ID             INTEGER              not null,
			USER_ID              INTEGER              not null,
			DATE_FROM            TEXT                 not null,
			DATE_TO              TEXT,
			primary key (CLASS_ID, USER_ID, DATE_FROM),
			foreign key (CLASS_ID)
			      references CLASS (CLASS_ID),
			foreign key (USER_ID)
			      references USER (USER_ID)
			);
			   
			--==============================================================
			-- Table: STUDENT_IN_CLASS
			--==============================================================
			create table STUDENT_IN_CLASS (
			CLASS_ID             INTEGER              not null,
			USER_ID              INTEGER              not null,
			DATE_FROM            TEXT                 not null,
			DATE_TO              TEXT,
			primary key (CLASS_ID, USER_ID, DATE_FROM),
			foreign key (CLASS_ID)
			      references CLASS (CLASS_ID),
			foreign key (USER_ID)
			      references USER (USER_ID)
			);
			   
			--==============================================================
			-- Table: TOWN
			--==============================================================
			create table TOWN (
			TOWN_ID              INTEGER              primary key autoincrement,
			TOWN_NAME            TEXT
			);
			   
			--==============================================================
			-- Table: TOWN_OF_USER
			--==============================================================
			create table TOWN_OF_USER (
			USER_ID              INTEGER              not null,
			TOWN_ID              INTEGER              not null,
			DATE_FROM            TEXT                 not null,
			DATE_TO              TEXT,
			primary key (USER_ID, TOWN_ID, DATE_FROM),
			foreign key (USER_ID)
			      references USER (USER_ID),
			foreign key (TOWN_ID)
			      references TOWN (TOWN_ID)
			);
			   
			--==============================================================
			-- Table: TYPE
			--==============================================================
			create table TYPE (
			TYPE_ID              INTEGER              primary key,
			TYPE_NAME            TEXT
			);
			   
			--==============================================================
			-- Table: TYPE_OF_USER
			--==============================================================
			create table TYPE_OF_USER (
			TYPE_ID              INTEGER              not null,
			USER_ID              INTEGER              not null,
			DATE_FROM            TEXT                 not null,
			DATE_TO              TEXT,
			primary key (TYPE_ID, USER_ID, DATE_FROM),
			foreign key (TYPE_ID)
			      references TYPE (TYPE_ID),
			foreign key (USER_ID)
			      references USER (USER_ID)
			);
			--==============================================================
			-- View: ACCOUNT_FOR_LOG_IN
			--==============================================================
			   
			--==============================================================
			-- View: BOOK_FOR_BOOKS_CATEGORY
			--==============================================================
			create view BOOK_FOR_BOOKS_CATEGORY as
			select BOOK.BOOK_ID, BOOK_TITLE, AUTHOR_NAME
			from BOOK
			inner join AUTHOR_OF_BOOK AOB on BOOK.BOOK_ID = AOB.BOOK_ID
			inner join AUTHOR A on A.AUTHOR_ID = AOB.AUTHOR_ID
			order by BOOK.BOOK_ID;
			--==============================================================
			-- View: BOOK_FOR_BOOKS_CATEGORY
			--==============================================================
			create view COPY_FOR_BOOKS_CATEGORY as
			select COPY.COPY_ID, COPY_SERIAL_NUMBER, COPY_ISBN, BOOK_TITLE, PUBLISHER_NAME
			from COPY
			inner join BOOK B on B.BOOK_ID = COPY.BOOK_ID
			inner join PUBLISHER P on P.PUBLISHER_ID = COPY.PUBLISHER_ID;
			--==============================================================
			-- View: USER_FOR_USER_CATEGORY
			--==============================================================
			create view USER_FOR_USERS_CATEGORY as
			select USER.USER_ID, USER.USER_NAME, USER.USER_SURNAME, T.TYPE_NAME, Y1.YEAR_NAME as 'STUDENT_YEAR', Y2.YEAR_NAME as 'HOMEROOM_YEAR', C1.CLASS_INDEX as 'CLASS_INDEX', C2.CLASS_INDEX as 'HOMEROOM_TEACHER'
			from USER
			left join STUDENT_IN_CLASS SIC on USER.USER_ID = SIC.USER_ID
			left join CLASS C1 on C1.CLASS_ID = SIC.CLASS_ID
			left join YEAR Y1 on C1.YEAR_ID = Y1.YEAR_ID
			left join HOMEROOM_TEACHER HT on USER.USER_ID = HT.USER_ID
			left JOIN CLASS C2 on C2.CLASS_ID = HT.CLASS_ID
			left join YEAR Y2 on C2.YEAR_ID = Y2.YEAR_ID
			left join TYPE_OF_USER TOU on USER.USER_ID = TOU.USER_ID
			left join TYPE T on T.TYPE_ID = TOU.TYPE_ID
			where HT.DATE_TO is null and SIC.DATE_TO is null and TOU.DATE_TO is null and T.TYPE_ID != 4;
			--==============================================================
			-- View: COPY_FOR_TRANSACTION
			--==============================================================
			create view COPY_FOR_TRANSACTIONS_CATEGORY as
			select COPY.COPY_ID, COPY_SERIAL_NUMBER, BOOK_TITLE, PUBLISHER_NAME, DATE_FROM, DATE_TO
			from COPY
			inner join BOOK B on B.BOOK_ID = COPY.BOOK_ID
			inner join PUBLISHER P on P.PUBLISHER_ID = COPY.PUBLISHER_ID
			left join BORROW on BORROW.COPY_ID = COPY.COPY_ID
			order by COPY_SERIAL_NUMBER, DATE_FROM desc, DATE_TO desc;
			   
			-- Pre-generated
			-- Type
			INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (1, 'Učenik');
			INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (2, 'Profesor');
			INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (3, 'Bibliotekar');
			INSERT INTO TYPE (TYPE_ID, TYPE_NAME) VALUES (4, 'Admin');
			-- Town
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
			-- User
			INSERT INTO USER (USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS) VALUES ('Administrator', '', '', '');
			INSERT INTO USER (USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS) VALUES ('Luka', 'Nedeljkov', '2005-02-04', 'Djordja Zličića 23');
			INSERT INTO USER (USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS) VALUES ('Relja', 'Djordjevic', '2004-04-25', 'Drvarska 1');
			INSERT INTO USER (USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS) VALUES ('Stefan', 'Vasiljevic', '2004-03-19', 'Dimitrija Tucovica 3');
			INSERT INTO USER (USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS) VALUES ('Svetlana', 'Erceg', '1975-01-01', 'Novosadska 1');
			-- Type of user
			INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (4, 1, '2023-05-18', null);
			INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (2, 2, '2023-05-18', null);
			INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (2, 3, '2023-05-18', null);
			INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (2, 4, '2023-05-18', null);
			INSERT INTO TYPE_OF_USER (TYPE_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (3, 5, '2023-05-18', null);
			-- Account
			INSERT INTO ACCOUNT (ACCOUNT_ID, USER_ID, ACCOUNT_USERNAME, ACCOUNT_PASSWORD) VALUES (1, 1, 'admin', 'admin');
			-- Author
			INSERT INTO AUTHOR (AUTHOR_NAME) VALUES ('I. Andric');
			INSERT INTO AUTHOR (AUTHOR_NAME) VALUES ('J. J. Zmaj');
			INSERT INTO AUTHOR (AUTHOR_NAME) VALUES ('Branislav Nušić');
			-- Publisher
			INSERT INTO PUBLISHER (PUBLISHER_NAME) VALUES ('Laguna');
			INSERT INTO PUBLISHER (PUBLISHER_NAME) VALUES ('Zavod za udžbenike');
			-- Book
			INSERT INTO BOOK (BOOK_TITLE) VALUES ('Na Drini cuprija');
			INSERT INTO BOOK (BOOK_TITLE) VALUES ('Orlovi rano lete');
			INSERT INTO BOOK (BOOK_TITLE) VALUES ('Zbirka matematike za 4. razred');
			-- Author of Book
			INSERT INTO AUTHOR_OF_BOOK (AUTHOR_ID, BOOK_ID) VALUES (2, 1);
			INSERT INTO AUTHOR_OF_BOOK (AUTHOR_ID, BOOK_ID) VALUES (2, 2);
			INSERT INTO AUTHOR_OF_BOOK (AUTHOR_ID, BOOK_ID) VALUES (3, 1);
			INSERT INTO AUTHOR_OF_BOOK (AUTHOR_ID, BOOK_ID) VALUES (3, 2);
			INSERT INTO AUTHOR_OF_BOOK (AUTHOR_ID, BOOK_ID) VALUES (4, 3);
			-- Copy
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (1, 1, '1001', '974-1000000001');
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (1, 2, '1002', '974-1000000002');
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (2, 1, '1003', '974-1000000003');
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (2, 2, '1004', '974-1000000004');
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (3, 1, '1005', '974-1000000005');
			INSERT INTO COPY (BOOK_ID, PUBLISHER_ID, COPY_SERIAL_NUMBER, COPY_ISBN) VALUES (3, 2, '1006', '974-1000000006');
			-- Year
			INSERT INTO YEAR (YEAR_NAME) VALUES ('2019');
			-- Class
			INSERT INTO CLASS (YEAR_ID, CLASS_INDEX) VALUES (1, '1');
			-- Student in Class
			INSERT INTO STUDENT_IN_CLASS (CLASS_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (1, 2, '2023-05-21', null);
			INSERT INTO STUDENT_IN_CLASS (CLASS_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (1, 3, '2023-05-21', null);
			INSERT INTO STUDENT_IN_CLASS (CLASS_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (1, 4, '2023-05-21', null);
			-- Homeroom Teacher
			INSERT INTO HOMEROOM_TEACHER (CLASS_ID, USER_ID, DATE_FROM, DATE_TO) VALUES (1, 5, '2023-05-21', null);
			-- Borrow
			INSERT INTO BORROW (COPY_ID, USER_ID, DATE_FROM, DATE_TO, ACCOUNT_ID) VALUES (1, 2, '2023-01-01', '2023-01-02', 1);
			INSERT INTO BORROW (COPY_ID, USER_ID, DATE_FROM, DATE_TO, ACCOUNT_ID) VALUES (1, 3, '2023-01-02', '2023-01-03', 1);
			INSERT INTO BORROW (COPY_ID, USER_ID, DATE_FROM, DATE_TO, ACCOUNT_ID) VALUES (1, 4, '2023-01-03', null, 1);
			INSERT INTO BORROW (COPY_ID, USER_ID, DATE_FROM, DATE_TO, ACCOUNT_ID) VALUES (2, 5, '2023-01-01', null, 1);""";

	public static void initializeDatabase() {
		File file = new File("data.db");
		if(file.exists()) {
			return;
		}
		SQLUtils sqlUtils = SQLUtils.getInstance();
		String[] sqlQuerries = sqlBlock.split(";");
		for(String sql : sqlQuerries) {
			sqlUtils.createTables(sql);
		}
	}

}
