package com.dom.bd.scenes;

import com.dom.bd.controller.SceneController;
import com.dom.bd.template.BlanketTemplate;
import com.dom.bd.template.CellType;
import com.dom.bd.template.BlanketCell;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class DesignerScene {

	private SceneController controller;
	private BlanketTemplate template;
	private int colorSelectorSize = 35;
	private Color currentColor = Color.WHITE;

	public DesignerScene(SceneController controller) {
		this.controller = controller;
	}

	public void setTemplate(BlanketTemplate template) {
		this.template = template;
	}

	public BlanketTemplate getTemplate() {
		return this.template;
	}

	public RadioButton configureColorButton(Color color, int size, ToggleGroup grp) {

		RadioButton btn = new RadioButton();
		btn.setToggleGroup(grp);
		btn.setStyle(String.format("-fx-base: rgb(%f, %f, %f); ", color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255));
		btn.setUserData(color);
		btn.setMinSize(size, size);
		btn.setMaxSize(size, size);
		return btn;
	}

	private GridPane createColorGrid(ToggleGroup colorSelectors) {

		GridPane colorGrid = new GridPane();
		colorGrid.setAlignment(Pos.CENTER);

		// Row 1
		RadioButton selectBlack = configureColorButton(Color.BLACK, colorSelectorSize, colorSelectors);
		RadioButton selectDarkGray = configureColorButton(Color.DARKGRAY, colorSelectorSize, colorSelectors);
		RadioButton selectGray = configureColorButton(Color.GRAY, colorSelectorSize, colorSelectors);
		RadioButton selectBlue = configureColorButton(Color.BLUE, colorSelectorSize, colorSelectors);
		RadioButton selectSlate = configureColorButton(Color.SLATEGRAY, colorSelectorSize, colorSelectors);
		RadioButton selectPurple = configureColorButton(Color.PURPLE, colorSelectorSize, colorSelectors);
		RadioButton selectGreen = configureColorButton(Color.GREEN, colorSelectorSize, colorSelectors);
		RadioButton selectOlive = configureColorButton(Color.DARKOLIVEGREEN, colorSelectorSize, colorSelectors);
		RadioButton selectDarkRed = configureColorButton(Color.DARKRED, colorSelectorSize, colorSelectors);
		RadioButton selectBrown = configureColorButton(Color.SADDLEBROWN, colorSelectorSize, colorSelectors);

		// Row 2
		RadioButton selectWhite = configureColorButton(Color.WHITE, colorSelectorSize, colorSelectors);
		RadioButton selectLightGray = configureColorButton(Color.LIGHTGRAY, colorSelectorSize, colorSelectors);
		RadioButton selectBlueGray = configureColorButton(Color.CADETBLUE, colorSelectorSize, colorSelectors);
		RadioButton selectLightBlue = configureColorButton(Color.LIGHTSKYBLUE, colorSelectorSize, colorSelectors);
		RadioButton selectCyan = configureColorButton(Color.CYAN, colorSelectorSize, colorSelectors);
		RadioButton selectOrange = configureColorButton(Color.DARKORANGE, colorSelectorSize, colorSelectors);
		RadioButton selectYellow = configureColorButton(Color.YELLOW, colorSelectorSize, colorSelectors);
		RadioButton selectLightGreen = configureColorButton(Color.LAWNGREEN, colorSelectorSize, colorSelectors);
		RadioButton selectRed = configureColorButton(Color.RED, colorSelectorSize, colorSelectors);
		RadioButton selectLightBrown = configureColorButton(Color.SANDYBROWN, colorSelectorSize, colorSelectors);

		colorGrid.addRow(0,
				selectBlack,
				selectDarkGray,
				selectGray,
				selectBlue,
				selectSlate,
				selectPurple,
				selectGreen,
				selectOlive,
				selectDarkRed,
				selectBrown);

		colorGrid.addRow(1,
				selectWhite,
				selectLightGray,
				selectBlueGray,
				selectLightBlue,
				selectCyan,
				selectOrange,
				selectYellow,
				selectLightGreen,
				selectRed,
				selectLightBrown);

		colorSelectors.selectToggle(selectBlack);

		return colorGrid;
	}
	
	private void showCellByType(StackPane cell, StackPane halfType) {
		
		if (((BlanketCell)cell.getUserData()).getType() != CellType.WHOLE) {
			cell.getChildren().get(1).toBack();
			if (((BlanketCell)cell.getUserData()).getType() == CellType.HALF_TLBR) {
				halfType.setRotate(0);
			} else {
				halfType.setRotate(90);
			}
		}
	}

	private GridPane createBlanketGrid() {

		GridPane templateGrid = new GridPane();
		templateGrid.setAlignment(Pos.CENTER);
		templateGrid.setHgap(1);
		templateGrid.setVgap(1);

		for (int i = 0; i < template.getHeight(); i++) {
			for (int j = 0; j < template.getWidth(); j++) {

				StackPane cell = new StackPane();
				StackPane halfType = new StackPane();

				Rectangle wholeCell = new Rectangle(this.template.getCellSize(), this.template.getCellSize());
				Polygon priCell = new Polygon();
				Polygon altCell = new Polygon();

				priCell.getPoints().addAll(new Double[] {
						0.0, 0.0,
						(double)this.template.getCellSize(), 0.0,
						0.0, (double)this.template.getCellSize()
				});

				altCell.getPoints().addAll(new Double[] {
						(double)this.template.getCellSize(), (double)this.template.getCellSize(),
						0.0, (double)this.template.getCellSize(),
						(double)this.template.getCellSize(), 0.0
				});

				halfType.getChildren().addAll(priCell, altCell);
				cell.getChildren().addAll(halfType, wholeCell);

				cell.setUserData(template.getCell(i, j));
				wholeCell.setFill(template.getCell(i, j).getColor());

				priCell.setFill(template.getCell(i, j).getColor());
				altCell.setFill(template.getCell(i, j).getAltColor());
				
				showCellByType(cell, halfType);
				cell.setStyle("-fx-border-color: black");

				/*
				 * Handles clicking on a single cell:
				 * 
				 * Left Click: Set the primary color to the currently selected color
				 * Shift + Click: Switch cell type (Whole -> TLBR -> TRBL)
				 * Control + Click: Swap primary and alternate colors
				 * Alt + Click OR Right Click: Set the alternate color to the currently selected color
				 * TODO: Fix alt + left click
				 */
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						
						if (!event.isShiftDown()) {
							if (event.getButton() == MouseButton.PRIMARY) {
								if (!event.isAltDown() && !event.isControlDown()) {
									wholeCell.setFill(currentColor);
									priCell.setFill(currentColor);
									((BlanketCell)cell.getUserData()).setColor(currentColor);
								} else if (event.isControlDown() && !event.isAltDown()) {
									((BlanketCell)cell.getUserData()).swapColors();
									wholeCell.setFill(((BlanketCell)cell.getUserData()).getColor());
									priCell.setFill(((BlanketCell)cell.getUserData()).getColor());
									altCell.setFill(((BlanketCell)cell.getUserData()).getAltColor());
								} else {
									altCell.setFill(currentColor);
									((BlanketCell)cell.getUserData()).setAltColor(currentColor);
								}
							} else if (event.getButton() == MouseButton.SECONDARY) {
								altCell.setFill(currentColor);
								((BlanketCell)cell.getUserData()).setAltColor(currentColor);
							}
						} else {
							if (((BlanketCell)cell.getUserData()).getType() == CellType.WHOLE) {
								((BlanketCell)cell.getUserData()).setType(CellType.HALF_TLBR);
								cell.getChildren().get(1).toBack();
							} else if (((BlanketCell)cell.getUserData()).getType() == CellType.HALF_TLBR) {
								((BlanketCell)cell.getUserData()).setType(CellType.HALF_TRBL);
								halfType.setRotate(90);
							} else {
								((BlanketCell)cell.getUserData()).setType(CellType.WHOLE);
								halfType.setRotate(0);
								cell.getChildren().get(1).toBack();
							}
						}
					}
				});
				
				/*
				 * Handles cell highlighting when the mouse enters a given cell
				 * 
				 * Hold down alt before entering the cell to highlight the alternate color slot
				 */
				cell.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {					
						if (((BlanketCell)cell.getUserData()).getType() != CellType.WHOLE) {
							if (event.isAltDown()) {
								altCell.setStroke(Color.CYAN);
								altCell.setStrokeType(StrokeType.INSIDE);
							} else {
								priCell.setStroke(Color.CYAN);
								priCell.setStrokeType(StrokeType.INSIDE);
							}
						} else {
							cell.setStyle("-fx-border-color: cyan");
						}
					}
				});
				
				/*
				 * Handles deactivating cell highlighting when the mouse exits a given cell
				 */
				cell.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						cell.setStyle("-fx-border-color: black");
						priCell.setStroke(null);
						altCell.setStroke(null);
					}
				});
				//				
				//				cell.setOnDragDetected(new EventHandler<MouseEvent>() {
				//					@Override
				//					public void handle(MouseEvent event) {
				//						Dragboard db = cell.startDragAndDrop(TransferMode.ANY);
				//						ClipboardContent cbc = new ClipboardContent();
				//						cbc.putString(currentColor.toString());
				//						db.setContent(cbc);
				//						cell.setFill(currentColor);
				//						((BlanketCell)cell.getUserData()).setColor(currentColor);
				//						event.consume();
				//					}
				//				});
				//				
				//				cell.setOnDragOver(new EventHandler<DragEvent>() {
				//					@Override
				//					public void handle(DragEvent event) {
				//						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				//						cell.setFill(currentColor);
				//						((BlanketCell)cell.getUserData()).setColor(currentColor);
				//						event.consume();
				//					}
				//				});
				//				
				//				cell.setOnDragDropped(new EventHandler<DragEvent>() {
				//					@Override
				//					public void handle(DragEvent event) {
				//						Dragboard db = event.getDragboard();
				//						cell.setFill(Color.valueOf(db.getString()));
				//						((BlanketCell)cell.getUserData()).setColor(currentColor);
				//						event.setDropCompleted(true);
				//						event.consume();
				//					}
				//				});
				//				
				//				cell.setOnDragDone(new EventHandler<DragEvent>() {
				//					@Override
				//					public void handle(DragEvent event) {
				//						event.consume();
				//					}
				//				});

				templateGrid.add(cell, j, i);
			}
		}

		return templateGrid;
	}

	public Scene getDesignerScene() {

		ToggleGroup colorSelectors = new ToggleGroup();

		Rectangle rect = new Rectangle(template.getWidth() * template.getCellSize(), template.getHeight() * template.getCellSize());
		rect.setStroke(Color.BLACK);

		Rectangle currentColorDisplay = new Rectangle(colorSelectorSize, colorSelectorSize);
		currentColorDisplay.setStroke(Color.BLACK);
		currentColorDisplay.setStrokeWidth(2);

		Button toLauncher = new Button("Return to Launcher");
		toLauncher.setMinSize(150, 50);
		toLauncher.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				controller.useLauncherScene();
			}
		});

		Button save = new Button("Save");
		save.setMinSize(150, 50);
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.getFileManager().saveTemplate(template);
			}
		});

		Button saveAs = new Button("Save As");
		saveAs.setMinSize(150, 50);
		saveAs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.getFileManager().saveTemplateAs(template);
			}
		});

		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(25);
		buttons.getChildren().addAll(save, saveAs, toLauncher);

		colorSelectors.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

				Color newColor = (Color)colorSelectors.getSelectedToggle().getUserData();
				rect.setFill(newColor);
				rect.setOpacity(1);

				currentColorDisplay.setFill(newColor);
				currentColorDisplay.setOpacity(1);
				currentColor = newColor;
			}
		});

		HBox colorContent = new HBox();
		colorContent.setAlignment(Pos.CENTER);
		colorContent.setSpacing(10);

		VBox currentColor = new VBox();
		currentColor.setAlignment(Pos.CENTER);
		currentColor.getChildren().addAll(new Label("Current"), currentColorDisplay, new Label("Color"));

		colorContent.getChildren().addAll(currentColor, createColorGrid(colorSelectors));

		VBox content = new VBox();

		GridPane templateGrid = createBlanketGrid();

		content.getChildren().addAll(colorContent, templateGrid, buttons);
		content.setAlignment(Pos.CENTER);
		content.setSpacing(10);
		content.setPadding(new Insets(10, 10, 10, 10));

		Scene designerScene = new Scene(content);
		controller.useTitle("Blanket Designer - " + this.template.getName());

		templateGrid.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				designerScene.setCursor(Cursor.HAND);
			}
		});

		templateGrid.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				designerScene.setCursor(Cursor.DEFAULT);
			}
		});

		return designerScene;
	}

}
