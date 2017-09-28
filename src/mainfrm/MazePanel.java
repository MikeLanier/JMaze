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
	
	MazePanel(ControlPanel _controlPanel) {
		controlPanel = _controlPanel;
		buildMazePanel();
	}

	Map<Integer, MazeCell> cells = new HashMap<Integer, MazeCell>();
	Map<Integer, MazeWall> walls = new HashMap<Integer, MazeWall>();

	MazeCell currentMazeCell = null;
	MazeCell entranceMazeCell = null;

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

	void buildMazePanel()
	{
		resetMazePanel();

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
		MazeGlobal.sizeX = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeX.getText());
		MazeGlobal.sizeY = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeY.getText());
		MazeGlobal.sizeCell = Integer.parseInt(controlPanel.cellSizeControl.tfCellSize.getText());

		cells = new HashMap<>();
		walls = new HashMap<>();

		buildMazePanel();

		Integer x = 0;
		Integer y = MazeGlobal.sizeY/3;
		entranceMazeCell = createCell(x,y);
		entranceMazeCell.setType(MazeCell.CellType.eCellTypeEntrance);
		entranceMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);

//		Integer x = MazeGlobal.sizeX/2;
//		Integer y = MazeGlobal.sizeY+1;
//		entranceMazeCell = createCell(x,y);
//		entranceMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);

//		Integer x = MazeGlobal.sizeX/2;
//		Integer y = 0;
//		entranceMazeCell = createCell(x,y);
//		entranceMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);

//		Integer x = MazeGlobal.sizeX+1;
//		Integer y = MazeGlobal.sizeY/3;
//		entranceMazeCell = createCell(x,y);
//		entranceMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);

		x = MazeGlobal.sizeX+1;
		y = MazeGlobal.sizeY*2/3;
		MazeCell exitMazeCell = createCell(x,y);

		int startCellX = Integer.parseInt(controlPanel.startCellControl.tfStartCellX.getText());
		int startCellY = Integer.parseInt(controlPanel.startCellControl.tfStartCellY.getText());
		currentMazeCell = cells.get(MazeGlobal.ID(startCellX, startCellY, false));
		if(currentMazeCell != null)
		{
			currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
		}

		exitMazeCell.setType(MazeCell.CellType.eCellTypeExit);
		exitMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);

		entranceMazeCell.setVisited();
		exitMazeCell.setVisited();

		if(entranceMazeCell.facingWest() || entranceMazeCell.facingEast())
		{
			if(entranceMazeCell.W(MazeCell.west) != null)	entranceMazeCell.W(MazeCell.west).Open(true);
			if(entranceMazeCell.W(MazeCell.east) != null)	entranceMazeCell.W(MazeCell.east).Open(true);
		}
		else {
			if (entranceMazeCell.W(MazeCell.north) != null) entranceMazeCell.W(MazeCell.north).Open(true);
			if (entranceMazeCell.W(MazeCell.south) != null) entranceMazeCell.W(MazeCell.south).Open(true);
		}

		if(exitMazeCell.W(MazeCell.west) != null)	exitMazeCell.W(MazeCell.west).Open(true);
		if(exitMazeCell.W(MazeCell.east) != null)	exitMazeCell.W(MazeCell.east).Open(true);

//		for(int i=0; i<controlPanel.algorithmControl.algorithms.length; ++i) {
//			if (controlPanel.algorithmControl.cbAlgorithm.getValue().equals(controlPanel.algorithmControl.algorithms[i].title)) {
//				switch(controlPanel.algorithmControl.algorithms[i].index)
//				{
//					case 0:	recursiveBacktracker();	break;
//				}
//			}
//		}
		recursiveBacktracker(animate);

		drawMaze();
	}

	private void drawDoors3D(GraphicsContext gc, MazeCell cell, int x, double z, MazeCell.CellFacing facing)
	{
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

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
//		MazeMath.rectangle front = new MazeMath.rectangle(p000, p100, p110, p010);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		left = m.dot(left);
		back = m.dot(back);
		right = m.dot(right);
//		front = m.dot(front);

		double doorFrameSize = 15;
		left.p[0].y += doorFrameSize;
		left.p[1].y += doorFrameSize;
		left.p[0].z += doorFrameSize;
		left.p[1].z -= doorFrameSize;
		left.p[2].z -= doorFrameSize;
		left.p[3].z += doorFrameSize;

		right.p[0].y += doorFrameSize;
		right.p[1].y += doorFrameSize;
		right.p[0].z += doorFrameSize;
		right.p[1].z -= doorFrameSize;
		right.p[2].z -= doorFrameSize;
		right.p[3].z += doorFrameSize;

//		front.p[0].y += doorFrameSize;
//		front.p[1].y += doorFrameSize;

		back.p[0].y += doorFrameSize;
		back.p[1].y += doorFrameSize;
		back.p[0].x += doorFrameSize;
		back.p[1].x -= doorFrameSize;
		back.p[2].x -= doorFrameSize;
		back.p[3].x += doorFrameSize;

//		left.dump("left");
//		right.dump("right");
//		back.dump("back");
////		front.dump("front");

		left.perspective(500.0);
		back.perspective(500.0);
		right.perspective(500.0);
//		front.perspective(500.0);

		double xOffset = 500;
		double yOffset = 250;

		left.move(xOffset, yOffset);
		back.move(xOffset, yOffset);
		right.move(xOffset, yOffset);
//		front.move(xOffset, yOffset);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.LIGHTGRAY);

		if(facing == MazeCell.CellFacing.eCellFacingEast) {
			if (cell.W(MazeCell.east).Door()) back.draw(gc);
			if (cell.W(MazeCell.north).Door()) left.draw(gc);
			if (cell.W(MazeCell.south).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingWest) {
			if (cell.W(MazeCell.west).Door()) back.draw(gc);
			if (cell.W(MazeCell.south).Door()) left.draw(gc);
			if (cell.W(MazeCell.north).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingNorth) {
			if (cell.W(MazeCell.north).Door()) back.draw(gc);
			if (cell.W(MazeCell.west).Door()) left.draw(gc);
			if (cell.W(MazeCell.east).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingSouth) {
			if (cell.W(MazeCell.south).Door()) back.draw(gc);
			if (cell.W(MazeCell.east).Door()) left.draw(gc);
			if (cell.W(MazeCell.west).Door()) right.draw(gc);
		}
	}

	private void drawCell3D(GraphicsContext gc, MazeCell cell, int x, double z, MazeCell.CellFacing facing)
	{
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

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
//		MazeMath.rectangle front = new MazeMath.rectangle(p000, p100, p110, p010);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		left = m.dot(left);
		back = m.dot(back);
		right = m.dot(right);
//		front = m.dot(front);

		left.perspective(500.0);
		back.perspective(500.0);
		right.perspective(500.0);
//		front.perspective(500.0);

		double xOffset = 500;
		double yOffset = 250;

		left.move(xOffset, yOffset);
		back.move(xOffset, yOffset);
		right.move(xOffset, yOffset);
//		front.move(xOffset, yOffset);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.WHITE);

		if(facing == MazeCell.CellFacing.eCellFacingEast) {
			if (!cell.W(MazeCell.east).Open() || cell.W(MazeCell.east).Door()) back.draw(gc);
			if (!cell.W(MazeCell.north).Open() || cell.W(MazeCell.north).Door()) left.draw(gc);
			if (!cell.W(MazeCell.south).Open() || cell.W(MazeCell.south).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingWest) {
			if (!cell.W(MazeCell.west).Open() || cell.W(MazeCell.west).Door()) back.draw(gc);
			if (!cell.W(MazeCell.south).Open() || cell.W(MazeCell.south).Door()) left.draw(gc);
			if (!cell.W(MazeCell.north).Open() ||cell.W(MazeCell.north).Door() ) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingNorth) {
			if (!cell.W(MazeCell.north).Open() || cell.W(MazeCell.north).Door()) back.draw(gc);
			if (!cell.W(MazeCell.west).Open() || cell.W(MazeCell.west).Door()) left.draw(gc);
			if (!cell.W(MazeCell.east).Open() || cell.W(MazeCell.east).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingSouth) {
			if (!cell.W(MazeCell.south).Open() || cell.W(MazeCell.south).Door()) back.draw(gc);
			if (!cell.W(MazeCell.east).Open() || cell.W(MazeCell.east).Door()) left.draw(gc);
			if (!cell.W(MazeCell.west).Open() || cell.W(MazeCell.west).Door()) right.draw(gc);
		}

		drawDoors3D(gc, cell, x, z, facing);
	}

	private void drawMaze3D(GraphicsContext gc) {
		if(!MazeGlobal.maze3Ddisplay) return;
		if(entranceMazeCell != null) {

			int index[] = {-2,-1,2,1,0};

			int x = currentMazeCell.X();
			int y = currentMazeCell.Y();

			MazeCell cell = null;
			for(int j=5; j>=0; j--) {
				for (int i = 0; i < 5; i++) {
					if(currentMazeCell.facingEast()) {
//						int cx = x + j;
//						int cy = y + index[i];
						cell = cells.get(MazeGlobal.ID(x + j, y + index[i], false));
					}
					else if(currentMazeCell.facingWest()) {
//						int cx = x - j;
//						int cy = y + index[i];
						cell = cells.get(MazeGlobal.ID(x - j, y - index[i], false));
					}
					else if(currentMazeCell.facingSouth()) {
//						int cx = x + index[i];
//						int cy = y + j;
						cell = cells.get(MazeGlobal.ID(x  - index[i], y + j, false));
					}
					else if(currentMazeCell.facingNorth()) {
//						int cx = x + index[i];
//						int cy = y - j;
						cell = cells.get(MazeGlobal.ID(index[i] + x, y - j, false));
					}

					if(cell != null) {
						drawCell3D(gc, cell, index[i], j+1, currentMazeCell.direction());
					}
				}
			}
		}
	}

	private void drawMaze2D(GraphicsContext gc) {
		if(!MazeGlobal.maze2Ddisplay) return;

		Collection<MazeCell> cc = cells.values();
		for(MazeCell c: cc)
		{
			c.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}

		Collection<MazeWall> wc = walls.values();
		for(MazeWall w: wc)
		{
			w.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}
	}

	public void drawMaze() {
		GraphicsContext gc = getGraphicsContext2D();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 1000, 1000);

		drawMaze3D(gc);
		drawMaze2D(gc);
	}

	void turnLeft()
	{
		if(currentMazeCell.facingNorth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(currentMazeCell.facingWest())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(currentMazeCell.facingSouth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		else if(currentMazeCell.facingEast())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		drawMaze();
	}

	void turnRight()
	{
		if(currentMazeCell.facingNorth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		else if(currentMazeCell.facingEast())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(currentMazeCell.facingSouth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(currentMazeCell.facingWest())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		drawMaze();
	}

	void stepForward()
	{
		int x = currentMazeCell.X();
		int y = currentMazeCell.Y();

		if(currentMazeCell.facingNorth() && !currentMazeCell.W(MazeCell.north).Open())	return;
		if(currentMazeCell.facingSouth() && !currentMazeCell.W(MazeCell.south).Open())	return;
		if(currentMazeCell.facingEast() && !currentMazeCell.W(MazeCell.east).Open())	return;
		if(currentMazeCell.facingWest() && !currentMazeCell.W(MazeCell.west).Open())	return;

		GraphicsContext gc = getGraphicsContext2D();

		if(currentMazeCell.facingNorth()) {
			y--;

			currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingEast()) {
			x++;

			currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingSouth()) {
			y++;

			currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(currentMazeCell.facingWest()) {
			x--;

			currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			currentMazeCell = cells.get(MazeGlobal.ID(x,y,false));
			if(currentMazeCell != null)
			{
				currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
				currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}

		drawMaze();
	}

	void turnAround()
	{
		if(currentMazeCell.facingNorth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(currentMazeCell.facingEast())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(currentMazeCell.facingSouth())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		else if(currentMazeCell.facingWest())	currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		drawMaze();
	}

	private void createMazeStep(boolean redrawMaze)
	{
		int x = currentMazeCell.X();
		int y = currentMazeCell.Y();

		currentMazeCell.setVisited();

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

		List<Group3> cells = new ArrayList<>();

		if (westMazeCell != null && !westMazeCell.Visited())		cells.add(new Group3(westMazeCell, currentMazeCell.W(MazeCell.west)));
		if (northMazeCell != null && !northMazeCell.Visited())		cells.add(new Group3(northMazeCell, currentMazeCell.W(MazeCell.north)));
		if (eastMazeCell != null && !eastMazeCell.Visited())		cells.add(new Group3(eastMazeCell, currentMazeCell.W(MazeCell.east)));
		if (southMazeCell != null && !southMazeCell.Visited())		cells.add(new Group3(southMazeCell, currentMazeCell.W(MazeCell.south)));

		if (cells.size() > 0) {
			int r = controlPanel.rand.nextInt(cells.size());

			cells.get(r).wall.Open(true);
			currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
			currentMazeCell = cells.get(r).cell;
			currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			currentMazeCell.setVisited();
			stack.push(currentMazeCell);
		} else {
			currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
			currentMazeCell = stack.pop();

			if (stack.size() > 0)
				currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			else
				currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
		}

		if(redrawMaze) drawMaze();

		if(stack.size() == 0) {
			timeline.stop();
			currentMazeCell = entranceMazeCell;
			drawMaze();
		}
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

	public void createRoom(int roomOriginX, int roomOriginY, int roomSizeX, int roomSizeY)
	{
		System.out.println("createRoom: " + roomOriginX + ",  " + roomOriginY + ", " + roomSizeX + ", " + roomSizeY);
		int right = roomOriginX + roomSizeX - 1;
		int bottom = roomOriginY + roomSizeY - 1;

		for(int x=roomOriginX; x<=right; x++) {
			for(int y=roomOriginY; y<=bottom; y++) {
				MazeCell cell = this.cells.get(MazeGlobal.ID(x,y,false));
				if(cell != null) {
					cell.setType(MazeCell.CellType.eCellTypeRoom);

					if(x != right) cell.W(MazeCell.east).Open(true);
					if(y != bottom) cell.W(MazeCell.south).Open(true);

					if(y == roomOriginY) {
						if(cell.W(MazeCell.north) != null)
							if(cell.W(MazeCell.north).Open())
								cell.W(MazeCell.north).setDoor();
					}

					if(y == bottom) {
						if(cell.W(MazeCell.south) != null)
							if(cell.W(MazeCell.south).Open())
								cell.W(MazeCell.south).setDoor();
					}

					if(x == roomOriginX) {
						if(cell.W(MazeCell.west) != null)
							if(cell.W(MazeCell.west).Open())
								cell.W(MazeCell.west).setDoor();
					}

					if(x == right) {
						if(cell.W(MazeCell.east) != null)
							if(cell.W(MazeCell.east).Open())
								cell.W(MazeCell.east).setDoor();
					}
				}
			}
		}

		drawMaze();
	}
}
