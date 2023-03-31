package util.adts;

import java.util.Iterator;
import java.util.List;

import domain.facade.ISong;

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
     * Adds the element e and selects it
     * 
     * @param e
     */
    @Override
    public void add(E e) {
        selected = e;
        objList.add(e);
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
        int index = 0;
        for (int i = 0; i < objList.size(); i++) {
            if (objList.get(i).equals(selected)) {
                index = i;
            }
        }
        return index;
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
            selected = null;
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
        return objList.get(getIndexSelected());
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
	 * @return  an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return objList.iterator();
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Moves the current selected song up to position i, 
	 * shifting down all elements in the playlist from 
	 * positions i+1 to \old getIndexSelected()-1, 
	 * if movement in the playlist is possible 
	 * 
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() && 
	 * 					getIndexSelected() == i  && 
	 * 					size() == \old(size()) 
	 */
	public boolean moveUpSelected(int i) {
		if (getIndexSelected() > 0) {	
			E selected1 = getSelected();
			objList.remove(getIndexSelected());
			objList.add(i, selected1);
			return true;
		}
		return false;
	}
    
}
