package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze2DCell {
	private int	xOrigin = 0;
	private int yOrigin = 0;

	public int X() { return xOrigin; }
	public int Y() { return yOrigin; }

	private Maze2DWall[] maze2DWalls = new Maze2DWall[4];
	private boolean visited = false;

	public boolean Visited() { return visited; }
	public void Visited(boolean _visited) { visited = _visited; }

	public static int	west = 0;
	public static int	north = 1;
	public static int	east = 2;
	public static int	south = 3;

	public Maze2DWall W(int which)
	{
		return maze2DWalls[which];
	}

	public static enum CellType
	{
		eNormal,
		eCellTypeStart,
		eCellTypeEntrance,
		eCellTypeExit
	}

	private CellType type = CellType.eNormal;

	public void SetType( CellType _type )
	{
		type = _type;
	}

	public Maze2DCell(int _xOrigin, int _yOrigin, Maze2DWall west, Maze2DWall north, Maze2DWall east, Maze2DWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		maze2DWalls[0] = west;
		maze2DWalls[1] = north;
		maze2DWalls[2] = east;
		maze2DWalls[3] = south;
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

			double xpoints[] = {
				x + 1 * scale,
				x + 3 * scale,
				x + 3 * scale,
				x + 5 * scale,
				x + 3 * scale,
				x + 3 * scale,
				x + 1 * scale,
				x + 1 * scale
			};

			double ypoints[] = {
				y + 2 * scale,
				y + 2 * scale,
				y + 1 * scale,
				y + 3 * scale,
				y + 5 * scale,
				y + 4 * scale,
				y + 4 * scale,
				y + 2 * scale
			};

			if(type == CellType.eCellTypeStart)
			{
				gc.setFill(Color.YELLOW);
				gc.fillRect(x, y, cellSize, cellSize);
			}

			else if(type == CellType.eCellTypeEntrance)
			{
				gc.setFill((Color.RED));
				gc.fillPolygon( xpoints, ypoints, 8);
			}

			else if(type == CellType.eCellTypeExit)
			{
				gc.setFill((Color.RED));
				gc.fillPolygon( xpoints, ypoints, 8);
			}
			else
			{
				gc.setFill(Color.WHITE);
				gc.fillRect(x, y, cellSize, cellSize);
			}
		}
	}
}
