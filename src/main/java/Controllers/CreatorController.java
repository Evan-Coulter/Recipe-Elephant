package Controllers;

import Controllers.CustomListView.CustomCell;
import Controllers.CustomListView.EditableListView;
import Controllers.UtilityControllers.GetRecipeController;
import Controllers.UtilityControllers.SetNameController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatorController implements Initializable, SavableController {
    public Recipe recipe;
    private ListView<CustomCell> ingredientListView;
    private ListView<CustomCell> stepListView;
    @FXML
    private VBox stepVBox;
    @FXML
    private VBox ingredientVBox;
    @FXML
    private TextArea ingredientTextArea;
    @FXML
    private TextArea stepTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = new Recipe();
        ingredientListView = new EditableListView();
        ingredientVBox.getChildren().add(ingredientListView);
        stepListView = new EditableListView();
        stepVBox.getChildren().add(stepListView);
    }

    public void setUpClose(){
        extractSetNameFunctions();
    }
    @FXML
    public void menuSaveClicked(){
        extractSetNameFunctions();
    }

    private void extractSetNameFunctions(){
        if(recipe==null) return;
        if(recipe.getName().matches("")) {
            SetNameController setNameController = new SetNameController();
            setNameController.drawWindow("Set Name", 300, 200);
            setNameController.setRecipeName(recipe);
        }
        save();
    }

    private void save() {
        RecipeManager manager = RecipeManager.getInstance();
        if(!manager.contains(recipe)) {
            manager.addRecipe(recipe);
        }else{
            manager.update(recipe);
        }
    }

    public void addStep() {
        ((EditableListView)stepListView).add(stepTextArea.getText());
    }
    public void addIngredient(){
        ((EditableListView)ingredientListView).add(ingredientTextArea.getText());
    }

    public void loadPreviousRecipe() {
        GetRecipeController getter = new GetRecipeController();
        getter.drawWindow("Choose a Recipe to edit", 200, 100 + 40*(RecipeManager.getInstance().size()));
        recipe = getter.getRecipe();
        if(recipe == null) return;
        for(String ingredient:recipe.getIngredients()){
            ((EditableListView)ingredientListView).add(ingredient);
        }
        for(String step:recipe.getSteps()){
            ((EditableListView)stepListView).add(step);
        }
    }
}
