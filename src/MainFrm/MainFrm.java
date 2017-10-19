package MainFrm;

import Maze.MazeCell;
import Maze.MazeGlobal;
import Maze.MazePanel;
import RadialMenu.CommandEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import ControlPanel.ControlPanel;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	public MazePanel mazePanel = null;
	public static RadialMenu.Command	commands = null;

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

		commands = new RadialMenu.Command(-2, "Commands", "Commands", "");
		commands.add(new RadialMenu.Command(1, "Forward", "Move forward one cell", "..\\Images\\Forward32.png"));
//		commands.add(new RadialMenu.Command(5, "Open Door", "Open Door", "..\\Images\\OpenDoor32.png"));
//		commands.add(null);
		commands.add(new RadialMenu.Command(3, "Turn Right", "Turn right", "..\\Images\\TurnRight32.png"));
//		commands.add(new RadialMenu.Command(8, "Retreat", "Run away", "..\\Images\\Retreat32.png"));
//		commands.add(null);
		commands.add(new RadialMenu.Command(4, "Turn Around", "Turn Around", "..\\Images\\TurnAround32.png"));
//		commands.add(new RadialMenu.Command(7, "Fight", "Fight", "..\\Images\\Fight32.png"));
//		commands.add(null);
		commands.add(new RadialMenu.Command(2, "Turn Left", "Turn left", "..\\Images\\TurnLeft32.png"));
//		commands.add(new RadialMenu.Command(6, "Get Item", "Get visible item", "..\\Images\\GetItem32.png"));
//		commands.add(null);

		add(new RadialMenu.RadialMenu(40, 100, commands),1, 0);

		addEventFilter(
				CommandEvent.RUN_COMMAND,
				event -> System.out.println(
						"Field filtered strike: " + event.getCommand()
				)
		);

		addEventHandler(
				CommandEvent.RUN_COMMAND,
				event -> System.out.println(
						"Field handled strike: " + event.getCommand().toString()
				)
		);

		mazePanel.updateStartCell();
		MazeGlobal.entranceMazeCell = mazePanel.createEntranceExitCell(MazeGlobal.entranceCellX, MazeGlobal.entranceCellY, MazeCell.CellType.eCellTypeEntrance);
		MazeGlobal.exitMazeCell = mazePanel.createEntranceExitCell(MazeGlobal.exitCellX, MazeGlobal.exitCellY, MazeCell.CellType.eCellTypeExit);

//		mazePanel.createStairCell(MazeGlobal.entranceMazeCell, MazeCell.CellType.eCellTypeStairsUp);
//		mazePanel.createStairCell(MazeGlobal.exitMazeCell, MazeCell.CellType.eCellTypeStairsDown);

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
