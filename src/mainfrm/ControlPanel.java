package mainfrm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class ControlPanel extends VBox {

	public int			_sizeX = 10;
	public int			_sizeY = 10;
	public int			_sizeCell = 50;
	public int			_xOffset = 10 + _sizeCell;
	public int			_yOffset = 10 + _sizeCell;

	public Random rand = new Random(System.currentTimeMillis());

	public static Label Marker(String title, int size, boolean disabled)
	{
		Label lbl = new Label();
		lbl.setText(title);
		if(size > 0) {
			lbl.setMinWidth(size);
			lbl.setMaxWidth(size);
		}
		lbl.setDisable(disabled);
		return lbl;
	}

	public static Label Spacer()
	{
		return Marker("", 5, false);
	}

	private MainFrm mainFrm;

	public ControlPanelMazeSize mazeSizeControl = null;
	public ControlPanelCellSize cellSizeControl = null;
	public ControlPanelAlgorithm algorithmControl = null;
	public ControlPanelMaze2D3D maze2D3DControl = null;
	public ControlPanelStartCell startCellControl = null;
	public ControlPanelEntrance entranceControl = null;

	public ControlPanel(MainFrm _mainFrm) {
		System.out.println("buildControls");

		mainFrm = _mainFrm;
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		setMargin(this, margin);

		getChildren().add(new ControlPanelLoadMazes());
		getChildren().add(new ControlPanelSaveMazes());
		getChildren().add(mazeSizeControl = new ControlPanelMazeSize(_sizeX, _sizeY));
		getChildren().add(cellSizeControl = new ControlPanelCellSize(_sizeCell));
		getChildren().add(algorithmControl = new ControlPanelAlgorithm());
		getChildren().add(new ControlPanelMazeName());
		getChildren().add(maze2D3DControl = new ControlPanelMaze2D3D(mainFrm));
		getChildren().add(startCellControl = new ControlPanelStartCell(_sizeX, _sizeY));
		getChildren().add(entranceControl = new ControlPanelEntrance(_sizeX, _sizeY));
		getChildren().add(new ControlPanelCreateMaze(mainFrm));
		getChildren().add(new ControlPanelSolveMaze());
		getChildren().add(new ControlPanelPrintMaze());
		getChildren().add(new ControlPanelStartWalkthrough());
	}
}
