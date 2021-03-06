package Controllers;

import Controllers.UtilityControllers.GetRecipeController;
import Model.Recipe;
import Model.RecipeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private ListView<HBox> ingredientListView;
    @FXML
    private ListView<HBox> stepsListView;

    private ObservableList<HBox> internalIngredientList;
    private ObservableList<HBox> internalStepList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetRecipeController getRecipeController = new GetRecipeController();
        getRecipeController.drawWindow("Choose a Recipe", 200, 100 + 40*RecipeManager.getInstance().size());
        recipe = getRecipeController.getRecipe();
        internalIngredientList = FXCollections.observableArrayList();
        internalStepList = FXCollections.observableArrayList();
        ingredientListView.setItems(internalIngredientList);
        stepsListView.setItems(internalStepList);
        if(recipe != null){
            title.setText(recipe.getName());
            fillStepsAndIngredients();
        }
    }

    private void fillStepsAndIngredients() {
        for(String step:recipe.getSteps()){
            addRow(internalStepList, step);
        }
        for(String ingredient:recipe.getIngredients()) {
            addRow(internalIngredientList, ingredient);
        }
    }

    private void addRow(ObservableList<HBox> parent, String rowText){
        Label label = new Label(rowText);
        label.setWrapText(true);
        label.setPadding(new Insets(0,32,0,0));
        Button toggleButton = new CustomToggleButton(label);
        HBox row = getNewRow();
        row.getChildren().addAll(toggleButton, label);
        row.setPadding(new Insets(0,0,0,8));
        parent.add(row);
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
