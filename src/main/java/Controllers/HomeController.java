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
            System.err.println("An error occurred loading that recipe");
            exception.printStackTrace();
        }
    }

    public void createButtonClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/create_recipe.fxml"));
            Parent root = fxmlLoader.load(); //This must be called before controller access
            CreatorController controller = (CreatorController)fxmlLoader.getController();
            Stage window = new Stage();
            window.setTitle("Create Recipe");
            window.setScene(new Scene(root, 700, 500));
            window.setOnCloseRequest(event->{
                controller.setUpClose();
                window.close();
            });
            window.show();
        }
        catch(IOException exception){
            System.err.println("An error occurred opening the create button window.");
            exception.printStackTrace();
        }
    }
}
