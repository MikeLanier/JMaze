package RadialMenu;

import RadialMenu.Geometry.ZPoint;
import RadialMenu.Geometry.ZRect;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class RadialMenu extends Pane {

	boolean mousePressed = false;

	public RadialMenu(int innerRadius, int outerRadius, Command commands)
	{
		int	buffer = 10; // distance from the edge of the background to the edge of the radial menu
		int	numCommands = commands.subCommmands.size();

		System.out.println("RadialMenu");

		setWidth(outerRadius*2 + buffer*2);
		setHeight(outerRadius*2 + buffer*2);
		setTranslateX(700);
		setTranslateY(0);

		ZRect rect = new ZRect( 0, 0, outerRadius*2 + buffer*2, outerRadius*2 + buffer*2);
		ZPoint center = rect.Center();

//		Rectangle rectangle = new Rectangle(outerRadius*2 + buffer*2, outerRadius*2 + buffer*2);
//		rectangle.setFill(Color.ORANGE);
//		getChildren().add(rectangle);

		double d = Math.PI * 2 / numCommands;
		double r = -d/2 - Math.PI / 2;

		for(int i=0; i<numCommands; i++, r=r+d) {
			Command cmd = commands.subCommmands.get(i);
			if (cmd != null) {
				getChildren().add(new Wedge(this, center, innerRadius, outerRadius, r, r + d, cmd));

				double radius = (outerRadius - innerRadius) / 2 + innerRadius;
				double angle = r + d / 2;
				double x = radius * Math.cos(angle) + center.x;
				double y = radius * Math.sin(angle) + center.y;

				if (!cmd.icon.isEmpty()) {
					String icon = cmd.icon;
					try {

						javafx.scene.image.Image img = new javafx.scene.image.Image(getClass().getResource(icon).toString());
						ImageView iv = new ImageView(img);
						iv.setTranslateX(x - 16);
						iv.setTranslateY(y - 16);
						iv.setMouseTransparent(true);
						getChildren().add(iv);
					} catch (Exception e) {

					}
				}
			}
		}

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("Mouse Pressed");
				mousePressed = true;
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("Mouse Relesed");
				mousePressed = false;
			}
		});

		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				if(mousePressed) {
//					System.out.println("Mouse Moved");
//				}
			}
		});
	}
}
