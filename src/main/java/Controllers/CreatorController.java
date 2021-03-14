package Controllers;

import Controllers.CustomListView.CustomCell;
import Controllers.CustomListView.CustomListView;
import Controllers.CustomListView.EditableListView;
import Controllers.Dimensions.DimensionKeeper;
import Controllers.UtilityControllers.GetRecipeController;
import Controllers.UtilityControllers.SetNameController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
        ingredientListView = new EditableListView(DimensionKeeper.INGREDIENT_LIST_MAX_WIDTH);
        ingredientVBox.getChildren().add(ingredientListView);
        stepListView = new EditableListView(DimensionKeeper.STEP_LIST_MAX_WIDTH);
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
        String text = stepTextArea.getText();
        handleAdd(text, (EditableListView) stepListView, ()->recipe.removeStep(text), ()->recipe.addStep(text));
    }

    public void addIngredient(){
        String text = ingredientTextArea.getText();
        handleAdd(text, (EditableListView) ingredientListView, ()->recipe.removeIngredient(text), ()->recipe.addIngredient(text));
    }

    public void handleAdd(String text, EditableListView source, Runnable recipeRemove, Runnable recipeAdd){
        source.add(text, new MyEventHandler(()->source.remove(text), recipeRemove));
        recipeAdd.run();
    }

    public void loadPreviousRecipe() {
        GetRecipeController getter = new GetRecipeController();
        getter.drawWindow("Choose a Recipe to edit", 200, 100 + 40*(RecipeManager.getInstance().size()));
        recipe = getter.getRecipe();
        if(recipe != null){
            EditableListView ingredientList = (EditableListView) ingredientListView;
            EditableListView stepList = (EditableListView) stepListView;
            ingredientList.setInternalList(recipe.getIngredients());
            for(String ingredient:recipe.getIngredients()){
                ingredientList.add(ingredient, new MyEventHandler(()->ingredientList.remove(ingredient), ()->recipe.removeStep(ingredient)));
            }
            stepList.setInternalList(recipe.getSteps());
            for(String step:recipe.getSteps()){
                stepList.add(step, new MyEventHandler(()->stepList.remove(step), ()->recipe.removeStep(step)));
            }
        }
    }
}

/**
 * Packages up all the runnable parameters.
 */
class MyEventHandler implements EventHandler<ActionEvent>{
    private final Runnable removeFromListViewMethod;
    private final Runnable removeFromRecipeMethod;

    public MyEventHandler(Runnable removeFromListViewMethod, Runnable removeFromRecipeMethod){
        this.removeFromListViewMethod = removeFromListViewMethod;
        this.removeFromRecipeMethod = removeFromRecipeMethod;
    }

    @Override
    public void handle(ActionEvent event) {
        removeFromListViewMethod.run();
        removeFromRecipeMethod.run();
    }
}