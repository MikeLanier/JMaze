package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCell {
	private int	xOrigin = 0;
	private int yOrigin = 0;

	public int X() { return xOrigin; }
	public int Y() { return yOrigin; }

	private MazeWall[] mazeWalls = new MazeWall[4];
	private boolean visited = false;

	public boolean Visited() { return visited; }
	public void Visited(boolean _visited) { visited = _visited; }

	public static int	west = 0;
	public static int	north = 1;
	public static int	east = 2;
	public static int	south = 3;

	public MazeWall W(int which)
	{
		return mazeWalls[which];
	}

	public static enum CellType
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

	public boolean facingNorth() { return type == CellType.eCellFacingNorth ? true : false; }
	public boolean facingSouth() { return type == CellType.eCellFacingSouth ? true : false; }
	public boolean facingEast() { return type == CellType.eCellFacingEast ? true : false; }
	public boolean facingWest() { return type == CellType.eCellFacingWest ? true : false; }
	public CellType direction() { return type; }

	public void SetType( CellType _type )
	{
		type = _type;
	}

	public MazeCell(int _xOrigin, int _yOrigin, MazeWall west, MazeWall north, MazeWall east, MazeWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		mazeWalls[0] = west;
		mazeWalls[1] = north;
		mazeWalls[2] = east;
		mazeWalls[3] = south;
	}

	public Integer ID()
	{
		return MazeGlobal.ID(xOrigin, yOrigin, false);
	}

	public void draw(GraphicsContext gc, int xOffset, int yOffset, int cellSize)
	{
//		if(type != CellType.eNormal)
		{
			double x = (double) (xOffset + xOrigin * cellSize);
			double y = (double) (yOffset + yOrigin * cellSize);

			double scale = ((double)cellSize) / 6.0;

			double xpointsEast[] = {
				x + 1 * scale,		x + 3 * scale,		x + 3 * scale,		x + 5 * scale,
				x + 3 * scale,		x + 3 * scale,		x + 1 * scale,		x + 1 * scale
			};

			double ypointsEast[] = {
				y + 2 * scale,		y + 2 * scale,		y + 1 * scale,		y + 3 * scale,
				y + 5 * scale,		y + 4 * scale,		y + 4 * scale,		y + 2 * scale
			};

			double xpointsSouth[] = {
					x + 2 * scale,		x + 2 * scale,		x + 1 * scale,		x + 3 * scale,
					x + 5 * scale,		x + 4 * scale,		x + 4 * scale,		x + 2 * scale
			};

			double ypointsSouth[] = {
					y + 1 * scale,		y + 3 * scale,		y + 3 * scale,		y + 5 * scale,
					y + 3 * scale,		y + 3 * scale,		y + 1 * scale,		y + 1 * scale
			};

			double xpointsWest[] = {
					x + 5 * scale,		x + 3 * scale,		x + 3 * scale,		x + 1 * scale,
					x + 3 * scale,		x + 3 * scale,		x + 5 * scale,		x + 5 * scale
			};

			double ypointsWest[] = {
					y + 2 * scale,		y + 2 * scale,		y + 1 * scale,		y + 3 * scale,
					y + 5 * scale,		y + 4 * scale,		y + 4 * scale,		y + 2 * scale
			};

			double xpointsNorth[] = {
					x + 2 * scale,		x + 2 * scale,		x + 1 * scale,		x + 3 * scale,
					x + 5 * scale,		x + 4 * scale,		x + 4 * scale,		x + 2 * scale
			};

			double ypointsNorth[] = {
					y + 5 * scale,		y + 3 * scale,		y + 3 * scale,		y + 1 * scale,
					y + 3 * scale,		y + 3 * scale,		y + 5 * scale,		y + 5 * scale
			};

			gc.setFill(Color.WHITE);
			gc.fillRect(x+1, y+1, cellSize-2, cellSize-2);

			if(type == CellType.eCellTypeStart)
			{
				gc.setFill(Color.YELLOW);
				gc.fillRect(x, y, cellSize, cellSize);
			}

			else if(type == CellType.eCellTypeEntrance)
			{
				gc.setFill((Color.RED));
				gc.fillPolygon( xpointsEast, ypointsEast, 8);
			}

			else if(type == CellType.eCellTypeExit)
			{
				gc.setFill((Color.RED));
				gc.fillPolygon( xpointsEast, ypointsEast, 8);
			}

			else if(type == CellType.eCellFacingEast)
			{
				gc.setFill((Color.GREEN));
				gc.fillPolygon( xpointsEast, ypointsEast, 8);
			}

			else if(type == CellType.eCellFacingNorth)
			{
				gc.setFill((Color.GREEN));
				gc.fillPolygon( xpointsNorth, ypointsNorth, 8);
			}

			else if(type == CellType.eCellFacingSouth)
			{
				gc.setFill((Color.GREEN));
				gc.fillPolygon( xpointsSouth, ypointsSouth, 8);
			}

			else if(type == CellType.eCellFacingWest)
			{
				gc.setFill((Color.GREEN));
				gc.fillPolygon( xpointsWest, ypointsWest, 8);
			}

			else
			{
				gc.setFill(Color.WHITE);
				gc.fillRect(x+1, y+1, cellSize-2, cellSize-2);
			}
		}
	}
}
