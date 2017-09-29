package mainfrm.ControlPanel;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ControlPanelMazeName extends HBox {
	public TextField tfMazeName			= new TextField();
	public ControlPanelMazeName()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Names", 60, true));
		getChildren().add(ControlPanel.Spacer());

		tfMazeName.setMinWidth(150);
		tfMazeName.setMaxWidth(150);
		tfMazeName.setDisable(true);
		tfMazeName.setTooltip(new Tooltip("Name of the current maze"));
		getChildren().add(tfMazeName);

//				tfMazeName.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeName");
//					}
//				});
	}
}
