package mainfrm;

public class Wall {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private boolean horizontal = true;
	private boolean open = false;

	public Wall( int _xOrigin, int _yOrigin, boolean _horizontal )
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		horizontal = _horizontal;
	}

	public long ID()
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return	(xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}
}
