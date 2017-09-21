package mainfrm;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import mainfrm.ControlPanel.ControlPanel;

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

		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
		maze2DPanel.currentMaze2DCell = maze2DPanel.cells.get(MazeGlobal.ID(startCellX, startCellY, false));
		if(maze2DPanel.currentMaze2DCell != null)
		{
			maze2DPanel.currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
		}

		maze2DPanel.drawMaze();

		setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("GridPane: OnKeyPressed");
			}
		});
	}
}
