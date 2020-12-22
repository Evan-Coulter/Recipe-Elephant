package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Controllers.UtilityControllers.PopUpBox;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, SavableController {
    private Recipe recipe;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetRecipeController getRecipeController = new GetRecipeController();
        getRecipeController.drawWindow("Choose a Recipe", 400, 400);
        recipe = getRecipeController.getRecipe();
        if(recipe == null){
            System.err.println("ITS NULL!");
        }
        else{
            System.err.println(recipe);
        }
        System.out.println("Here we have to load in the requested recipe");
        /* it might be better to create your own method here
            since you don't know how to use this yet */
    }

    public void setUpClose() {
    }
}
