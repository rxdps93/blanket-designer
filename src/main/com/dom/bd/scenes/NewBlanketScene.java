package com.dom.bd.scenes;

import com.dom.bd.controller.SceneController;
import com.dom.bd.controls.LimitedTextField;
import com.dom.bd.template.BlanketTemplate;
import com.dom.bd.utilities.Global;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Scene for creating a new blanket template.
 * 
 * @author rxdps93
 */
public class NewBlanketScene {

	private SceneController controller;
	
	private final int NAME_LEN_MAX = 30;
	private final int SPINNER_WIDTH_MIN = 1;
	private final int SPINNER_WIDTH_MAX = 99;
	private final int SPINNER_WIDTH_DEF = 24;
	private final int SPINNER_HEIGHT_MIN = 1;
	private final int SPINNER_HEIGHT_MAX = 99;
	private final int SPINNER_HEIGHT_DEF = 32;
	
	public NewBlanketScene(SceneController controller) {
		this.controller = controller;
	}
	
	private void addSpacer(GridPane grid, int col, int row) {
		grid.add(new Label("\t"), col, row);
	}

	public Scene getNewBlanketScene() {

		GridPane contentGrid = new GridPane();
		contentGrid.setAlignment(Pos.CENTER);

		Label widthLabel = new Label("Blanket Width (in # of squares)");
		Spinner<Integer> widthSpinner = new Spinner<>();
		widthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(SPINNER_WIDTH_MIN, SPINNER_WIDTH_MAX, SPINNER_WIDTH_DEF));

		Label heightLabel = new Label("Blanket Height (in # of squares)");
		Spinner<Integer> heightSpinner = new Spinner<>();
		heightSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(SPINNER_HEIGHT_MIN, SPINNER_HEIGHT_MAX, SPINNER_HEIGHT_DEF));
		
		Label nameLabel = new Label("Blanket Name (max of " + this.NAME_LEN_MAX + " characters)");
		TextField nameField = new LimitedTextField(this.NAME_LEN_MAX);
		nameField.setText("Untitled");
		
		Label colorLabel = new Label("Select initial background color");
		ComboBox<String> colorSelector = new ComboBox<>();
		colorSelector.getItems().addAll(Global.OBJECTS.getColorNames());
		colorSelector.setValue("Black");

		Button goBack = new Button("Cancel");
		goBack.setMinSize(150, 50);
		goBack.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				controller.useLauncherScene();
			}
		});

		Button accept = new Button("Accept");
		accept.setMinSize(150, 50);
		accept.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				controller.useDesignerScene(new BlanketTemplate(heightSpinner.getValue(), widthSpinner.getValue(), 
						nameField.getText(), Global.OBJECTS.getColorByName(colorSelector.getValue())));
			}
		});

		// Add content for dimensions
		contentGrid.add(widthLabel, 0, 0);
		contentGrid.add(widthSpinner, 2, 0);
		contentGrid.add(heightLabel, 0, 2);
		contentGrid.add(heightSpinner, 2, 2);
		
		// Add name selection
		contentGrid.add(nameLabel, 0, 4);
		contentGrid.add(nameField, 2, 4);
		
		// Add color selector
		contentGrid.add(colorLabel, 0, 6);
		contentGrid.add(colorSelector, 2, 6);

		// Add buttons
		contentGrid.add(goBack, 0, 8);
		contentGrid.add(accept, 2, 8);

		//Add spacers
		for (int i = 0; i < 8; i++)
			addSpacer(contentGrid, 1, i);

		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setSpacing(25);
		content.getChildren().addAll(controller.getLogoView(), contentGrid);

		Scene newBlanketScene = new Scene(content, 550, 475);
		controller.useTitle("Blanket Designer - New Template");

		return newBlanketScene;
	}
}
