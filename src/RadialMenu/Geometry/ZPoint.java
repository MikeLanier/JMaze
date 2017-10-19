package RadialMenu.Geometry;

public class ZPoint {
	public double x = 0;
	public double y = 0;

	public ZPoint() {
	}

	public ZPoint(int _x, int _y) {
		x = (double) _x;
		y = (double) _y;
	}

	public ZPoint(double _x, double _y) {
		x = _x;
		y = _y;
	}

	public ZPoint(ZPoint _p) {
		x = _p.x;
		y = _p.y;
	}

	public void copy(ZPoint _p) {
		x = _p.x;
		y = _p.y;
	}

	public String toString() {
		Double dx = x;
		Double dy = y;
		return dx.toString() + ", " + dy.toString();
	}
}
