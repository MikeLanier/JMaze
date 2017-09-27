package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ControlPanelExit extends HBox {
	public ComboBox<String> tfExitX			= new ComboBox<>();
	public ComboBox<String>	tfExitY			= new ComboBox<>();
	public Button btnExitSet			= new Button();
	public ControlPanelExit(int _sizeX, int _sizeY)
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Exit", 70, true));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsX = new ArrayList<>();
		itemsX.add("west");
		itemsX.add("east");
		for (Integer i = 0; i < _sizeX; i++) {
			itemsX.add(i.toString());
		}

		tfExitX.getItems().setAll(itemsX);
		tfExitX.setValue(itemsX.get(1));
		tfExitX.setMinWidth(70);
		tfExitX.setMaxWidth(70);
		tfExitX.setDisable(true);
		tfExitX.setTooltip(new Tooltip("The X position of the maze exit can be\n" +
				"on the east or west side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the left across the top or bottom of the grid"));

		getChildren().add(tfExitX);

//				tfExitX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitX");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 0, true));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsY = new ArrayList<>();
		itemsY.add("north");
		itemsY.add("south");
		for (Integer i = 0; i < _sizeY; i++) {
			itemsY.add(i.toString());
		}

		tfExitY.getItems().setAll(itemsY);
		tfExitY.setValue(itemsY.get(_sizeY * 2 / 3 + 2));
		tfExitY.setMinWidth(70);
		tfExitY.setMaxWidth(70);
		tfExitY.setDisable(true);
		tfExitY.setTooltip(new Tooltip("The Y position of the maze exit can be\n" +
				"on the north or south side of the grid, or \n" +
				"the nth (with 1 begin the first) cell from \n" +
				"the topmost across the left or right of the grid"));

		getChildren().add(tfExitY);

//				tfExitY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitY");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());

		Image img = new Image(getClass().getResource("random.png").toString());
		ImageView iv = new ImageView(img);

		btnExitSet.setGraphic(iv);
		btnExitSet.setMinWidth(25);
		btnExitSet.setMaxWidth(25);
		btnExitSet.setDisable(true);
		btnExitSet.setTooltip(new Tooltip("randomly select the maze exit"));
		getChildren().add(btnExitSet);

		btnExitSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnExitSet");
			}
		});
	}
}
