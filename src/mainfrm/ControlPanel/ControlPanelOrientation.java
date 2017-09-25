package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;

public class ControlPanelOrientation extends HBox {
	public CheckBox cb2DView			= new CheckBox();
	public CheckBox		cb3DView			= new CheckBox();
	private MainFrm mainFrm = null;
	public ControlPanelOrientation(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Orientation", 70, true));
		getChildren().add(ControlPanel.Spacer());

		cb2DView.setText("2D view");
		cb2DView.setMinWidth(90);
		cb2DView.setMaxWidth(90);
		cb2DView.setIndeterminate(false);
		cb2DView.setSelected(true);
		cb2DView.setTooltip(new Tooltip("Show a 2D top view of the maze"));
		cb2DView.setDisable(true);
		getChildren().add(cb2DView);

		cb2DView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMouseClicked: cb2DView: " + cb2DView.isSelected());
				if(cb2DView.isSelected()) {
//					cb3DView.setSelected(false);
//					mainFrm.SwitchTo2DMaze();
				}
			}
		});

		getChildren().add(ControlPanel.Spacer());

		cb3DView.setText("3D view");
		cb3DView.setMinWidth(90);
		cb3DView.setMaxWidth(90);
		cb3DView.setSelected(true);
		cb3DView.setTooltip(new Tooltip("Show the maze in 3D from inside the maze"));
		cb3DView.setDisable(true);
		getChildren().add(cb3DView);

		cb3DView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(cb3DView.isSelected())	{
					System.out.println("OnMouseClicked: cb3DView: " + cb3DView.isSelected());
//					cb2DView.setSelected(false);
//					mainFrm.SwitchTo3D2DMaze();
				}
			}
		});
	}
}
