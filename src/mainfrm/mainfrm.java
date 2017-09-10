package mainfrm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class mainfrm extends GridPane
{
	private Button		btnMazeOpen			= new Button();
	private TextField	tfMazeFilename		= new TextField();
	private Button		btnMazeSelect		= new Button();
	private Button		btnMazeSave			= new Button();
	private TextField	tfMazeSaveFilename	= new TextField();
	private Button		btnMazeSaveSelect	= new Button();
	private TextField	tfMazeSizeX			= new TextField();
	private TextField	tfMazeSizeY			= new TextField();
	private TextField	tfCellSize			= new TextField();
	private ComboBox<String> cbAlgorithm	= new ComboBox<>();
	private TextField	tfMazeName			= new TextField();
	private CheckBox	cbMaze2D			= new CheckBox();
	private CheckBox	cbMaze3D			= new CheckBox();
	private TextField	tfStartCellX		= new TextField();
	private TextField	tfStartCellY		= new TextField();
	private Button		btnStartCellSet		= new Button();
	private TextField	tfEntranceX			= new TextField();
	private TextField	tfEntranceY			= new TextField();
	private Button		btnEntranceSet		= new Button();
	private TextField	tfExitX				= new TextField();
	private TextField	tfExitY				= new TextField();
	private Button		btnExitSet			= new Button();
	private Button		btnMazeCreate		= new Button();
	private Button		btnMazeSolve		= new Button();
	private Button		btnMazePrint		= new Button();
	private Canvas		cvsMazePanel		= new Canvas();

	private int			_sizeX = 20;
	private int			_sizeY = 20;
	private int			_sizeCell = 25;
	private int			_xOffset = 10 + _sizeCell;
	private int			_yOffset = 10 + _sizeCell;

	private Map<Integer, Cell> cells = new HashMap<Integer, Cell>();
	private Map<Integer, Wall> walls = new HashMap<Integer, Wall>();

	private String algorithms[] = {
		"Depth-first search",
		"Recursive backtracker",
		"Randomized Kruskal's algorithm",
		"Randomized Prim's algorithm",
		"Randomized Prim's algorithm: Modified version",
		"Recursive division method",
		"Simple algorithms",
		"Cellular automaton algorithms"
	};

	private Label Marker(String title, int size)
	{
		Label lbl = new Label();
		lbl.setText(title);
		if(size > 0) {
			lbl.setMinWidth(size);
			lbl.setMaxWidth(size);
		}
		return lbl;
	}

	private Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}

	private Label Spacer()
	{
		return Marker("", 5);
	}

	public mainfrm() {
		System.out.println("mainfrm");

		// define the grid layout as two columns and one row.
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPrefWidth(300);
		col2.setPercentWidth(70);
		getColumnConstraints().addAll(col1, col2);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		getRowConstraints().addAll(row1);

		setGridLinesVisible(true);

		buildControls();
		buildMazePanel();
		drawMaze();

		Integer i = _sizeX;
		tfMazeSizeX.setText(i.toString());

		i = _sizeY;
		tfMazeSizeY.setText(i.toString());

		i = _sizeCell;
		tfCellSize.setText(i.toString());
	}

	private void createCell(int x, int y)
	{
		Wall west = walls.get(ID(x, y, true));
		if(west == null)
		{
			west = new Wall(x, y, true);
			walls.put(west.ID(), west);
		}

		Wall north = walls.get(ID(x, y, false));
		if(north == null)
		{
			north = new Wall(x, y, false);
			walls.put(north.ID(), north);
		}

		Wall east = walls.get(ID(x+1, y, false));
		if(east == null)
		{
			east = new Wall(x+1, y, false);
			walls.put(east.ID(), east);
		}

		Wall south = walls.get(ID(x, y+1, true));
		if(south == null)
		{
			south = new Wall(x, y+1, true);
			walls.put(south.ID(), south);
		}

		Cell cell = new Cell(x, y, west, north, east, south);
		cells.put(cell.ID(), cell);
	}

	private void buildMazePanel()
	{
		System.out.println("buildMazepanel");

		HBox vbMazeBox = new HBox();
		add(vbMazeBox, 1, 0);

		int width = (_sizeX + 2) * _sizeCell + 1;
		int height = (_sizeY + 2) * _sizeCell + 1;
		System.out.println("width, height: " + width + ", " + height);

		cvsMazePanel.setWidth(width+_xOffset);
		cvsMazePanel.setHeight(height+_yOffset);

		vbMazeBox.getChildren().add(cvsMazePanel);
		GraphicsContext gc = cvsMazePanel.getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.AQUA);
		gc.fillRect(_xOffset, _yOffset, width, height);

		for(int x=1; x<=_sizeX; x++)
		{
			for(int y=1; y<=_sizeY; y++)
			{
				createCell(x, y);
			}
		}

		System.out.println("cells.size: " + cells.size());
		System.out.println("walls.size: " + walls.size());

		Wall w = walls.get(ID(3,3,false));
		w.Open(true);
	}

	private void drawMaze()
	{
		GraphicsContext gc = cvsMazePanel.getGraphicsContext2D();

		Collection<Wall> wc = walls.values();
		for(Wall w: wc)
		{
			w.draw(gc, _xOffset, _yOffset, _sizeCell);
		}
	}

	private void buildControls()
	{
		javafx.geometry.Insets margin = new javafx.geometry.Insets(5, 5, 5, 5);

		// vertical box for the controls
		VBox vbControlBox = new VBox();
//		if(vbControlBox != null)
		{
			add(vbControlBox, 0, 0);
			setMargin(vbControlBox, margin);

			// controls for loading a maze
			HBox hbMazeOpenControls = new HBox();
//			if(hbMazeOpenControls != null)
			{
				vbControlBox.getChildren().add(hbMazeOpenControls);
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
				vbControlBox.getChildren().add(hbMazeSaveControls);
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
				vbControlBox.getChildren().add(hbMazeSizeControls);
				hbMazeSizeControls.paddingProperty().setValue(margin);

				hbMazeSizeControls.getChildren().add(Marker("Size", 70));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in x
				tfMazeSizeX.setText("512");
				tfMazeSizeX.setMinWidth(40);
				tfMazeSizeX.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeX);

				tfMazeSizeX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfMazeSizeX");
					}
				});

				hbMazeSizeControls.getChildren().add(Spacer());
				hbMazeSizeControls.getChildren().add(Marker("x", 0));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in y
				tfMazeSizeY.setText("512");
				tfMazeSizeY.setMinWidth(40);
				tfMazeSizeY.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeY);

				tfMazeSizeY.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfMazeSizeY");
					}
				});
			}

			// control for defining the size of a cell in pixels
			HBox hbMazeCellSizeControls = new HBox();
//			if(hbMazeCellSizeControls != null)
			{
				vbControlBox.getChildren().add(hbMazeCellSizeControls);
				hbMazeCellSizeControls.paddingProperty().setValue(margin);

				hbMazeCellSizeControls.getChildren().add(Marker("Cell Size", 70));
				hbMazeCellSizeControls.getChildren().add(Spacer());

				tfCellSize.setText("25");
				tfCellSize.setMinWidth(40);
				tfCellSize.setMaxWidth(40);
				hbMazeCellSizeControls.getChildren().add(tfCellSize);

				tfCellSize.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfCellSize");
					}
				});
			}

			// controls for defining the algorithm used to create the maze
			HBox hbMazeAlgorithmControls = new HBox();
//			if(hbMazeAlgorithmControls != null)
			{
				vbControlBox.getChildren().add(hbMazeAlgorithmControls);
				hbMazeAlgorithmControls.paddingProperty().setValue(margin);

				hbMazeAlgorithmControls.getChildren().add(Marker("Algorithm", 70));
				hbMazeAlgorithmControls.getChildren().add(Spacer());

				// combobox containing the algorithms to use
				cbAlgorithm.setMinWidth(150);
				cbAlgorithm.setMaxWidth(150);
				hbMazeAlgorithmControls.getChildren().add(cbAlgorithm);

				cbAlgorithm.getItems().setAll(algorithms);
				cbAlgorithm.setValue(algorithms[0]);

				cbAlgorithm.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event)
					{
						System.out.println("OnAction: cbAlgorithm: " + cbAlgorithm.getValue());
					}
				});
			}

			// controls for setting the name of the maze
			HBox hbMazeNameControls = new HBox();
//			if(hbMazeNameControls != null)
			{
				vbControlBox.getChildren().add(hbMazeNameControls);
				hbMazeNameControls.paddingProperty().setValue(margin);

				Label title = Marker("Maze Name", 70);
				title.setDisable(true);
				hbMazeNameControls.getChildren().add(title);
				hbMazeNameControls.getChildren().add(Spacer());

				tfMazeName.setMinWidth(150);
				tfMazeName.setMaxWidth(150);
				tfMazeName.setDisable(true);
				hbMazeNameControls.getChildren().add(tfMazeName);

				tfMazeName.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfMazeName");
					}
				});
			}

			// controls to define the maze as 2D or 3D
			HBox hbMaze2D3DControls = new HBox();
//			if(hbMaze2D3DControls != null)
			{
				vbControlBox.getChildren().add(hbMaze2D3DControls);
				hbMaze2D3DControls.paddingProperty().setValue(margin);

				hbMaze2D3DControls.getChildren().add(Marker("", 70));
				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze2D.setText("2D");
				cbMaze2D.setMinWidth(40);
				cbMaze2D.setMaxWidth(40);
				cbMaze2D.setIndeterminate(false);
				cbMaze2D.setSelected(true);
				cbMaze2D.setDisable(true);
				hbMaze2D3DControls.getChildren().add(cbMaze2D);

				cbMaze2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMouseClicked: cbMaze2D");
					}
				});

				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze3D.setText("3D");
				cbMaze3D.setMinWidth(40);
				cbMaze3D.setMaxWidth(40);
				cbMaze3D.setDisable(true);
				hbMaze2D3DControls.getChildren().add(cbMaze3D);

				cbMaze3D.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMouseClicked: cbMAze3D");
					}
				});
			}

			// controls for setting the start cell
			HBox hbMazeStartCellControls = new HBox();
//			if(hbMazeStartCellControls != null)
			{
				vbControlBox.getChildren().add(hbMazeStartCellControls);
				hbMazeStartCellControls.paddingProperty().setValue(margin);

				hbMazeStartCellControls.getChildren().add(Marker("Start Cell", 70));
				hbMazeStartCellControls.getChildren().add(Spacer());

				Integer x = new Integer(_sizeX/2);
				Integer y = new Integer(_sizeY/2);

				tfStartCellX.setText(x.toString());
				tfStartCellX.setMinWidth(40);
				tfStartCellX.setMaxWidth(40);
				hbMazeStartCellControls.getChildren().add(tfStartCellX);

				tfStartCellX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfStartCellX");
					}
				});

				hbMazeStartCellControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeStartCellControls.getChildren().add(lblX);

				hbMazeStartCellControls.getChildren().add(Spacer());

				tfStartCellY.setText(x.toString());
				tfStartCellY.setMinWidth(40);
				tfStartCellY.setMaxWidth(40);
				hbMazeStartCellControls.getChildren().add(tfStartCellY);

				tfStartCellY.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfStartCellY");
					}
				});

				hbMazeStartCellControls.getChildren().add(Spacer());

				btnStartCellSet.setText("R");
				btnStartCellSet.setMinWidth(25);
				btnStartCellSet.setMaxWidth(25);
				btnStartCellSet.setDisable(true);
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
				vbControlBox.getChildren().add(hbMazeEntranceControls);
				hbMazeEntranceControls.paddingProperty().setValue(margin);

				hbMazeEntranceControls.getChildren().add(Marker("Entrance", 70));
				hbMazeEntranceControls.getChildren().add(Spacer());

				Integer x = new Integer(0);
				Integer y = new Integer(_sizeY/3);
				createCell(x,y);

				tfEntranceX.setText("left");
				tfEntranceX.setMinWidth(40);
				tfEntranceX.setMaxWidth(40);
				hbMazeEntranceControls.getChildren().add(tfEntranceX);

				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfEntranceX");
					}
				});

				hbMazeEntranceControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeEntranceControls.getChildren().add(lblX);

				hbMazeEntranceControls.getChildren().add(Spacer());

				tfEntranceY.setText(y.toString());
				tfEntranceY.setMinWidth(40);
				tfEntranceY.setMaxWidth(40);
				hbMazeEntranceControls.getChildren().add(tfEntranceY);

				tfEntranceY.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfEntranceY");
					}
				});

				hbMazeEntranceControls.getChildren().add(Spacer());

				btnEntranceSet.setText("R");
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
				vbControlBox.getChildren().add(hbMazeExitControls);
				hbMazeExitControls.paddingProperty().setValue(margin);

				hbMazeExitControls.getChildren().add(Marker("Exit", 70));
				hbMazeExitControls.getChildren().add(Spacer());

				Integer x = new Integer(_sizeX+1);
				Integer y = new Integer(_sizeY*2/3);
				createCell(x,y);

				tfExitX.setText("right");
				tfExitX.setMinWidth(40);
				tfExitX.setMaxWidth(40);
				hbMazeExitControls.getChildren().add(tfExitX);

				tfExitX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfExitX");
					}
				});

				hbMazeExitControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeExitControls.getChildren().add(lblX);

				hbMazeExitControls.getChildren().add(Spacer());

				tfExitY.setText(y.toString());
				tfExitY.setMinWidth(40);
				tfExitY.setMaxWidth(40);
				hbMazeExitControls.getChildren().add(tfExitY);

				tfExitY.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfExitY");
					}
				});

				hbMazeExitControls.getChildren().add(Spacer());

				btnExitSet.setText("R");
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
				vbControlBox.getChildren().add(hbMazeCreateControls);
				hbMazeCreateControls.paddingProperty().setValue(margin);

				btnMazeCreate.setText("Create");
				btnMazeCreate.setMinWidth(70);
				btnMazeCreate.setMaxWidth(70);
				hbMazeCreateControls.getChildren().add(btnMazeCreate);

				btnMazeCreate.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("OnMousePressed: btnMazeCreate");
					}
				});
			}

			// controls to solve the puzzle
			HBox hbMazeSolveControls = new HBox();
//			if(hbMazeSolveControls != null)
			{
				vbControlBox.getChildren().add(hbMazeSolveControls);
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
				vbControlBox.getChildren().add(hbMazePrintControls);
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
