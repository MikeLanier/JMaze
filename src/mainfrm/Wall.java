package mainfrm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private boolean horizontal = true;
	private boolean open = false;

	public void Open(boolean _open)
	{
		open = _open;
	}

	public Wall( int _xOrigin, int _yOrigin, boolean _horizontal )
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		horizontal = _horizontal;
	}

	public Integer ID()
	{
		int h = (horizontal) ? 0x40000000 : 0;

		int _id = 	(xOrigin & 0x7fff) |
					(yOrigin & 0x7fff) << 15 |
					h;
//		System.out.println(_id);

		return new Integer(_id);
	}

	public void draw(GraphicsContext gc, int xOffset, int yOffset, int cellSize)
	{
		double x = (double)(xOffset + xOrigin * cellSize);
		double y = (double)(yOffset + yOrigin * cellSize);

//		System.out.println("draw: moveTo: " + x + ", " + y);

		gc.setLineDashes(0);
		gc.setLineWidth(1);

		if(open)
			gc.setStroke(Color.YELLOW);
		else
			gc.setStroke(Color.BLACK);

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
