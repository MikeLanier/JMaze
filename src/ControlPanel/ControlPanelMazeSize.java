package ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import MainFrm.MainFrm;
import Maze.MazeGlobal;

public class ControlPanelMazeSize extends HBox {
	public TextField tfMazeSizeX			= new TextField();
	public TextField	tfMazeSizeY			= new TextField();

	private MainFrm mainFrm = null;
	private ControlPanel controlPanel = null;

	public ControlPanelMazeSize(MainFrm _mainFrm, ControlPanel _controlPanel, int _sizeX, int _sizeY)
	{
		mainFrm = _mainFrm;
		controlPanel = _controlPanel;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Size", 60, false));
		getChildren().add(ControlPanel.Spacer());

		// number of cells in x
		Integer x = _sizeX;
		tfMazeSizeX.setText(x.toString());
		tfMazeSizeX.setMinWidth(70);
		tfMazeSizeX.setMaxWidth(70);
		tfMazeSizeX.setTooltip(new ControlPanelTooltip("Number of cells in a row of the maze"));
		getChildren().add(tfMazeSizeX);

		tfMazeSizeX.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("OnKeyReleased: tfMazeSizeX");
//				mainFrm.OnKeyPressed(event);
				MazeGlobal.sizeX = MazeGlobal.parseTextField(tfMazeSizeX, MazeGlobal.sizeX);
				controlPanel.entranceExitControl.adjustEntranceExitParameters();
			}
		});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 10, false));
		getChildren().add(ControlPanel.Spacer());

		// number of cells in y
		Integer y = _sizeY;
		tfMazeSizeY.setText(y.toString());
		tfMazeSizeY.setMinWidth(75);
		tfMazeSizeY.setMaxWidth(75);
		tfMazeSizeY.setTooltip(new ControlPanelTooltip("Number of cells in a column of the maze"));
		getChildren().add(tfMazeSizeY);

		tfMazeSizeY.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("OnKeyReleased: tfMazeSizeY");
//				mainFrm.OnKeyPressed(event);
				MazeGlobal.sizeY = MazeGlobal.parseTextField(tfMazeSizeY, MazeGlobal.sizeY);
				controlPanel.entranceExitControl.adjustEntranceExitParameters();
			}
		});
	}
}
