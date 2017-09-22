package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeWall {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private boolean horizontal = true;
	private boolean open = false;
	private Color closedColor = Color.BLACK;

	public void ClosedColor(Color _closedColor) { closedColor = _closedColor; }

	public void Open(boolean _open)
	{
		open = _open;
	}
	public boolean Open()
	{
		return open;
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

//		System.out.println("draw: moveTo: " + x + ", " + y);

		gc.setLineDashes(0);
		gc.setLineWidth(1);

		if(open)
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
