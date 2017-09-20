package mainfrm.ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mainfrm.MainFrm;

public class ControlPanelOrientation extends HBox {
	public CheckBox cbTopView			= new CheckBox();
	public CheckBox		cbWalkthrough			= new CheckBox();
	private MainFrm mainFrm = null;
	public ControlPanelOrientation(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Orientation", 70, false));
		getChildren().add(ControlPanel.Spacer());

		cbTopView.setText("Top View");
		cbTopView.setMinWidth(90);
		cbTopView.setMaxWidth(90);
		cbTopView.setIndeterminate(false);
		cbTopView.setSelected(false);
//		cbTopView.setDisable(true);
		getChildren().add(cbTopView);

		cbTopView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("OnMouseClicked: cbTopView: " + cbTopView.isSelected());
				if(cbTopView.isSelected()) {
					cbWalkthrough.setSelected(false);
//					mainFrm.SwitchTo2DMaze();
				}
			}
		});

		getChildren().add(ControlPanel.Spacer());

		cbWalkthrough.setText("Walkthrough");
		cbWalkthrough.setMinWidth(90);
		cbWalkthrough.setMaxWidth(90);
		cbWalkthrough.setSelected(true);
//		cbWalkthrough.setDisable(true);
		getChildren().add(cbWalkthrough);

		cbWalkthrough.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(cbWalkthrough.isSelected())	{
					System.out.println("OnMouseClicked: cbWalkthrough: " + cbWalkthrough.isSelected());
					cbTopView.setSelected(false);
//					mainFrm.SwitchTo3D2DMaze();
				}
			}
		});
	}
}
