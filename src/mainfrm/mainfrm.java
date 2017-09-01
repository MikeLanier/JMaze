package mainfrm;

import javafx.scene.control.*;
import javafx.scene.layout.*;

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
	private Button		btnStartCellX		= new Button();
	private Button		btnStartCellY		= new Button();
	private TextField	tfEntranceX			= new TextField();
	private TextField	tfEntranceY			= new TextField();
	private TextField	tfExitX				= new TextField();
	private TextField	tfExitY				= new TextField();
	private Button		btnMazeCreate		= new Button();
	private Button		btnMazeSolve		= new Button();
	private Button		btnMazePrint		= new Button();
	
	private Label Spacer()
	{
		Label spacer = new Label();
		spacer.setMinWidth(5);
		spacer.setMaxWidth(5);
		return spacer;
	}

	public mainfrm()
	{
		System.out.println("mainfrm");

		// define the grid layout as two columns and one row.
		ColumnConstraints	col1 = new ColumnConstraints();
		ColumnConstraints	col2 = new ColumnConstraints();
		col1.setPrefWidth(300);
		col2.setPercentWidth(70);
		getColumnConstraints().addAll(col1, col2);

		RowConstraints	row1 = new RowConstraints();
		row1.setPercentHeight(100);
		getRowConstraints().addAll(row1);

		setGridLinesVisible(true);
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

				btnMazeOpen.setText("Open");
				btnMazeOpen.setMinWidth(70);
				btnMazeOpen.setMaxWidth(70);
				hbMazeOpenControls.getChildren().add(btnMazeOpen);

				hbMazeOpenControls.getChildren().add(Spacer());

				tfMazeFilename.setText("filename");
				tfMazeFilename.setMinWidth(150);
				hbMazeOpenControls.getChildren().add(tfMazeFilename);

				hbMazeOpenControls.getChildren().add(Spacer());

				btnMazeSelect.setText("^");
				btnMazeSelect.setMinWidth(25);
				hbMazeOpenControls.getChildren().add(btnMazeSelect);
			}

			// controls for saving the active maze
			HBox hbMazeSaveControls = new HBox();
//			if(hbMazeSaveControls != null)
			{
				vbControlBox.getChildren().add(hbMazeSaveControls);
				hbMazeSaveControls.paddingProperty().setValue(margin);

				btnMazeSave.setText("Save");
				btnMazeSave.setMinWidth(70);
				btnMazeSave.setMaxWidth(70);
				hbMazeSaveControls.getChildren().add(btnMazeSave);

				hbMazeSaveControls.getChildren().add(Spacer());

				tfMazeSaveFilename.setText("filename");
				tfMazeSaveFilename.setMinWidth(150);
				hbMazeSaveControls.getChildren().add(tfMazeSaveFilename);

				hbMazeSaveControls.getChildren().add(Spacer());

				btnMazeSaveSelect.setText(">");
				btnMazeSaveSelect.setMinWidth(25);
				hbMazeSaveControls.getChildren().add(btnMazeSaveSelect);
			}

			HBox hbMazeSizeControls = new HBox();
//			if(hbMazeSizeControls != null)
			{
				vbControlBox.getChildren().add(hbMazeSizeControls);
				hbMazeSizeControls.paddingProperty().setValue(margin);

				Label lblMazeSize = new Label();
				lblMazeSize.setText("Size");
				lblMazeSize.setMinWidth(70);
				lblMazeSize.setMaxWidth(70);
				hbMazeSizeControls.getChildren().add(lblMazeSize);

				hbMazeSizeControls.getChildren().add(Spacer());

				tfMazeSizeX.setText("512");
				tfMazeSizeX.setMinWidth(40);
				tfMazeSizeX.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeX);

				hbMazeSizeControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeSizeControls.getChildren().add(lblX);

				hbMazeSizeControls.getChildren().add(Spacer());

				tfMazeSizeY.setText("512");
				tfMazeSizeY.setMinWidth(40);
				tfMazeSizeY.setMaxWidth(40);
				hbMazeSizeControls.getChildren().add(tfMazeSizeY);
			}

			HBox hbMazeCellSizeControls = new HBox();
//			if(hbMazeCellSizeControls != null)
			{
				vbControlBox.getChildren().add(hbMazeCellSizeControls);
				hbMazeCellSizeControls.paddingProperty().setValue(margin);

				Label lblMazeCellSize = new Label();
				lblMazeCellSize.setText("Cell Size");
				lblMazeCellSize.setMinWidth(70);
				lblMazeCellSize.setMaxWidth(70);
				hbMazeCellSizeControls.getChildren().add(lblMazeCellSize);

				hbMazeCellSizeControls.getChildren().add(Spacer());

				tfCellSize.setText("25");
				tfCellSize.setMinWidth(40);
				tfCellSize.setMaxWidth(40);
				hbMazeCellSizeControls.getChildren().add(tfCellSize);
			}

			HBox hbMazeAlgorithmControls = new HBox();
//			if(hbMazeAlgorithmControls != null)
			{
				vbControlBox.getChildren().add(hbMazeAlgorithmControls);
				hbMazeAlgorithmControls.paddingProperty().setValue(margin);

				Label lblMazeAlgorithm = new Label();
				lblMazeAlgorithm.setText("Algorithm");
				lblMazeAlgorithm.setMinWidth(70);
				lblMazeAlgorithm.setMaxWidth(70);
				hbMazeAlgorithmControls.getChildren().add(lblMazeAlgorithm);

				hbMazeAlgorithmControls.getChildren().add(Spacer());

				cbAlgorithm.setMinWidth(150);
				cbAlgorithm.setMaxWidth(150);
				hbMazeAlgorithmControls.getChildren().add(cbAlgorithm);
			}

			HBox hbMazeNameControls = new HBox();
//			if(hbMazeNameControls != null)
			{
				vbControlBox.getChildren().add(hbMazeNameControls);
				hbMazeNameControls.paddingProperty().setValue(margin);

				Label lblMazeName = new Label();
				lblMazeName.setText("Maze Name");
				lblMazeName.setMinWidth(70);
				lblMazeName.setMaxWidth(70);
				hbMazeNameControls.getChildren().add(lblMazeName);

				hbMazeNameControls.getChildren().add(Spacer());

				tfMazeName.setMinWidth(150);
				tfMazeName.setMaxWidth(150);
				hbMazeNameControls.getChildren().add(tfMazeName);
			}

			HBox hbMaze2D3DControls = new HBox();
//			if(hbMaze2D3DControls != null)
			{
				vbControlBox.getChildren().add(hbMaze2D3DControls);
				hbMaze2D3DControls.paddingProperty().setValue(margin);

				Label lblMaze2D3D = new Label();
				lblMaze2D3D.setText("");
				lblMaze2D3D.setMinWidth(70);
				lblMaze2D3D.setMaxWidth(70);
				hbMaze2D3DControls.getChildren().add(lblMaze2D3D);

				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze2D.setText("2D");
				cbMaze2D.setMinWidth(40);
				cbMaze2D.setMaxWidth(40);
				hbMaze2D3DControls.getChildren().add(cbMaze2D);

				hbMaze2D3DControls.getChildren().add(Spacer());

				cbMaze3D.setText("3D");
				cbMaze3D.setMinWidth(40);
				cbMaze3D.setMaxWidth(40);
				hbMaze2D3DControls.getChildren().add(cbMaze3D);
			}

			HBox hbMazeStartCellControls = new HBox();
//			if(hbMazeStartCellControls != null)
			{
				vbControlBox.getChildren().add(hbMazeStartCellControls);
				hbMazeStartCellControls.paddingProperty().setValue(margin);

				Label lblMazeStartCell = new Label();
				lblMazeStartCell.setText("Start Cell");
				lblMazeStartCell.setMinWidth(70);
				lblMazeStartCell.setMaxWidth(70);
				hbMazeStartCellControls.getChildren().add(lblMazeStartCell);

				hbMazeStartCellControls.getChildren().add(Spacer());

				btnStartCellX.setText("512");
				btnStartCellX.setMinWidth(40);
				btnStartCellX.setMaxWidth(40);
				hbMazeStartCellControls.getChildren().add(btnStartCellX);

				hbMazeStartCellControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeStartCellControls.getChildren().add(lblX);

				hbMazeStartCellControls.getChildren().add(Spacer());

				btnStartCellY.setText("512");
				btnStartCellY.setMinWidth(40);
				btnStartCellY.setMaxWidth(40);
				hbMazeStartCellControls.getChildren().add(btnStartCellY);
			}

			HBox hbMazeEntranceControls = new HBox();
//			if(hbMazeEntranceControls != null)
			{
				vbControlBox.getChildren().add(hbMazeEntranceControls);
				hbMazeEntranceControls.paddingProperty().setValue(margin);

				Label lblMazeEntrance = new Label();
				lblMazeEntrance.setText("Entrance");
				lblMazeEntrance.setMinWidth(70);
				lblMazeEntrance.setMaxWidth(70);
				hbMazeEntranceControls.getChildren().add(lblMazeEntrance);

				hbMazeEntranceControls.getChildren().add(Spacer());

				tfEntranceX.setText("512");
				tfEntranceX.setMinWidth(40);
				tfEntranceX.setMaxWidth(40);
				hbMazeEntranceControls.getChildren().add(tfEntranceX);

				hbMazeEntranceControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeEntranceControls.getChildren().add(lblX);

				hbMazeEntranceControls.getChildren().add(Spacer());

				tfEntranceY.setText("512");
				tfEntranceY.setMinWidth(40);
				tfEntranceY.setMaxWidth(40);
				hbMazeEntranceControls.getChildren().add(tfEntranceY);
			}

			HBox hbMazeExitControls = new HBox();
//			if(hbMazeEntranceControls != null)
			{
				vbControlBox.getChildren().add(hbMazeExitControls);
				hbMazeExitControls.paddingProperty().setValue(margin);

				Label lblMazeExit = new Label();
				lblMazeExit.setText("Exit");
				lblMazeExit.setMinWidth(70);
				lblMazeExit.setMaxWidth(70);
				hbMazeExitControls.getChildren().add(lblMazeExit);

				hbMazeExitControls.getChildren().add(Spacer());

				tfExitX.setText("512");
				tfExitX.setMinWidth(40);
				tfExitX.setMaxWidth(40);
				hbMazeExitControls.getChildren().add(tfExitX);

				hbMazeExitControls.getChildren().add(Spacer());

				Label lblX = new Label();
				lblX.setText("x");
				hbMazeExitControls.getChildren().add(lblX);

				hbMazeExitControls.getChildren().add(Spacer());

				tfExitY.setText("512");
				tfExitY.setMinWidth(40);
				tfExitY.setMaxWidth(40);
				hbMazeExitControls.getChildren().add(tfExitY);
			}

			HBox hbMazeCreateControls = new HBox();
//			if(hbMazeCreateControls != null)
			{
				vbControlBox.getChildren().add(hbMazeCreateControls);
				hbMazeCreateControls.paddingProperty().setValue(margin);

				btnMazeCreate.setText("Create");
				btnMazeCreate.setMinWidth(70);
				btnMazeCreate.setMaxWidth(70);
				hbMazeCreateControls.getChildren().add(btnMazeCreate);
			}

			HBox hbMazeSolveControls = new HBox();
//			if(hbMazeSolveControls != null)
			{
				vbControlBox.getChildren().add(hbMazeSolveControls);
				hbMazeSolveControls.paddingProperty().setValue(margin);

				btnMazeSolve.setText("Solve");
				btnMazeSolve.setMinWidth(70);
				btnMazeSolve.setMaxWidth(70);
				hbMazeSolveControls.getChildren().add(btnMazeSolve);
			}

			HBox hbMazePrintControls = new HBox();
//			if(hbMazePrintControls != null)
			{
				vbControlBox.getChildren().add(hbMazePrintControls);
				hbMazePrintControls.paddingProperty().setValue(margin);

				btnMazePrint.setText("Print");
				btnMazePrint.setMinWidth(70);
				btnMazePrint.setMaxWidth(70);
				hbMazePrintControls.getChildren().add(btnMazePrint);
			}
		}
	}
}
