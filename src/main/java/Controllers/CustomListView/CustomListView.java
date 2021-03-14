package Controllers.CustomListView;

import Controllers.Dimensions.DimensionKeeper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Class responsible for getting information from recipe manager and displaying
 * it in a listview.
 */
public class CustomListView extends ListView<CustomCell>{
    protected List<String> internalList;
    protected ObservableList<CustomCell> observableList;
    protected Double width;

    public CustomListView(List<String> internalList, Double width){
        super();
        this.width = width;
        this.internalList = internalList;
        observableList = FXCollections.observableArrayList();
        for (String item:internalList) {
            add(item);
        }
        setItems(observableList);
    }

    public void setInternalList(List<String> newInternalList){
        this.internalList = newInternalList;
    }

    public void add(String item) {
        CustomCell cell = new ROCell(item);
        cell.setMaxWidth(width);
        observableList.add(cell);
    }

    @Override
    public String toString() {
        return "CustomListView{" +
                "internalList=" + internalList +
                ", observableList=" + observableList +
                '}';
    }
}
