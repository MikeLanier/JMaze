package mainfrm.ControlPanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ControlPanelAlgorithm extends HBox {
	public ComboBox<String> cbAlgorithm		= new ComboBox<>();

	class Group2
	{
		String	title;
		int		index;

		Group2(String _title, int _index)
		{
			title =  _title;
			index = _index;
		}
	}

	public Group2 algorithms[] = {
			new Group2("Recursive backtracker", 0),
//			new Group2("Randomized Kruskal's algorithm", 1),
//			new Group2("Randomized Prim's algorithm", 2),
//			new Group2("Randomized Prim's algorithm: Modified version", 3),
//			new Group2("Recursive division method", 4),
	};

	public ControlPanelAlgorithm()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Algorithm", 70, false));
		getChildren().add(ControlPanel.Spacer());

		// combobox containing the algorithms to use
		cbAlgorithm.setMinWidth(190);
		cbAlgorithm.setMaxWidth(190);
//		cbAlgorithm.setVisible(false);
		cbAlgorithm.setTooltip(new Tooltip("List of maze creating algorithm.\nSelect which one to use"));
		getChildren().add(cbAlgorithm);

		System.out.println("algorithms.length: " + algorithms.length);
		for (int i = 0; i < algorithms.length; i++) {
			System.out.println("algorithms[" + i + "].title: " + algorithms[i].title);
			cbAlgorithm.getItems().add(algorithms[i].title);
		}
		cbAlgorithm.setValue(algorithms[0].title);

//				cbAlgorithm.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event)
//					{
//						System.out.println("OnAction: cbAlgorithm: " + cbAlgorithm.getValue());
//					}
//				});
	}
}
