package mainfrm.ControlPanel;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import mainfrm.MainFrm;
import mainfrm.MazeGlobal;


public class ControlPanel extends VBox {


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
	public ControlPanelEntranceExit entranceExitControl = null;
	public ControlPanelCreateRoom createRoomControl = null;

	public ControlPanel(MainFrm _mainFrm)
	{
//		System.out.println("buildControls");

		mainFrm = _mainFrm;
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		setMargin(this, margin);

		getChildren().add(new ControlPanelLoadMazes());
		getChildren().add(new ControlPanelSaveMazes());
		getChildren().add(new ControlPanelMazeName());
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(mazeSizeControl = new ControlPanelMazeSize(mainFrm, this, MazeGlobal.sizeX, MazeGlobal.sizeY));
		getChildren().add(cellSizeControl = new ControlPanelCellSize(this, mainFrm, MazeGlobal.sizeCell));
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(algorithmControl = new ControlPanelAlgorithm());
//		getChildren().add(maze2D3DControl = new ControlPanelMaze2D3D(mainFrm));
		getChildren().add(startCellControl = new ControlPanelStartCell(mainFrm, MazeGlobal.sizeX, MazeGlobal.sizeY));
		getChildren().add(entranceExitControl = new ControlPanelEntranceExit(this, mainFrm));
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(new ControlPanelCreateMaze(this, mainFrm));
		getChildren().add(new ControlPanelSolveMaze());
		getChildren().add(new ControlPanelPrintMaze());
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(new ControlPanel2D3DDisplay(mainFrm));
		getChildren().add(new ControlPanelSeparator());
		getChildren().add(createRoomControl = new ControlPanelCreateRoom(mainFrm));

		createRoomControl.setDisable(true);
	}
}
