package Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Package private class used to to listen for changes to lists
 * in CreatorController and update the item's indices accordingly.
 *
 * Idea from https://stackoverflow.com/questions/16217814/how-to-bind-the-position-in-a-list-to-an-attribute-of-that-element
 * credit to Petr Hudeček.
 */
class ListListener<T> extends SimpleIntegerProperty implements ListChangeListener<T> {
    //The subject item we want to keep track of
    private final T item;
    //The list in which we want to keep track of the item
    private final ObservableList<T> list;

    public ListListener(T item, ObservableList<T> list){
        this.item = item;
        this.list = list;
        this.setValue(list.indexOf(item));
        list.addListener(this);
    }
    @Override
    public void onChanged(Change<? extends T> c) {
        this.setValue(list.indexOf(item));
    }
}
