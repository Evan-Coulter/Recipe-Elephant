package Controllers.CustomListView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ROCell extends CustomCell {
    public ROCell(String item) {
        super(item);
    }

    @Override
    protected Button getButtonAppearance() {
        return new CustomToggleButton((Label)getChildren().get(0));
    }

    /**
     * Custom button used in ROCell.
     */
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


