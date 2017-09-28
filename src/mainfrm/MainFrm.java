package mainfrm;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import mainfrm.ControlPanel.ControlPanel;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	public MazePanel mazePanel = null;

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
		add(mazePanel =new MazePanel(controlPanel), 1, 0);

		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
		mazePanel.currentMazeCell = mazePanel.cells.get(MazeGlobal.ID(startCellX, startCellY, false));
		if(mazePanel.currentMazeCell != null)
		{
			mazePanel.currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
		}

		mazePanel.drawMaze();

		setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event)
			{
//				System.out.println("GridPane: OnKeyPressed: [" + event.getCode() + "]");
				if(event.getCode() == KeyCode.NUMPAD4) mazePanel.turnLeft();
				if(event.getCode() == KeyCode.NUMPAD6) mazePanel.turnRight();
				if(event.getCode() == KeyCode.NUMPAD2) mazePanel.turnAround();
				if(event.getCode() == KeyCode.NUMPAD8) mazePanel.stepForward();
			}
		});
	}
}
