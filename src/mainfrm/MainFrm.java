package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;

import java.util.*;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	private Canvas		cvsMazePanel		= new Canvas();

	private Map<Integer, MazeCell> cells = new HashMap<Integer, MazeCell>();
	private Map<Integer, MazeCellWall> walls = new HashMap<Integer, MazeCellWall>();

	private MazeCell currentMazeCell = null;

	private Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}

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
		buildMazePanel();

		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
		currentMazeCell = cells.get(ID(startCellX, startCellY, false));
		if(currentMazeCell != null)
		{
			currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
		}

		drawMaze();
	}

	private MazeCell createCell(int x, int y)
	{
		MazeCellWall west = walls.get(ID(x, y, false));
		if(west == null)
		{
			west = new MazeCellWall(x, y, false);
			walls.put(west.ID(), west);
		}

		MazeCellWall north = walls.get(ID(x, y, true));
		if(north == null)
		{
			north = new MazeCellWall(x, y, true);
			walls.put(north.ID(), north);
		}

		MazeCellWall east = walls.get(ID(x+1, y, false));
		if(east == null)
		{
			east = new MazeCellWall(x+1, y, false);
			walls.put(east.ID(), east);
		}

		MazeCellWall south = walls.get(ID(x, y+1, true));
		if(south == null)
		{
			south = new MazeCellWall(x, y+1, true);
			walls.put(south.ID(), south);
		}

		MazeCell mazeCell = new MazeCell(x, y, west, north, east, south);
		cells.put(mazeCell.ID(), mazeCell);

		return mazeCell;
	}

	private void buildMazePanel()
	{
		System.out.println("buildMazepanel");

		HBox vbMazeBox = new HBox();
		add(vbMazeBox, 1, 0);

		int width = (controlPanel._sizeX + 2) * controlPanel._sizeCell + 1;
		int height = (controlPanel._sizeY + 2) * controlPanel._sizeCell + 1;
//		System.out.println("width, height: " + width + ", " + height);

		cvsMazePanel.setWidth(width+controlPanel._xOffset);
		cvsMazePanel.setHeight(height+controlPanel._yOffset);

		vbMazeBox.getChildren().add(cvsMazePanel);
		GraphicsContext gc = cvsMazePanel.getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.WHITE);
		gc.fillRect(controlPanel._xOffset, controlPanel._yOffset, width, height);

		for(int x=1; x<=controlPanel._sizeX; x++)
		{
			for(int y=1; y<=controlPanel._sizeY; y++)
			{
				createCell(x, y);
			}
		}
	}

	private void drawMaze()
	{
		System.out.println("drawMaze");

		GraphicsContext gc = cvsMazePanel.getGraphicsContext2D();

		Collection<MazeCell> cc = cells.values();
		for(MazeCell c: cc)
		{
			c.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
		}

		Collection<MazeCellWall> wc = walls.values();
		for(MazeCellWall w: wc)
		{
			w.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
		}
	}

	private Stack<MazeCell> stack = null; //new Stack<MazeCell>();

	public void createMaze()
	{
		System.out.println("createMaze");
		System.out.println("algorithm: " + controlPanel.cbAlgorithm.getValue());

		controlPanel._sizeX = Integer.parseInt(controlPanel.tfMazeSizeX.getText());
		controlPanel._sizeY = Integer.parseInt(controlPanel.tfMazeSizeY.getText());
		controlPanel._sizeCell = Integer.parseInt(controlPanel.tfCellSize.getText());

		cells = new HashMap<Integer, MazeCell>();
		walls = new HashMap<Integer, MazeCellWall>();

		buildMazePanel();

		Integer x = 0;
		Integer y = controlPanel._sizeY/3;
		MazeCell entranceMazeCell = createCell(x,y);

		x = controlPanel._sizeX+1;
		y = controlPanel._sizeY*2/3;
		MazeCell exitMazeCell = createCell(x,y);

		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
		currentMazeCell = cells.get(ID(startCellX, startCellY, false));
		if(currentMazeCell != null)
		{
			currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
		}

		entranceMazeCell.SetType(MazeCell.CellType.eCellTypeEntrance);
		exitMazeCell.SetType(MazeCell.CellType.eCellTypeExit);

		entranceMazeCell.Visited(true);
		exitMazeCell.Visited(true);

		if(entranceMazeCell.W(MazeCell.west) != null)	entranceMazeCell.W(MazeCell.west).Open(true);
		if(entranceMazeCell.W(MazeCell.north) != null)	entranceMazeCell.W(MazeCell.north).Open(true);
		if(entranceMazeCell.W(MazeCell.east) != null)	entranceMazeCell.W(MazeCell.east).Open(true);
		if(entranceMazeCell.W(MazeCell.south) != null)	entranceMazeCell.W(MazeCell.south).Open(true);

		if(exitMazeCell.W(MazeCell.west) != null)	exitMazeCell.W(MazeCell.west).Open(true);
		if(exitMazeCell.W(MazeCell.north) != null)	exitMazeCell.W(MazeCell.north).Open(true);
		if(exitMazeCell.W(MazeCell.east) != null)	exitMazeCell.W(MazeCell.east).Open(true);
		if(exitMazeCell.W(MazeCell.south) != null)	exitMazeCell.W(MazeCell.south).Open(true);

		for(int i=0; i<controlPanel.algorithms.length; ++i) {
			if (controlPanel.cbAlgorithm.getValue().equals(controlPanel.algorithms[i].title)) {
				switch(controlPanel.algorithms[i].index)
				{
					case 0:	recursiveBacktracker();	break;
					case 1:	randomizedKruskalAlgorithm();	break;
					case 2:	randomizedPrimAlgorithm();	break;
					case 3:	randomizedPrimAlgorithmModified();	break;
					case 4:	recursiveDivision();	break;
				}
			}
		}

		drawMaze();
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

	private void recursiveBacktracker()
	{
		System.out.println("recursiveBacktracker");

		if(currentMazeCell != null) {
			if(stack == null) {
				stack = new Stack<MazeCell>();
			}
			stack.push(currentMazeCell);

			while(stack.size()>0) {
				int x = currentMazeCell.X();
				int y = currentMazeCell.Y();

				currentMazeCell.Visited(true);

				MazeCell westMazeCell = cells.get(ID(x - 1, y, false));
				MazeCell northMazeCell = cells.get(ID(x, y - 1, false));
				MazeCell eastMazeCell = cells.get(ID(x + 1, y, false));
				MazeCell southMazeCell = cells.get(ID(x, y + 1, false));

				class Group3
				{
					private MazeCell cell;
					private MazeCellWall wall;

					private Group3(MazeCell _cell, MazeCellWall _wall) {
						cell = _cell;
						wall = _wall;
					}
				}

				List<Group3> cells = new ArrayList<Group3>();

				if (westMazeCell != null && !westMazeCell.Visited()) cells.add(new Group3(westMazeCell, currentMazeCell.W(MazeCell.west)));
				if (northMazeCell != null && !northMazeCell.Visited()) cells.add(new Group3(northMazeCell, currentMazeCell.W(MazeCell.north)));
				if (eastMazeCell != null && !eastMazeCell.Visited()) cells.add(new Group3(eastMazeCell, currentMazeCell.W(MazeCell.east)));
				if (southMazeCell != null && !southMazeCell.Visited()) cells.add(new Group3(southMazeCell, currentMazeCell.W(MazeCell.south)));
				if(cells.size()>0) {
					int r = controlPanel.rand.nextInt(cells.size());

					cells.get(r).wall.Open(true);
					currentMazeCell.SetType(MazeCell.CellType.eNormal);
					currentMazeCell = cells.get(r).cell;
					currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
					currentMazeCell.Visited(true);
					stack.push(currentMazeCell);
				}
				else {
					currentMazeCell.SetType(MazeCell.CellType.eNormal);
					currentMazeCell = stack.pop();

					if(stack.size()>0)
						currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
					else
						currentMazeCell.SetType(MazeCell.CellType.eNormal);
				}
			}
		}
	}
}
