package mainfrm;

import javafx.scene.control.TextField;
import java.util.Random;


public class MazeGlobal {
	public static int			sizeX = 10;
	public static int			sizeY = 10;
	public static int			sizeCell = 25;
	public static boolean		maze2Ddisplay = true;
	public static boolean		maze3Ddisplay = true;
	public static Integer		startCellX = sizeX/2;
	public static Integer		startCellY = sizeY/2;

	public static Random rand = new Random(System.currentTimeMillis());

	public MazeGlobal()
	{
	}

	public static Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}

	public static Integer parseTextField(TextField tf, Integer value)
	{
		try {
			if(tf.getText().isEmpty()) {
				value = 0;
			}
			else
				value = Integer.parseInt(tf.getText());
		}
		catch(Exception e) {
//			System.out.println("Invalid value entered");
			tf.setText(value.toString());
		}

		return value;
	}
}
