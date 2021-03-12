package Controllers.CustomListView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * List Cell (Button + Label) class that allows users to define their own custom
 * button functionality.
 */
public abstract class CustomCell extends HBox {
    public CustomCell(String item) {
        getChildren().add(new Label(item));
        Button customButton = getButtonAppearance();
        getChildren().add(0, customButton);
        setSpacing(16);
    }

    protected abstract Button getButtonAppearance();

    public void setButtonFunctionality(EventHandler<ActionEvent> handler){
        ((Button)getChildren().get(0)).setOnAction(handler);
    }

    public String getString() {
        return ((Label)(getChildren().get(1)) ).getText();
    }
}
