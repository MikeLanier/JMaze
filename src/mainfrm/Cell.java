package mainfrm;

public class Cell {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private Wall[] walls = new Wall[4];

	public Cell(int _xOrigin, int _yOrigin, Wall left, Wall top, Wall right, Wall bottom)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		walls[0] = left;
		walls[1] = top;
		walls[2] = right;
		walls[3] = bottom;
	}

	public int ID()
	{
		return (xOrigin & 0x7fff) | ((yOrigin & 0x7fff) << 15);
	}
}
