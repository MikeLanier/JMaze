package mainfrm;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlPanelStartWalkthrough extends HBox {
	public Button btnStartWalkthrough		= new Button();
	public ControlPanelStartWalkthrough()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		btnStartWalkthrough.setText("Start Walkthrough");
		btnStartWalkthrough.setMinWidth(120);
		btnStartWalkthrough.setMaxWidth(120);
		btnStartWalkthrough.setDisable(true);
		getChildren().add(btnStartWalkthrough);

		btnStartWalkthrough.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMousePressed: btnStartWalkthrough");
			}
		});
	}
}
