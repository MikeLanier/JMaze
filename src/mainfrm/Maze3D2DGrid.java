package mainfrm;


import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class Maze3D2DGrid extends Xform {

	private ControlPanel controlPanel = null;

	public Map<Integer, Maze3D2DCell> cells = new HashMap<Integer, Maze3D2DCell>();
	public Map<Integer, Maze3D2DWall> walls = new HashMap<Integer, Maze3D2DWall>();

	public Maze3D2DCell currentMaze2DCell = null;

	public Maze3D2DGrid(ControlPanel _controlPanel)
	{
		controlPanel = _controlPanel;
		int color = 4;
		for(int x=-50; x<=50; x+=10) {
			for(int z=-50; z<=50; z+=10) {
//				if(x!=50) getChildren().add(new Maze3D2DWall(x, 0, z, true, color));
//				if(z!=50) getChildren().add(new Maze3D2DWall(x, 0, z, false, color));
//				color++;
//				if(color>=5) color = 0;
				createCell(x, 0, z);
			}
		}
	}

	public Integer ID(int xOrigin, int yOrigin, int zOrigin, boolean horizonal)
	{
		int h = (horizonal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(zOrigin & 0x7fff) << 15 |
				h;
	}

	private Maze3D2DCell createCell(int x, int y, int z)
	{
		Maze3D2DWall west = walls.get(ID(x, y, z, false));
		if(west == null)
		{
			west = new Maze3D2DWall(x, y, z, false, 0);
			walls.put(west.ID(), west);
			getChildren().add(west);
		}

		Maze3D2DWall north = walls.get(ID(x, y, z, true));
		if(north == null)
		{
			north = new Maze3D2DWall(x, y, z, true, 0);
			walls.put(north.ID(), north);
			getChildren().add(north);
		}

		Maze3D2DWall east = walls.get(ID(x+10, y, z, false));
		if(east == null)
		{
			east = new Maze3D2DWall(x+1, y, z, false, 0);
			walls.put(east.ID(), east);
			getChildren().add(east);
		}

		Maze3D2DWall south = walls.get(ID(x, y, z+10, true));
		if(south == null)
		{
			south = new Maze3D2DWall(x, y, z+1, true, 0);
			walls.put(south.ID(), south);
			getChildren().add(south);
		}

		Maze3D2DCell maze2DCell = new Maze3D2DCell(x, y, z, west, north, east, south);
		cells.put(maze2DCell.ID(), maze2DCell);

		return maze2DCell;
	}

//	private void buildMazePanel()
//	{
//		System.out.println("buildMazepanel");
//
////		HBox vbMazeBox = new HBox();
////		add(vbMazeBox, 1, 0);
//
//		int width = (controlPanel._sizeX + 2) * controlPanel._sizeCell + 1;
//		int height = (controlPanel._sizeY + 2) * controlPanel._sizeCell + 1;
////		System.out.println("width, height: " + width + ", " + height);
//
////		this.setWidth(width+controlPanel._xOffset);
////		this.setHeight(height+controlPanel._yOffset);
////
//////		vbMazeBox.getChildren().add(this);
////		GraphicsContext gc = this.getGraphicsContext2D();
////		gc.setFill(javafx.scene.paint.Color.WHITE);
////		gc.fillRect(controlPanel._xOffset, controlPanel._yOffset, width, height);
////
////		for(int x=1; x<=controlPanel._sizeX; x++)
////		{
////			for(int y=1; y<=controlPanel._sizeY; y++)
////			{
////				createCell(x, y);
////			}
////		}
//	}
//
//	private Stack<Maze2DCell> stack = null; //new Stack<Maze2DCell>();
//
//	public void createMaze()
//	{
//		System.out.println("createMaze");
//		System.out.println("algorithm: " + controlPanel.cbAlgorithm.getValue());
//
//		controlPanel._sizeX = Integer.parseInt(controlPanel.tfMazeSizeX.getText());
//		controlPanel._sizeY = Integer.parseInt(controlPanel.tfMazeSizeY.getText());
//		controlPanel._sizeCell = Integer.parseInt(controlPanel.tfCellSize.getText());
//
//		cells = new HashMap<Integer, Maze2DCell>();
//		walls = new HashMap<Integer, Maze2DWall>();
//
//		buildMazePanel();
//
//		Integer x = 0;
//		Integer y = controlPanel._sizeY/3;
//		Maze2DCell entranceMaze2DCell = createCell(x,y);
//
//		x = controlPanel._sizeX+1;
//		y = controlPanel._sizeY*2/3;
//		Maze2DCell exitMaze2DCell = createCell(x,y);
//
//		int startCellX = Integer.parseInt(controlPanel.tfStartCellX.getText());
//		int startCellY = Integer.parseInt(controlPanel.tfStartCellY.getText());
//		currentMaze2DCell = cells.get(ID(startCellX, startCellY, false));
//		if(currentMaze2DCell != null)
//		{
//			currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
//		}
//
//		entranceMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeEntrance);
//		exitMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeExit);
//
//		entranceMaze2DCell.Visited(true);
//		exitMaze2DCell.Visited(true);
//
//		if(entranceMaze2DCell.W(Maze2DCell.west) != null)	entranceMaze2DCell.W(Maze2DCell.west).Open(true);
//		if(entranceMaze2DCell.W(Maze2DCell.north) != null)	entranceMaze2DCell.W(Maze2DCell.north).Open(true);
//		if(entranceMaze2DCell.W(Maze2DCell.east) != null)	entranceMaze2DCell.W(Maze2DCell.east).Open(true);
//		if(entranceMaze2DCell.W(Maze2DCell.south) != null)	entranceMaze2DCell.W(Maze2DCell.south).Open(true);
//
//		if(exitMaze2DCell.W(Maze2DCell.west) != null)	exitMaze2DCell.W(Maze2DCell.west).Open(true);
//		if(exitMaze2DCell.W(Maze2DCell.north) != null)	exitMaze2DCell.W(Maze2DCell.north).Open(true);
//		if(exitMaze2DCell.W(Maze2DCell.east) != null)	exitMaze2DCell.W(Maze2DCell.east).Open(true);
//		if(exitMaze2DCell.W(Maze2DCell.south) != null)	exitMaze2DCell.W(Maze2DCell.south).Open(true);
//
//		for(int i=0; i<controlPanel.algorithms.length; ++i) {
//			if (controlPanel.cbAlgorithm.getValue().equals(controlPanel.algorithms[i].title)) {
//				switch(controlPanel.algorithms[i].index)
//				{
//					case 0:	recursiveBacktracker();	break;
//					case 1:	randomizedKruskalAlgorithm();	break;
//					case 2:	randomizedPrimAlgorithm();	break;
//					case 3:	randomizedPrimAlgorithmModified();	break;
//					case 4:	recursiveDivision();	break;
//				}
//			}
//		}
//
//		drawMaze();
//	}
//
//	public void drawMaze()
//	{
//		System.out.println("drawMaze");
//
//		GraphicsContext gc = getGraphicsContext2D();
//
//		Collection<Maze2DCell> cc = cells.values();
//		for(Maze2DCell c: cc)
//		{
//			c.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
//		}
//
//		Collection<Maze2DWall> wc = walls.values();
//		for(Maze2DWall w: wc)
//		{
//			w.draw(gc, controlPanel._xOffset, controlPanel._yOffset, controlPanel._sizeCell);
//		}
//	}
//
//	private void randomizedKruskalAlgorithm()
//	{
//		System.out.println("randomizedKruskalAlgorithm");
//	}
//
//	private void randomizedPrimAlgorithm()
//	{
//		System.out.println("randomizedPrimAlgorithm");
//	}
//
//	private void randomizedPrimAlgorithmModified()
//	{
//		System.out.println("randomizedPrimAlgorithmModified");
//	}
//
//	private void recursiveDivision()
//	{
//		System.out.println("recursiveDivision");
//	}
//
//	private void recursiveBacktracker() {
//		System.out.println("recursiveBacktracker");
//
//		if (currentMaze2DCell != null) {
//			if (stack == null) {
//				stack = new Stack<Maze2DCell>();
//			}
//			stack.push(currentMaze2DCell);
//
//			while (stack.size() > 0) {
//				int x = currentMaze2DCell.X();
//				int y = currentMaze2DCell.Y();
//
//				currentMaze2DCell.Visited(true);
//
//				Maze2DCell westMaze2DCell = cells.get(ID(x - 1, y, false));
//				Maze2DCell northMaze2DCell = cells.get(ID(x, y - 1, false));
//				Maze2DCell eastMaze2DCell = cells.get(ID(x + 1, y, false));
//				Maze2DCell southMaze2DCell = cells.get(ID(x, y + 1, false));
//
//				class Group3 {
//					private Maze2DCell cell;
//					private Maze2DWall wall;
//
//					private Group3(Maze2DCell _cell, Maze2DWall _wall) {
//						cell = _cell;
//						wall = _wall;
//					}
//				}
//
//				List<Group3> cells = new ArrayList<Group3>();
//
//				if (westMaze2DCell != null && !westMaze2DCell.Visited())
//					cells.add(new Group3(westMaze2DCell, currentMaze2DCell.W(Maze2DCell.west)));
//				if (northMaze2DCell != null && !northMaze2DCell.Visited())
//					cells.add(new Group3(northMaze2DCell, currentMaze2DCell.W(Maze2DCell.north)));
//				if (eastMaze2DCell != null && !eastMaze2DCell.Visited())
//					cells.add(new Group3(eastMaze2DCell, currentMaze2DCell.W(Maze2DCell.east)));
//				if (southMaze2DCell != null && !southMaze2DCell.Visited())
//					cells.add(new Group3(southMaze2DCell, currentMaze2DCell.W(Maze2DCell.south)));
//				if (cells.size() > 0) {
//					int r = controlPanel.rand.nextInt(cells.size());
//
//					cells.get(r).wall.Open(true);
//					currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
//					currentMaze2DCell = cells.get(r).cell;
//					currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
//					currentMaze2DCell.Visited(true);
//					stack.push(currentMaze2DCell);
//				} else {
//					currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
//					currentMaze2DCell = stack.pop();
//
//					if (stack.size() > 0)
//						currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
//					else
//						currentMaze2DCell.SetType(Maze2DCell.CellType.eNormal);
//				}
//			}
//		}
//	}
}
