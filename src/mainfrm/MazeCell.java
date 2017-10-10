package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCell {
	private int	xOrigin = 0;
	private int yOrigin = 0;

	int X() { return xOrigin; }
	int Y() { return yOrigin; }

	private MazeWall[] mazeWalls = new MazeWall[4];
	private boolean visited = false;

	boolean Visited() { return visited; }
	void setVisited() { visited = true; }

	static int	west = 0;
	static int	north = 1;
	static int	east = 2;
	static int	south = 3;

	MazeWall W(int which)
	{
		return mazeWalls[which];
	}

	public enum CellType {
		eCellTypeNormal,
		eCellTypeStart,
		eCellTypeEntrance,
		eCellTypeExit,
		eCellTypeRoom,
		eCellTypeStairsDown,
		eCellTypeStairsUp
	}

	public enum CellFacing {
		eCellFacingNone,
		eCellFacingEast,
		eCellFacingWest,
		eCellFacingNorth,
		eCellFacingSouth
	}

	private CellType type = CellType.eCellTypeNormal;
	private CellFacing facing = CellFacing.eCellFacingNone;

	boolean facingNorth() { return facing == CellFacing.eCellFacingNorth; }// ? true : false; }
	boolean facingSouth() { return facing == CellFacing.eCellFacingSouth; }// ? true : false; }
	boolean facingEast() { return facing == CellFacing.eCellFacingEast; }// ? true : false; }
	boolean facingWest() { return facing == CellFacing.eCellFacingWest; }// ? true : false; }
	boolean facingNone() { return facing == CellFacing.eCellFacingNone; }// ? true : false; }
	boolean stairsUp() { return type == CellType.eCellTypeStairsUp; }
	boolean stairsDown() { return type == CellType.eCellTypeStairsDown; }

	CellFacing direction() { return facing; }

	public void setType( CellType _type ) { type = _type; }
	public void setFacing( CellFacing _facing ) { facing = _facing; }

	MazeCell(int _xOrigin, int _yOrigin, MazeWall west, MazeWall north, MazeWall east, MazeWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		mazeWalls[0] = west;
		mazeWalls[1] = north;
		mazeWalls[2] = east;
		mazeWalls[3] = south;
	}

	Integer ID()
	{
		return MazeGlobal.ID(xOrigin, yOrigin, false);
	}

	void draw(GraphicsContext gc, int xOffset, int yOffset, int cellSize) {
		double x = (double) (xOffset + xOrigin * cellSize);
		double y = (double) (yOffset + yOrigin * cellSize);

		double scale = ((double) cellSize) / 6.0;

		double xpointsEast[] = {
				x + 1 * scale, x + 3 * scale, x + 3 * scale, x + 5 * scale,
				x + 3 * scale, x + 3 * scale, x + 1 * scale, x + 1 * scale
		};

		double ypointsEast[] = {
				y + 2 * scale, y + 2 * scale, y + 1 * scale, y + 3 * scale,
				y + 5 * scale, y + 4 * scale, y + 4 * scale, y + 2 * scale
		};

		double xpointsSouth[] = {
				x + 2 * scale, x + 2 * scale, x + 1 * scale, x + 3 * scale,
				x + 5 * scale, x + 4 * scale, x + 4 * scale, x + 2 * scale
		};

		double ypointsSouth[] = {
				y + 1 * scale, y + 3 * scale, y + 3 * scale, y + 5 * scale,
				y + 3 * scale, y + 3 * scale, y + 1 * scale, y + 1 * scale
		};

		double xpointsWest[] = {
				x + 5 * scale, x + 3 * scale, x + 3 * scale, x + 1 * scale,
				x + 3 * scale, x + 3 * scale, x + 5 * scale, x + 5 * scale
		};

		double ypointsWest[] = {
				y + 2 * scale, y + 2 * scale, y + 1 * scale, y + 3 * scale,
				y + 5 * scale, y + 4 * scale, y + 4 * scale, y + 2 * scale
		};

		double xpointsNorth[] = {
				x + 2 * scale, x + 2 * scale, x + 1 * scale, x + 3 * scale,
				x + 5 * scale, x + 4 * scale, x + 4 * scale, x + 2 * scale
		};

		double ypointsNorth[] = {
				y + 5 * scale, y + 3 * scale, y + 3 * scale, y + 1 * scale,
				y + 3 * scale, y + 3 * scale, y + 5 * scale, y + 5 * scale
		};

		gc.setFill(Color.WHITE);
		gc.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);

		if (type == CellType.eCellTypeStart) {
			gc.setFill(Color.YELLOW);
		} else if (type == CellType.eCellTypeRoom) {
			gc.setFill(Color.WHITE);
		} else if (type == CellType.eCellTypeEntrance) {
			gc.setFill((Color.LIGHTGREEN));
		} else if (type == CellType.eCellTypeExit) {
			gc.setFill((Color.LIGHTPINK));
		} else {
			gc.setFill(Color.WHITE);
		}
		gc.fillRect(x, y, cellSize, cellSize);

		if(!facingNone()) {
			if (type == CellType.eCellTypeEntrance) {
				gc.setFill((Color.RED));
			} else if (type == CellType.eCellTypeExit) {
				gc.setFill((Color.RED));
			} else {
				gc.setFill((Color.GREEN));
			}

			if (facingEast()) {
				gc.fillPolygon(xpointsEast, ypointsEast, 8);
			} else if (facingNorth()) {
				gc.fillPolygon(xpointsNorth, ypointsNorth, 8);
			} else if (facingSouth()) {
				gc.fillPolygon(xpointsSouth, ypointsSouth, 8);
			} else if (facingWest()) {
				gc.fillPolygon(xpointsWest, ypointsWest, 8);
			}
		}
		else {
			if(type == CellType.eCellTypeStairsDown ||
				type == CellType.eCellTypeStairsUp) {
//				gc.fillRect(x, y, cellSize, cellSize);

				gc.setStroke(Color.BLACK);
				int s = cellSize / 4;
				for(int i=0; i<cellSize; i+=s)
				{
					gc.strokeLine(x+i, y, x+i, y+cellSize);
				}
			}
		}
	}

	private void drawDoors3D(GraphicsContext gc, int x, double z, MazeCell.CellFacing facing)
	{
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

		MazeMath.point p000 = new MazeMath.point(x1, y1, z1);
		MazeMath.point p100 = new MazeMath.point(x2, y1, z1);
		MazeMath.point p010 = new MazeMath.point(x1, y2, z1);
		MazeMath.point p110 = new MazeMath.point(x2, y2, z1);
		MazeMath.point p001 = new MazeMath.point(x1, y1, z2);
		MazeMath.point p101 = new MazeMath.point(x2, y1, z2);
		MazeMath.point p011 = new MazeMath.point(x1, y2, z2);
		MazeMath.point p111 = new MazeMath.point(x2, y2, z2);

		MazeMath.rectangle left = new MazeMath.rectangle(p000, p001, p011, p010);
		MazeMath.rectangle back = new MazeMath.rectangle(p001, p101, p111, p011);
		MazeMath.rectangle right = new MazeMath.rectangle(p100, p101, p111, p110);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		left = m.dot(left);
		back = m.dot(back);
		right = m.dot(right);

		double doorFrameSize = 15;
		left.p[0].y += doorFrameSize;
		left.p[1].y += doorFrameSize;
		left.p[0].z += doorFrameSize;
		left.p[1].z -= doorFrameSize;
		left.p[2].z -= doorFrameSize;
		left.p[3].z += doorFrameSize;

		right.p[0].y += doorFrameSize;
		right.p[1].y += doorFrameSize;
		right.p[0].z += doorFrameSize;
		right.p[1].z -= doorFrameSize;
		right.p[2].z -= doorFrameSize;
		right.p[3].z += doorFrameSize;

		back.p[0].y += doorFrameSize;
		back.p[1].y += doorFrameSize;
		back.p[0].x += doorFrameSize;
		back.p[1].x -= doorFrameSize;
		back.p[2].x -= doorFrameSize;
		back.p[3].x += doorFrameSize;

		left.perspective(500.0);
		back.perspective(500.0);
		right.perspective(500.0);

		double xOffset = 500;
		double yOffset = 250;

		left.move(xOffset, yOffset);
		back.move(xOffset, yOffset);
		right.move(xOffset, yOffset);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.LIGHTGRAY);

		if(facing == MazeCell.CellFacing.eCellFacingEast) {
			if (W(MazeCell.east).Door()) back.draw(gc);
			if (W(MazeCell.north).Door()) left.draw(gc);
			if (W(MazeCell.south).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingWest) {
			if (W(MazeCell.west).Door()) back.draw(gc);
			if (W(MazeCell.south).Door()) left.draw(gc);
			if (W(MazeCell.north).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingNorth) {
			if (W(MazeCell.north).Door()) back.draw(gc);
			if (W(MazeCell.west).Door()) left.draw(gc);
			if (W(MazeCell.east).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingSouth) {
			if (W(MazeCell.south).Door()) back.draw(gc);
			if (W(MazeCell.east).Door()) left.draw(gc);
			if (W(MazeCell.west).Door()) right.draw(gc);
		}
	}

	private void drawStairsDown3D(GraphicsContext gc, int x, double z, MazeCell.CellFacing facing) {
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

		MazeMath.point p000 = new MazeMath.point(x1, y1, z1);
		MazeMath.point p100 = new MazeMath.point(x2, y1, z1);
		MazeMath.point p010 = new MazeMath.point(x1, y2, z1);
		MazeMath.point p110 = new MazeMath.point(x2, y2, z1);
		MazeMath.point p001 = new MazeMath.point(x1, y1, z2);
		MazeMath.point p101 = new MazeMath.point(x2, y1, z2);
		MazeMath.point p011 = new MazeMath.point(x1, y2+1, z2);
		MazeMath.point p111 = new MazeMath.point(x2, y2+1, z2);

		MazeMath.rectangle bottom = new MazeMath.rectangle(p010, p011, p111, p110);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		bottom = m.dot(bottom);
		bottom.perspective(500.0);

		double xOffset = 500;
		double yOffset = 250;

		bottom.move(xOffset, yOffset);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLACK);

		bottom.draw(gc);
	}

	private void drawStairsUp3D(GraphicsContext gc, int x, double z, MazeCell.CellFacing facing)
	{
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

		MazeMath.point p000 = new MazeMath.point(x1, y1, z1);
		MazeMath.point p100 = new MazeMath.point(x2, y1, z1);
		MazeMath.point p010 = new MazeMath.point(x1, y2, z1);
		MazeMath.point p110 = new MazeMath.point(x2, y2, z1);
		MazeMath.point p001 = new MazeMath.point(x1, y1, z2);
		MazeMath.point p101 = new MazeMath.point(x2, y1, z2);
		MazeMath.point p011 = new MazeMath.point(x1, y2, z2);
		MazeMath.point p111 = new MazeMath.point(x2, y2, z2);

		MazeMath.rectangle left = new MazeMath.rectangle(p000, p001, p011, p010);
		MazeMath.rectangle back = new MazeMath.rectangle(p001, p101, p111, p011);
		MazeMath.rectangle right = new MazeMath.rectangle(p100, p101, p111, p110);
		MazeMath.rectangle front = new MazeMath.rectangle(p000, p100, p110, p010);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		MazeMath.rectangle stairs = new MazeMath.rectangle(p000, p000, p000, p000);

		for(int i=0; i<11; i++) {
			if (facing == MazeCell.CellFacing.eCellFacingEast) {
				stairs = back;
			} else if (facing == MazeCell.CellFacing.eCellFacingWest) {
//				System.out.println("stairs: before");
//				System.out.println("  p[0]: " + front.p[0].x + ", " + front.p[0].y + ", " + front.p[0].z);
//				System.out.println("  p[1]: " + front.p[1].x + ", " + front.p[1].y + ", " + front.p[1].z);
//				System.out.println("  p[2]: " + front.p[2].x + ", " + front.p[2].y + ", " + front.p[2].z);
//				System.out.println("  p[3]: " + front.p[3].x + ", " + front.p[3].y + ", " + front.p[3].z);

				if(i==0 || i==2 || i==4 || i==6 || i==8 || i==10) {
					front.p[2].z = front.p[0].z;
					front.p[3].z = front.p[1].z;
					front.p[0].y = front.p[2].y - .2;
					front.p[1].y = front.p[3].y - .2;
				}
				else {
					front.p[2].y = front.p[0].y;
					front.p[3].y = front.p[1].y;
					front.p[0].z = front.p[2].z + .2;
					front.p[1].z = front.p[3].z + .2;
				}
//				System.out.println("stairs: after");
//				System.out.println("  p[0]: " + front.p[0].x + ", " + front.p[0].y + ", " + front.p[0].z);
//				System.out.println("  p[1]: " + front.p[1].x + ", " + front.p[1].y + ", " + front.p[1].z);
//				System.out.println("  p[2]: " + front.p[2].x + ", " + front.p[2].y + ", " + front.p[2].z);
//				System.out.println("  p[3]: " + front.p[3].x + ", " + front.p[3].y + ", " + front.p[3].z);

				stairs.copy(front);
			} else if (facing == MazeCell.CellFacing.eCellFacingNorth) {
				stairs = right;
			} else if (facing == MazeCell.CellFacing.eCellFacingSouth) {
				stairs = left;
			}

			stairs = m.dot(stairs);
			stairs.perspective(500.0);

			double xOffset = 500;
			double yOffset = 250;

			stairs.move(xOffset, yOffset);

			gc.setStroke(Color.BLACK);
			gc.setFill(Color.LIGHTGRAY);

			stairs.draw(gc);
		}
	}

	public void draw3D(GraphicsContext gc, int x, double z, MazeCell.CellFacing facing)
	{
		double x1 = -1+(x*2);
		double x2 = 1+(x*2);
		double y1 = -1;
		double y2 = 1;
		double z1 = z;
		double z2 = z+1;

		MazeMath.point p000 = new MazeMath.point(x1, y1, z1);
		MazeMath.point p100 = new MazeMath.point(x2, y1, z1);
		MazeMath.point p010 = new MazeMath.point(x1, y2, z1);
		MazeMath.point p110 = new MazeMath.point(x2, y2, z1);
		MazeMath.point p001 = new MazeMath.point(x1, y1, z2);
		MazeMath.point p101 = new MazeMath.point(x2, y1, z2);
		MazeMath.point p011 = new MazeMath.point(x1, y2, z2);
		MazeMath.point p111 = new MazeMath.point(x2, y2, z2);

		MazeMath.rectangle left = new MazeMath.rectangle(p000, p001, p011, p010);
		MazeMath.rectangle back = new MazeMath.rectangle(p001, p101, p111, p011);
		MazeMath.rectangle right = new MazeMath.rectangle(p100, p101, p111, p110);

		MazeMath.matrix m = new MazeMath.matrix();
		m.scale(100);

		left = m.dot(left);
		back = m.dot(back);
		right = m.dot(right);

		left.perspective(500.0);
		back.perspective(500.0);
		right.perspective(500.0);

		double xOffset = 500;
		double yOffset = 250;

		left.move(xOffset, yOffset);
		back.move(xOffset, yOffset);
		right.move(xOffset, yOffset);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.WHITE);

		if(facing == MazeCell.CellFacing.eCellFacingEast) {
			if (!W(MazeCell.east).Open() || W(MazeCell.east).Door()) back.draw(gc);
			if (!W(MazeCell.north).Open() || W(MazeCell.north).Door()) left.draw(gc);
			if (!W(MazeCell.south).Open() || W(MazeCell.south).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingWest) {
			if (!W(MazeCell.west).Open() || W(MazeCell.west).Door()) back.draw(gc);
			if (!W(MazeCell.south).Open() || W(MazeCell.south).Door()) left.draw(gc);
			if (!W(MazeCell.north).Open() ||W(MazeCell.north).Door() ) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingNorth) {
			if (!W(MazeCell.north).Open() || W(MazeCell.north).Door()) back.draw(gc);
			if (!W(MazeCell.west).Open() || W(MazeCell.west).Door()) left.draw(gc);
			if (!W(MazeCell.east).Open() || W(MazeCell.east).Door()) right.draw(gc);
		}
		else if(facing == MazeCell.CellFacing.eCellFacingSouth) {
			if (!W(MazeCell.south).Open() || W(MazeCell.south).Door()) back.draw(gc);
			if (!W(MazeCell.east).Open() || W(MazeCell.east).Door()) left.draw(gc);
			if (!W(MazeCell.west).Open() || W(MazeCell.west).Door()) right.draw(gc);
		}

		drawDoors3D(gc, x, z, facing);

		if(stairsUp())		drawStairsUp3D(gc, x, z, facing);
		if(stairsDown())	drawStairsDown3D(gc, x, z, facing);
	}

}
