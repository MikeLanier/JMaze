package RadialMenu;

import RadialMenu.Geometry.ZLine;
import RadialMenu.Geometry.ZPoint;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Wedge extends Path {

	private Command command = null;
	public ZLine line1 = null;
	private RadialMenu parent;

	public Wedge(RadialMenu _parent, ZPoint origin, double innerRadius, double outerRadius, double angle1, double angle2, Command _command) {

		parent = _parent;
		command = _command;

		line1 = new ZLine( origin, innerRadius, outerRadius, angle1);
		ZLine line2 = new ZLine( origin, innerRadius, outerRadius, angle2);

		MoveTo moveTo1 = new MoveTo( line1.p1.x, line1.p1.y );
		LineTo lineTo2 = new LineTo( line1.p2.x, line1.p2.y );
		ArcTo arcTo3 = new ArcTo( outerRadius, outerRadius, angle2 - angle1, line2.p2.x, line2.p2.y, false, true);
		LineTo lineTo4 = new LineTo( line2.p1.x, line2.p1.y );
		ArcTo arcTo5 = new ArcTo( innerRadius, innerRadius, angle2 - angle1, line1.p1.x, line1.p1.y, false, false);

		getElements().add(moveTo1);
		getElements().add(lineTo2);
		getElements().add(arcTo3);
		getElements().add(lineTo4);
		getElements().add(arcTo5);

		setFill(Color.WHITE);

		setOnMouseEntered(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				setFill(Color.YELLOW);
			}
		});

		setOnMouseExited(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				setFill(Color.WHITE);
			}
		});

		setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				EventTarget et = new EventTarget() {
					@Override
					public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
//						System.out.println("buildEventDispatchChain");
						return null;
					}
				};
//				System.out.println("Wedge:click: " + command.toString());
				CommandEvent commandEvent = new CommandEvent(parent, (EventTarget)command);
				parent.fireEvent(commandEvent);
			}
		});
	}
}
