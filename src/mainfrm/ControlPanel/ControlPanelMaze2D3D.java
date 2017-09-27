package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;
import mainfrm.MazeGlobal;

public class ControlPanelMaze2D3D extends HBox {
	public CheckBox cbMaze2D			= new CheckBox();
	public CheckBox		cbMaze3D2D			= new CheckBox();
	public CheckBox		cbMaze3D			= new CheckBox();
	private MainFrm mainFrm = null;
	public ControlPanelMaze2D3D(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("", 70, false));
		getChildren().add(ControlPanel.Spacer());

		cbMaze2D.setText("2D");
		cbMaze2D.setMinWidth(40);
		cbMaze2D.setMaxWidth(40);
		cbMaze2D.setIndeterminate(false);
		cbMaze2D.setSelected(MazeGlobal.maze2Ddisplay);
//		cbMaze2D.setDisable(true);
		getChildren().add(cbMaze2D);

		cbMaze2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMouseClicked: cbMaze2D: " + cbMaze2D.isSelected());
				MazeGlobal.maze2Ddisplay = cbMaze2D.isSelected();
			}
		});

		getChildren().add(ControlPanel.Spacer());

		cbMaze3D2D.setText("3D/2D");
		cbMaze3D2D.setMinWidth(60);
		cbMaze3D2D.setMaxWidth(60);
		cbMaze3D2D.setSelected(MazeGlobal.maze3Ddisplay);
//		cbMaze3D2D.setDisable(true);
		getChildren().add(cbMaze3D2D);

		cbMaze3D2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			}
		});

		getChildren().add(ControlPanel.Spacer());

		cbMaze3D.setText("3D");
		cbMaze3D.setMinWidth(40);
		cbMaze3D.setMaxWidth(40);
//		cbMaze3D.setDisable(true);
		getChildren().add(cbMaze3D);

		cbMaze3D.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
//				System.out.println("OnMouseClicked: cbMaze3D");
				MazeGlobal.maze3Ddisplay = cbMaze3D.isSelected();
			}
		});
	}
}
