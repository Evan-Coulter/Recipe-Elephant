package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, Savable {
    private Recipe recipe;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = RecipeManager.getInstance().get(GetRecipeController.askForWhichRecipe());
        System.out.println("Here we have to load in the requested recipe");
        /* it might be better to create your own method here
            since you don't know how to use this yet */
    }

    public void setUpClose() {
    }
}
