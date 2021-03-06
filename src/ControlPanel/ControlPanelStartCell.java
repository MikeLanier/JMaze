package ControlPanel;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import MainFrm.MainFrm;
import Maze.MazeGlobal;

public class ControlPanelStartCell extends HBox {
	private TextField tfStartCellX		= new TextField();
	private TextField tfStartCellY		= new TextField();
	private Button btnStartCellSet		= new Button();

	private MainFrm mainFrm = null;

	public ControlPanelStartCell(MainFrm _mainFrm, int _sizeX, int _sizeY)
	{
		mainFrm = _mainFrm;

		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
		paddingProperty().setValue(margin);

		getChildren().add(ControlPanel.Marker("Start Cell", 60, false));
		getChildren().add(ControlPanel.Spacer());

		tfStartCellX.setText(MazeGlobal.startCellX.toString());
		tfStartCellX.setMinWidth(70);
		tfStartCellX.setMaxWidth(70);
		tfStartCellX.setTooltip(new ControlPanelTooltip("x coordinate of cell at which to start\ncreating the maze."));
//		tfStartCellX.setAlignment(Pos.CENTER);
//		tfStartCellX.setDisable(true);
		getChildren().add(tfStartCellX);

		tfStartCellX.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("OnKeyReleased: tfStartCellX");

				Integer i = MazeGlobal.parseTextField(tfStartCellX, MazeGlobal.startCellX);
				if(i > 0 && i <= MazeGlobal.sizeX) {
					MazeGlobal.startCellX = i;
					mainFrm.mazePanel.updateStartCell();
					mainFrm.mazePanel.drawMaze();
				} else {
//					tfStartCellX.setText(MazeGlobal.startCellX.toString());
				}
			}
		});

		getChildren().add(ControlPanel.Spacer());
		getChildren().add(ControlPanel.Marker("x", 10, false));
		getChildren().add(ControlPanel.Spacer());

		tfStartCellY.setText(MazeGlobal.startCellY.toString());
		tfStartCellY.setMinWidth(75);
		tfStartCellY.setMaxWidth(75);
		tfStartCellY.setTooltip(new ControlPanelTooltip("y coordinate of cell at which to start\ncreating the maze."));
//		tfStartCellY.setAlignment(Pos.CENTER);
//		tfStartCellY.setDisable(true);
		getChildren().add(tfStartCellY);

		tfStartCellY.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("OnKeyReleased: tfStartCellY");
				Integer i = MazeGlobal.parseTextField(tfStartCellY, MazeGlobal.startCellY);
				if(i > 0 && i <= MazeGlobal.sizeY) {
					MazeGlobal.startCellY = i;
					mainFrm.mazePanel.updateStartCell();
					mainFrm.mazePanel.drawMaze();
				} else {
//					tfStartCellY.setText(MazeGlobal.startCellY.toString());
				}
			}
		});

		getChildren().add(ControlPanel.Spacer());

		Image img = new Image(getClass().getResource("random.png").toString());
		ImageView iv = new ImageView(img);

		btnStartCellSet.setGraphic(iv);
		btnStartCellSet.setMinWidth(25);
		btnStartCellSet.setMaxWidth(25);
		btnStartCellSet.setTooltip(new ControlPanelTooltip("randomly select the start cell"));
//		btnStartCellSet.setDisable(true);
		getChildren().add(btnStartCellSet);

		btnStartCellSet.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("OnMousePressed: btnStartCellSet");
				int x = MazeGlobal.rand.nextInt(MazeGlobal.sizeX) + 1;
				int y = MazeGlobal.rand.nextInt(MazeGlobal.sizeY) + 1;

				MazeGlobal.startCellX = x;
				MazeGlobal.startCellY = y;

				tfStartCellX.setText(MazeGlobal.startCellX.toString());
				tfStartCellY.setText(MazeGlobal.startCellY.toString());

				mainFrm.mazePanel.updateStartCell();
				mainFrm.mazePanel.drawMaze();
			}
		});
	}
}
