package Controllers.UtilityControllers;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Dumb class that just outputs a simple view to get a name for the recipe from user.
 * Code derived from https://www.youtube.com/watch?v=HFAsMWkiLvg&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=6.
 * NewBoston - JavaFX Java GUI Tutorial - 6 - Communicating Between Windows.
 */
public class SetNameController {
    public static String setName() {
        AtomicReference<String> returnValue = new AtomicReference<>("N/A");

        Stage stage = new Stage();
        stage.setTitle("Your recipe's name");

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(20);

        TextField title = new TextField();
        title.setPromptText("title");
        title.setAlignment(Pos.CENTER);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            returnValue.set(title.getText());
            stage.close();
        });

        layout.getChildren().addAll(title, submitButton);

        stage.setScene(new Scene(layout, 300, 250));
        stage.showAndWait();

        return returnValue.get();
    }

}
