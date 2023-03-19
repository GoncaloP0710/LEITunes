package util.adts;

public interface QListWithSelection<E> extends QList<E> {
    /**
     * Selects the element on the position i
     * 
     * @param i
     * @requires 0 <= i < size()
     */
    void select(int i);

    /**
     * Adds the element e and selects it
     * 
     * @param e
     */
    @Override
    void add(E e);

    /**
     * Indicates whether there is an element selected
     * 
     * @return true if there is an element selected
     */
    boolean someSelected();

    /**
     * returns the selected object position in the list
     * 
     * @requires someSelected()
     * @return selected object position in the list
     */
    int getIndexSelected();

    /**
     * selects the element in the position next to the selected one if
     * getIndexSelected()<size()-1, otherwise there
     * is no longer an element selected
     */
    void next();

    /**
     * selects the element in the position before the selected one if
     * getIndexSelected()>0, otherwise there is no longer an element selected
     */
    void previous();

    /**
     * deletes the selected element, if someSelected() and does nothing otherwise
     */
    void remove();

    /**
     * Assuming there is an element selected, returns it
     * 
     * @requires someSelected()
     * @return element selected
     */
    E getSelected();
}
