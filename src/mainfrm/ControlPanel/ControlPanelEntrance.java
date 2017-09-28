package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;
import mainfrm.MazeGlobal;

import java.util.ArrayList;

public class ControlPanelEntrance extends HBox {
	private ComboBox<String> tfEntranceX	= new ComboBox<>();
	private ComboBox<String> tfEntranceY	= new ComboBox<>();
	private Button btnEntranceSet			= new Button();

	private MainFrm mainFrm = null;
	public ControlPanelEntrance(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Entrance", 70, true));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsX = new ArrayList<>();
		itemsX.add("west");
		itemsX.add("east");
		for (Integer i = 1; i <= MazeGlobal.sizeX; i++) {
			itemsX.add(i.toString());
		}

		int index = 0;
		if(MazeGlobal.entranceCellX == 0)	index = 0;
		else if(MazeGlobal.entranceCellX == (MazeGlobal.sizeX+1))	index = MazeGlobal.sizeX+1;
		else index = MazeGlobal.entranceCellX + 1;

		tfEntranceX.getItems().setAll(itemsX);
		tfEntranceX.setValue(itemsX.get(index));
		tfEntranceX.setMinWidth(70);
		tfEntranceX.setMaxWidth(70);
//		tfEntranceX.setDisable(true);
		tfEntranceX.setTooltip(new Tooltip("The X position of the maze entrance can be\n" +
												"on the east or west side of the grid, or \n" +
												"the nth (with 1 begin the first) cell from \n" +
												"the left across the top or bottom of the grid"));

		getChildren().add(tfEntranceX);

//				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceX");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 0, true));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsY = new ArrayList<>();
		itemsY.add("north");
		itemsY.add("south");
		for (Integer i = 1; i <= MazeGlobal.sizeY; i++) {
			itemsY.add(i.toString());
		}

		index = 0;
		if(MazeGlobal.entranceCellY == 0)	index = 0;
		else if(MazeGlobal.entranceCellY == (MazeGlobal.sizeY+1))	index = MazeGlobal.sizeY+1;
		else index = MazeGlobal.entranceCellY + 1;

		tfEntranceY.getItems().setAll(itemsY);
		tfEntranceY.setValue(itemsY.get(index));
		tfEntranceY.setMinWidth(70);
		tfEntranceY.setMaxWidth(70);
//		tfEntranceY.setDisable(true);
		tfEntranceY.setTooltip(new Tooltip("The Y position of the maze entrance can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		getChildren().add(tfEntranceY);

//				tfEntranceY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceY");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());

		Image img = new Image(getClass().getResource("random.png").toString());
		ImageView iv = new ImageView(img);

		btnEntranceSet.setGraphic(iv);
		btnEntranceSet.setMinWidth(25);
		btnEntranceSet.setMaxWidth(25);
//		btnEntranceSet.setDisable(true);
		btnEntranceSet.setTooltip(new Tooltip("randomly select the maze entrance"));
		getChildren().add(btnEntranceSet);

		btnEntranceSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnEntranceSet");
			}
		});
		
	}
}
