package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;

import java.util.*;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	public Maze2DPanel	maze2DPanel = null;
	public Maze3D2DPanel	maze3D2DPanel = null;

	public MainFrm() {
//		System.out.println("MainFrm");

		// define the grid layout as two columns and one row.
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPrefWidth(300);
		col2.setPercentWidth(70);
		getColumnConstraints().addAll(col1, col2);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		getRowConstraints().addAll(row1);

		setGridLinesVisible(true);

		add(controlPanel=new ControlPanel(this), 0, 0);
		add(maze2DPanel=new Maze2DPanel(controlPanel), 1, 0);
		add(maze3D2DPanel=new Maze3D2DPanel(), 1, 0);
		maze3D2DPanel.ShowMazeGrid(true);
		maze2DPanel.setVisible(controlPanel.cbMaze2D.isSelected());
		maze3D2DPanel.setVisible(controlPanel.cbMaze3D2D.isSelected());

		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
		maze2DPanel.currentMazeCell = maze2DPanel.cells.get(maze2DPanel.ID(startCellX, startCellY, false));
		if(maze2DPanel.currentMazeCell != null)
		{
			maze2DPanel.currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
		}

		maze2DPanel.drawMaze();
	}

	public void SwitchTo2DMaze()
	{
		maze2DPanel.setVisible(true);
		maze3D2DPanel.setVisible(false);
	}

	public void SwitchTo3D2DMaze()
	{
		maze2DPanel.setVisible(false);
		maze3D2DPanel.setVisible(true);
	}
}
