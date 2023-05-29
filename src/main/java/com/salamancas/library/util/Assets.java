package com.salamancas.library.util;

import javafx.scene.image.Image;

import java.net.URL;

public class Assets {

	public static URL logIn = Assets.class.getResource("/com/salamancas/library/assets/fxml/LogIn.fxml");
	public static URL library = Assets.class.getResource("/com/salamancas/library/assets/fxml/Library.fxml");
	public static URL books = Assets.class.getResource("/com/salamancas/library/assets/fxml/categories/Books.fxml");
	public static URL users = Assets.class.getResource("/com/salamancas/library/assets/fxml/categories/Users.fxml");
	public static URL transaction = Assets.class.getResource("/com/salamancas/library/assets/fxml/categories/Transactions.fxml");
	public static URL authorInputDialog = Assets.class.getResource("/com/salamancas/library/assets/fxml/dialog/AuthorInputDialog.fxml");

	public static Image userIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/user_icon.png"));
	public static Image bookIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/book_icon.png"));
	public static Image transactionIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/transaction_icon.png"));
	public static Image settingsIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/cog_icon.png"));
	public static Image usersIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/users_icon.png"));

}
