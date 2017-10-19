package ControlPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelLoadMazes extends HBox {
	public Button btnMazeOpen			= new Button();
	public TextField tfMazeFilename		= new TextField();
	public Button		btnMazeSelect		= new Button();
	public ControlPanelLoadMazes()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		// press this button to open the maze in the filename in the TextField
		btnMazeOpen.setText("Load");
		btnMazeOpen.setMinWidth(60);
		btnMazeOpen.setMaxWidth(60);
		btnMazeOpen.setTooltip(new ControlPanelTooltip("Load mazes from the named file"));
		getChildren().add(btnMazeOpen);

		btnMazeOpen.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnMazeOpen");
			}
		});

		getChildren().add(ControlPanel.Spacer());

		// contains the name of the filename containing the maze to load
		tfMazeFilename.setText("filename");
		tfMazeFilename.setMinWidth(165);
		tfMazeFilename.setTooltip(new ControlPanelTooltip("Name of file from which to load mazes"));
		getChildren().add(tfMazeFilename);

		tfMazeFilename.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				System.out.println("OnAction: tfMazeFilename");
			}
		});

		getChildren().add(ControlPanel.Spacer());

		// press this button to show the fileopen dialog.
		Image img = new Image(getClass().getResource("Load.png").toString());
		ImageView iv = new ImageView(img);

		btnMazeSelect.setGraphic(iv);
//		btnMazeSelect.setText("^");
		btnMazeSelect.setMinWidth(25);
		btnMazeSelect.setTooltip(new ControlPanelTooltip("Open FileOpenDialog to select file\nfrom which to load mazes"));
		getChildren().add(btnMazeSelect);

		btnMazeSelect.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnMazeSelect");
			}
		});
	}
}
