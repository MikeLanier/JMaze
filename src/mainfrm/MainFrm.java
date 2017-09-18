package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;

import java.util.*;

public class MainFrm extends GridPane
{
	private ControlPanel controlPanel = null;
	private Canvas		cvsMazePanel		= new Canvas();

	private Map<Integer, Cell> cells = new HashMap<Integer, Cell>();
	private Map<Integer, Wall> walls = new HashMap<Integer, Wall>();

	private Cell	currentCell = null;

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
		currentCell = cells.get(ID(startCellX, startCellY, false));
		if(currentCell != null)
		{
			currentCell.SetType(Cell.CellType.eCellTypeStart);
		}

		drawMaze();
	}

	private Cell createCell(int x, int y)
	{
		Wall west = walls.get(ID(x, y, false));
		if(west == null)
		{
			west = new Wall(x, y, false);
			walls.put(west.ID(), west);
		}

		Wall north = walls.get(ID(x, y, true));
		if(north == null)
		{
			north = new Wall(x, y, true);
			walls.put(north.ID(), north);
		}

		Wall east = walls.get(ID(x+1, y, false));
		if(east == null)
		{
			east = new Wall(x+1, y, false);
			walls.put(east.ID(), east);
		}

		Wall south = walls.get(ID(x, y+1, true));
		if(south == null)
		{
			south = new Wall(x, y+1, true);
			walls.put(south.ID(), south);
		}

		Cell cell = new Cell(x, y, west, north, east, south);
		cells.put(cell.ID(), cell);

		return cell;
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

		Collection<Cell> cc = cells.values();
		for(Cell c: cc)
		{
			c.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
		}

		Collection<Wall> wc = walls.values();
		for(Wall w: wc)
		{
			w.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
		}
	}

	private Stack<Cell> stack = null; //new Stack<Cell>();

	public void createMaze()
	{
		System.out.println("createMaze");
		System.out.println("algorithm: " + controlPanel.cbAlgorithm.getValue());

		controlPanel._sizeX = Integer.parseInt(controlPanel.tfMazeSizeX.getText());
		controlPanel._sizeY = Integer.parseInt(controlPanel.tfMazeSizeY.getText());
		controlPanel._sizeCell = Integer.parseInt(controlPanel.tfCellSize.getText());

		cells = new HashMap<Integer, Cell>();
		walls = new HashMap<Integer, Wall>();

		buildMazePanel();

		Integer x = 0;
		Integer y = controlPanel._sizeY/3;
		Cell entranceCell = createCell(x,y);

		x = controlPanel._sizeX+1;
		y = controlPanel._sizeY*2/3;
		Cell exitCell = createCell(x,y);

		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
		currentCell = cells.get(ID(startCellX, startCellY, false));
		if(currentCell != null)
		{
			currentCell.SetType(Cell.CellType.eCellTypeStart);
		}

		entranceCell.SetType(Cell.CellType.eCellTypeEntrance);
		exitCell.SetType(Cell.CellType.eCellTypeExit);

		entranceCell.Visited(true);
		exitCell.Visited(true);

		if(entranceCell.W(Cell.west) != null)	entranceCell.W(Cell.west).Open(true);
		if(entranceCell.W(Cell.north) != null)	entranceCell.W(Cell.north).Open(true);
		if(entranceCell.W(Cell.east) != null)	entranceCell.W(Cell.east).Open(true);
		if(entranceCell.W(Cell.south) != null)	entranceCell.W(Cell.south).Open(true);

		if(exitCell.W(Cell.west) != null)	exitCell.W(Cell.west).Open(true);
		if(exitCell.W(Cell.north) != null)	exitCell.W(Cell.north).Open(true);
		if(exitCell.W(Cell.east) != null)	exitCell.W(Cell.east).Open(true);
		if(exitCell.W(Cell.south) != null)	exitCell.W(Cell.south).Open(true);

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

		if(currentCell != null) {
			if(stack == null) {
				stack = new Stack<Cell>();
			}
			stack.push(currentCell);

			while(stack.size()>0) {
				int x = currentCell.X();
				int y = currentCell.Y();

				currentCell.Visited(true);

				Cell westCell = cells.get(ID(x - 1, y, false));
				Cell northCell = cells.get(ID(x, y - 1, false));
				Cell eastCell = cells.get(ID(x + 1, y, false));
				Cell southCell = cells.get(ID(x, y + 1, false));

				class Group3
				{
					private Cell	cell;
					private Wall	wall;

					private Group3(Cell _cell, Wall _wall) {
						cell = _cell;
						wall = _wall;
					}
				}

				List<Group3> cells = new ArrayList<Group3>();

				if (westCell != null && !westCell.Visited()) cells.add(new Group3(westCell, currentCell.W(Cell.west)));
				if (northCell != null && !northCell.Visited()) cells.add(new Group3(northCell, currentCell.W(Cell.north)));
				if (eastCell != null && !eastCell.Visited()) cells.add(new Group3(eastCell, currentCell.W(Cell.east)));
				if (southCell != null && !southCell.Visited()) cells.add(new Group3(southCell, currentCell.W(Cell.south)));
				if(cells.size()>0) {
					int r = controlPanel.rand.nextInt(cells.size());

					cells.get(r).wall.Open(true);
					currentCell.SetType(Cell.CellType.eNormal);
					currentCell = cells.get(r).cell;
					currentCell.SetType(Cell.CellType.eCellTypeStart);
					currentCell.Visited(true);
					stack.push(currentCell);
				}
				else {
					currentCell.SetType(Cell.CellType.eNormal);
					currentCell = stack.pop();

					if(stack.size()>0)
						currentCell.SetType(Cell.CellType.eCellTypeStart);
					else
						currentCell.SetType(Cell.CellType.eNormal);
				}
			}
		}
	}
}
