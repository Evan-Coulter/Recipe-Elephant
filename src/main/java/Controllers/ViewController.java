package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Controllers.UtilityControllers.PopUpBox;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, SavableController {
    @FXML
    private Label title;
    @FXML
    private VBox ingredients;
    @FXML
    private VBox steps;

    private Recipe recipe;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetRecipeController getRecipeController = new GetRecipeController();
        getRecipeController.drawWindow("Choose a Recipe", 400, 400);
        recipe = getRecipeController.getRecipe();
        if(recipe == null){
            System.err.println("Error loading recipe!");
        }
        else{
            title.setText(recipe.getName());
            fillStepsAndIngredients();
        }
    }

    private void fillStepsAndIngredients() {
        for(String step:recipe.getSteps()){
            addRow(steps, step);
        }
        for(String ingredient:recipe.getIngredients()) {
            addRow(ingredients, ingredient);
        }
    }


    private void addRow(VBox parent, String rowText){
        Label label = new Label(rowText);
        parent.getChildren().add(label);
    }

    public void setUpClose() {}
}
