package Controllers.CustomListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Extension of CustomListView that can have items added, removed, and reordered.
 * This class also updates the model recipe as changes come in.
 */
public class EditableListView extends CustomListView {
    protected EditableListView(List<String> internalList) {
        super(internalList);
    }

    @Override
    public void add(String item) {
        CustomCell cell = new DragAndDropableCell(item);
        cell.setOnDragDropped(event->{
            reorder((CustomCell)event.getGestureSource(), (CustomCell)event.getGestureTarget());
            event.consume();
        });
        observableList.add(cell);
        updateInternalList();
    }

    private void reorder(CustomCell source, CustomCell target) {
        observableList.removeIf(cell -> cell.getString().equals(source.getString()));
        ListIterator<CustomCell> iterator = observableList.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().getString().equals(target.getString())){
                iterator.add(source);
            }
        }
    }

    public void remove(CustomCell cell) {
        Iterator<CustomCell> iterator = observableList.iterator();
        while(iterator.hasNext()){
            if( iterator.next().getString().equals(cell.getString()) ){
                iterator.remove();
                updateInternalList();
                return;
            }
        }

    }

    /*This is really bad (for both memory and time) but it works...*/
    private void updateInternalList(){
        List<String> newInternalList = new ArrayList<>();
        for(CustomCell cell:observableList){
            newInternalList.add(cell.getString());
        }
        internalList = newInternalList;
    }
}
