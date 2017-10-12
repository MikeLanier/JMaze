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
		MazeGlobal.cells = new HashMap<>();
		MazeGlobal.walls = new HashMap<>();
		buildMazePanel();
	}

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
		MazeWall west = MazeGlobal.walls.get(MazeGlobal.ID(x, y, false));
		if(west == null)
		{
			west = new MazeWall(x, y, false);
			MazeGlobal.walls.put(west.ID(), west);
		}

		MazeWall north = MazeGlobal.walls.get(MazeGlobal.ID(x, y, true));
		if(north == null)
		{
			north = new MazeWall(x, y, true);
			MazeGlobal.walls.put(north.ID(), north);
		}

		MazeWall east = MazeGlobal.walls.get(MazeGlobal.ID(x+1, y, false));
		if(east == null)
		{
			east = new MazeWall(x+1, y, false);
			MazeGlobal.walls.put(east.ID(), east);
		}

		MazeWall south = MazeGlobal.walls.get(MazeGlobal.ID(x, y+1, true));
		if(south == null)
		{
			south = new MazeWall(x, y+1, true);
			MazeGlobal.walls.put(south.ID(), south);
		}

		MazeCell mazeCell = new MazeCell(x, y, west, north, east, south);
		MazeGlobal.cells.put(mazeCell.ID(), mazeCell);

		return mazeCell;
	}

	private Stack<MazeCell> stack = null;

	public void updateStartCell() {
		int startCellX = MazeGlobal.startCellX;
		int startCellY = MazeGlobal.startCellY;

		if(MazeGlobal.currentMazeCell != null)
		{
			MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
		}

		MazeGlobal.currentMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(startCellX, startCellY, false));
		if(MazeGlobal.currentMazeCell != null)
		{
			MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
		}
	}

	public void deleteEntranceExitCell(MazeCell cell) {
		MazeGlobal.walls.remove(cell.W(MazeCell.north).ID());
		MazeGlobal.walls.remove(cell.W(MazeCell.south).ID());
		MazeGlobal.walls.remove(cell.W(MazeCell.east).ID());
		cell.W(MazeCell.west).Open(false);
		MazeGlobal.cells.remove(cell.ID());
	}

	public MazeCell createEntranceExitCell(Integer x, Integer y, MazeCell.CellType type) {
		System.out.println("createEntranceExitCell: x, y: " + x + ", " + y);
		MazeCell cell = null;

		cell = createCell(x,y);
		cell.setType(type);
		cell.setVisited();

		int west = 0;
		int east = MazeGlobal.sizeX + 1;
		int north = 0;
		int south = MazeGlobal.sizeY + 1;

		// is the cell on the west side?
		if(x == west && type == MazeCell.CellType.eCellTypeEntrance) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		}
		else if(x == west && type == MazeCell.CellType.eCellTypeExit) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		}
		else if(x == east && type == MazeCell.CellType.eCellTypeEntrance) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		}
		else if(x == east && type == MazeCell.CellType.eCellTypeExit) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		}
		else if(y == north && type == MazeCell.CellType.eCellTypeEntrance) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		}
		else if(y == north && type == MazeCell.CellType.eCellTypeExit) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		}
		else if(y == south && type == MazeCell.CellType.eCellTypeEntrance) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		}
		else if(y == south && type == MazeCell.CellType.eCellTypeExit) {
			cell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		}

		if(cell.facingWest() || cell.facingEast())
		{
			if(cell.W(MazeCell.west) != null)	cell.W(MazeCell.west).Open(true);
			if(cell.W(MazeCell.east) != null)	cell.W(MazeCell.east).Open(true);
		}
		else {
			if (cell.W(MazeCell.north) != null) cell.W(MazeCell.north).Open(true);
			if (cell.W(MazeCell.south) != null) cell.W(MazeCell.south).Open(true);
		}

		return cell;
	}

	public void createStairCell(MazeCell cell, MazeCell.CellType type) {
		int x = cell.X();
		int y = cell.Y();
		System.out.println("cell: x, y: " + x + ", " + y);

		if(x == 0)  // on the west size
		{
			x = x - 1;
		}
		if(x == (MazeGlobal.sizeX+1))  // on the west size
		{
			x = x + 1;
		}
		MazeCell stair = null;

		stair = createCell(x,y);
		stair.setType(type);
		stair.setVisited();
	}

	public void createMaze(boolean animate)
	{
		MazeGlobal.sizeX = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeX.getText());
		MazeGlobal.sizeY = Integer.parseInt(controlPanel.mazeSizeControl.tfMazeSizeY.getText());
		MazeGlobal.sizeCell = Integer.parseInt(controlPanel.cellSizeControl.tfCellSize.getText());

		MazeGlobal.cells = new HashMap<>();
		MazeGlobal.walls = new HashMap<>();

		buildMazePanel();

		Integer x = 0;
		Integer y = MazeGlobal.sizeY/3;

		MazeGlobal.entranceMazeCell = createEntranceExitCell(MazeGlobal.entranceCellX, MazeGlobal.entranceCellY, MazeCell.CellType.eCellTypeEntrance);
		MazeGlobal.exitMazeCell = createEntranceExitCell(MazeGlobal.exitCellX, MazeGlobal.exitCellY, MazeCell.CellType.eCellTypeExit);

//		createStairCell(MazeGlobal.entranceMazeCell, MazeCell.CellType.eCellTypeStairsUp);
//		createStairCell(MazeGlobal.exitMazeCell, MazeCell.CellType.eCellTypeStairsDown);

		updateStartCell();

		recursiveBacktracker(animate);

		drawMaze();
	}

	private void drawMaze3D(GraphicsContext gc) {
		if(!MazeGlobal.maze3Ddisplay) return;
		if(MazeGlobal.entranceMazeCell != null) {

			int index[] = {-2,-1,2,1,0};

			int x = MazeGlobal.currentMazeCell.X();
			int y = MazeGlobal.currentMazeCell.Y();

			MazeCell cell = null;
			for(int j=5; j>=0; j--) {
				for (int i = 0; i < 5; i++) {
					if(MazeGlobal.currentMazeCell.facingEast()) {
						cell = MazeGlobal.cells.get(MazeGlobal.ID(x + j, y + index[i], false));
					}
					else if(MazeGlobal.currentMazeCell.facingWest()) {
						cell = MazeGlobal.cells.get(MazeGlobal.ID(x - j, y - index[i], false));
					}
					else if(MazeGlobal.currentMazeCell.facingSouth()) {
						cell = MazeGlobal.cells.get(MazeGlobal.ID(x  - index[i], y + j, false));
					}
					else if(MazeGlobal.currentMazeCell.facingNorth()) {
						cell = MazeGlobal.cells.get(MazeGlobal.ID(index[i] + x, y - j, false));
					}

					if(cell != null) {
						cell.draw3D(gc, index[i], j+1, MazeGlobal.currentMazeCell.direction());
//						drawCell3D(gc, cell, index[i], j+1, MazeGlobal.currentMazeCell.direction());
					}
				}
			}
		}
	}

	private void drawMaze2D(GraphicsContext gc) {
		if(!MazeGlobal.maze2Ddisplay) return;

		Collection<MazeCell> cc = MazeGlobal.cells.values();
		for(MazeCell c: cc)
		{
			c.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
		}

		Collection<MazeWall> wc = MazeGlobal.walls.values();
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
		if(MazeGlobal.currentMazeCell.facingNorth())		MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(MazeGlobal.currentMazeCell.facingWest())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(MazeGlobal.currentMazeCell.facingSouth())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		else if(MazeGlobal.currentMazeCell.facingEast())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		drawMaze();
	}

	void turnRight()
	{
		if(MazeGlobal.currentMazeCell.facingNorth())		MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		else if(MazeGlobal.currentMazeCell.facingEast())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(MazeGlobal.currentMazeCell.facingSouth())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(MazeGlobal.currentMazeCell.facingWest())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		drawMaze();
	}

	void stepForward()
	{
		int x = MazeGlobal.currentMazeCell.X();
		int y = MazeGlobal.currentMazeCell.Y();

		if(MazeGlobal.currentMazeCell.facingNorth() && !MazeGlobal.currentMazeCell.W(MazeCell.north).Open())	return;
		if(MazeGlobal.currentMazeCell.facingSouth() && !MazeGlobal.currentMazeCell.W(MazeCell.south).Open())	return;
		if(MazeGlobal.currentMazeCell.facingEast() && !MazeGlobal.currentMazeCell.W(MazeCell.east).Open())	return;
		if(MazeGlobal.currentMazeCell.facingWest() && !MazeGlobal.currentMazeCell.W(MazeCell.west).Open())	return;

		GraphicsContext gc = getGraphicsContext2D();

		if(MazeGlobal.currentMazeCell.facingNorth()) {
			y--;

			MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			MazeGlobal.currentMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x,y,false));
			if(MazeGlobal.currentMazeCell != null)
			{
				MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
				MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(MazeGlobal.currentMazeCell.facingEast()) {
			x++;

			MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			MazeGlobal.currentMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x,y,false));
			if(MazeGlobal.currentMazeCell != null)
			{
				MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
				MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(MazeGlobal.currentMazeCell.facingSouth()) {
			y++;

			MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			MazeGlobal.currentMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x,y,false));
			if(MazeGlobal.currentMazeCell != null)
			{
				MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
				MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}
		else if(MazeGlobal.currentMazeCell.facingWest()) {
			x--;

			MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNone);
			MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			MazeGlobal.currentMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x,y,false));
			if(MazeGlobal.currentMazeCell != null)
			{
				MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
				MazeGlobal.currentMazeCell.draw(gc, xOffset, yOffset, MazeGlobal.sizeCell);
			}
		}

		drawMaze();
	}

	void turnAround()
	{
		if(MazeGlobal.currentMazeCell.facingNorth())		MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingSouth);
		else if(MazeGlobal.currentMazeCell.facingEast())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingWest);
		else if(MazeGlobal.currentMazeCell.facingSouth())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingNorth);
		else if(MazeGlobal.currentMazeCell.facingWest())	MazeGlobal.currentMazeCell.setFacing(MazeCell.CellFacing.eCellFacingEast);
		drawMaze();
	}

	private void createMazeStep(boolean redrawMaze)
	{
		int x = MazeGlobal.currentMazeCell.X();
		int y = MazeGlobal.currentMazeCell.Y();

		MazeGlobal.currentMazeCell.setVisited();

		MazeCell westMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x - 1, y, false));
		MazeCell northMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x, y - 1, false));
		MazeCell eastMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x + 1, y, false));
		MazeCell southMazeCell = MazeGlobal.cells.get(MazeGlobal.ID(x, y + 1, false));

		class Group3 {
			private MazeCell cell;
			private MazeWall wall;

			private Group3(MazeCell _cell, MazeWall _wall) {
				cell = _cell;
				wall = _wall;
			}
		}

		List<Group3> cells = new ArrayList<>();

		if (westMazeCell != null && !westMazeCell.Visited())		cells.add(new Group3(westMazeCell, MazeGlobal.currentMazeCell.W(MazeCell.west)));
		if (northMazeCell != null && !northMazeCell.Visited())		cells.add(new Group3(northMazeCell, MazeGlobal.currentMazeCell.W(MazeCell.north)));
		if (eastMazeCell != null && !eastMazeCell.Visited())		cells.add(new Group3(eastMazeCell, MazeGlobal.currentMazeCell.W(MazeCell.east)));
		if (southMazeCell != null && !southMazeCell.Visited())		cells.add(new Group3(southMazeCell, MazeGlobal.currentMazeCell.W(MazeCell.south)));

		if (cells.size() > 0) {
			int r = MazeGlobal.rand.nextInt(cells.size());

			cells.get(r).wall.Open(true);
			MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
			MazeGlobal.currentMazeCell = cells.get(r).cell;
			MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			MazeGlobal.currentMazeCell.setVisited();
			stack.push(MazeGlobal.currentMazeCell);
		} else {
			MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
			MazeGlobal.currentMazeCell = stack.pop();

			if (stack.size() > 0)
				MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeStart);
			else
				MazeGlobal.currentMazeCell.setType(MazeCell.CellType.eCellTypeNormal);
		}

		if(redrawMaze) drawMaze();

		if(stack.size() == 0) {
			timeline.stop();
			MazeGlobal.currentMazeCell = MazeGlobal.entranceMazeCell;
			drawMaze();
		}
	}

	private Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(30),
			ae -> createMazeStep(true)));

	private void recursiveBacktracker(boolean animate) {
		System.out.println("recursiveBacktracker");

		if (MazeGlobal.currentMazeCell != null) {
			if (stack == null) {
				stack = new Stack<MazeCell>();
			}
			stack.push(MazeGlobal.currentMazeCell);

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
				MazeCell cell = MazeGlobal.cells.get(MazeGlobal.ID(x,y,false));
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
