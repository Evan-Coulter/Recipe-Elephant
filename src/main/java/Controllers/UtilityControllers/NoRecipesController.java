package Controllers.UtilityControllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NoRecipesController extends PopUpBox {
    @Override
    protected void fillContent(VBox layout, Stage stage) {
        Label label = new Label("Please create a recipe first");
        Button closeButton = new Button("Ok");
        closeButton.setTextFill(Color.WHITE);
        closeButton.setStyle("-fx-background-color: #7A93AC;");
        closeButton.setOnAction(event-> stage.close());
        layout.getChildren().addAll(label, closeButton);
    }
}
