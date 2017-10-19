package ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import MainFrm.MainFrm;

public class ControlPanelCreateMaze extends HBox {
	public Button btnMazeCreate		= new Button();
	public Button btnMazeStep		= new Button();
	private MainFrm mainFrm = null;
	private ControlPanel controlPanel = null;
	public ControlPanelCreateMaze(ControlPanel _controlPanel, MainFrm _mainFrm)
	{
		controlPanel = _controlPanel;
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnMazeCreate.setText("Create");
		btnMazeCreate.setMinWidth(60);
		btnMazeCreate.setMaxWidth(60);
		btnMazeCreate.setTooltip(new ControlPanelTooltip("Create a maze"));
		getChildren().add(btnMazeCreate);

		btnMazeCreate.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(controlPanel.createRoomControl != null) controlPanel.createRoomControl.setDisable(false);
				mainFrm.mazePanel.createMaze(false);
			}
		});

		getChildren().add(ControlPanel.Spacer());

		btnMazeStep.setText("Animate Create");
		btnMazeStep.setMinWidth(100);
		btnMazeStep.setMaxWidth(100);
		btnMazeStep.setTooltip(new ControlPanelTooltip("Create a maze, animating the process"));
		getChildren().add(btnMazeStep);

		btnMazeStep.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnMazeStep");
				if(controlPanel.createRoomControl != null) controlPanel.createRoomControl.setDisable(false);
				mainFrm.mazePanel.createMaze(true);
			}
		});
	}

	public void setDisableCreate(boolean disable)
	{
		if(btnMazeCreate != null)	btnMazeCreate.setDisable(disable);
		if(btnMazeStep != null)		btnMazeStep.setDisable(disable);
	}
}
