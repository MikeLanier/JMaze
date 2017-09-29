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

		mazePanel.updateStartCell();
		MazeGlobal.entranceMazeCell = mazePanel.createEntranceExitCell(MazeGlobal.entranceCellX, MazeGlobal.entranceCellY, MazeCell.CellType.eCellTypeEntrance);
		MazeGlobal.exitMazeCell = mazePanel.createEntranceExitCell(MazeGlobal.exitCellX, MazeGlobal.exitCellY, MazeCell.CellType.eCellTypeExit);
		mazePanel.drawMaze();

		setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event)
			{
//				System.out.println("GridPane: OnKeyPressed: [" + event.getCode() + "]");
				if(event.getCode() == KeyCode.LEFT) mazePanel.turnLeft();
				if(event.getCode() == KeyCode.RIGHT) mazePanel.turnRight();
				if(event.getCode() == KeyCode.DOWN) mazePanel.turnAround();
				if(event.getCode() == KeyCode.UP) mazePanel.stepForward();

				if(event.getCode() == KeyCode.NUMPAD4) mazePanel.turnLeft();
				if(event.getCode() == KeyCode.NUMPAD6) mazePanel.turnRight();
				if(event.getCode() == KeyCode.NUMPAD2) mazePanel.turnAround();
				if(event.getCode() == KeyCode.NUMPAD8) mazePanel.stepForward();
			}
		});
	}

	public void OnKeyPressed(KeyEvent event)
	{
//		System.out.println("OnKeyPressed: [" + event.getCode() + "]");
		if(event.getCode() == KeyCode.LEFT) mazePanel.turnLeft();
		if(event.getCode() == KeyCode.RIGHT) mazePanel.turnRight();
		if(event.getCode() == KeyCode.DOWN) mazePanel.turnAround();
		if(event.getCode() == KeyCode.UP) mazePanel.stepForward();

		if(event.getCode() == KeyCode.NUMPAD4) mazePanel.turnLeft();
		if(event.getCode() == KeyCode.NUMPAD6) mazePanel.turnRight();
		if(event.getCode() == KeyCode.NUMPAD2) mazePanel.turnAround();
		if(event.getCode() == KeyCode.NUMPAD8) mazePanel.stepForward();
	}
}
