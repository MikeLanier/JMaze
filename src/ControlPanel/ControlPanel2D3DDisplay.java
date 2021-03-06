package ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import MainFrm.MainFrm;
import Maze.MazeGlobal;

public class ControlPanel2D3DDisplay extends HBox {
	public CheckBox cb2DView			= new CheckBox();
	public CheckBox		cb3DView			= new CheckBox();
	private MainFrm mainFrm = null;
	public ControlPanel2D3DDisplay(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("", 70, true));
		getChildren().add(ControlPanel.Spacer());

		cb2DView.setText("2D view");
		cb2DView.setMinWidth(90);
		cb2DView.setMaxWidth(90);
		cb2DView.setIndeterminate(false);
		cb2DView.setSelected(true);
		cb2DView.setTooltip(new ControlPanelTooltip("Show a 2D top view of the maze"));
//		cb2DView.setDisable(true);
		getChildren().add(cb2DView);

		cb2DView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				MazeGlobal.maze2Ddisplay = cb2DView.isSelected();
				mainFrm.mazePanel.drawMaze();
			}
		});

		getChildren().add(ControlPanel.Spacer());

		cb3DView.setText("3D view");
		cb3DView.setMinWidth(90);
		cb3DView.setMaxWidth(90);
		cb3DView.setSelected(true);
		cb3DView.setTooltip(new ControlPanelTooltip("Show the maze in 3D from inside the maze"));
//		cb3DView.setDisable(true);
		getChildren().add(cb3DView);

		cb3DView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				MazeGlobal.maze3Ddisplay = cb3DView.isSelected();
				mainFrm.mazePanel.drawMaze();
			}
		});
	}
}
