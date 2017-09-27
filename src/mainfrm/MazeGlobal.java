package mainfrm;

public class MazeGlobal {
	public static int			sizeX = 10;
	public static int			sizeY = 10;
	public static int			sizeCell = 25;
	public static boolean		maze2Ddisplay = true;
	public static boolean		maze3Ddisplay = true;

	public MazeGlobal()
	{
	}

	static Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}
}
