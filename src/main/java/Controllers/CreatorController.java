package Controllers;

import Model.Recipe;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void addStep() {
        addItem(stepVBox, stepTextArea);
    }

    public void addIngredient(){
        addItem(ingredientVBox, ingredientTextArea);
    }

    /**
     * Private method that accepts a vbox, and text area and updates that vbox with
     * whatever text was in the text area. USED TO UPDATE TWO INNER VBOXS IN CREATE RECIPE.
     * @param vbox the input vbox.
     * @param textArea the input textArea
     */
    private void addItem(VBox vbox, TextArea textArea){
        HBox newStep = new HBox();

        //Set up Delete Button
        Button deleteButton = new Button("-");
        deleteButton.setStyle("-fx-text-fill: red");
        deleteButton.setOnAction(event -> vbox.getChildren().remove(newStep));
        //Set user-visible list index and text area content
        Label number = new Label();
        Label step = new Label( textArea.getText() );

        //Add all new content to HBox then add HBox to parent VBox
        newStep.getChildren().addAll(deleteButton,number, step);
        vbox.getChildren().addAll(newStep);

        //add step to recipe
        recipe.addStep(textArea.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = new Recipe();
    }
}
