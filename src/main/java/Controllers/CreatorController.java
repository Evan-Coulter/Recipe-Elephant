package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class CreatorController implements Initializable {

    private AtomicLong ingredientCounter;
    private AtomicLong stepCounter;

    @FXML
    private VBox stepVBox;
    @FXML
    private VBox ingredientVBox;
    @FXML
    private final TextArea ingredientTextArea = new TextArea();;
    @FXML
    private final TextArea stepTextArea = new TextArea();;

    public void addStep() throws IOException {
        HBox newStep = new HBox();

        Label number = new Label( Long.toString(stepCounter.incrementAndGet()) + ") " );
        Label step = new Label(stepTextArea.getText());
        newStep.getChildren().addAll(number, step);

        stepVBox.getChildren().addAll(newStep);
    }

    public void addIngredient() throws IOException {
        HBox newIngredient = new HBox();

        Label number = new Label( Long.toString(ingredientCounter.incrementAndGet()) + ") " );
        Label ingredient = new Label(ingredientTextArea.getText());
        newIngredient.getChildren().addAll(number, ingredient);

        ingredientVBox.getChildren().add(newIngredient);
    }


    /**
     * Could add edit recipe functionality here that loads a recipe.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingredientCounter = new AtomicLong();
        stepCounter = new AtomicLong();
    }
}
