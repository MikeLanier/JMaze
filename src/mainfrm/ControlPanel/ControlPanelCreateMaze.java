package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;

public class ControlPanelCreateMaze extends HBox {
	public Button btnMazeCreate		= new Button();
	public Button btnMazeStep		= new Button();
	private MainFrm mainFrm = null;
	public ControlPanelCreateMaze(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnMazeCreate.setText("Create");
		btnMazeCreate.setMinWidth(70);
		btnMazeCreate.setMaxWidth(70);
		btnMazeCreate.setTooltip(new Tooltip("Create a maze"));
		getChildren().add(btnMazeCreate);

		btnMazeCreate.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeCreate");
				mainFrm.mazePanel.createMaze(false);
			}
		});

		getChildren().add(ControlPanel.Spacer());

		btnMazeStep.setText("Animate Create");
		btnMazeStep.setMinWidth(100);
		btnMazeStep.setMaxWidth(100);
		btnMazeStep.setTooltip(new Tooltip("Create a maze, animating the process"));
		getChildren().add(btnMazeStep);

		btnMazeStep.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnMazeStep");
				mainFrm.mazePanel.createMaze(true);
			}
		});
	}
}
