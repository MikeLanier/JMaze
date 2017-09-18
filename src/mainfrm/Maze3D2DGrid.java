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
				createCell(x, 0, z);
			}
		}
		createMaze();
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
			east = new Maze3D2DWall(x+10, y, z, false, 0);
			walls.put(east.ID(), east);
			getChildren().add(east);
		}

		Maze3D2DWall south = walls.get(ID(x, y, z+10, true));
		if(south == null)
		{
			south = new Maze3D2DWall(x, y, z+10, true, 0);
			walls.put(south.ID(), south);
			getChildren().add(south);
		}

		Maze3D2DCell maze2DCell = new Maze3D2DCell(x, y, z, west, north, east, south);
		cells.put(maze2DCell.ID(), maze2DCell);

		return maze2DCell;
	}

	private Stack<Maze3D2DCell> stack = null; //new Stack<Maze2DCell>();

	public void createMaze()
	{
		System.out.println("createMaze");
		System.out.println("algorithm: " + controlPanel.algorithmControl.cbAlgorithm.getValue());

		controlPanel._sizeX = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeX.getText());
		controlPanel._sizeY = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeY.getText());
		controlPanel._sizeCell = Integer.parseInt(controlPanel.cellSizeControl.tfCellSize.getText());

//		cells = new HashMap<Integer, Maze3D2DCell>();
//		walls = new HashMap<Integer, Maze3D2DWall>();
//
//		buildMazePanel();

//		Integer x = 0;
//		Integer z = controlPanel._sizeY/3;
//		Maze3D2DCell entranceMaze2DCell = createCell(x,y);
		Maze3D2DCell entranceMaze2DCell = createCell(-60, 0, -20);
//
//		x = controlPanel._sizeX+1;
//		z = controlPanel._sizeY*2/3;
//		Maze2DCell exitMaze2DCell = createCell(x,y);
		Maze3D2DCell exitMaze2DCell = createCell(60, 0, 20);
//
		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
//		currentMaze2DCell = cells.get(ID(startCellX, 0, startCellY, false));
		currentMaze2DCell = cells.get(ID(0, 0, 0, false));
		if(currentMaze2DCell != null)
		{
			currentMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeStart);
		}

		entranceMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeEntrance);
		exitMaze2DCell.SetType(Maze2DCell.CellType.eCellTypeExit);

		entranceMaze2DCell.Visited(true);
		exitMaze2DCell.Visited(true);

		if(entranceMaze2DCell.W(Maze2DCell.west) != null)	entranceMaze2DCell.W(Maze2DCell.west).setVisible(false);
		if(entranceMaze2DCell.W(Maze2DCell.north) != null)	entranceMaze2DCell.W(Maze2DCell.north).setVisible(false);
		if(entranceMaze2DCell.W(Maze2DCell.east) != null)	entranceMaze2DCell.W(Maze2DCell.east).setVisible(false);
		if(entranceMaze2DCell.W(Maze2DCell.south) != null)	entranceMaze2DCell.W(Maze2DCell.south).setVisible(false);

		if(exitMaze2DCell.W(Maze2DCell.west) != null)	exitMaze2DCell.W(Maze2DCell.west).setVisible(false);
		if(exitMaze2DCell.W(Maze2DCell.north) != null)	exitMaze2DCell.W(Maze2DCell.north).setVisible(false);
		if(exitMaze2DCell.W(Maze2DCell.east) != null)	exitMaze2DCell.W(Maze2DCell.east).setVisible(false);
		if(exitMaze2DCell.W(Maze2DCell.south) != null)	exitMaze2DCell.W(Maze2DCell.south).setVisible(false);

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
		recursiveBacktracker();
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

	private void recursiveBacktracker() {
		System.out.println("recursiveBacktracker");

		if (currentMaze2DCell != null) {
			if (stack == null) {
				stack = new Stack<Maze3D2DCell>();
			}
			stack.push(currentMaze2DCell);

			while (stack.size() > 0) {
				int x = currentMaze2DCell.X();
				int z = currentMaze2DCell.Z();

				currentMaze2DCell.Visited(true);

				Maze3D2DCell westMaze2DCell = cells.get(ID(x - 10, 0, z, false));
				Maze3D2DCell northMaze2DCell = cells.get(ID(x, 0, z - 10, false));
				Maze3D2DCell eastMaze2DCell = cells.get(ID(x + 10, 0, z, false));
				Maze3D2DCell southMaze2DCell = cells.get(ID(x, 0, z + 10, false));

				class Group3 {
					private Maze3D2DCell cell;
					private Maze3D2DWall wall;

					private Group3(Maze3D2DCell _cell, Maze3D2DWall _wall) {
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

					//cells.get(r).wall.Open(true);
					cells.get(r).wall.setVisible(false);
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
			}
		}
	}
}
