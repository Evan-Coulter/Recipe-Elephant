package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class CreatorController {

    private AtomicLong ingredientCounter = new AtomicLong();
    private AtomicLong stepCounter = new AtomicLong();

    @FXML
    private VBox stepVBox;
    @FXML
    private VBox ingredientVBox;

    public void addStep() throws IOException {
        HBox newStep = new HBox();

        Label number = new Label( Long.toString(stepCounter.incrementAndGet()) + ") " );
        Label step = new Label("Some stuff about what to cook here");
        newStep.getChildren().addAll(number, step);

        stepVBox.getChildren().addAll(newStep);
    }

    public void addIngredient() throws IOException {
        HBox newIngredient = new HBox();

        Label number = new Label( Long.toString(ingredientCounter.incrementAndGet()) + ") " );
        Label ingredient = new Label("Some ingredients here");
        newIngredient.getChildren().addAll(number, ingredient);

        ingredientVBox.getChildren().addAll(newIngredient);
    }

}
