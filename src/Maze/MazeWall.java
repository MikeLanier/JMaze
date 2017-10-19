package Maze;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeWall {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private boolean horizontal = true;
	private boolean open = false;
	private boolean door = false;
	private Color closedColor = Color.BLACK;

//	public void ClosedColor(Color _closedColor) { closedColor = _closedColor; }

	public void Open(boolean _open)
	{
		open = _open;
	}
	public boolean Open()
	{
		return open;
	}

	public void setDoor()
	{
		door = true;
	}
	public boolean Door()
	{
		return door;
	}

	public MazeWall(int _xOrigin, int _yOrigin, boolean _horizontal )
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		horizontal = _horizontal;
	}

	public Integer ID()
	{
		return MazeGlobal.ID(xOrigin, yOrigin, horizontal);
	}

	public void draw(GraphicsContext gc, int xOffset, int yOffset, int cellSize)
	{
		double x = (double)(xOffset + xOrigin * cellSize);
		double y = (double)(yOffset + yOrigin * cellSize);

		gc.setLineDashes(0);
		gc.setLineWidth(1);

		if(open)
			if(door)
				gc.setStroke(Color.RED);
			else
				gc.setStroke(Color.WHITE);
		else
			gc.setStroke(closedColor);

		if(horizontal)
		{
			gc.strokeLine(x, y, x + cellSize, y);
		}
		else
		{
			gc.strokeLine(x, y, x, y + cellSize);
		}
	}
}
