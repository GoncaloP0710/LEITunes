package util.adts;

import java.util.Iterator;
import java.util.List;

public abstract class AbsQListWithSelection<E> implements QListWithSelection<E> {

    private E selected;
    protected List<E> objList;

    /**
     * triggers list creation without, however,
     * committing to any specific implementation for these lists
     */
    public AbsQListWithSelection() {
        selected = null;
        objList = createList();
    }

    /**
     * list creation
     * 
     * @return list
     */
    public abstract List<E> createList();

    /**
     * Selects the element on the position i
     * 
     * @param i
     * @requires 0 <= i < size()
     */
    @Override
    public void select(int i) {
        selected = objList.get(i);
    }

    /**
     * removes the selection on the element that was selected
     */
    public void deSelect() {
        selected = null;
    }

    /**
     * Adds the element e and selects it
     * 
     * @param e
     */
    @Override
    public void add(E e) {
        objList.add(e);
        selected = e;
    }

    /**
     * Indicates whether there is an element selected
     * 
     * @return true if there is an element selected
     */
    @Override
    public boolean someSelected() {
        return selected != null;
    }

    /**
     * returns the selected object position in the list
     * 
     * @requires someSelected()
     * @return selected object position in the list
     */
    @Override
    public int getIndexSelected() {
        return this.objList.indexOf(selected);
    }

    /**
     * selects the element in the position next to the selected one if
     * getIndexSelected()<size()-1, otherwise there
     * is no longer an element selected
     */
    @Override
    public void next() {
        if (someSelected()) {
            if (getIndexSelected() < size() - 1) {
                selected = objList.get(getIndexSelected() + 1);
            } else {
                selected = null;
            }
        }
    }

    /**
     * selects the element in the position before the selected one if
     * getIndexSelected()>0, otherwise there is no longer an element selected
     */
    @Override
    public void previous() {
        if (someSelected()) {
            if (getIndexSelected() > 0) {
                selected = objList.get(getIndexSelected() - 1);
            } else {
                selected = null;
            }
        }
    }

    /**
     * deletes the selected element, if someSelected() and does nothing otherwise
     */
    @Override
    public void remove() {
        if (someSelected()) {
            objList.remove(selected);
            this.selected = null;
        }
    }

    /**
     * Assuming there is an element selected, returns it
     * 
     * @requires someSelected()
     * @return element selected
     */
    @Override
    public E getSelected() {
        return this.selected;
    }

    /**
     * Returns the number of elements in the list
     * 
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return objList.size();
    }

    /**
     * Returns the element at position i
     * 
     * @param i the position of the element to return
     * @requires 0 <= i < size()
     * @return the element at position i
     */
    @Override
    public E get(int i) {
        return objList.get(i);
    }

    /**
     * Returns an iterator over the elements in the list
     * 
     * @return an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return objList.iterator();
    }

    /**
     * creates string that represents an QList with selection
     * 
     * @return string that represents an QList with selection
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            str.append(String.valueOf(i)).append(" ");
            str.append(get(i).toString());
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * checks if the objects are the same
     * 
     * @return true if the objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AbsQListWithSelection)) {
            return false;
        }

        AbsQListWithSelection<?> other = (AbsQListWithSelection<?>) obj;

        if (this.size() != other.size()) {
            return false;
        }

        Iterator<?> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }

        if (this.someSelected() != other.someSelected()) {
            return false;
        }
        if (this.someSelected() && !this.getSelected().equals(other.getSelected())) {
            return false;
        }

        return true;
    }

    // ---------------------------------------------------------------------------------

    /**
     * Moves the current selected song up to position i,
     * shifting down all elements in the play list from
     * positions i+1 to \old getIndexSelected()-1,
     * if movement in the play list is possible
     * 
     * @param i the index where this element is going to be moved
     * @requires someSelected() && 0 <= i < getIndexSelected()
     * @ensures \return ==> someSelected() &&
     *          getIndexSelected() == i &&
     *          size() == \old(size())
     */
    public boolean moveUpSelected(int i) {
        if (someSelected() && 0 <= i && i < getIndexSelected()) {
            E selected1 = getSelected();
            objList.remove(getIndexSelected());
            objList.add(i, selected1);
            select(i);
            return true;
        }
        return false;
    }
}
