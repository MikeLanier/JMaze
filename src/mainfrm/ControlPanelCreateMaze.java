package mainfrm;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelCreateMaze extends HBox {
	public Button btnMazeCreate		= new Button();
	private MainFrm mainFrm = null;
	public ControlPanelCreateMaze(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnMazeCreate.setText("Create");
		btnMazeCreate.setMinWidth(70);
		btnMazeCreate.setMaxWidth(70);
		getChildren().add(btnMazeCreate);

		btnMazeCreate.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeCreate");
				mainFrm.maze2DPanel.createMaze();
			}
		});
	}
}
