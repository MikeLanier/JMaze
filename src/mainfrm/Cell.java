package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cell {
	private int	xOrigin = 0;
	private int yOrigin = 0;

	public int X() { return xOrigin; }
	public int Y() { return yOrigin; }

	private Wall[] walls = new Wall[4];
	private boolean visited = false;

	public boolean Visited() { return visited; }
	public void Visited(boolean _visited) { visited = _visited; }

	public static int	west = 0;
	public static int	north = 1;
	public static int	east = 2;
	public static int	south = 3;

	public Wall	W(int which)
	{
		return walls[which];
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

	public Cell(int _xOrigin, int _yOrigin, Wall west, Wall north, Wall east, Wall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		walls[0] = west;
		walls[1] = north;
		walls[2] = east;
		walls[3] = south;
	}

	public Integer ID()
	{
		int _id = (xOrigin & 0x7fff) | ((yOrigin & 0x7fff) << 15);
		return new Integer(_id);
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
