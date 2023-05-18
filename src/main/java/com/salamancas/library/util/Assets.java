package com.salamancas.library.util;

import javafx.scene.image.Image;

import java.net.URL;

public class Assets {

	public static URL logIn = Assets.class.getResource("/com/salamancas/library/ui/fxml/LogIn.fxml");
	public static URL library = Assets.class.getResource("/com/salamancas/library/ui/fxml/Library.fxml");

	public static Image userIcon = new Image(Assets.class.getResourceAsStream("/com/salamancas/library/images/userIcon.png"));

}
