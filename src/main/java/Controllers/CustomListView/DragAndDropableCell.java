package Controllers.CustomListView;

import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DragAndDropableCell extends CustomCell {
    public DragAndDropableCell(String item) {
        super(item);
        setUpDragAndDrop();
    }

    @Override
    protected Button getButtonAppearance() {
        return new Button("-");
    }

    private void setUpDragAndDrop() {
        setOnDragDetected(event->{
            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getString());
            dragboard.setContent(content);
            event.consume();
        });
        setOnDragOver(event->{
            if(event.getGestureSource() != this)
            {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });
        setOnDragEntered(event->{
            if(event.getGestureSource() != this && event.getDragboard().hasString()){
                setStyle("-fx-border-width: 0 0 3px 0;" +
                        "-fx-border-color: blue");
            }
            event.consume();
        });
        setOnDragExited(event->{
            setStyle("");
            event.consume();
        });
    }
}
