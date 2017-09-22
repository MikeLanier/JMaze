package mainfrm;

public class MazeGlobal {
	public static int			sizeX = 10;
	public static int			sizeY = 10;
	public static int			sizeCell = 25;

	public MazeGlobal()
	{
	}

	public static Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}
}
