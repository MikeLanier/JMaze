package mainfrm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mainfrm.ControlPanel.ControlPanel;

import java.util.*;

public class MazePanel extends Canvas {
	private ControlPanel controlPanel = null;
	
	public MazePanel(ControlPanel _controlPanel) {
		controlPanel = _controlPanel;
		buildMazePanel();
	}

	public Map<Integer, MazeCell> cells = new HashMap<Integer, MazeCell>();
	public Map<Integer, MazeWall> walls = new HashMap<Integer, MazeWall>();

	public MazeCell currentMazeCell = null;
	public MazeCell entranceMazeCell = null;

	private int		xOffset = 10 + MazeGlobal.sizeCell;
	private int		yOffset = 10 + MazeGlobal.sizeCell;

	public void resetMazePanel()
	{
		int width = (MazeGlobal.sizeX + 2) * MazeGlobal.sizeCell + 1;
		int height = (MazeGlobal.sizeY + 2) * MazeGlobal.sizeCell + 1;

		width += 600;
		height += 300;

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

	private MazeCell createCell(int x, int y)
	{
		MazeWall west = walls.get(MazeGlobal.ID(x, y, false));
		if(west == null)
		{
			west = new MazeWall(x, y, false);
			walls.put(west.ID(), west);
		}

		MazeWall north = walls.get(MazeGlobal.ID(x, y, true));
		if(north == null)
		{
			north = new MazeWall(x, y, true);
			walls.put(north.ID(), north);
		}

		MazeWall east = walls.get(MazeGlobal.ID(x+1, y, false));
		if(east == null)
		{
			east = new MazeWall(x+1, y, false);
			walls.put(east.ID(), east);
		}

		MazeWall south = walls.get(MazeGlobal.ID(x, y+1, true));
		if(south == null)
		{
			south = new MazeWall(x, y+1, true);
			walls.put(south.ID(), south);
		}

		MazeCell mazeCell = new MazeCell(x, y, west, north, east, south);
		cells.put(mazeCell.ID(), mazeCell);

		return mazeCell;
	}

	private Stack<MazeCell> stack = null; //new Stack<MazeCell>();

	public void createMaze(boolean animate)
	{
		System.out.println("createMaze");
		System.out.println("algorithm: " + controlPanel.algorithmControl.cbAlgorithm.getValue());

		MazeGlobal.sizeX = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeX.getText());
		MazeGlobal.sizeY = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeY.getText());
		MazeGlobal.sizeCell = Integer.parseInt(controlPanel.cellSizeControl.tfCellSize.getText());

		cells = new HashMap<Integer, MazeCell>();
		walls = new HashMap<Integer, MazeWall>();

		buildMazePanel();

		Integer x = 0;
		Integer y = MazeGlobal.sizeY/3;
		entranceMazeCell = createCell(x,y);

		x = MazeGlobal.sizeX+1;
		y = MazeGlobal.sizeY*2/3;
		MazeCell exitMazeCell = createCell(x,y);

		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
		currentMazeCell = cells.get(MazeGlobal.ID(startCellX, startCellY, false));
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

		currentMazeCell = entranceMazeCell;
		currentMazeCell.SetType(MazeCell.CellType.eCellFacingEast);

		drawMaze();
	}

	public void drawMaze3D(GraphicsContext gc) {
		if(entranceMazeCell != null) {
//			System.out.println( entranceMazeCell.X() + ", " + entranceMazeCell.Y());

			int x = currentMazeCell.X();
			int y = currentMazeCell.Y();
			System.out.println("currentCell: " + x + ", " + y);

			int yindex[] = {-2,-1,2,1,0};
			for(int i=5; i>=0; i--) {
				for(int j=0; j<5; j++) {
					MazeCell c = null;
					if(currentMazeCell.facingEast())
						c = cells.get(MazeGlobal.ID(i+x, y+yindex[j], false));
					else if(currentMazeCell.facingWest())
						c = cells.get(MazeGlobal.ID(x-i, y+yindex[j], false));
					else if(currentMazeCell.facingSouth())
						c = cells.get(MazeGlobal.ID(yindex[j]+x, y+i, false));
					else if(currentMazeCell.facingNorth())
						c = cells.get(MazeGlobal.ID(yindex[j]+x, y-i, false));

					if (c != null) {
//						System.out.println("cell: (" + i + "," + y + ") west : " + c.W(MazeCell.west).Open());
//						System.out.println("cell: (" + i + "," + y + ") north: " + c.W(MazeCell.north).Open());
//						System.out.println("cell: (" + i + "," + y + ") east : " + c.W(MazeCell.east).Open());
//						System.out.println("cell: (" + i + "," + y + ") south: " + c.W(MazeCell.south).Open());

//						c.SetType(MazeCell.CellType.eCellTypeStart);

						double x1 = -1+(yindex[j]*2);
						double x2 = 1+(yindex[j]*2);
						double y1 = -1;
						double y2 = 1;
						double z1 = i-.5;
						double z2 = i+.5;

						MazeMath.point p000 = new MazeMath.point(x1, y1, z1);
						MazeMath.point p100 = new MazeMath.point(x2, y1, z1);
						MazeMath.point p010 = new MazeMath.point(x1, y2, z1);
						MazeMath.point p110 = new MazeMath.point(x2, y2, z1);
						MazeMath.point p001 = new MazeMath.point(x1, y1, z2);
						MazeMath.point p101 = new MazeMath.point(x2, y1, z2);
						MazeMath.point p011 = new MazeMath.point(x1, y2, z2);
						MazeMath.point p111 = new MazeMath.point(x2, y2, z2);

						MazeMath.rectangle left = new MazeMath.rectangle(p000, p001, p011, p010);
						MazeMath.rectangle back = new MazeMath.rectangle(p001, p101, p111, p011);
						MazeMath.rectangle right = new MazeMath.rectangle(p100, p101, p111, p110);
						MazeMath.rectangle front = new MazeMath.rectangle(p000, p100, p110, p010);

						MazeMath.matrix m = new MazeMath.matrix();
						m.scale(100);

						left = m.dot(left);
						back = m.dot(back);
						right = m.dot(right);
						front = m.dot(front);

						left.perspective(800.0);
						back.perspective(800.0);
						right.perspective(800.0);
						front.perspective(800.0);

						gc.setLineDashes(0);
						gc.setLineWidth(1);

						double xOffset = 500;
						double yOffset = 500;

						left.move(xOffset, yOffset);
						back.move(xOffset, yOffset);
						right.move(xOffset, yOffset);
						front.move(xOffset, yOffset);

//						front.dump("front");

						gc.setStroke(Color.BLACK);
						gc.setFill(Color.LIGHTBLUE);
						if (!c.W(MazeCell.east).Open()) back.draw(gc);
						if (!c.W(MazeCell.north).Open()) left.draw(gc);
						if (!c.W(MazeCell.south).Open()) right.draw(gc);
						if (!c.W(MazeCell.west).Open()) front.draw(gc);
					}
				}
			}
		}
	}

	public void drawMaze2D(GraphicsContext gc) {
		System.out.println("drawMaze2D");
		Collection<MazeCell> cc = cells.values();
		for(MazeCell c: cc)
		{
//			if(c.facingNorth())	System.out.println("facing north: " + c.X() + ", " + c.Y());
//			else if(c.facingSouth())	System.out.println("facing south: " + c.X() + ", " + c.Y());
//			else if(c.facingEast())	System.out.println("facing east: " + c.X() + ", " + c.Y());
//			else if(c.facingWest())	System.out.println("facing west: " + c.X() + ", " + c.Y());
//			else System.out.println("facing none: " + c.X() + ", " + c.Y());
			c.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}

		Collection<MazeWall> wc = walls.values();
		for(MazeWall w: wc)
		{
			w.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}
	}

	public void drawMaze() {
		System.out.println("drawMaze");
		GraphicsContext gc = getGraphicsContext2D();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 1000, 1000);

		drawMaze3D(gc);
		drawMaze2D(gc);
	}

	public void turnLeft()
	{
		System.out.println("Turn left");
		if(currentMazeCell.facingNorth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingWest);
		else if(currentMazeCell.facingWest())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingSouth);
		else if(currentMazeCell.facingSouth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingEast);
		else if(currentMazeCell.facingEast())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingNorth);
		drawMaze();
	}

	public void turnRight()
	{
		System.out.println("Turn right");
		if(currentMazeCell.facingNorth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingEast);
		else if(currentMazeCell.facingEast())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingSouth);
		else if(currentMazeCell.facingSouth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingWest);
		else if(currentMazeCell.facingWest())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingNorth);
		drawMaze();
	}

	public void stepForward()
	{
		System.out.println("stepForward");
		int x = currentMazeCell.X();
		int y = currentMazeCell.Y();

		if(currentMazeCell.facingNorth() && !currentMazeCell.W(MazeCell.north).Open())	return;
		if(currentMazeCell.facingSouth() && !currentMazeCell.W(MazeCell.south).Open())	return;
		if(currentMazeCell.facingEast() && !currentMazeCell.W(MazeCell.east).Open())	return;
		if(currentMazeCell.facingWest() && !currentMazeCell.W(MazeCell.west).Open())	return;

		if(currentMazeCell.facingNorth()) {
			y--;
			GraphicsContext gc = getGraphicsContext2D();
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);

			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				System.out.println("move east");
				currentMazeCell.SetType(MazeCell.CellType.eCellFacingNorth);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingEast()) {
			x++;
			GraphicsContext gc = getGraphicsContext2D();
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);

			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				System.out.println("move east");
				currentMazeCell.SetType(MazeCell.CellType.eCellFacingEast);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingSouth()) {
			y++;
			GraphicsContext gc = getGraphicsContext2D();
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);

			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				System.out.println("move east");
				currentMazeCell.SetType(MazeCell.CellType.eCellFacingSouth);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingWest()) {
			x--;
			GraphicsContext gc = getGraphicsContext2D();
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);

			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				System.out.println("move east");
				currentMazeCell.SetType(MazeCell.CellType.eCellFacingWest);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}

		drawMaze();
	}

	public void turnAround()
	{
		System.out.println("Turn around");
		if(currentMazeCell.facingNorth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingSouth);
		else if(currentMazeCell.facingEast())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingWest);
		else if(currentMazeCell.facingSouth())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingNorth);
		else if(currentMazeCell.facingWest())	currentMazeCell.SetType(MazeCell.CellType.eCellFacingEast);
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

	public void createMazeStep(boolean redrawMaze)
	{
		int x = currentMazeCell.X();
		int y = currentMazeCell.Y();

		currentMazeCell.Visited(true);

		MazeCell westMazeCell = cells.get(MazeGlobal.ID(x - 1, y, false));
		MazeCell northMazeCell = cells.get(MazeGlobal.ID(x, y - 1, false));
		MazeCell eastMazeCell = cells.get(MazeGlobal.ID(x + 1, y, false));
		MazeCell southMazeCell = cells.get(MazeGlobal.ID(x, y + 1, false));

		class Group3 {
			private MazeCell cell;
			private MazeWall wall;

			private Group3(MazeCell _cell, MazeWall _wall) {
				cell = _cell;
				wall = _wall;
			}
		}

		List<Group3> cells = new ArrayList<Group3>();

		if (westMazeCell != null && !westMazeCell.Visited())
			cells.add(new Group3(westMazeCell, currentMazeCell.W(MazeCell.west)));
		if (northMazeCell != null && !northMazeCell.Visited())
			cells.add(new Group3(northMazeCell, currentMazeCell.W(MazeCell.north)));
		if (eastMazeCell != null && !eastMazeCell.Visited())
			cells.add(new Group3(eastMazeCell, currentMazeCell.W(MazeCell.east)));
		if (southMazeCell != null && !southMazeCell.Visited())
			cells.add(new Group3(southMazeCell, currentMazeCell.W(MazeCell.south)));
		if (cells.size() > 0) {
			int r = controlPanel.rand.nextInt(cells.size());

			cells.get(r).wall.Open(true);
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell = cells.get(r).cell;
			currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
			currentMazeCell.Visited(true);
			stack.push(currentMazeCell);
		} else {
			currentMazeCell.SetType(MazeCell.CellType.eNormal);
			currentMazeCell = stack.pop();

			if (stack.size() > 0)
				currentMazeCell.SetType(MazeCell.CellType.eCellTypeStart);
			else
				currentMazeCell.SetType(MazeCell.CellType.eNormal);
		}

		if(redrawMaze) drawMaze();
		if(stack.size() == 0) timeline.stop();
	}

	private Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(30),
			ae -> createMazeStep(true)));

	private void recursiveBacktracker(boolean animate) {
		System.out.println("recursiveBacktracker");

		if (currentMazeCell != null) {
			if (stack == null) {
				stack = new Stack<MazeCell>();
			}
			stack.push(currentMazeCell);

			if(animate) {
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}
			else {
				while (stack.size() > 0) {
					createMazeStep(false);
				}
			}
		}
	}
}
