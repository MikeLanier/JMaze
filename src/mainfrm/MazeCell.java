package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCell {
	private int	xOrigin = 0;
	private int yOrigin = 0;

	int X() { return xOrigin; }
	int Y() { return yOrigin; }

	private MazeWall[] mazeWalls = new MazeWall[4];
	private boolean visited = false;

	boolean Visited() { return visited; }
	void setVisited() { visited = true; }

	static int	west = 0;
	static int	north = 1;
	static int	east = 2;
	static int	south = 3;

	MazeWall W(int which)
	{
		return mazeWalls[which];
	}

	public enum CellType {
		eCellTypeNormal,
		eCellTypeStart,
		eCellTypeEntrance,
		eCellTypeExit,
		eCellTypeRoom,
		eCellTypeStairsDown,
		eCellTypeStairsUp
	}

	public enum CellFacing {
		eCellFacingNone,
		eCellFacingEast,
		eCellFacingWest,
		eCellFacingNorth,
		eCellFacingSouth
	}

	private CellType type = CellType.eCellTypeNormal;
	private CellFacing facing = CellFacing.eCellFacingNone;

	boolean facingNorth() { return facing == CellFacing.eCellFacingNorth; }// ? true : false; }
	boolean facingSouth() { return facing == CellFacing.eCellFacingSouth; }// ? true : false; }
	boolean facingEast() { return facing == CellFacing.eCellFacingEast; }// ? true : false; }
	boolean facingWest() { return facing == CellFacing.eCellFacingWest; }// ? true : false; }
	boolean facingNone() { return facing == CellFacing.eCellFacingNone; }// ? true : false; }

	CellFacing direction() { return facing; }

	public void setType( CellType _type ) { type = _type; }
	public void setFacing( CellFacing _facing ) { facing = _facing; }

	MazeCell(int _xOrigin, int _yOrigin, MazeWall west, MazeWall north, MazeWall east, MazeWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		mazeWalls[0] = west;
		mazeWalls[1] = north;
		mazeWalls[2] = east;
		mazeWalls[3] = south;
	}

	Integer ID()
	{
		return MazeGlobal.ID(xOrigin, yOrigin, false);
	}

	void draw(GraphicsContext gc, int xOffset, int yOffset, int cellSize) {
		double x = (double) (xOffset + xOrigin * cellSize);
		double y = (double) (yOffset + yOrigin * cellSize);

		double scale = ((double) cellSize) / 6.0;

		double xpointsEast[] = {
				x + 1 * scale, x + 3 * scale, x + 3 * scale, x + 5 * scale,
				x + 3 * scale, x + 3 * scale, x + 1 * scale, x + 1 * scale
		};

		double ypointsEast[] = {
				y + 2 * scale, y + 2 * scale, y + 1 * scale, y + 3 * scale,
				y + 5 * scale, y + 4 * scale, y + 4 * scale, y + 2 * scale
		};

		double xpointsSouth[] = {
				x + 2 * scale, x + 2 * scale, x + 1 * scale, x + 3 * scale,
				x + 5 * scale, x + 4 * scale, x + 4 * scale, x + 2 * scale
		};

		double ypointsSouth[] = {
				y + 1 * scale, y + 3 * scale, y + 3 * scale, y + 5 * scale,
				y + 3 * scale, y + 3 * scale, y + 1 * scale, y + 1 * scale
		};

		double xpointsWest[] = {
				x + 5 * scale, x + 3 * scale, x + 3 * scale, x + 1 * scale,
				x + 3 * scale, x + 3 * scale, x + 5 * scale, x + 5 * scale
		};

		double ypointsWest[] = {
				y + 2 * scale, y + 2 * scale, y + 1 * scale, y + 3 * scale,
				y + 5 * scale, y + 4 * scale, y + 4 * scale, y + 2 * scale
		};

		double xpointsNorth[] = {
				x + 2 * scale, x + 2 * scale, x + 1 * scale, x + 3 * scale,
				x + 5 * scale, x + 4 * scale, x + 4 * scale, x + 2 * scale
		};

		double ypointsNorth[] = {
				y + 5 * scale, y + 3 * scale, y + 3 * scale, y + 1 * scale,
				y + 3 * scale, y + 3 * scale, y + 5 * scale, y + 5 * scale
		};

		gc.setFill(Color.WHITE);
		gc.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);

		if (type == CellType.eCellTypeStart) {
			gc.setFill(Color.YELLOW);
		} else if (type == CellType.eCellTypeRoom) {
			gc.setFill(Color.WHITE);
		} else if (type == CellType.eCellTypeEntrance) {
			gc.setFill((Color.LIGHTGREEN));
		} else if (type == CellType.eCellTypeExit) {
			gc.setFill((Color.LIGHTPINK));
		} else {
			gc.setFill(Color.WHITE);
		}
		gc.fillRect(x, y, cellSize, cellSize);

		if(!facingNone()) {
			if (type == CellType.eCellTypeEntrance) {
				gc.setFill((Color.RED));
			} else if (type == CellType.eCellTypeExit) {
				gc.setFill((Color.RED));
			} else {
				gc.setFill((Color.GREEN));
			}

			if (facingEast()) {
				gc.fillPolygon(xpointsEast, ypointsEast, 8);
			} else if (facingNorth()) {
				gc.fillPolygon(xpointsNorth, ypointsNorth, 8);
			} else if (facingSouth()) {
				gc.fillPolygon(xpointsSouth, ypointsSouth, 8);
			} else if (facingWest()) {
				gc.fillPolygon(xpointsWest, ypointsWest, 8);
			}
		}
		else {
			if(type == CellType.eCellTypeStairsDown ||
				type == CellType.eCellTypeStairsUp) {
//				gc.fillRect(x, y, cellSize, cellSize);

				gc.setStroke(Color.BLACK);
				int s = cellSize / 4;
				for(int i=0; i<cellSize; i+=s)
				{
					gc.strokeLine(x+i, y, x+i, y+cellSize);
				}
			}
		}
	}
}
