package Controllers.CustomListView;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomCell extends HBox {
    public CustomCell(String item) {
        getChildren().add(new Label(item));
    }

    public String getString() {
        return ((Label)(getChildren().get(0)) ).getText();
    }
}
