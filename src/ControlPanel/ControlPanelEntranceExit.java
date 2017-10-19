package ControlPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import MainFrm.MainFrm;
import Maze.MazeGlobal;

import java.util.ArrayList;

public class ControlPanelEntranceExit extends VBox {
	private ComboBox<String> tfEntranceX	= new ComboBox<>();
	private ComboBox<String> tfEntranceY	= new ComboBox<>();
	private Button btnEntranceSet			= new Button();
	private Button btnEntranceCreate		= new Button();

	public ComboBox<String> tfExitX			= new ComboBox<>();
	public ComboBox<String>	tfExitY			= new ComboBox<>();
	public Button btnExitSet				= new Button();
	public Button btnExitCreate				= new Button();

	private MainFrm mainFrm = null;
	private ControlPanel controlPanel = null;

	public ControlPanelEntranceExit(ControlPanel _controlPanel, MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;
		controlPanel = _controlPanel;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);

		Image imgRandomEntrance = new Image(getClass().getResource("random.png").toString());
		ImageView ivRandomEntrance = new ImageView(imgRandomEntrance);

		Image imgRandomExit = new Image(getClass().getResource("random.png").toString());
		ImageView ivRandomExit = new ImageView(imgRandomExit);

		Image imgCreateEntrance = new Image(getClass().getResource("create16.png").toString());
		ImageView ivCreateEntrance = new ImageView(imgCreateEntrance);

		Image imgCreateExit = new Image(getClass().getResource("create16.png").toString());
		ImageView ivCreateExit = new ImageView(imgCreateExit);

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		HBox hboxEntrance = new HBox();
		getChildren().add(hboxEntrance);
		hboxEntrance.paddingProperty().setValue(margin);

		hboxEntrance.getChildren().add(ControlPanel.Marker("Entrance", 60, false));
		hboxEntrance.getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsEntranceX = new ArrayList<>();
		itemsEntranceX.add("west");
		itemsEntranceX.add("east");
		for (Integer i = 1; i <= MazeGlobal.sizeX; i++) {
			itemsEntranceX.add(i.toString());
		}

		int index = 0;
		if(MazeGlobal.entranceCellX == 0)	index = 0;
		else if(MazeGlobal.entranceCellX == (MazeGlobal.sizeX+1))	index = MazeGlobal.sizeX+1;
		else index = MazeGlobal.entranceCellX + 1;

		tfEntranceX.getItems().setAll(itemsEntranceX);
		tfEntranceX.setValue(itemsEntranceX.get(index));
		tfEntranceX.setMinWidth(70);
		tfEntranceX.setMaxWidth(70);
//		tfEntranceX.setDisable(true);
		tfEntranceX.setTooltip(new ControlPanelTooltip("The X position of the maze entrance can be\n" +
												"on the east or west side of the grid, or \n" +
												"the nth (with 1 begin the first) cell from \n" +
												"the left across the top or bottom of the grid"));

		hboxEntrance.getChildren().add(tfEntranceX);

		tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				System.out.println("OnAction: tfEntranceX");
				updateEntranceCellParameters();
			}
		});

		hboxEntrance.getChildren().add(ControlPanel.Spacer());
		hboxEntrance.getChildren().add(ControlPanel.Marker("x", 10, false));
		hboxEntrance.getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsEntranceY = new ArrayList<>();
		itemsEntranceY.add("north");
		itemsEntranceY.add("south");
		for (Integer i = 1; i <= MazeGlobal.sizeY; i++) {
			itemsEntranceY.add(i.toString());
		}

		index = 0;
		if(MazeGlobal.entranceCellY == 0)	index = 0;
		else if(MazeGlobal.entranceCellY == (MazeGlobal.sizeY+1))	index = MazeGlobal.sizeY+1;
		else index = MazeGlobal.entranceCellY + 1;

		tfEntranceY.getItems().setAll(itemsEntranceY);
		tfEntranceY.setValue(itemsEntranceY.get(index));
		tfEntranceY.setMinWidth(75);
		tfEntranceY.setMaxWidth(75);
//		tfEntranceY.setDisable(true);
		tfEntranceY.setTooltip(new ControlPanelTooltip("The Y position of the maze entrance can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		hboxEntrance.getChildren().add(tfEntranceY);

		tfEntranceY.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				System.out.println("OnAction: tfEntranceY");
				updateEntranceCellParameters();
			}
		});

		hboxEntrance.getChildren().add(ControlPanel.Spacer());

		btnEntranceSet.setGraphic(ivRandomEntrance);
		btnEntranceSet.setMinWidth(25);
		btnEntranceSet.setMaxWidth(25);
//		btnEntranceSet.setDisable(true);
		btnEntranceSet.setTooltip(new ControlPanelTooltip("randomly select the maze entrance"));
		hboxEntrance.getChildren().add(btnEntranceSet);

		btnEntranceSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnEntranceSet");
				int side = MazeGlobal.rand.nextInt(4);
				int index = 0;
				if(side < 2) {
					index = MazeGlobal.rand.nextInt(MazeGlobal.sizeX);
					tfEntranceX.setValue(itemsEntranceX.get(side));
					tfEntranceY.setValue(itemsEntranceY.get(index+2));
				}
				else {
					index = MazeGlobal.rand.nextInt(MazeGlobal.sizeY);
					tfEntranceY.setValue(itemsEntranceY.get(side-2));
					tfEntranceX.setValue(itemsEntranceX.get(index+2));
				}

				parametersValid();
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		HBox hboxExit = new HBox();
		getChildren().add(hboxExit);
		hboxExit.paddingProperty().setValue(margin);

		hboxExit.getChildren().add(ControlPanel.Marker("Exit", 60, false));
		hboxExit.getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsExitX = new ArrayList<>();
		itemsExitX.add("west");
		itemsExitX.add("east");
		for (Integer i = 0; i < MazeGlobal.sizeX; i++) {
			itemsExitX.add(i.toString());
		}

		tfExitX.getItems().setAll(itemsExitX);
		tfExitX.setValue(itemsExitX.get(1));
		tfExitX.setMinWidth(70);
		tfExitX.setMaxWidth(70);
//		tfExitX.setDisable(true);
		tfExitX.setTooltip(new ControlPanelTooltip("The X position of the maze exit can be\n" +
				"on the east or west side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the left across the top or bottom of the grid"));

		hboxExit.getChildren().add(tfExitX);

		tfExitX.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				System.out.println("OnAction: tfExitX");
				updateExitCellParameters();
			}
		});

		hboxExit.getChildren().add(ControlPanel.Spacer());
		hboxExit.getChildren().add(ControlPanel.Marker("x", 10, false));
		hboxExit.getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsExitY = new ArrayList<>();
		itemsExitY.add("north");
		itemsExitY.add("south");
		for (Integer i = 0; i < MazeGlobal.sizeY; i++) {
			itemsExitY.add(i.toString());
		}

		tfExitY.getItems().setAll(itemsExitY);
		tfExitY.setValue(itemsExitY.get(MazeGlobal.sizeY * 2 / 3 + 2));
		tfExitY.setMinWidth(75);
		tfExitY.setMaxWidth(75);
//		tfExitY.setDisable(true);
		tfExitY.setTooltip(new ControlPanelTooltip("The Y position of the maze exit can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		hboxExit.getChildren().add(tfExitY);

		tfExitY.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				System.out.println("OnAction: tfExitY");
				updateExitCellParameters();
			}
		});

		hboxExit.getChildren().add(ControlPanel.Spacer());

		btnExitSet.setGraphic(ivRandomExit);
		btnExitSet.setMinWidth(25);
		btnExitSet.setMaxWidth(25);
//		btnExitSet.setDisable(true);
		btnExitSet.setTooltip(new ControlPanelTooltip("randomly select the maze exit"));
		hboxExit.getChildren().add(btnExitSet);

		btnExitSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnExitSet");
				int side = MazeGlobal.rand.nextInt(4);
				int index = 0;
				if(side < 2) {
					index = MazeGlobal.rand.nextInt(MazeGlobal.sizeX);
					tfExitX.setValue(itemsExitX.get(side));
					tfExitY.setValue(itemsExitY.get(index+2));
				}
				else {
					index = MazeGlobal.rand.nextInt(MazeGlobal.sizeY);
					tfExitY.setValue(itemsExitY.get(side-2));
					tfExitX.setValue(itemsExitX.get(index+2));
				}

				parametersValid();
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	public void adjustEntranceExitParameters()
	{
		System.out.println("ControlPanelEntranceExit: Validate");
		String strX = tfEntranceX.getValue();
		String strY = tfEntranceY.getValue();
		Integer x = 0;
		Integer y = 0;
		System.out.println("x, y: " + MazeGlobal.entranceCellX + ", " + MazeGlobal.entranceCellY);
		System.out.println("x, y: " + strX + ", " + strY);

		if(strX == "west")	x = 0;
		else if(strX == "east")	x = MazeGlobal.sizeX + 1;
		else x = MazeGlobal.parseTextField(strX, -1);

		if(strY == "north")	y = 0;
		else if(strY == "south")	y = MazeGlobal.sizeY + 1;
		else y = MazeGlobal.parseTextField(strY, -1);

		MazeGlobal.entranceCellX = x;
		MazeGlobal.entranceCellY = y;
		System.out.println("x, y: " + MazeGlobal.entranceCellX + ", " + MazeGlobal.entranceCellY);

		strX = tfExitX.getValue();
		strY = tfExitY.getValue();
		x = 0;
		y = 0;
		System.out.println("x, y: " + MazeGlobal.exitCellX + ", " + MazeGlobal.exitCellY);
		System.out.println("x, y: " + strX + ", " + strY);

		if(strX == "west")	x = 0;
		else if(strX == "east")	x = MazeGlobal.sizeX + 1;
		else x = MazeGlobal.parseTextField(strX, -1);

		if(strY == "north")	y = 0;
		else if(strY == "south")	y = MazeGlobal.sizeY + 1;
		else y = MazeGlobal.parseTextField(strY, -1);

		MazeGlobal.exitCellX = x;
		MazeGlobal.exitCellY = y;
		System.out.println("x, y: " + MazeGlobal.exitCellX + ", " + MazeGlobal.exitCellY);
	}

	private boolean parametersValid() {

		Integer entranceX = parseComboValue(tfEntranceX, MazeGlobal.sizeX);
		Integer entranceY = parseComboValue(tfEntranceY, MazeGlobal.sizeY);
		Integer exitX = parseComboValue(tfExitX, MazeGlobal.sizeX);
		Integer exitY = parseComboValue(tfExitY, MazeGlobal.sizeY);

		Integer maxX = MazeGlobal.sizeX + 1;
		Integer maxY = MazeGlobal.sizeY + 1;
		Integer centerX = maxX/2;
		Integer centerY = maxY/2;

		System.out.println("entrance, exit: [" + entranceX + ", " + entranceY + "], [" + exitX + ", " + exitY + "]");

		// is the entrance/exit on the same side of the maze
		if(entranceX == exitX) { controlPanel.createMazeControl.setDisableCreate(true); return false; }
		if(entranceY == exitY) { controlPanel.createMazeControl.setDisableCreate(true); return false; }

		// has a side been selected for both X and Y?
		if(entranceX < 2 &&  entranceY < 2) { controlPanel.createMazeControl.setDisableCreate(true); return false; }
		if(exitX < 2 &&  exitY < 2)			{ controlPanel.createMazeControl.setDisableCreate(true); return false; }

		// has a side been selected for neither X and Y?
		if((entranceX > 1 && entranceX < maxX) &&  (entranceY > 1 && entranceY < maxY)) { controlPanel.createMazeControl.setDisableCreate(true); return false; }
		if((exitX > 1 && exitX < maxX) &&  (exitY > 1 && exitY < maxY)) { controlPanel.createMazeControl.setDisableCreate(true); return false; }

		if(entranceX == 0 && exitX < centerX) { controlPanel.createMazeControl.setDisableCreate(true); return false; }
		if(entranceX == maxX && exitX > centerX) { controlPanel.createMazeControl.setDisableCreate(true); return false; }

		if(entranceY == 0 && exitY < centerY) { controlPanel.createMazeControl.setDisableCreate(true); return false; }
		if(entranceY == maxY && exitY > centerY) { controlPanel.createMazeControl.setDisableCreate(true); return false; }

		controlPanel.createMazeControl.setDisableCreate(false);
		return true;
	}

	private Integer parseComboValue(ComboBox<String> cb, int max) {
		Integer value = 0;
		String str = cb.getValue();

		if (str == "west") value = 0;
		else if (str == "north") value = 0;
		else if (str == "east") value = max + 1;
		else if (str == "west") value = max + 1;
		else value = MazeGlobal.parseTextField(str, -1);

		return value;
	}
	private void updateEntranceCellParameters() {
		if(parametersValid()) {
			MazeGlobal.entranceCellX = parseComboValue(tfEntranceX, MazeGlobal.sizeX);
			MazeGlobal.entranceCellY = parseComboValue(tfEntranceY, MazeGlobal.sizeY);
		}
	}

	private void updateExitCellParameters() {
		if(parametersValid()) {
			MazeGlobal.exitCellX = parseComboValue(tfExitX, MazeGlobal.sizeX);
			MazeGlobal.exitCellY = parseComboValue(tfExitY, MazeGlobal.sizeY);
		}
	}
}
