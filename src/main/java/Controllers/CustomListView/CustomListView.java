package Controllers.CustomListView;

import Model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Class responsible for getting information from recipe manager and displaying
 * it in a listview.
 */
public class CustomListView extends ListView<CustomCell>{
    protected List<String> internalList;
    protected ObservableList<CustomCell> observableList;

    public CustomListView(List<String> internalList){
        super();
        this.internalList = internalList;
        observableList = FXCollections.observableArrayList();
        for (String item:internalList) {
            add(item);
        }
        setItems(observableList);
    }

    public CustomListView(){
        super();
        this.internalList = FXCollections.observableArrayList();
        setItems(observableList);
    }

    public void add(String item) {
        observableList.add(new ROCell(item));
    }

    @Override
    public String toString() {
        return "CustomListView{" +
                "internalList=" + internalList +
                ", observableList=" + observableList +
                '}';
    }
}
