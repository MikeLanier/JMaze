import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("start");
		//GridPane root = FXMLLoader.load(getClass().getResource("sample\\sample.fxml"));
        GridPane root = new mainfrm.mainfrm();
        System.out.println(root.getClass().getName());
        primaryStage.setTitle("Maze");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
