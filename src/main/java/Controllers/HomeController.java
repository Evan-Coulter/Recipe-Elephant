package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public void viewButtonClicked() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View Recipe");

        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/Templates/view_recipe.fxml"));
            primaryStage.setScene(new Scene(parent, 500, 350));
            primaryStage.showAndWait();
        }
        catch(IOException exception) {
            System.err.println("An error occurred creating a recipe");
            exception.printStackTrace();
        }
    }

    public void createButtonClicked(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View Recipe");

        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/Templates/create_recipe.fxml"));
            primaryStage.setScene(new Scene(parent, 700, 500));
            primaryStage.showAndWait();
        }
        catch(IOException exception) {
            System.err.println("An error occurred loading that recipe");
            exception.printStackTrace();
        }
    }
}
