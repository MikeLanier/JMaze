package mainfrm;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class MazeGridWall extends Xform {
	public MazeGridWall(int xOrigin, int yOrigin, int zOrigin, boolean horizonal, int color)
	{
		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial yellowMaterial = new PhongMaterial();
		yellowMaterial.setDiffuseColor(Color.DARKGOLDENROD);
		yellowMaterial.setSpecularColor(Color.YELLOW);

		final PhongMaterial whiteMaterial = new PhongMaterial();
		whiteMaterial.setDiffuseColor(Color.LIGHTGRAY);
		whiteMaterial.setSpecularColor(Color.DARKGRAY);

		Box box = null;

		if(horizonal)
		{
			box = new Box(10, 10, .5);
		}
		else
		{
			box = new Box(.5, 10, 10);
		}

		setTx(xOrigin + (horizonal ? 5 : 0));
		setTz(zOrigin + (horizonal ? 0 : 5));

		if(color == 0)	box.setMaterial(whiteMaterial);
		if(color == 1)	box.setMaterial(blueMaterial);
		if(color == 2)	box.setMaterial(greenMaterial);
		if(color == 3)	box.setMaterial(redMaterial);
		if(color == 4)	box.setMaterial(yellowMaterial);

		getChildren().add(box);
	}
}
