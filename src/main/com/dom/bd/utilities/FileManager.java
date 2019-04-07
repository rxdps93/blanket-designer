package com.dom.bd.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.dom.bd.controller.SceneController;
import com.dom.bd.template.BlanketTemplate;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManager {

	private SceneController controller;

	public FileManager(SceneController controller) {

		this.controller = controller;
		manageSaveDirectory();
	}

	private void manageSaveDirectory() {
		File saveDir = new File(System.getProperty("user.home") + "/Documents/BlanketDesigner/templates");
		if (!saveDir.exists() || !saveDir.isDirectory()) {
			saveDir.mkdirs();
		}
	}
	
	private void writeToFile(Object content, File file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(content);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private BlanketTemplate readFromFile(File file) {
		
		BlanketTemplate template = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			template = (BlanketTemplate)in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return template;
	}
	
	public void saveTemplate(BlanketTemplate template) {
		
		File file = new File(
				System.getProperty("user.home") + "/Documents/BlanketDesigner/templates/" + template.getName() + ".bdjf");
		writeToFile(template, file);
	}

	public void saveTemplateAs(BlanketTemplate template) {
		FileChooser saveFileAs = new FileChooser();
		saveFileAs.setTitle("Save Blanket Design Template");
		saveFileAs.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/BlanketDesigner/templates"));
		saveFileAs.setInitialFileName(template.getName() + ".bdjf");
		saveFileAs.getExtensionFilters().addAll(
				new ExtensionFilter("BDJF", "*.bdjf"),
				new ExtensionFilter("All Files", "*.*"));
		
		File file = saveFileAs.showSaveDialog(this.controller.getMainStage());
		
		if (file != null)
			writeToFile(template, file);
	}

	public BlanketTemplate openTemplateFile() {
		FileChooser openFile = new FileChooser();
		openFile.setTitle("Load Blanket Design Template");
		openFile.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/BlanketDesigner/templates"));
		openFile.getExtensionFilters().addAll(
				new ExtensionFilter("BDJF", "*.bdjf"),
				new ExtensionFilter("All Files", "*.*"));
		return readFromFile(openFile.showOpenDialog(this.controller.getMainStage()));
	}
}
