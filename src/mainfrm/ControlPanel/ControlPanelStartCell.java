package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelStartCell extends HBox {
	public TextField tfStartCellX		= new TextField();
	public TextField	tfStartCellY		= new TextField();
	public Button btnStartCellSet		= new Button();
	public ControlPanelStartCell(int _sizeX, int _sizeY)
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Start Maze2DCell", 70, false));
		getChildren().add(ControlPanel.Spacer());

		Integer x = _sizeX / 2;
		Integer y = _sizeY / 2;

		tfStartCellX.setText(x.toString());
		tfStartCellX.setMinWidth(40);
		tfStartCellX.setMaxWidth(40);
//				tfStartCellX.setDisable(true);
		getChildren().add(tfStartCellX);

//				tfStartCellX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfStartCellX");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 0, false));
		getChildren().add(ControlPanel.Spacer());

		tfStartCellY.setText(y.toString());
		tfStartCellY.setMinWidth(40);
		tfStartCellY.setMaxWidth(40);
//				tfStartCellY.setDisable(true);
		getChildren().add(tfStartCellY);

//				tfStartCellY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfStartCellY");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());

		Image img = new Image(getClass().getResource("random.png").toString());
		ImageView iv = new ImageView(img);

		btnStartCellSet.setGraphic(iv);
		btnStartCellSet.setMinWidth(25);
		btnStartCellSet.setMaxWidth(25);
//				btnStartCellSet.setDisable(true);
		getChildren().add(btnStartCellSet);

		btnStartCellSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnStartCellSet");
			}
		});
		
	}
}
