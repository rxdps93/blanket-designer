package com.dom.bd.scenes;

import com.dom.bd.controller.SceneController;
import com.dom.bd.template.BlanketTemplate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Initial screen in the application (launcher, main menu, etc.).
 * 
 * @author rxdps93
 */
public class LauncherScene {
	
	private SceneController controller;
	
	public LauncherScene(SceneController controller) {
		this.controller = controller;
	}

	public Scene getMenuScene() {
		
		Button newBlanket = new Button("Create New Blanket");
		newBlanket.setMinSize(150, 50);
		newBlanket.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				controller.useNewBlanketScene();
			}
		});
		
		Button loadBlanket = new Button("Load Existing Blanket");
		loadBlanket.setMinSize(150, 50);
		loadBlanket.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				BlanketTemplate template = controller.getFileManager().openTemplateFile();
				if (template != null) {
					controller.useDesignerScene(template);
				}
			}
		});
		
		Button exitApplication = new Button("Exit Blanket Designer");
		exitApplication.setMinSize(150, 50);
		exitApplication.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent t) {
				controller.getMainStage().close();
			}
		});
		
		VBox contentPane = new VBox();
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setSpacing(25);
		
		HBox buttonPane = new HBox();
		buttonPane.setSpacing(25);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(newBlanket, loadBlanket, exitApplication);
		contentPane.getChildren().addAll(controller.getLogoView(), buttonPane);
		
		Scene launchScene = new Scene(contentPane, 550, 300);
		controller.useTitle("Blanket Designer");
		
		return launchScene;
	}
}
