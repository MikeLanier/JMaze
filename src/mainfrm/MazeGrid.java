package mainfrm;


public class MazeGrid extends Xform {
	public MazeGrid()
	{
		int color = 4;
		for(int x=-50; x<=50; x+=10) {
			for(int z=-50; z<=50; z+=10) {
				if(x!=50) getChildren().add(new MazeGridWall(x, 0, z, true, color));
				if(z!=50) getChildren().add(new MazeGridWall(x, 0, z, false, color));
//				color++;
//				if(color>=5) color = 0;
			}
		}
	}
}
