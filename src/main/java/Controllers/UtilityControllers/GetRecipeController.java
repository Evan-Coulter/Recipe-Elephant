package Controllers.UtilityControllers;

import Model.Recipe;
import Model.RecipeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * Similarly dumb class that creates and displays a small alert box
 * that user which recipe they wish to view.
 */
public class GetRecipeController extends PopUpBox {
    private Recipe recipe = null;
    private Stage stage = null;

    public Recipe getRecipe(){
        return recipe;
    }

    @Override
    protected void fillContent(VBox layout, Stage stage){
        this.stage = stage;
        RecipeManager manager = RecipeManager.getInstance();
        if(manager.size() == 0){
            setMessage(layout, "Please create a recipe first");
        }
        else{
            setMessage(layout, "Choose a Recipe From Below");
        }
        //Fill ObservableList to add items to ListView
        ObservableList<Button> observableList = FXCollections.observableArrayList();
        for(Recipe currentRecipe:manager){
            Button row = new Button(currentRecipe.getName());
            row.setOnAction(event -> {
                recipe = currentRecipe;
                stage.close();
            });
            observableList.add(row);
        }
        ListView<Button> listView = new ListView<>();
        listView.setItems(observableList);
        layout.getChildren().add(listView);
    }

    private void setMessage(VBox layout, String message) {
        layout.getChildren().add(new Label(message));
    }

}
