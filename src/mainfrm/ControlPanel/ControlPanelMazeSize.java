package mainfrm.ControlPanel;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControlPanelMazeSize extends HBox {
	public TextField tfMazeSizeX			= new TextField();
	public TextField	tfMazeSizeY			= new TextField();
	public ControlPanelMazeSize(int _sizeX, int _sizeY)
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Size", 70, false));
		getChildren().add(ControlPanel.Spacer());

		// number of cells in x
		Integer x = _sizeX;
		tfMazeSizeX.setText(x.toString());
		tfMazeSizeX.setMinWidth(40);
		tfMazeSizeX.setMaxWidth(40);
		getChildren().add(tfMazeSizeX);

//				tfMazeSizeX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeSizeX");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 0, false));
		getChildren().add(ControlPanel.Spacer());

		// number of cells in y
		Integer y = _sizeY;
		tfMazeSizeY.setText(y.toString());
		tfMazeSizeY.setMinWidth(40);
		tfMazeSizeY.setMaxWidth(40);
		getChildren().add(tfMazeSizeY);

//				tfMazeSizeY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeSizeY");
//					}
//				});
	}
}
