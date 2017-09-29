package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainfrm.MainFrm;
import mainfrm.MazeGlobal;

import java.util.ArrayList;

public class ControlPanelEntranceExit extends VBox {
	private ComboBox<String> tfEntranceX	= new ComboBox<>();
	private ComboBox<String> tfEntranceY	= new ComboBox<>();
	private Button btnEntranceSet			= new Button();

	public ComboBox<String> tfExitX			= new ComboBox<>();
	public ComboBox<String>	tfExitY			= new ComboBox<>();
	public Button btnExitSet				= new Button();

	private MainFrm mainFrm = null;
	public ControlPanelEntranceExit(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		HBox hboxEntrance = new HBox();
		getChildren().add(hboxEntrance);
		hboxEntrance.paddingProperty().setValue(margin);

		hboxEntrance.getChildren().add(ControlPanel.Marker("Entrance", 70, true));
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
		tfEntranceX.setTooltip(new Tooltip("The X position of the maze entrance can be\n" +
												"on the east or west side of the grid, or \n" +
												"the nth (with 1 begin the first) cell from \n" +
												"the left across the top or bottom of the grid"));

		hboxEntrance.getChildren().add(tfEntranceX);

//				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceX");
//					}
//				});

		hboxEntrance.getChildren().add(ControlPanel.Spacer());
		hboxEntrance.getChildren().add(ControlPanel.Marker("x", 0, true));
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
		tfEntranceY.setMinWidth(70);
		tfEntranceY.setMaxWidth(70);
//		tfEntranceY.setDisable(true);
		tfEntranceY.setTooltip(new Tooltip("The Y position of the maze entrance can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		hboxEntrance.getChildren().add(tfEntranceY);

//				tfEntranceY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceY");
//					}
//				});

		hboxEntrance.getChildren().add(ControlPanel.Spacer());

		Image imgEntrance = new Image(getClass().getResource("random.png").toString());
		ImageView ivEntrance = new ImageView(imgEntrance);

		btnEntranceSet.setGraphic(ivEntrance);
		btnEntranceSet.setMinWidth(25);
		btnEntranceSet.setMaxWidth(25);
//		btnEntranceSet.setDisable(true);
		btnEntranceSet.setTooltip(new Tooltip("randomly select the maze entrance"));
		hboxEntrance.getChildren().add(btnEntranceSet);

		btnEntranceSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnEntranceSet");
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		HBox hboxExit = new HBox();
		getChildren().add(hboxExit);
		hboxExit.paddingProperty().setValue(margin);

		hboxExit.getChildren().add(ControlPanel.Marker("Exit", 70, false));
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
		tfExitX.setTooltip(new Tooltip("The X position of the maze exit can be\n" +
				"on the east or west side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the left across the top or bottom of the grid"));

		hboxExit.getChildren().add(tfExitX);

//				tfExitX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitX");
//					}
//				});

		hboxExit.getChildren().add(ControlPanel.Spacer());
		hboxExit.getChildren().add(ControlPanel.Marker("x", 0, false));
		hboxExit.getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsExitY = new ArrayList<>();
		itemsExitY.add("north");
		itemsExitY.add("south");
		for (Integer i = 0; i < MazeGlobal.sizeY; i++) {
			itemsExitY.add(i.toString());
		}

		tfExitY.getItems().setAll(itemsExitY);
		tfExitY.setValue(itemsExitY.get(MazeGlobal.sizeY * 2 / 3 + 2));
		tfExitY.setMinWidth(70);
		tfExitY.setMaxWidth(70);
//		tfExitY.setDisable(true);
		tfExitY.setTooltip(new Tooltip("The Y position of the maze exit can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		hboxExit.getChildren().add(tfExitY);

//				tfExitY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitY");
//					}
//				});

		hboxExit.getChildren().add(ControlPanel.Spacer());

		Image imgExit = new Image(getClass().getResource("random.png").toString());
		ImageView ivExit = new ImageView(imgExit);

		btnExitSet.setGraphic(ivExit);
		btnExitSet.setMinWidth(25);
		btnExitSet.setMaxWidth(25);
//		btnExitSet.setDisable(true);
		btnExitSet.setTooltip(new Tooltip("randomly select the maze exit"));
		hboxExit.getChildren().add(btnExitSet);

		btnExitSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnExitSet");
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////

	}
}