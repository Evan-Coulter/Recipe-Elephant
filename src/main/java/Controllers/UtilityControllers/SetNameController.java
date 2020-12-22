package Controllers.UtilityControllers;


import Model.Recipe;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SetNameController extends PopUpBox {
    private static String name = "";

    public void setRecipeName(Recipe recipe){
        recipe.setName(name);
    }

    @Override
    protected void fillContent(VBox layout, Stage stage) {
        TextField textField = new TextField();
        textField.setPromptText("Recipe Name Here");

        Button saveButton = new Button("Save Name");
        saveButton.setOnAction(event -> {
            name = textField.getText();
            stage.close();
        });
        layout.getChildren().addAll(textField, saveButton);
    }
}
