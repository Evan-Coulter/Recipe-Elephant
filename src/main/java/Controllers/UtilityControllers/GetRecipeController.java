package Controllers.UtilityControllers;

import Model.Recipe;
import Model.RecipeManager;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Similarly dumb class that creates and displays a small alert box
 * that user which recipe they wish to view.
 */
public class GetRecipeController extends PopUpBox {
    private Recipe recipe = null;

    public Recipe getRecipe(){
        return recipe;
    }

    @Override
    protected void fillContent(VBox layout, Stage stage){
        RecipeManager manager = RecipeManager.getInstance();
        for(Recipe currentRecipe:manager){
            Button button = new Button(currentRecipe.getName());
            button.setOnAction(event->{
                recipe = currentRecipe;
                stage.close();
            });
            layout.getChildren().add(button);
        }
    }

}
