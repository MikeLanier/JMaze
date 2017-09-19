package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelSolveMaze extends HBox {
	public Button btnMazeSolve		= new Button();
	public ControlPanelSolveMaze()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnMazeSolve.setText("Solve");
		btnMazeSolve.setMinWidth(70);
		btnMazeSolve.setMaxWidth(70);
		btnMazeSolve.setDisable(true);
		getChildren().add(btnMazeSolve);

		btnMazeSolve.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeSolve");
			}
		});
	}
}
