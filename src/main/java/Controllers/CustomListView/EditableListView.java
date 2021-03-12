package Controllers.CustomListView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Extension of CustomListView that can have items added, removed, and reordered.
 */
public class EditableListView extends CustomListView {
    public EditableListView(){
        super(new ArrayList<>());
    }

    public void add(String item, EventHandler<ActionEvent> onDeleteButtonClicked) {
        DragAndDropableCell cell = new DragAndDropableCell(item);
        cell.setOnDragDropped(event->{
            reorder((CustomCell)event.getGestureSource(), (CustomCell)event.getGestureTarget());
            event.consume();
        });
        cell.setButtonFunctionality(onDeleteButtonClicked);
        observableList.add(cell);
    }

    private void reorder(CustomCell source, CustomCell target) {
        observableList.removeIf(cell -> cell.getString().equals(source.getString()));
        ListIterator<CustomCell> iterator = observableList.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().getString().equals(target.getString())){
                iterator.add(source);
                return;
            }
        }
    }

    public void remove(String cellText) {
        ListIterator<CustomCell> iterator = observableList.listIterator();
        while(iterator.hasNext()) {
            if(iterator.next().getString().equals(cellText)){
                iterator.remove();
                return;
            }
        }
    }
}
