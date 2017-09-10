package mainfrm;

public class Cell {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private Wall[] walls = new Wall[4];

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
}
