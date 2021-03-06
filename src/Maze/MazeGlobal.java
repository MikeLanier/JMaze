package Maze;

import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MazeGlobal {
	public static int			sizeX = 10;
	public static int			sizeY = 10;
	public static int			sizeCell = 25;
	public static boolean		maze2Ddisplay = true;
	public static boolean		maze3Ddisplay = true;
	public static Integer		startCellX = sizeX/2;
	public static Integer		startCellY = sizeY/2;
	public static Integer		entranceCellX = 0;
	public static Integer		entranceCellY = sizeY / 3;
	public static Integer		exitCellX = sizeX + 1;
	public static Integer		exitCellY = sizeY * 2 / 3;
	public static MazeCell		currentMazeCell = null;
	public static MazeCell		entranceMazeCell = null;
	public static MazeCell		exitMazeCell = null;

	public static Map<Integer, MazeCell>	cells = null;
	public static Map<Integer, MazeWall>	walls = null;

	public static Map<String, MazeInfo>		mazes = new HashMap<>();
	public static String		mazeFilename = "mazes.mazes";

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

	public static Integer parseTextField(String str, Integer value)
	{
		try {
			if(str.isEmpty()) {
				value = 0;
			}
			else
				value = Integer.parseInt(str);
		}
		catch(Exception e) {
//			System.out.println("Invalid value entered");
		}

		return value;
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
