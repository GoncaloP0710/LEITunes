package util.adts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

    public ArrayQListWithSelection() {
    	super();
    }

	@Override
	public List<E> createList() {
		return new ArrayList<>();
	}
    
}
