package Controllers;

import Model.Recipe;
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

public class CreatorController implements Initializable {
    private Recipe recipe;
    @FXML
    private VBox stepVBox;
    @FXML
    private VBox ingredientVBox;
    @FXML
    private TextArea ingredientTextArea;
    @FXML
    private TextArea stepTextArea;
    private static final Boolean STEP = true;


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

        //Set user-visible list index and text area content
        Label number = new Label();
        Label step = new Label( textArea.getText() );

        //Set up delete button functionality, needs to be after step declaration
        deleteButton.setOnAction(event -> {
            vbox.getChildren().remove(newStep);
            recipe.removeStep(step.getText());
        });

        //Add all new content to HBox then add HBox to parent VBox
        newStep.getChildren().addAll(deleteButton,number, step);
        vbox.getChildren().addAll(newStep);

        //add step or ingredient to recipe
        if(isStep){
            recipe.addStep(textArea.getText());
        }else{
            recipe.addIngredient(textArea.getText());
        }

        System.out.println(recipe);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = new Recipe();
    }
}
