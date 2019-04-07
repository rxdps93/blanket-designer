package com.dom.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.dom.bd.controller.SceneController;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The purpose of this program is to assist my incredibly talented wife with her "granny-square" blankets.
 * 
 * @author rxdps93
 */
public class BlanketDesigner extends Application {
	
	private ImageView getLogoView() {
		
		Image logo;
		try {
			logo = new Image(new FileInputStream("src/resources/logo.png"));
			return new ImageView(logo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		SceneController controller = new SceneController(primaryStage, getLogoView());
		controller.useLauncherScene();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
