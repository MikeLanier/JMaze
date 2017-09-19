package mainfrm;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;

public class Maze3D2DCell extends Xform {
	private int	xOrigin = 0;
	private int yOrigin = 0;
	private int zOrigin = 0;

	public int X() { return xOrigin; }
	public int Y() { return yOrigin; }
	public int Z() { return zOrigin; }

	private Maze3D2DWall[] mazeCellWalls = new Maze3D2DWall[4];
	private boolean visited = false;

	public boolean Visited() { return visited; }
	public void Visited(boolean _visited) { visited = _visited; }

	public static int	west = 0;
	public static int	north = 1;
	public static int	east = 2;
	public static int	south = 3;

	public Maze3D2DWall W(int which)
	{
		return mazeCellWalls[which];
	}

	private Maze2DCell.CellType type = Maze2DCell.CellType.eNormal;

	public void SetType( Maze2DCell.CellType _type )
	{
		type = _type;
		if(_type == Maze2DCell.CellType.eCellTypeEntrance)
		{
			cylinder.setVisible(true);
		}
		else
		if(_type == Maze2DCell.CellType.eCellTypeExit)
		{
			cylinder.setVisible(true);
		}
	}

	private Box box = null;
	private Cylinder cylinder = null;

	public Maze3D2DCell(int _xOrigin, int _yOrigin, int _zOrigin, Maze3D2DWall west, Maze3D2DWall north, Maze3D2DWall east, Maze3D2DWall south)
	{
		xOrigin = _xOrigin;
		yOrigin = _yOrigin;
		zOrigin = _zOrigin;
		mazeCellWalls[0] = west;
		mazeCellWalls[1] = north;
		mazeCellWalls[2] = east;
		mazeCellWalls[3] = south;

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.RED);
		redMaterial.setSpecularColor(Color.WHITE);

		setTx(xOrigin+5);
		setTz(zOrigin+5);
		setRotateX(90);
		setRotateY(90);

		box = new Box(5, .5, 5);
		box.setMaterial(greenMaterial);
		box.setVisible(false);

		cylinder = new Cylinder(1, 6);
		cylinder.setMaterial(redMaterial);
		cylinder.setVisible(false);

		getChildren().add(box);
		getChildren().add(cylinder);
	}

	public Integer ID()
	{
		int _id = (xOrigin & 0x7fff) | ((zOrigin & 0x7fff) << 15);
		return new Integer(_id);
	}
}
