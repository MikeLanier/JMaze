package mainfrm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

import java.util.*;

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
	private ComboBox<String>	tfEntranceX	= new ComboBox<>();
	private ComboBox<String>	tfEntranceY	= new ComboBox<>();
	private Button		btnEntranceSet		= new Button();
	private ComboBox<String>	tfExitX		= new ComboBox<>();
	private ComboBox<String>	tfExitY		= new ComboBox<>();
	private Button		btnExitSet			= new Button();
	private Button		btnMazeCreate		= new Button();
	private Button		btnMazeSolve		= new Button();
	private Button		btnMazePrint		= new Button();
	private Canvas		cvsMazePanel		= new Canvas();

	private int			_sizeX = 10;
	private int			_sizeY = 10;
	private int			_sizeCell = 50;
	private int			_xOffset = 10 + _sizeCell;
	private int			_yOffset = 10 + _sizeCell;

	private Map<Integer, Cell> cells = new HashMap<Integer, Cell>();
	private Map<Integer, Wall> walls = new HashMap<Integer, Wall>();

	private Cell	currentCell = null;
	private Cell	entranceCell = null;
	private Cell	exitCell = null;

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

	private Random rand = new Random(System.currentTimeMillis());

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

	private Integer ID(int xOrigin, int yOrigin, boolean horizontal)
	{
		int h = (horizontal) ? 0x40000000 : 0;

		return (xOrigin & 0x7fff) |
				(yOrigin & 0x7fff) << 15 |
				h;
	}

	private Label Spacer()
	{
		return Marker("", 5, false);
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

		System.out.println("start cell: " + tfStartCellX.getText() + "," + tfStartCellX.getText());
		int startCellX = Integer.parseInt(tfStartCellX.getText());
		int startCellY = Integer.parseInt(tfStartCellY.getText());
		currentCell = cells.get(ID(startCellX, startCellY, false));
		if(currentCell != null)
		{
			currentCell.SetType(Cell.CellType.eCellTypeStart);
		}

		entranceCell.SetType(Cell.CellType.eCellTypeEntrance);
		exitCell.SetType(Cell.CellType.eCellTypeExit);

		if(entranceCell.W(Cell.west) != null)	entranceCell.W(Cell.west).Open(true);
		if(entranceCell.W(Cell.north) != null)	entranceCell.W(Cell.north).Open(true);
		if(entranceCell.W(Cell.east) != null)	entranceCell.W(Cell.east).Open(true);
		if(entranceCell.W(Cell.south) != null)	entranceCell.W(Cell.south).Open(true);

		if(exitCell.W(Cell.west) != null)	exitCell.W(Cell.west).Open(true);
		if(exitCell.W(Cell.north) != null)	exitCell.W(Cell.north).Open(true);
		if(exitCell.W(Cell.east) != null)	exitCell.W(Cell.east).Open(true);
		if(exitCell.W(Cell.south) != null)	exitCell.W(Cell.south).Open(true);

		drawMaze();
	}

	private Cell createCell(int x, int y)
	{
		Wall west = walls.get(ID(x, y, false));
		if(west == null)
		{
			west = new Wall(x, y, false);
			walls.put(west.ID(), west);
		}

		Wall north = walls.get(ID(x, y, true));
		if(north == null)
		{
			north = new Wall(x, y, true);
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

		return cell;
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
		gc.setFill(javafx.scene.paint.Color.WHITE);
		gc.fillRect(_xOffset, _yOffset, width, height);

		for(int x=1; x<=_sizeX; x++)
		{
			for(int y=1; y<=_sizeY; y++)
			{
				createCell(x, y);
			}
		}

//		System.out.println("cells.size: " + cells.size());
//		System.out.println("walls.size: " + walls.size());
	}

	private void drawMaze()
	{
		GraphicsContext gc = cvsMazePanel.getGraphicsContext2D();

//		currentCell.W(Cell.west).ClosedColor(Color.RED);
//		currentCell.W(Cell.north).ClosedColor(Color.GREEN);
//		currentCell.W(Cell.east).ClosedColor(Color.BLUE);
//		currentCell.W(Cell.south).ClosedColor(Color.ORANGE);
//
//		currentCell.W(Cell.west).draw(gc, _xOffset, _yOffset, _sizeCell);
//		currentCell.W(Cell.north).draw(gc, _xOffset, _yOffset, _sizeCell);
//		currentCell.W(Cell.east).draw(gc, _xOffset, _yOffset, _sizeCell);
//		currentCell.W(Cell.south).draw(gc, _xOffset, _yOffset, _sizeCell);

		Collection<Cell> cc = cells.values();
		for(Cell c: cc)
		{
			c.draw(gc, _xOffset, _yOffset, _sizeCell);
		}

		Collection<Wall> wc = walls.values();
		for(Wall w: wc)
		{
			w.draw(gc, _xOffset, _yOffset, _sizeCell);
		}
	}

	private void createMaze()
	{
		System.out.println("createMaze");

		if(currentCell != null) {
			int x = currentCell.X();
			int y = currentCell.Y();

			System.out.println("createMaze: currentCell: " + x + ", " + y);

			currentCell.Visited(true);
			System.out.println("currentCell: " + x + ", " + y);

			Cell westCell = cells.get(ID(x - 1, y, false));
			Cell northCell = cells.get(ID(x, y - 1, false));
			Cell eastCell = cells.get(ID(x + 1, y, false));
			Cell southCell = cells.get(ID(x, y + 1, false));

			if (westCell != null) System.out.println("West Cell Visited: " + westCell.Visited());
			if (northCell != null) System.out.println("North Cell Visited: " + northCell.Visited());
			if (eastCell != null) System.out.println("East Cell Visited: " + eastCell.Visited());
			if (southCell != null) System.out.println("South Cell Visited: " + southCell.Visited());

			List<Pair<Cell, Wall>> cells = new ArrayList<Pair<Cell, Wall>>();

			if (westCell != null && !westCell.Visited()) cells.add(new Pair(westCell, currentCell.W(Cell.west)));
			if (northCell != null && !northCell.Visited()) cells.add(new Pair(northCell, currentCell.W(Cell.north)));
			if (eastCell != null && !eastCell.Visited()) cells.add(new Pair(eastCell, currentCell.W(Cell.east)));
			if (southCell != null && !southCell.Visited()) cells.add(new Pair(southCell, currentCell.W(Cell.south)));

			System.out.println("number of unvisitor neighbors: " + cells.size());

			int r = rand.nextInt(cells.size());
			System.out.println("random number: " + r);

			cells.get(r).getValue().Open(true);
			currentCell.SetType(Cell.CellType.eNormal);
			currentCell = cells.get(r).getKey();
			currentCell.SetType(Cell.CellType.eCellTypeStart);
			drawMaze();
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

				hbMazeSizeControls.getChildren().add(Marker("Size", 70, true));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in x
				Integer x = _sizeX;
				tfMazeSizeX.setText(x.toString());
				tfMazeSizeX.setMinWidth(40);
				tfMazeSizeX.setMaxWidth(40);
				tfMazeSizeX.setDisable(true);
				hbMazeSizeControls.getChildren().add(tfMazeSizeX);

				tfMazeSizeX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfMazeSizeX");
					}
				});

				hbMazeSizeControls.getChildren().add(Spacer());
				hbMazeSizeControls.getChildren().add(Marker("x", 0, true));
				hbMazeSizeControls.getChildren().add(Spacer());

				// number of cells in y
				Integer y = _sizeY;
				tfMazeSizeY.setText(y.toString());
				tfMazeSizeY.setMinWidth(40);
				tfMazeSizeY.setMaxWidth(40);
				tfMazeSizeY.setDisable(true);
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

				hbMazeCellSizeControls.getChildren().add(Marker("Cell Size", 70, true));
				hbMazeCellSizeControls.getChildren().add(Spacer());

				Integer cs = _sizeCell;
				tfCellSize.setText(cs.toString());
				tfCellSize.setMinWidth(40);
				tfCellSize.setMaxWidth(40);
				tfCellSize.setDisable(true);
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

				hbMazeAlgorithmControls.getChildren().add(Marker("Algorithm", 70, true));
				hbMazeAlgorithmControls.getChildren().add(Spacer());

				// combobox containing the algorithms to use
				cbAlgorithm.setMinWidth(150);
				cbAlgorithm.setMaxWidth(150);
				cbAlgorithm.setDisable(true);
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

				hbMazeNameControls.getChildren().add(Marker("Maze Name", 70, true));
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

				hbMaze2D3DControls.getChildren().add(Marker("", 70, false));
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

				hbMazeStartCellControls.getChildren().add(Marker("Start Cell", 70, false));
				hbMazeStartCellControls.getChildren().add(Spacer());

				Integer x = _sizeX/2;
				Integer y = _sizeY/2;

				tfStartCellX.setText(x.toString());
				tfStartCellX.setMinWidth(40);
				tfStartCellX.setMaxWidth(40);
//				tfStartCellX.setDisable(true);
				hbMazeStartCellControls.getChildren().add(tfStartCellX);

				tfStartCellX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfStartCellX");
					}
				});

				hbMazeStartCellControls.getChildren().add(Spacer());
				hbMazeStartCellControls.getChildren().add(Marker("x", 0, false));
				hbMazeStartCellControls.getChildren().add(Spacer());

				tfStartCellY.setText(x.toString());
				tfStartCellY.setMinWidth(40);
				tfStartCellY.setMaxWidth(40);
//				tfStartCellY.setDisable(true);
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

				hbMazeEntranceControls.getChildren().add(Marker("Entrance", 70, false));
				hbMazeEntranceControls.getChildren().add(Spacer());

				Integer x = new Integer(0);
				Integer y = new Integer(_sizeY/3);
				entranceCell = createCell(x,y);

				ArrayList<String> itemsX = new ArrayList<>();
				itemsX.add("west");
				itemsX.add("east");
				for(Integer i=0; i<_sizeX; i++)
				{
					itemsX.add(i.toString());
				}

				tfEntranceX.getItems().setAll(itemsX);
				tfEntranceX.setValue(itemsX.get(0));
				tfEntranceX.setMinWidth(70);
				tfEntranceX.setMaxWidth(70);

				hbMazeEntranceControls.getChildren().add(tfEntranceX);

				tfEntranceX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfEntranceX");
					}
				});

				hbMazeEntranceControls.getChildren().add(Spacer());
				hbMazeEntranceControls.getChildren().add(Marker("x", 0, false));
				hbMazeEntranceControls.getChildren().add(Spacer());

				ArrayList<String> itemsY = new ArrayList<>();
				itemsY.add("north");
				itemsY.add("south");
				for(Integer i=0; i<_sizeY; i++)
				{
					itemsY.add(i.toString());
				}

				tfEntranceY.getItems().setAll(itemsY);
				tfEntranceY.setValue(itemsY.get(y+2));
				tfEntranceY.setMinWidth(70);
				tfEntranceY.setMaxWidth(70);

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

				hbMazeExitControls.getChildren().add(Marker("Exit", 70, false));
				hbMazeExitControls.getChildren().add(Spacer());

				Integer x = new Integer(_sizeX+1);
				Integer y = new Integer(_sizeY*2/3);
				exitCell = createCell(x,y);

				ArrayList<String> itemsX = new ArrayList<>();
				itemsX.add("west");
				itemsX.add("east");
				for(Integer i=0; i<_sizeX; i++)
				{
					itemsX.add(i.toString());
				}

				tfExitX.getItems().setAll(itemsX);
				tfExitX.setValue(itemsX.get(1));
				tfExitX.setMinWidth(70);
				tfExitX.setMaxWidth(70);

				hbMazeExitControls.getChildren().add(tfExitX);

				tfExitX.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("OnAction: tfExitX");
					}
				});

				hbMazeExitControls.getChildren().add(Spacer());
				hbMazeExitControls.getChildren().add(Marker("x", 0, false));
				hbMazeExitControls.getChildren().add(Spacer());

				ArrayList<String> itemsY = new ArrayList<>();
				itemsY.add("north");
				itemsY.add("south");
				for(Integer i=0; i<_sizeY; i++)
				{
					itemsY.add(i.toString());
				}

				tfExitY.getItems().setAll(itemsY);
				tfExitY.setValue(itemsY.get(y+2));
				tfExitY.setMinWidth(70);
				tfExitY.setMaxWidth(70);

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
					public void handle(MouseEvent event)
					{
						System.out.println("OnMousePressed: btnMazeCreate");
						createMaze();
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
