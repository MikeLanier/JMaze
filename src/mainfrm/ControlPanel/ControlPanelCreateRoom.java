package mainfrm.ControlPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainfrm.MainFrm;
import mainfrm.MazeGlobal;

public class ControlPanelCreateRoom extends VBox {

	private MainFrm mainFrm = null;
	private Integer roomOriginX = 3;
	private Integer roomOriginY = 3;
	private Integer roomSizeX = 2;
	private Integer roomSizeY = 2;

	public ControlPanelCreateRoom(MainFrm _mainFrm)
	{
		mainFrm = _mainFrm;

		{
			HBox hboxTitle = new HBox();
			getChildren().add(hboxTitle);

			javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);
			hboxTitle.paddingProperty().setValue(margin);

			hboxTitle.getChildren().add(ControlPanel.Marker("Create a room", 140, false));
		}

		{
			HBox hboxRoomOrigin = new HBox();
			getChildren().add(hboxRoomOrigin);

			javafx.geometry.Insets margin = new javafx.geometry.Insets(0, 5, 0, 20);
			hboxRoomOrigin.paddingProperty().setValue(margin);

			hboxRoomOrigin.getChildren().add(ControlPanel.Marker("Origin", 70, false));

			hboxRoomOrigin.getChildren().add(ControlPanel.Spacer());
			hboxRoomOrigin.getChildren().add(ControlPanel.Marker("x", 10, false));
			hboxRoomOrigin.getChildren().add(ControlPanel.Spacer());

			Integer x = roomOriginX;
			TextField tfRoomOriginX = new TextField();
			tfRoomOriginX.setText(x.toString());
			tfRoomOriginX.setMinWidth(40);
			tfRoomOriginX.setMaxWidth(40);
			tfRoomOriginX.setTooltip(new Tooltip("Number of cells in a row of the maze"));
			hboxRoomOrigin.getChildren().add(tfRoomOriginX);

			tfRoomOriginX.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event)
				{
					System.out.println("Room Origin X: Key event");
					roomOriginX = MazeGlobal.parseTextField(tfRoomOriginX, roomOriginX);
				}
			});

			hboxRoomOrigin.getChildren().add(ControlPanel.Marker("", 10, false));
			hboxRoomOrigin.getChildren().add(ControlPanel.Marker("y", 10, false));
			hboxRoomOrigin.getChildren().add(ControlPanel.Spacer());

			Integer y = roomOriginX;
			TextField tfRoomOriginY = new TextField();
			tfRoomOriginY.setText(y.toString());
			tfRoomOriginY.setMinWidth(40);
			tfRoomOriginY.setMaxWidth(40);
			tfRoomOriginY.setTooltip(new Tooltip("Number of cells in a column of the maze"));
			hboxRoomOrigin.getChildren().add(tfRoomOriginY);

			tfRoomOriginY.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event)
				{
					System.out.println("Room Origin Y: Key event");
					roomOriginY = MazeGlobal.parseTextField(tfRoomOriginY, roomOriginY);
				}
			});

			hboxRoomOrigin.getChildren().add(ControlPanel.Spacer());

			Image img = new Image(getClass().getResource("random.png").toString());
			ImageView iv = new ImageView(img);

			Button btnRandomOrigin = new Button();

			btnRandomOrigin.setGraphic(iv);
			btnRandomOrigin.setMinWidth(25);
			btnRandomOrigin.setMaxWidth(25);
			btnRandomOrigin.setTooltip(new Tooltip("randomly select the start cell"));
			btnRandomOrigin.setDisable(true);
			hboxRoomOrigin.getChildren().add(btnRandomOrigin);
		}

		{
			HBox hboxRoomSize = new HBox();
			getChildren().add(hboxRoomSize);

			javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 0, 20);
			hboxRoomSize.paddingProperty().setValue(margin);

			hboxRoomSize.getChildren().add(ControlPanel.Marker("Size", 70, false));

			hboxRoomSize.getChildren().add(ControlPanel.Spacer());
			hboxRoomSize.getChildren().add(ControlPanel.Marker("w", 10, false));
			hboxRoomSize.getChildren().add(ControlPanel.Spacer());

			Integer x = roomSizeX;
			TextField tfRoomSizeX = new TextField();
			tfRoomSizeX.setText(x.toString());
			tfRoomSizeX.setMinWidth(40);
			tfRoomSizeX.setMaxWidth(40);
			tfRoomSizeX.setTooltip(new Tooltip("Number of cells in a row of the maze"));
			hboxRoomSize.getChildren().add(tfRoomSizeX);

			tfRoomSizeX.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event)
				{
					System.out.println("Room Size X: Key event");
					roomSizeX = MazeGlobal.parseTextField(tfRoomSizeX, roomSizeX);
				}
			});

			hboxRoomSize.getChildren().add(ControlPanel.Marker("", 10, false));
			hboxRoomSize.getChildren().add(ControlPanel.Marker("h", 10, false));
			hboxRoomSize.getChildren().add(ControlPanel.Spacer());

			Integer y = roomSizeY;
			TextField tfRoomSizeY = new TextField();
			tfRoomSizeY.setText(y.toString());
			tfRoomSizeY.setMinWidth(40);
			tfRoomSizeY.setMaxWidth(40);
			tfRoomSizeY.setTooltip(new Tooltip("Number of cells in a column of the maze"));
			hboxRoomSize.getChildren().add(tfRoomSizeY);

			tfRoomSizeY.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event)
				{
					System.out.println("Room Size Y: Key Event");
					roomSizeY = MazeGlobal.parseTextField(tfRoomSizeY, roomSizeY);
				}
			});

			hboxRoomSize.getChildren().add(ControlPanel.Spacer());

			Image img = new Image(getClass().getResource("random.png").toString());
			ImageView iv = new ImageView(img);

			Button btnRandomSize = new Button();

			btnRandomSize.setGraphic(iv);
			btnRandomSize.setMinWidth(25);
			btnRandomSize.setMaxWidth(25);
			btnRandomSize.setTooltip(new Tooltip("randomly select the start cell"));
			btnRandomSize.setDisable(true);
			hboxRoomSize.getChildren().add(btnRandomSize);
		}
		{
			HBox hboxCreate = new HBox();
			getChildren().add(hboxCreate);

			javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 0, 20);
			hboxCreate.paddingProperty().setValue(margin);

			Button btnRoomCreate = new Button();
			btnRoomCreate.setText("Create");
			btnRoomCreate.setMinWidth(70);
			btnRoomCreate.setMaxWidth(70);
			btnRoomCreate.setTooltip(new Tooltip("Print the maze"));
//			btnRoomCreate.setDisable(true);
			hboxCreate.getChildren().add(btnRoomCreate);

			btnRoomCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					mainFrm.mazePanel.createRoom(roomOriginX, roomOriginY, roomSizeX, roomSizeY);
				}
			});

			hboxCreate.getChildren().add(ControlPanel.Spacer());

			Button btnRoomCreateAll = new Button();
			btnRoomCreateAll.setText("Create All");
			btnRoomCreateAll.setMinWidth(70);
			btnRoomCreateAll.setMaxWidth(70);
			btnRoomCreateAll.setTooltip(new Tooltip("Randomly create all the rooms in the maze"));
//			btnRoomCreateAll.setDisable(true);
			hboxCreate.getChildren().add(btnRoomCreateAll);

			btnRoomCreateAll.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
//					mainFrm.mazePanel.CreateAllRoom(roomOriginX, roomOriginY, roomSizeX, roomSizeY);
				}
			});
		}
	}
}
