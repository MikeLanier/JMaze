package RadialMenu.Geometry;

public class ZLine {
	public ZPoint	p1 = new ZPoint();
	public ZPoint	p2 = new ZPoint();

	private double	distance;
	private double	angle;

	public ZLine(ZPoint _p1, ZPoint _p2) {
		p1.copy(_p1);
		p2.copy(_p2);
	}

	public ZLine(ZPoint _p1, double _distance, double _angle) {
		p1.copy(_p1);

		distance = _distance;
		angle = _angle;

		p2.x = p1.x + distance * Math.cos(angle);
		p2.y = p1.y + distance * Math.sin(angle);
	}

	public ZLine(ZPoint _p1, double _distance1, double _distance2, double _angle) {
		p1.copy(_p1);

		distance = _distance2;
		angle = _angle;

		p2.x = p1.x + distance * Math.cos(angle);
		p2.y = p1.y + distance * Math.sin(angle);
		p1.x = p1.x + _distance1 * Math.cos(angle);
		p1.y = p1.y + _distance1 * Math.sin(angle);
	}
}
