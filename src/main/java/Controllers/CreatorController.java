package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Controllers.UtilityControllers.SetNameController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatorController implements Initializable, SavableController {
    public Recipe recipe;
    private static final Boolean STEP = true;
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
            setNameController.drawWindow("Set Name", 400, 400);
            setNameController.setRecipeName(recipe);
        }
        save();
    }

    /**
     * Saves the recipe.
     */
    private void save() {
        RecipeManager manager = RecipeManager.getInstance();
        if(!manager.contains(recipe)) {
            manager.addRecipe(recipe);
        }else{
            manager.update(recipe);
        }
    }

    public void addStep() {
        addItem(stepVBox, stepTextArea, STEP);
    }
    public void addIngredient(){
        addItem(ingredientVBox, ingredientTextArea, !STEP);
    }

    /**
     * Private method that accepts a vbox, and text area and updates that vbox with
     * whatever text was in the text area. USED TO UPDATE TWO INNER VBOXS IN CREATE RECIPE.
     * @param vbox the input vbox.
     * @param textArea the input textArea
     */
    private void addItem(VBox vbox, TextArea textArea, boolean isStep){
        //Set up Row
        HBox newStep = getNewTextRow(vbox, textArea.getText(), isStep);
        //Add Row
        vbox.getChildren().addAll(newStep);
        //add step or ingredient to recipe
        String text = textArea.getText();
        if(isStep){
            recipe.addStep(text);
        }else{
            recipe.addIngredient(text);
        }
    }

    private HBox getNewTextRow(VBox parent, String text, boolean isStep){
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        row.setSpacing(16);
        //Set up row text
        Label label = new Label(text);
        label.setWrapText(true);
        //Get delete button
        Button button = getNewDeleteButton(parent, row, label, isStep);
        //add components
        row.getChildren().addAll(button, label);
        return row;
    }

    private Button getNewDeleteButton(VBox parent, HBox currentRow, Label rowText, boolean isStep){
        Button deleteButton = new Button("-");
        deleteButton.setOnAction(event->{
            parent.getChildren().remove(currentRow);
            if(isStep){
                recipe.removeStep(rowText.getText());
            }else{
                recipe.removeIngredient(rowText.getText());
            }
        });
        return deleteButton;
    }

    public void loadPreviousRecipe() {
        GetRecipeController getter = new GetRecipeController();
        getter.drawWindow("Choose a Recipe to edit", 300, 100*(RecipeManager.getInstance().size()));
        recipe = getter.getRecipe();
        if(recipe == null) return;
        for(String ingredient:recipe.getIngredients()){
            HBox row = getNewTextRow(ingredientVBox, ingredient, false);
            ingredientVBox.getChildren().add(row);
        }
        for(String step:recipe.getSteps()){
            HBox row = getNewTextRow(stepVBox, step, true);
            stepVBox.getChildren().add(row);
        }
    }
}
