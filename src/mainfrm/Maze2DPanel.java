package mainfrm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import mainfrm.ControlPanel.ControlPanel;

import java.util.*;

public class Maze2DPanel extends Canvas {
	private ControlPanel controlPanel = null;
	
	public Maze2DPanel(ControlPanel _controlPanel) {
		controlPanel = _controlPanel;
		buildMazePanel();
	}

	public Map<Integer, Maze2DCell> cells = new HashMap<Integer, Maze2DCell>();
	public Map<Integer, Maze2DWall> walls = new HashMap<Integer, Maze2DWall>();

	public Maze2DCell currentMaze2DCell = null;

	private int		xOffset = 10 + MazeGlobal.sizeCell;
	private int		yOffset = 10 + MazeGlobal.sizeCell;

	public void resetMazePanel()
	{
		int width = (MazeGlobal.sizeX + 2) * MazeGlobal.sizeCell + 1;
		int height = (MazeGlobal.sizeY + 2) * MazeGlobal.sizeCell + 1;

		this.setWidth(width+xOffset);
		this.setHeight(height+yOffset);

		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.WHITE);
		gc.fillRect(xOffset, yOffset, width, height);
	}

	public void buildMazePanel()
	{
		System.out.println("buildMazepanel");

		resetMazePanel();
//		int width = (controlPanel._sizeX + 2) * controlPanel._sizeCell + 1;
//		int height = (controlPanel._sizeY + 2) * controlPanel._sizeCell + 1;
//
//		this.setWidth(width+controlPanel._xOffset);
//		this.setHeight(height+controlPanel._yOffset);
//
//		GraphicsContext gc = this.getGraphicsContext2D();
//		gc.setFill(javafx.scene.paint.Color.WHITE);
//		gc.fillRect(controlPanel._xOffset, controlPanel._yOffset, width, height);

		for(int x=1; x<=MazeGlobal.sizeX; x++)
		{
			for(int y=1; y<=MazeGlobal.sizeY; y++)
			{
				createCell(x, y);
			}
		}
	}

	private Maze2DCell createCell(int x, int y)
	{
		Maze2DWall west = walls.get(MazeGlobal.ID(x, y, false));
		if(west == null)
		{
			west = new Maze2DWall(x, y, false);
			walls.put(west.ID(), west);
		}

		Maze2DWall north = walls.get(MazeGlobal.ID(x, y, true));
		if(north == null)
		{
			north = new Maze2DWall(x, y, true);
			walls.put(north.ID(), north);
		}

		Maze2DWall east = walls.get(MazeGlobal.ID(x+1, y, false));
		if(east == null)
		{
			east = new Maze2DWall(x+1, y, false);
			walls.put(east.ID(), east);
		}

		Maze2DWall south = walls.get(MazeGlobal.ID(x, y+1, true));
		if(south == null)
		{
			south = new Maze2DWall(x, y+1, true);
			walls.put(south.ID(), south);
		}

		Maze2DCell maze2DCell = new Maze2DCell(x, y, west, north, east, south);
		cells.put(maze2DCell.ID(), maze2DCell);

		return maze2DCell;
	}

	private Stack<Maze2DCell> stack = null; //new Stack<Maze2DCell>();

	public void createMaze(boolean animate)
	{
		System.out.println("createMaze");
		System.out.println("algorithm: " + controlPanel.algorithmControl.cbAlgorithm.getValue());

		MazeGlobal.sizeX = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeX.getText());
		MazeGlobal.sizeY = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeY.getText());
		MazeGlobal.sizeCell = Integer.parseInt(controlPanel.cellSizeControl.tfCellSize.getText());

		cells = new HashMap<Integer, Maze2DCell>();
		walls = new HashMap<Integer, Maze2DWall>();

		buildMazePanel();

		Integer x = 0;
		Integer y = MazeGlobal.sizeY/3;
		Maze2DCell entranceMaze2DCell = createCell(x,y);

		x = MazeGlobal.sizeX+1;
		y = MazeGlobal.sizeY*2/3;
		Maze2DCell exitMaze2DCell = createCell(x,y);

		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
		currentMaze2DCell = cells.get(MazeGlobal.ID(startCellX, startCellY, false));
		if(currentMaze2DCell != null)
		{
			currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
		}

		entranceMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeEntrance);
		exitMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeExit);

		entranceMaze2DCell.Visited(true);
		exitMaze2DCell.Visited(true);

		if(entranceMaze2DCell.W(Maze2DCell.west) != null)	entranceMaze2DCell.W(Maze2DCell.west).Open(true);
		if(entranceMaze2DCell.W(Maze2DCell.north) != null)	entranceMaze2DCell.W(Maze2DCell.north).Open(true);
		if(entranceMaze2DCell.W(Maze2DCell.east) != null)	entranceMaze2DCell.W(Maze2DCell.east).Open(true);
		if(entranceMaze2DCell.W(Maze2DCell.south) != null)	entranceMaze2DCell.W(Maze2DCell.south).Open(true);

		if(exitMaze2DCell.W(Maze2DCell.west) != null)	exitMaze2DCell.W(Maze2DCell.west).Open(true);
		if(exitMaze2DCell.W(Maze2DCell.north) != null)	exitMaze2DCell.W(Maze2DCell.north).Open(true);
		if(exitMaze2DCell.W(Maze2DCell.east) != null)	exitMaze2DCell.W(Maze2DCell.east).Open(true);
		if(exitMaze2DCell.W(Maze2DCell.south) != null)	exitMaze2DCell.W(Maze2DCell.south).Open(true);

//		for(int i=0; i<controlPanel.algorithmControl.algorithms.length; ++i) {
//			if (controlPanel.algorithmControl.cbAlgorithm.getValue().equals(controlPanel.algorithmControl.algorithms[i].title)) {
//				switch(controlPanel.algorithmControl.algorithms[i].index)
//				{
//					case 0:	recursiveBacktracker();	break;
//					case 1:	randomizedKruskalAlgorithm();	break;
//					case 2:	randomizedPrimAlgorithm();	break;
//					case 3:	randomizedPrimAlgorithmModified();	break;
//					case 4:	recursiveDivision();	break;
//				}
//			}
//		}
		recursiveBacktracker(animate);

		drawMaze();
	}

	public void drawMaze()
	{
		GraphicsContext gc = getGraphicsContext2D();

		Collection<Maze2DCell> cc = cells.values();
		for(Maze2DCell c: cc)
		{
			c.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}

		Collection<Maze2DWall> wc = walls.values();
		for(Maze2DWall w: wc)
		{
			w.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}
	}

	private void randomizedKruskalAlgorithm()
	{
		System.out.println("randomizedKruskalAlgorithm");
	}

	private void randomizedPrimAlgorithm()
	{
		System.out.println("randomizedPrimAlgorithm");
	}

	private void randomizedPrimAlgorithmModified()
	{
		System.out.println("randomizedPrimAlgorithmModified");
	}

	private void recursiveDivision()
	{
		System.out.println("recursiveDivision");
	}

	public void createMazeStep()
	{
		int x = currentMaze2DCell.X();
		int y = currentMaze2DCell.Y();

		currentMaze2DCell.Visited(true);

		Maze2DCell westMaze2DCell = cells.get(MazeGlobal.ID(x - 1, y, false));
		Maze2DCell northMaze2DCell = cells.get(MazeGlobal.ID(x, y - 1, false));
		Maze2DCell eastMaze2DCell = cells.get(MazeGlobal.ID(x + 1, y, false));
		Maze2DCell southMaze2DCell = cells.get(MazeGlobal.ID(x, y + 1, false));

		class Group3 {
			private Maze2DCell cell;
			private Maze2DWall wall;

			private Group3(Maze2DCell _cell, Maze2DWall _wall) {
				cell = _cell;
				wall = _wall;
			}
		}

		List<Group3> cells = new ArrayList<Group3>();

		if (westMaze2DCell != null && !westMaze2DCell.Visited())
			cells.add(new Group3(westMaze2DCell, currentMaze2DCell.W(Maze2DCell.west)));
		if (northMaze2DCell != null && !northMaze2DCell.Visited())
			cells.add(new Group3(northMaze2DCell, currentMaze2DCell.W(Maze2DCell.north)));
		if (eastMaze2DCell != null && !eastMaze2DCell.Visited())
			cells.add(new Group3(eastMaze2DCell, currentMaze2DCell.W(Maze2DCell.east)));
		if (southMaze2DCell != null && !southMaze2DCell.Visited())
			cells.add(new Group3(southMaze2DCell, currentMaze2DCell.W(Maze2DCell.south)));
		if (cells.size() > 0) {
			int r = controlPanel.rand.nextInt(cells.size());

			cells.get(r).wall.Open(true);
			currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
			currentMaze2DCell = cells.get(r).cell;
			currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
			currentMaze2DCell.Visited(true);
			stack.push(currentMaze2DCell);
		} else {
			currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
			currentMaze2DCell = stack.pop();

			if (stack.size() > 0)
				currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
			else
				currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
		}

		drawMaze();
		if(stack.size() == 0) timeline.stop();
	}

	private Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(30),
			ae -> createMazeStep()));

	private void recursiveBacktracker(boolean animate) {
		System.out.println("recursiveBacktracker");

		if (currentMaze2DCell != null) {
			if (stack == null) {
				stack = new Stack<Maze2DCell>();
			}
			stack.push(currentMaze2DCell);

			if(animate) {
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}
			else {
				while (stack.size() > 0) {
					createMazeStep();
				}
			}
		}
	}
}
