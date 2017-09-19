package mainfrm.ControlPanel;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControlPanelCellSize extends HBox {
	public TextField tfCellSize			= new TextField();
	public ControlPanelCellSize(int _sizeCell)
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Maze2DCell Size", 70, false));
		getChildren().add(ControlPanel.Spacer());

		Integer cs = _sizeCell;
		tfCellSize.setText(cs.toString());
		tfCellSize.setMinWidth(40);
		tfCellSize.setMaxWidth(40);
		getChildren().add(tfCellSize);

//				tfCellSize.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfCellSize");
//					}
//				});
	}
}
