package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;

import java.util.*;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	public Maze2DPanel	maze2DPanel = null;

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

		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
		maze2DPanel.currentMazeCell = maze2DPanel.cells.get(maze2DPanel.ID(startCellX, startCellY, false));
		if(maze2DPanel.currentMazeCell != null)
		{
			maze2DPanel.currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
		}

		maze2DPanel.drawMaze();
	}
}
