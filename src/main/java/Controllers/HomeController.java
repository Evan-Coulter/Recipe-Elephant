package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the home page.
 */
public class HomeController {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;


    public void viewButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/view_recipe.fxml"));
        String name = "View Recipe";
        extractHomePageButtons(fxmlLoader, name);
    }

    public void createButtonClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/create_recipe.fxml"));
        String name = "Create New Recipe";
        extractHomePageButtons(fxmlLoader, name);
    }

    private void extractHomePageButtons(FXMLLoader fxmlLoader, String title){
        if(fxmlLoader == null){
            throw new IllegalArgumentException();
        }
        try {
            //Layout
            Parent root = fxmlLoader.load();

            //Controller Class
            Savable controller = fxmlLoader.getController();

            //Set Up Stage
            Stage window = new Stage();
            window.setTitle(title);
            window.setScene(new Scene(root, WIDTH, HEIGHT));
            window.setOnCloseRequest(event->{
                /*setUpClose() must be implemented in each controller class, it is
                * implemented with my own interface Savable.*/
                controller.setUpClose();
                window.close();
            });
            window.show();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
