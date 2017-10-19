package Maze;

import java.util.Map;

public class MazeInfo {
	public int			sizeX = 10;
	public int			sizeY = 10;
	public boolean		maze2Ddisplay = true;
	public boolean		maze3Ddisplay = true;
	public Integer		startCellX = sizeX/2;
	public Integer		startCellY = sizeY/2;
	public Integer		entranceCellX = 0;
	public Integer		entranceCellY = sizeY / 3;
	public Integer		exitCellX = sizeX + 1;
	public Integer		exitCellY = sizeY * 2 / 3;
	public MazeCell		currentMazeCell = null;
	public MazeCell		entranceMazeCell = null;
	public MazeCell		exitMazeCell = null;

	public String		mazeName = "";

	public Map<Integer, MazeCell>	cells = null;
	public Map<Integer, MazeWall>	walls = null;

	public MazeInfo()
	{
		
	}
}
