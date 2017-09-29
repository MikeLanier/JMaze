package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelPrintMaze extends HBox {
	public Button btnMazePrint		= new Button();
	public ControlPanelPrintMaze()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnMazePrint.setText("Print");
		btnMazePrint.setMinWidth(70);
		btnMazePrint.setMaxWidth(70);
		btnMazePrint.setTooltip(new ControlPanelTooltip("Print the maze"));
		btnMazePrint.setDisable(true);
		getChildren().add(btnMazePrint);

		btnMazePrint.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnMazePrint");
			}
		});
	}
}
