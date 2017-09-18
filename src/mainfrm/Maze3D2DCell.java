package mainfrm;

public class Maze3D2DCell {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private int zOrigin = 0;

	public int X() { return xOrigin; }
	public int Y() { return yOrigin; }
	public int Z() { return zOrigin; }

	private Maze3D2DWall[] mazeCellWalls = new Maze3D2DWall[4];
	private boolean visited = false;

	public boolean Visited() { return visited; }
	public void Visited(boolean _visited) { visited = _visited; }

	public static int	west = 0;
	public static int	north = 1;
	public static int	east = 2;
	public static int	south = 3;

	public Maze3D2DWall W(int which)
	{
		return mazeCellWalls[which];
	}

	private Maze2DCell.CellType type = Maze2DCell.CellType.eNormal;

	public void SetType( Maze2DCell.CellType _type )
	{
		type = _type;
	}

	public Maze3D2DCell(int _xOrigin, int _yOrigin, int _zOrigin, Maze3D2DWall west, Maze3D2DWall north, Maze3D2DWall east, Maze3D2DWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		zOrigin = _zOrigin;
		mazeCellWalls[0] = west;
		mazeCellWalls[1] = north;
		mazeCellWalls[2] = east;
		mazeCellWalls[3] = south;
	}

	public Integer ID()
	{
		int _id = (xOrigin & 0x7fff) | ((yOrigin & 0x7fff) << 15);
		return new Integer(_id);
	}
}
