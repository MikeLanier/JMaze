package RadialMenu.Geometry;

public class ZRect {
	public int	left = 0;
	public int	right = 0;
	public int	top = 0;
	public int	bottom = 0;
	public int	width = 0;
	public int	height = 0;

	public ZRect(int _x, int _y, int _width, int _height) {
		left = _x;
		top = _y;
		right = _x + _width;
		bottom = _y + _height;
		width = _width;
		height = _height;
	}

	public ZPoint Center() {
		return new ZPoint(width/2 + left, height/2 + top);
	}

	public ZPoint TopLeft() {
		return new ZPoint(left, top);
	}

	public ZPoint BottomRight() {
		return new ZPoint(right, bottom);
	}
}
