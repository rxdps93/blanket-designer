package com.dom.bd.controller;

import com.dom.bd.scenes.DesignerScene;
import com.dom.bd.scenes.LauncherScene;
import com.dom.bd.scenes.NewBlanketScene;
import com.dom.bd.template.BlanketTemplate;
import com.dom.bd.utilities.FileManager;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Manages the stage - allows for more modular code.
 * 
 * @author rxdps93
 */
public class SceneController {
	
	private Stage mainStage;
	private ImageView logo;
	
	private FileManager fileManager;
	
	private LauncherScene launcherScene;
	private NewBlanketScene newBlanketScene;
	private DesignerScene designerScene;

	public SceneController(Stage mainStage, ImageView logo) {
		
		this.mainStage = mainStage;
		this.logo = logo;
		
		fileManager = new FileManager(this);
		launcherScene = new LauncherScene(this);
		newBlanketScene = new NewBlanketScene(this);
		designerScene = new DesignerScene(this);
	}
	
	private void switchScene(Scene scene) {
		mainStage.setScene(scene);
		mainStage.sizeToScene();
	}
	
	public void useLauncherScene() {
		this.switchScene(this.launcherScene.getMenuScene());
	}
	
	public void useNewBlanketScene() {
		this.switchScene(this.newBlanketScene.getNewBlanketScene());
	}
	
	public void useDesignerScene(BlanketTemplate template) {
		
		this.designerScene.setTemplate(template);
		this.switchScene(this.designerScene.getDesignerScene());
	}
	
	public void useTitle(String title) {
		this.mainStage.setTitle(title);
	}
	
	public Stage getMainStage() {
		return this.mainStage;
	}
	
	public ImageView getLogoView() {
		return this.logo;
	}
	
	public FileManager getFileManager() {
		return this.fileManager;
	}
}
