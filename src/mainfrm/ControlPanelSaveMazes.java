package mainfrm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelSaveMazes extends HBox {
	public Button btnMazeSave			= new Button();
	public TextField tfMazeSaveFilename	= new TextField();
	public Button		btnMazeSaveSelect	= new Button();
	
	public ControlPanelSaveMazes()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		// press this button to save the current maze to the filename in the TextField control
		btnMazeSave.setText("Save");
		btnMazeSave.setMinWidth(70);
		btnMazeSave.setMaxWidth(70);
		btnMazeSave.setDisable(true);
		getChildren().add(btnMazeSave);

		btnMazeSave.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeSave");
			}
		});

		getChildren().add(ControlPanel.Spacer());

		// contains the filename to which the current maze will be saved
		tfMazeSaveFilename.setText("filename");
		tfMazeSaveFilename.setMinWidth(150);
		tfMazeSaveFilename.setDisable(true);
		getChildren().add(tfMazeSaveFilename);

		tfMazeSaveFilename.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("OnAction: :tfMazeSaveFilename");
			}
		});

		getChildren().add(ControlPanel.Spacer());

		// press this button to show the filesave dialog
		btnMazeSaveSelect.setText(">");
		btnMazeSaveSelect.setMinWidth(25);
		btnMazeSaveSelect.setDisable(true);
		getChildren().add(btnMazeSaveSelect);

		btnMazeSaveSelect.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeSaveSelect");
			}
		});
	}
}
