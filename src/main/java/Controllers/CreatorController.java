package Controllers;

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

public class CreatorController implements Initializable{
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

    /**
     * Method used to encapsulate setting name in SetNameController by passing a
     * reference to our recipe and letting it handle that and then closing the stage.
     */
    public void setUpClose(){
        recipe.setName(SetNameController.setName());
        save();
    }

    /**
     * Method that stores our new recipe in recipe manager singleton.
     */
    private void save() {
        RecipeManager manager = RecipeManager.getInstance();
        manager.addRecipe(recipe);
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
        //Set up HBox
        HBox newStep = new HBox();
        newStep.setAlignment(Pos.CENTER);
        newStep.setSpacing(15);

        //Set up Delete Button
        Button deleteButton = new Button("-");
        deleteButton.setStyle("-fx-text-fill: red");

        //Get text area content
        Label step = new Label( textArea.getText() );

        //Set up delete button functionality, needs to be after step declaration
        deleteButton.setOnAction(event -> {
            vbox.getChildren().remove(newStep);
            if(isStep) {
                recipe.removeStep(step.getText());
            }else{
                recipe.removeIngredient(step.getText());
            }
        });

        //Add all new content to HBox then add HBox to parent VBox
        newStep.getChildren().addAll(deleteButton, step);
        vbox.getChildren().addAll(newStep);

        //add step or ingredient to recipe
        String text = textArea.getText();
        if(isStep){
            recipe.addStep(text);
        }else{
            recipe.addIngredient(text);
        }
        System.out.println(recipe);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = new Recipe();
    }
}
