package mainfrm.ControlPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;

public class ControlPanelCellSize extends HBox {
	public TextField tfCellSize			= new TextField();
	private MainFrm mainFrm = null;
	private ControlPanel controlPanel = null;
	public ControlPanelCellSize(ControlPanel _controlPanel, MainFrm _mainFrm, int _sizeCell) {
		mainFrm = _mainFrm;
		controlPanel = _controlPanel;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Cell Size", 70, false));
		getChildren().add(ControlPanel.Spacer());

		Integer cs = _sizeCell;
		tfCellSize.setText(cs.toString());
		tfCellSize.setMinWidth(40);
		tfCellSize.setMaxWidth(40);
		getChildren().add(tfCellSize);

		tfCellSize.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controlPanel._sizeCell = Integer.parseInt(tfCellSize.getText());
				System.out.println("OnAction: tfCellSize");
				mainFrm.maze2DPanel.resetMazePanel();
				mainFrm.maze2DPanel.drawMaze();
			}
		});
	}
}
