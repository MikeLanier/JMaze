package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ControlPanelEntrance extends HBox {
	public ComboBox<String> tfEntranceX		= new ComboBox<>();
	public ComboBox<String>	tfEntranceY		= new ComboBox<>();
	public Button btnEntranceSet		= new Button();
	public ControlPanelEntrance(int _sizeX, int _sizeY)
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Entrance", 70, false));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsX = new ArrayList<>();
		itemsX.add("west");
		itemsX.add("east");
		for (Integer i = 0; i < _sizeX; i++) {
			itemsX.add(i.toString());
		}

		tfEntranceX.getItems().setAll(itemsX);
		tfEntranceX.setValue(itemsX.get(0));
		tfEntranceX.setMinWidth(70);
		tfEntranceX.setMaxWidth(70);

		getChildren().add(tfEntranceX);

//				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceX");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 0, false));
		getChildren().add(ControlPanel.Spacer());

		ArrayList<String> itemsY = new ArrayList<>();
		itemsY.add("north");
		itemsY.add("south");
		for (Integer i = 0; i < _sizeY; i++) {
			itemsY.add(i.toString());
		}

		tfEntranceY.getItems().setAll(itemsY);
		tfEntranceY.setValue(itemsY.get(_sizeY / 3 + 2));
		tfEntranceY.setMinWidth(70);
		tfEntranceY.setMaxWidth(70);

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
		btnEntranceSet.setDisable(true);
		getChildren().add(btnEntranceSet);

		btnEntranceSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnEntranceSet");
			}
		});
		
	}
}
