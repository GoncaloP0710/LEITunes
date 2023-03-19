package util.adts;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

    public ArrayQListWithSelection(ArrayList<E> lista) {
    	super(lista);
    }
}
