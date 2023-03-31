package util.adts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

	/**
	 * provides an array-based implementation of QListWithSelection<E>
	 */
    public ArrayQListWithSelection() {
    	super();
    }

    /**
     * list creation
     * 
     * @return list
     */
	@Override
	public List<E> createList() {
		return new ArrayList<>();
	}
    
}
