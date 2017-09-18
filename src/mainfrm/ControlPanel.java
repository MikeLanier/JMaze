package mainfrm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class ControlPanel extends VBox {
	public Button		btnMazeOpen			= new Button();
	public TextField	tfMazeFilename		= new TextField();
	public Button		btnMazeSelect		= new Button();
	public Button		btnMazeSave			= new Button();
	public TextField	tfMazeSaveFilename	= new TextField();
	public Button		btnMazeSaveSelect	= new Button();
	public TextField	tfMazeSizeX			= new TextField();
	public TextField	tfMazeSizeY			= new TextField();
	public TextField	tfCellSize			= new TextField();
	public ComboBox<String> cbAlgorithm		= new ComboBox<>();
	public TextField	tfMazeName			= new TextField();
	public CheckBox 	cbMaze2D			= new CheckBox();
	public CheckBox		cbMaze3D2D			= new CheckBox();
	public CheckBox		cbMaze3D			= new CheckBox();
	public TextField	tfStartCellX		= new TextField();
	public TextField	tfStartCellY		= new TextField();
	public Button		btnStartCellSet		= new Button();
	public ComboBox<String>	tfEntranceX		= new ComboBox<>();
	public ComboBox<String>	tfEntranceY		= new ComboBox<>();
	public Button		btnEntranceSet		= new Button();
	public ComboBox<String>	tfExitX			= new ComboBox<>();
	public ComboBox<String>	tfExitY			= new ComboBox<>();
	public Button		btnExitSet			= new Button();
	public Button		btnMazeCreate		= new Button();
	public Button		btnMazeSolve		= new Button();
	public Button		btnMazePrint		= new Button();

	public int			_sizeX = 10;
	public int			_sizeY = 10;
	public int			_sizeCell = 50;
	public int			_xOffset = 10 + _sizeCell;
	public int			_yOffset = 10 + _sizeCell;

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
			new Group2("Randomized Kruskal's algorithm", 1),
			new Group2("Randomized Prim's algorithm", 2),
			new Group2("Randomized Prim's algorithm: Modified version", 3),
			new Group2("Recursive division method", 4),
	};

	public Random rand = new Random(System.currentTimeMillis());

	private Label Marker(String title, int size, boolean disabled)
	{
		Label lbl = new Label();
		lbl.setText(title);
		if(size > 0) {
			lbl.setMinWidth(size);
			lbl.setMaxWidth(size);
		}
		lbl.setDisable(disabled);
		return lbl;
	}

	private Label Spacer()
	{
		return Marker("", 5, false);
	}

	private MainFrm mainFrm;

	public ControlPanel(MainFrm _mainFrm) {
		System.out.println("buildControls");
		mainFrm = _mainFrm;
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);

		// vertical box for the controls
		//		if(vbControlBox != null)
		{
			setMargin(this, margin);

			// controls for loading a maze
			HBox hbMazeOpenControls = new HBox();
//			if(hbMazeOpenControls != null)
			{
				getChildren().add(hbMazeOpenControls);
				hbMazeOpenControls.paddingProperty().setValue(margin);

				// press this button to open the maze in the filename in the TextField
				btnMazeOpen.setText("Open");
				btnMazeOpen.setMinWidth(70);
				btnMazeOpen.setMaxWidth(70);
				btnMazeOpen.setDisable(true);
				hbMazeOpenControls.getChildren().add(btnMazeOpen);

				btnMazeOpen.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeOpen");
					}
				});

				hbMazeOpenControls.getChildren().add(Spacer());

				// contains the name of the filename containing the maze to load
				tfMazeFilename.setText("filename");
				tfMazeFilename.setMinWidth(150);
				tfMazeFilename.setDisable(true);
				hbMazeOpenControls.getChildren().add(tfMazeFilename);

				tfMazeFilename.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfMazeFilename");
					}
				});

				hbMazeOpenControls.getChildren().add(Spacer());

				// press this button to show the fileopen dialog.
				btnMazeSelect.setText("^");
				btnMazeSelect.setMinWidth(25);
				btnMazeSelect.setDisable(true);
				hbMazeOpenControls.getChildren().add(btnMazeSelect);

				btnMazeSelect.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeSelect");
					}
				});
			}

			// controls for saving the active maze
			HBox hbMazeSaveControls = new HBox();
//			if(hbMazeSaveControls != null)
			{
				getChildren().add(hbMazeSaveControls);
				hbMazeSaveControls.paddingProperty().setValue(margin);

				// press this button to save the current maze to the filename in the TextField control
				btnMazeSave.setText("Save");
				btnMazeSave.setMinWidth(70);
				btnMazeSave.setMaxWidth(70);
				btnMazeSave.setDisable(true);
				hbMazeSaveControls.getChildren().add(btnMazeSave);

				btnMazeSave.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeSave");
					}
				});

				hbMazeSaveControls.getChildren().add(Spacer());

				// contains the filename to which the current maze will be saved
				tfMazeSaveFilename.setText("filename");
				tfMazeSaveFilename.setMinWidth(150);
				tfMazeSaveFilename.setDisable(true);
				hbMazeSaveControls.getChildren().add(tfMazeSaveFilename);

				tfMazeSaveFilename.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: :tfMazeSaveFilename");
					}
				});

				hbMazeSaveControls.getChildren().add(Spacer());

				// press this button to show the filesave dialog
				btnMazeSaveSelect.setText(">");
				btnMazeSaveSelect.setMinWidth(25);
				btnMazeSaveSelect.setDisable(true);
				hbMazeSaveControls.getChildren().add(btnMazeSaveSelect);

				btnMazeSaveSelect.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeSaveSelect");
					}
				});
			}

			// controls for setting the size of the maze in number of cells in x and y
			HBox hbMazeSizeControls = new HBox();
//			if(hbMazeSizeControls != null)
			{
				getChildren().add(hbMazeSizeControls);
				hbMazeSizeControls.paddingProperty().setValue(margin);

				hbMazeSizeControls.getChildren().add(Marker("Size", 70, false));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in x
				Integer x = _sizeX;
				tfMazeSizeX.setText(x.toString());
				tfMazeSizeX.setMinWidth(40);
				tfMazeSizeX.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeX);

//				tfMazeSizeX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeSizeX");
//					}
//				});

				hbMazeSizeControls.getChildren().add(Spacer());
				hbMazeSizeControls.getChildren().add(Marker("x", 0, false));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in y
				Integer y = _sizeY;
				tfMazeSizeY.setText(y.toString());
				tfMazeSizeY.setMinWidth(40);
				tfMazeSizeY.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeY);

//				tfMazeSizeY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeSizeY");
//					}
//				});
			}

			// control for defining the size of a cell in pixels
			HBox hbMazeCellSizeControls = new HBox();
//			if(hbMazeCellSizeControls != null)
			{
				getChildren().add(hbMazeCellSizeControls);
				hbMazeCellSizeControls.paddingProperty().setValue(margin);

				hbMazeCellSizeControls.getChildren().add(Marker("MazeCell Size", 70, false));
				hbMazeCellSizeControls.getChildren().add(Spacer());

				Integer cs = _sizeCell;
				tfCellSize.setText(cs.toString());
				tfCellSize.setMinWidth(40);
				tfCellSize.setMaxWidth(40);
				hbMazeCellSizeControls.getChildren().add(tfCellSize);

//				tfCellSize.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfCellSize");
//					}
//				});
			}

			// controls for defining the algorithm used to create the maze
			HBox hbMazeAlgorithmControls = new HBox();
//			if(hbMazeAlgorithmControls != null)
			{
				getChildren().add(hbMazeAlgorithmControls);
				hbMazeAlgorithmControls.paddingProperty().setValue(margin);

				hbMazeAlgorithmControls.getChildren().add(Marker("Algorithm", 70, false));
				hbMazeAlgorithmControls.getChildren().add(Spacer());

				// combobox containing the algorithms to use
				cbAlgorithm.setMinWidth(150);
				cbAlgorithm.setMaxWidth(150);
				hbMazeAlgorithmControls.getChildren().add(cbAlgorithm);

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

			// controls for setting the name of the maze
			HBox hbMazeNameControls = new HBox();
//			if(hbMazeNameControls != null)
			{
				getChildren().add(hbMazeNameControls);
				hbMazeNameControls.paddingProperty().setValue(margin);

				hbMazeNameControls.getChildren().add(Marker("Maze Name", 70, true));
				hbMazeNameControls.getChildren().add(Spacer());

				tfMazeName.setMinWidth(150);
				tfMazeName.setMaxWidth(150);
				tfMazeName.setDisable(true);
				hbMazeNameControls.getChildren().add(tfMazeName);

//				tfMazeName.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfMazeName");
//					}
//				});
			}

			// controls to define the maze as 2D or 3D
			HBox hbMaze2D3DControls = new HBox();
//			if(hbMaze2D3DControls != null)
			{
				getChildren().add(hbMaze2D3DControls);
				hbMaze2D3DControls.paddingProperty().setValue(margin);

				hbMaze2D3DControls.getChildren().add(Marker("", 70, false));
				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze2D.setText("2D");
				cbMaze2D.setMinWidth(40);
				cbMaze2D.setMaxWidth(40);
				cbMaze2D.setIndeterminate(false);
				cbMaze2D.setSelected(true);
//				cbMaze2D.setDisable(true);
				hbMaze2D3DControls.getChildren().add(cbMaze2D);

//				cbMaze2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
//					@Override
//					public void handle(MouseEvent event) {
//						System.out.println("OnMouseClicked: cbMaze2D");
//					}
//				});

				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze3D2D.setText("3D/2D");
				cbMaze3D2D.setMinWidth(60);
				cbMaze3D2D.setMaxWidth(60);
//				cbMaze3D2D.setDisable(true);
				hbMaze2D3DControls.getChildren().add(cbMaze3D2D);

				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze3D.setText("3D");
				cbMaze3D.setMinWidth(40);
				cbMaze3D.setMaxWidth(40);
//				cbMaze3D.setDisable(true);
				hbMaze2D3DControls.getChildren().add(cbMaze3D);

//				cbMaze3D.setOnMouseClicked(new EventHandler<MouseEvent>() {
//					@Override
//					public void handle(MouseEvent event) {
//						System.out.println("OnMouseClicked: cbMAze3D");
//					}
//				});
			}

			// controls for setting the start cell
			HBox hbMazeStartCellControls = new HBox();
//			if(hbMazeStartCellControls != null)
			{
				getChildren().add(hbMazeStartCellControls);
				hbMazeStartCellControls.paddingProperty().setValue(margin);

				hbMazeStartCellControls.getChildren().add(Marker("Start MazeCell", 70, false));
				hbMazeStartCellControls.getChildren().add(Spacer());

				Integer x = _sizeX / 2;
				Integer y = _sizeY / 2;

				tfStartCellX.setText(x.toString());
				tfStartCellX.setMinWidth(40);
				tfStartCellX.setMaxWidth(40);
//				tfStartCellX.setDisable(true);
				hbMazeStartCellControls.getChildren().add(tfStartCellX);

//				tfStartCellX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfStartCellX");
//					}
//				});

				hbMazeStartCellControls.getChildren().add(Spacer());
				hbMazeStartCellControls.getChildren().add(Marker("x", 0, false));
				hbMazeStartCellControls.getChildren().add(Spacer());

				tfStartCellY.setText(y.toString());
				tfStartCellY.setMinWidth(40);
				tfStartCellY.setMaxWidth(40);
//				tfStartCellY.setDisable(true);
				hbMazeStartCellControls.getChildren().add(tfStartCellY);

//				tfStartCellY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfStartCellY");
//					}
//				});

				hbMazeStartCellControls.getChildren().add(Spacer());

				Image img = new Image(getClass().getResource("random.png").toString());
				ImageView iv = new ImageView(img);

				btnStartCellSet.setGraphic(iv);
				btnStartCellSet.setMinWidth(25);
				btnStartCellSet.setMaxWidth(25);
//				btnStartCellSet.setDisable(true);
				hbMazeStartCellControls.getChildren().add(btnStartCellSet);

				btnStartCellSet.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnStartCellSet");
					}
				});
			}

			// controls for setting the entrance
			HBox hbMazeEntranceControls = new HBox();
//			if(hbMazeEntranceControls != null)
			{
				getChildren().add(hbMazeEntranceControls);
				hbMazeEntranceControls.paddingProperty().setValue(margin);

				hbMazeEntranceControls.getChildren().add(Marker("Entrance", 70, false));
				hbMazeEntranceControls.getChildren().add(Spacer());

				ArrayList<String> itemsX = new ArrayList<>();
				itemsX.add("west");
				itemsX.add("east");
				for (Integer i = 0; i < _sizeX; i++) {
					itemsX.add(i.toString());
				}

				tfEntranceX.getItems().setAll(itemsX);
				tfEntranceX.setValue(itemsX.get(0));
				tfEntranceX.setMinWidth(70);
				tfEntranceX.setMaxWidth(70);

				hbMazeEntranceControls.getChildren().add(tfEntranceX);

//				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceX");
//					}
//				});

				hbMazeEntranceControls.getChildren().add(Spacer());
				hbMazeEntranceControls.getChildren().add(Marker("x", 0, false));
				hbMazeEntranceControls.getChildren().add(Spacer());

				ArrayList<String> itemsY = new ArrayList<>();
				itemsY.add("north");
				itemsY.add("south");
				for (Integer i = 0; i < _sizeY; i++) {
					itemsY.add(i.toString());
				}

				tfEntranceY.getItems().setAll(itemsY);
				tfEntranceY.setValue(itemsY.get(_sizeY / 3 + 2));
				tfEntranceY.setMinWidth(70);
				tfEntranceY.setMaxWidth(70);

				hbMazeEntranceControls.getChildren().add(tfEntranceY);

//				tfEntranceY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfEntranceY");
//					}
//				});

				hbMazeEntranceControls.getChildren().add(Spacer());

				Image img = new Image(getClass().getResource("random.png").toString());
				ImageView iv = new ImageView(img);

				btnEntranceSet.setGraphic(iv);
				btnEntranceSet.setMinWidth(25);
				btnEntranceSet.setMaxWidth(25);
				btnEntranceSet.setDisable(true);
				hbMazeEntranceControls.getChildren().add(btnEntranceSet);

				btnEntranceSet.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnEntranceSet");
					}
				});
			}

			// controls for setting the exit
			HBox hbMazeExitControls = new HBox();
//			if(hbMazeEntranceControls != null)
			{
				getChildren().add(hbMazeExitControls);
				hbMazeExitControls.paddingProperty().setValue(margin);

				hbMazeExitControls.getChildren().add(Marker("Exit", 70, false));
				hbMazeExitControls.getChildren().add(Spacer());

				ArrayList<String> itemsX = new ArrayList<>();
				itemsX.add("west");
				itemsX.add("east");
				for (Integer i = 0; i < _sizeX; i++) {
					itemsX.add(i.toString());
				}

				tfExitX.getItems().setAll(itemsX);
				tfExitX.setValue(itemsX.get(1));
				tfExitX.setMinWidth(70);
				tfExitX.setMaxWidth(70);

				hbMazeExitControls.getChildren().add(tfExitX);

//				tfExitX.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitX");
//					}
//				});

				hbMazeExitControls.getChildren().add(Spacer());
				hbMazeExitControls.getChildren().add(Marker("x", 0, false));
				hbMazeExitControls.getChildren().add(Spacer());

				ArrayList<String> itemsY = new ArrayList<>();
				itemsY.add("north");
				itemsY.add("south");
				for (Integer i = 0; i < _sizeY; i++) {
					itemsY.add(i.toString());
				}

				tfExitY.getItems().setAll(itemsY);
				tfExitY.setValue(itemsY.get(_sizeY * 2 / 3 + 2));
				tfExitY.setMinWidth(70);
				tfExitY.setMaxWidth(70);

				hbMazeExitControls.getChildren().add(tfExitY);

//				tfExitY.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						System.out.println("OnAction: tfExitY");
//					}
//				});

				hbMazeExitControls.getChildren().add(Spacer());

				Image img = new Image(getClass().getResource("random.png").toString());
				ImageView iv = new ImageView(img);

				btnExitSet.setGraphic(iv);
				btnExitSet.setMinWidth(25);
				btnExitSet.setMaxWidth(25);
				btnExitSet.setDisable(true);
				hbMazeExitControls.getChildren().add(btnExitSet);

				btnExitSet.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnExitSet");
					}
				});
			}

			// controls for start construction of the maze
			HBox hbMazeCreateControls = new HBox();
//			if(hbMazeCreateControls != null)
			{
				getChildren().add(hbMazeCreateControls);
				hbMazeCreateControls.paddingProperty().setValue(margin);

				btnMazeCreate.setText("Create");
				btnMazeCreate.setMinWidth(70);
				btnMazeCreate.setMaxWidth(70);
				hbMazeCreateControls.getChildren().add(btnMazeCreate);

				btnMazeCreate.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeCreate");
						mainFrm.createMaze();
					}
				});
			}

			// controls to solve the puzzle
			HBox hbMazeSolveControls = new HBox();
//			if(hbMazeSolveControls != null)
			{
				getChildren().add(hbMazeSolveControls);
				hbMazeSolveControls.paddingProperty().setValue(margin);

				btnMazeSolve.setText("Solve");
				btnMazeSolve.setMinWidth(70);
				btnMazeSolve.setMaxWidth(70);
				btnMazeSolve.setDisable(true);
				hbMazeSolveControls.getChildren().add(btnMazeSolve);

				btnMazeSolve.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeSolve");
					}
				});
			}

			// controls to print the maze
			HBox hbMazePrintControls = new HBox();
//			if(hbMazePrintControls != null)
			{
				getChildren().add(hbMazePrintControls);
				hbMazePrintControls.paddingProperty().setValue(margin);

				btnMazePrint.setText("Print");
				btnMazePrint.setMinWidth(70);
				btnMazePrint.setMaxWidth(70);
				btnMazePrint.setDisable(true);
				hbMazePrintControls.getChildren().add(btnMazePrint);

				btnMazePrint.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazePrint");
					}
				});
			}
		}
	}
}
