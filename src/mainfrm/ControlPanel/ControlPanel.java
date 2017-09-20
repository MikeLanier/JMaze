package mainfrm.ControlPanel;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import mainfrm.MainFrm;

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
	public ControlPanelExit exitControl = null;

	public ControlPanel(MainFrm _mainFrm) {
		System.out.println("buildControls");

		mainFrm = _mainFrm;
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		setMargin(this, margin);

		getChildren().add(new ControlPanelLoadMazes());
		getChildren().add(new ControlPanelSaveMazes());
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(mazeSizeControl = new ControlPanelMazeSize(_sizeX, _sizeY));
		getChildren().add(cellSizeControl = new ControlPanelCellSize(this, mainFrm, _sizeCell));
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(algorithmControl = new ControlPanelAlgorithm());
		getChildren().add(new ControlPanelMazeName());
//		getChildren().add(maze2D3DControl = new ControlPanelMaze2D3D(mainFrm));
		getChildren().add(startCellControl = new ControlPanelStartCell(_sizeX, _sizeY));
		getChildren().add(entranceControl = new ControlPanelEntrance(_sizeX, _sizeY));
		getChildren().add(exitControl = new ControlPanelExit(_sizeX, _sizeY));
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(new ControlPanelCreateMaze(mainFrm));
		getChildren().add(new ControlPanelSolveMaze());
		getChildren().add(new ControlPanelPrintMaze());
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(new ControlPanelStartWalkthrough());
	}
}
