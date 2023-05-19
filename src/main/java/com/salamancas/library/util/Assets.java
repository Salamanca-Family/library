package com.salamancas.library.util;

import javafx.scene.image.Image;

import java.net.URL;

public class Assets {

	public static URL logIn = Assets.class.getResource("/com/salamancas/library/assets/ui/fxml/LogIn.fxml");
	public static URL library = Assets.class.getResource("/com/salamancas/library/assets/ui/fxml/Library.fxml");

	public static Image userIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/user_icon.png"));
	public static Image bookIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/book_icon.png"));
	public static Image transactionIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/transaction_icon.png"));
	public static Image settingsIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/cog_icon.png"));
	public static Image usersIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/assets/images/users_icon.png"));


}
