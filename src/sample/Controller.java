package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {
	public Controller() {
		System.out.println("Controller: " + this.getClass().getName() );
	}

	public void LoadMaze(ActionEvent actionEvent)
	{
		System.out.println("LoadMaze");
	}

	public void LoadMazeFileName(ActionEvent actionEvent)
	{
		System.out.println("LoadMazeFileName");
		TextField tf = (TextField)actionEvent.getTarget();
		System.out.println(tf.getText());
	}

	public void LoadMazeSelect(ActionEvent actionEvent) {
		System.out.println("LoadMazeSelect");
	}

	public void SaveMaze(ActionEvent actionEvent) {
		System.out.println("SaveMaze");
	}

	public void SaveMazeFilename(ActionEvent actionEvent) {
		System.out.println("SaveMazeFilename");
	}

	public void SaveMazeSelect(ActionEvent actionEvent) {
		System.out.println("SaveMazeSelect");
	}

	public void SizeX(ActionEvent actionEvent) {
		System.out.println("SizeX");
	}

	public void SizeY(ActionEvent actionEvent) {
		System.out.println("SizeY");
	}

	public void GridSize(ActionEvent actionEvent) {
		System.out.println("GridSize");
	}

	public void AlgorithmSelect(ActionEvent actionEvent) {
		System.out.println("AlgorithmSelect");
	}

	public void MazeName(ActionEvent actionEvent) {
		System.out.println("MazeName");
	}

	public void Maze2D(ActionEvent actionEvent) {
		System.out.println("Maze2D");
	}

	public void Maze3D(ActionEvent actionEvent) {
		System.out.println("Maze3D");
	}

	public void StartCellX(ActionEvent actionEvent) {
		System.out.println("StartCellX");
	}

	public void StartCellY(ActionEvent actionEvent) {
		System.out.println("StartCellY");
	}

	public void EntranceCellX(ActionEvent actionEvent) {
		System.out.println("EntranceCellX");
	}

	public void EntranceCellY(ActionEvent actionEvent) {
		System.out.println("EntranceCellY");
	}

	public void ExitCellX(ActionEvent actionEvent) {
		System.out.println("ExitCellX");
	}

	public void ExitCellY(ActionEvent actionEvent) {
		System.out.println("ExitCellY");
	}

	public void CreateMaze(ActionEvent actionEvent) {
		System.out.println("CreateMaze");
	}

	public void SolveMaze(ActionEvent actionEvent) {
		System.out.println("SolveMaze");
	}

	public void PrintMaze(ActionEvent actionEvent) {
		System.out.println("PrintMaze");
	}
}
