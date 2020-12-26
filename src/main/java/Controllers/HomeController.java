package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the home page.
 */
public class HomeController implements Initializable {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    public void viewButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/view_recipe.fxml"));
        String name = "View Recipe";
        setUpAndStartController(fxmlLoader, name);
    }

    public void createButtonClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/create_recipe.fxml"));
        String name = "Create Recipe";
        setUpAndStartController(fxmlLoader, name);
    }

    public void editButtonClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Templates/create_recipe.fxml"));
        String name = "Edit Recipe";
        SavableController controller = setUpAndStartController(fxmlLoader, name);
        if(controller!=null){
            ((CreatorController)controller).loadPreviousRecipe();
        }
    }

    public void deleteButtonClicked(){
        GetRecipeController getter = new GetRecipeController();
        getter.drawWindow("Choose a recipe to delete", 300, 100*RecipeManager.getInstance().size());
        Recipe recipe = getter.getRecipe();
        if(recipe!=null){
            RecipeManager.getInstance().remove(recipe);
            RecipeManager.getInstance().serialize();
        }
    }

    private SavableController setUpAndStartController(FXMLLoader fxmlLoader, String title) throws IllegalArgumentException{
        if(fxmlLoader == null){
            throw new IllegalArgumentException();
        }
        try {
            //Layout
            Parent root = fxmlLoader.load();

            //Controller Class
            SavableController controller = fxmlLoader.getController();

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
            return controller;
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RecipeManager.getInstance().deserialize();
    }
}
