package mainfrm.ControlPanel;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class ControlPanelMazes extends HBox {
	public ComboBox cbMazeName			= new ComboBox();
	public Button btnMazeCreate			= new Button();
	public Button btnMazeDelete			= new Button();

	public ControlPanelMazes()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Mazes", 60, false));
		getChildren().add(ControlPanel.Spacer());

		cbMazeName.setMinWidth(165);
		cbMazeName.setMaxWidth(165);
		cbMazeName.setTooltip(new ControlPanelTooltip("Name of the current maze"));
		getChildren().add(cbMazeName);

//				tfMazeName.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeName");
//					}
//				});

		getChildren().add(ControlPanel.Spacer());

		// press this button to show the fileopen dialog.

//		btnMazeCreate.setGraphic(iv);
		btnMazeCreate.setText("+");
		btnMazeCreate.setMinWidth(25);
		btnMazeCreate.setTooltip(new ControlPanelTooltip("Create a new maze"));
		getChildren().add(btnMazeCreate);

		getChildren().add(ControlPanel.Spacer());

		// press this button to show the fileopen dialog.

//		btnMazeDelete.setGraphic(iv);
		btnMazeDelete.setText("-");
		btnMazeDelete.setMinWidth(25);
		btnMazeDelete.setTooltip(new ControlPanelTooltip("Delete the active maze"));
		getChildren().add(btnMazeDelete);
	}
}
