package mainfrm.ControlPanel;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ControlPanelSeparator extends HBox {
	public ControlPanelSeparator()
	{
		Rectangle rec = new Rectangle(300, 1);
		getChildren().add(rec);
	}
}
