package Controllers;

import Controllers.CustomListView.CustomCell;
import Controllers.CustomListView.CustomListView;
import Controllers.UtilityControllers.GetRecipeController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, SavableController {
    private Recipe recipe;
    private ListView<CustomCell> ingredientListView;
    private ListView<CustomCell> stepsListView;
    @FXML
    private Label title;
    @FXML
    private VBox ingredients;
    @FXML
    private VBox steps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetRecipeController getRecipeController = new GetRecipeController();
        getRecipeController.drawWindow("Choose a Recipe", 200, 100 + 40*RecipeManager.getInstance().size());
        recipe = getRecipeController.getRecipe();
        if(recipe != null){
            title.setText(recipe.getName());
            ingredientListView = new CustomListView(recipe.getIngredients());
            stepsListView = new CustomListView(recipe.getSteps());
        }
        ingredients.getChildren().add(ingredientListView);
        steps.getChildren().add(stepsListView);
    }

    public void setUpClose() {}
}
