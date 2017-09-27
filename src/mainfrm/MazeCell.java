package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class MazeCell {
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

	public enum CellType
	{
		eNormal,
		eCellTypeStart,
		eCellTypeEntrance,
		eCellTypeExit,
		eCellFacingEast,
		eCellFacingWest,
		eCellFacingNorth,
		eCellFacingSouth
	}

	private CellType type = CellType.eNormal;

	boolean facingNorth() { return type == CellType.eCellFacingNorth; }// ? true : false; }
	boolean facingSouth() { return type == CellType.eCellFacingSouth; }// ? true : false; }
	boolean facingEast() { return type == CellType.eCellFacingEast; }// ? true : false; }
	boolean facingWest() { return type == CellType.eCellFacingWest; }// ? true : false; }
	CellType direction() { return type; }

	void SetType( CellType _type )
	{
		type = _type;
	}

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
			gc.fillRect(x, y, cellSize, cellSize);
		} else if (type == CellType.eCellTypeEntrance) {
			gc.setFill((Color.RED));
			gc.fillPolygon(xpointsEast, ypointsEast, 8);
		} else if (type == CellType.eCellTypeExit) {
			gc.setFill((Color.RED));
			gc.fillPolygon(xpointsEast, ypointsEast, 8);
		} else if (type == CellType.eCellFacingEast) {
			gc.setFill((Color.GREEN));
			gc.fillPolygon(xpointsEast, ypointsEast, 8);
		} else if (type == CellType.eCellFacingNorth) {
			gc.setFill((Color.GREEN));
			gc.fillPolygon(xpointsNorth, ypointsNorth, 8);
		} else if (type == CellType.eCellFacingSouth) {
			gc.setFill((Color.GREEN));
			gc.fillPolygon(xpointsSouth, ypointsSouth, 8);
		} else if (type == CellType.eCellFacingWest) {
			gc.setFill((Color.GREEN));
			gc.fillPolygon(xpointsWest, ypointsWest, 8);
		} else {
			gc.setFill(Color.WHITE);
			gc.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
		}
	}
}
