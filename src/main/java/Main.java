import Controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/Templates/home_page.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Recipe Elephant");
        primaryStage.setScene(new Scene(parent, HomeController.WIDTH, HomeController.HEIGHT));
        primaryStage.show();
    }
}
