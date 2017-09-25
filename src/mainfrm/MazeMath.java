package mainfrm;

import javafx.scene.canvas.GraphicsContext;

public class MazeMath {
	public MazeMath() {

	}

	public static class point {
		public double x = 0.0;
		public double y = 0.0;
		public double z = 0.0;
		public double w = 1.0;

		public point(double _x, double _y, double _z, double _w)
		{
			x = _x;
			y = _y;
			z = _z;
			w = _w;
		}

		public point(double _x, double _y, double _z)
		{
			x = _x;
			y = _y;
			z = _z;
			w = 1.0;
		}

		public point()
		{
			x = 0.0;
			y = 0.0;
			z = 0.0;
			w = 1.0;
		}

		public point(point p) {
			x = p.x;
			y = p.y;
			z = p.z;
			w = p.w;
		}

		public void copy(point p) {
			x = p.x;
			y = p.y;
			z = p.z;
			w = p.w;
		}

		public void move(double _x, double _y) {
			x += _x;
			y += _y;
//			z = p.z;
//			w = p.w;
		}

		public void perspective(double d) {
			x = x * (d /z);
			y = y * (d/z);
//			x = x * (d - z) / d;
//			y = y * (d - z) / d;
		}

		public void dump(String t) {
			System.out.println(t + ": " + x + ", " + y + ", " + z + ", " + w);
		}
	}

	public static class rectangle {
		public point p[] = new point[4];

		public rectangle( point topLeft, point topRight, point bottomRight, point bottomLeft)
		{
			p[0] = new point(topLeft);
			p[1] = new point(topRight);
			p[2] = new point(bottomRight);
			p[3] = new point(bottomLeft);
		}

		public void move(double _x, double _y) {
			p[0].move(_x, _y);
			p[1].move(_x, _y);
			p[2].move(_x, _y);
			p[3].move(_x, _y);
		}

		public void perspective(double d) {
			p[0].perspective(d);
			p[1].perspective(d);
			p[2].perspective(d);
			p[3].perspective(d);
		}

		public void draw(GraphicsContext gc) {
			double xleft[] = { p[0].x, p[1].x, p[2].x, p[3].x, p[0].x };
			double yleft[] = { p[0].y, p[1].y, p[2].y, p[3].y, p[0].y };
			gc.fillPolygon( xleft, yleft, 5);

			gc.strokeLine(p[0].x, p[0].y, p[1].x, p[1].y);
			gc.strokeLine(p[1].x, p[1].y, p[2].x, p[2].y);
			gc.strokeLine(p[2].x, p[2].y, p[3].x, p[3].y);
			gc.strokeLine(p[3].x, p[3].y, p[0].x, p[0].y);
		}

		public void dump(String title) {
			System.out.println(title);
			p[0].dump("   ");
			p[1].dump("   ");
			p[2].dump("   ");
			p[3].dump("   ");
		}
	}

	public static class matrix {
		private double m[][] = new double[4][4];

		public matrix() {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					m[i][j] = 0.0;
				}
			}
			m[0][0] = 1.0;
			m[1][1] = 1.0;
			m[2][2] = 1.0;
			m[3][3] = 0.0;
		}

		public void scale(double xs, double ys, double zs)
		{
			m[0][0] *= xs;
			m[1][1] *= ys;
			m[2][2] *= zs;
		}

		public void scale(double s)
		{
			scale(s, s, s);
		}

		public void translate(double xt, double yt, double  zt)
		{
			m[3][0] = xt;
			m[3][1] = yt;
			m[3][2] = zt;
		}

		public matrix copy() {
			matrix mat = new matrix();

			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					mat.m[i][j] = m[i][j];
				}
			}

			return mat;
		}

		public matrix dot(matrix t) {
			matrix mat = new matrix();

			// [m00 m01 m02 m03]   [n00 n01 n02 n03]   [p00 p01 p02 p03]
			// [m10 m11 m12 m13] . [n10 n11 n12 n13] = [p10 p11 p12 p13]
			// [m20 m21 m22 m23]   [n20 n21 n22 n23]   [p20 p21 p22 p23]
			// [m30 m31 m32 m33]   [n30 n31 n32 n33]   [p30 p31 p32 p33]
			//
			// p00 = (m00 * n00) + (m01 * n10) + (m02 * n20) + (m03 * n30)
			// p01 = (m00 * n01) + (m01 * n11) + (m02 * n21) + (m03 * n31)
			//
			//
			// p10 = (m10 * n00) + (m11 * n10) + (m12 * n20) + (m13 * n30)

			for(int y=0; y<4; y++) {
				for(int x=0; x<4; x++) {
					double a  = m[y][0] * t.m[0][x];
					double b  = m[y][1] * t.m[1][x];
					double c  = m[y][2] * t.m[2][x];
					double d  = m[y][3] * t.m[3][x];

					mat.m[x][y] = a + b + c + d;
				}
			}

			return mat;
		}

		public void dump()
		{
			System.out.println(m[0][0] + ". " + m[0][1] + ", " + m[0][2] + ", " + m[0][3]);
			System.out.println(m[1][0] + ". " + m[1][1] + ", " + m[1][2] + ", " + m[1][3]);
			System.out.println(m[2][0] + ". " + m[2][1] + ", " + m[2][2] + ", " + m[2][3]);
			System.out.println(m[3][0] + ". " + m[3][1] + ", " + m[3][2] + ", " + m[3][3]);
		}

		public point dot(point p) {
			// [m00 m01 m02 m03]   [x]   [m00*x + m01*y + m02*z + m03*w]
			// [m10 m11 m12 m13] . [y] = [m10*x + m11*y + m12*z + m13*w]
			// [m20 m21 m22 m23]   [z]   [m20*x + m21*y + m22*z + m23*w]
			// [m30 m31 m32 m33]   [w]   [m30*x + m31*y + m32*z + m33*w]

			point pt = new point();

			pt.x = m[0][0] * p.x + m[0][1] * p.y + m[0][2] * p.z + m[0][3] * p.w;
			pt.y = m[1][0] * p.x + m[1][1] * p.y + m[1][2] * p.z + m[1][3] * p.w;
			pt.z = m[2][0] * p.x + m[2][1] * p.y + m[2][2] * p.z + m[2][3] * p.w;
			pt.w = m[3][0] * p.x + m[3][1] * p.y + m[3][2] * p.z + m[3][3] * p.w;

			return pt;
		}

		public rectangle dot(rectangle _r) {
			rectangle r = new rectangle(dot(_r.p[0]), dot(_r.p[1]), dot(_r.p[2]), dot(_r.p[3]));
			return  r;
		}
	}
}
