package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, SavableController {
    private Recipe recipe;
    @FXML
    private Label title;
    @FXML
    private VBox ingredients;
    @FXML
    private VBox steps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetRecipeController getRecipeController = new GetRecipeController();
        getRecipeController.drawWindow("Choose a Recipe", 200, 100 + 40*RecipeManager.getInstance().size());
        recipe = getRecipeController.getRecipe();
        if(recipe != null){
            title.setText(recipe.getName());
            fillStepsAndIngredients();
        }
    }

    private void fillStepsAndIngredients() {
        for(String step:recipe.getSteps()){
            addRow(steps, step);
        }
        for(String ingredient:recipe.getIngredients()) {
            addRow(ingredients, ingredient);
        }
    }

    private void addRow(VBox parent, String rowText){
        Label label = new Label(rowText);
        label.setWrapText(true);
        label.setPadding(new Insets(0,32,0,0));
        Button toggleButton = new CustomToggleButton(label);
        HBox row = getNewRow();
        row.getChildren().addAll(toggleButton, label);
        row.setPadding(new Insets(0,0,0,8));
        parent.getChildren().add(row);
    }

    private HBox getNewRow(){
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        row.setSpacing(8.0);
        return row;
    }

    public void setUpClose() {}

    public static class CustomToggleButton extends Button {
        private boolean clicked;
        private final Label associatedLabel;

        public CustomToggleButton(Label associatedLabel){
            this.setId("toggle-button");
            this.clicked = false;
            this.associatedLabel = associatedLabel;
            setOnClick();
        }

        private void setOnClick() {
            this.setOnAction(event->{
                if(clicked){
                    this.setStyle("-fx-background-color: white;");
                    associatedLabel.setTextFill(Color.web("000000"));
                    clicked = false;
                }else{
                    this.setStyle("-fx-background-color: #808080;");
                    associatedLabel.setTextFill(Color.web("808080"));
                    clicked = true;
                }
            });
        }
    }
}
