import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import util.adts.ArrayQListWithSelection;

public class ArrayQListWithSelectionTests {

    ArrayQListWithSelection<String> qlist;

    String s1;
    String s2;

    @BeforeEach
    void setUp() {
        qlist = new ArrayQListWithSelection<String>();
        s1 = "test1";
        s2 = "test2";
        qlist.add(s1);
        qlist.add(s2);
    }

    /**
     * Tests the add function of ArrayQListWithSelection
     */
    @Test
    public void addTest() {
        int oldSize = qlist.size();
        qlist.add("novo");
        assertTrue(qlist.size() > oldSize);
        assertEquals(qlist.getSelected(), "novo");
        assertEquals(qlist.getSelected(), qlist.get(qlist.size() - 1));
    }

    /**
     * Tests the select function of ArrayQListWithSelection
     */
    @Test
    public void selectTest() {
        assertEquals(qlist.getSelected(), s2);
        qlist.select(0);
        assertEquals(qlist.getSelected(), s1);
    }

    /**
     * Tests the remove function of ArrayQListWithSelection
     */
    @Test
    public void removeTest() {
        int oldSize = qlist.size();
        qlist.remove();
        assertTrue(oldSize > qlist.size());
        assertNull(qlist.getSelected());
    }

    /**
     * Tests the getSelected function of ArrayQListWithSelection
     */
    @Test
    public void getSelectedTest() {
        assertTrue(qlist.getSelected().equals("test2"));
    }

    /**
     * Tests the size function of ArrayQListWithSelection
     */
    @Test
    public void sizeTest() {
        assertTrue(qlist.size() == 2);
    }

    /**
     * Tests the someSelected function of ArrayQListWithSelection
     */
    @Test
    public void someSelectedTest() {
        assertTrue(qlist.someSelected());
    }

    /**
     * Tests the next and previous function of ArrayQListWithSelection
     */
    @Test
    public void nextAndPrevTest() {
        qlist.add("test3");
        assertEquals(qlist.getSelected(), "test3");
        qlist.previous();
        assertEquals(qlist.getSelected(), s2);
        qlist.next();
        assertEquals(qlist.getSelected(), "test3");
    }

    /**
     * Tests the getIndexSelected function of ArrayQListWithSelection
     */
    @Test
    public void getIndexTest() {
        assertTrue(qlist.getIndexSelected() == 1);
    }

    @Test
    public void getTest() { // Unico teste q mudei
        String teste = "teste";
        ArrayQListWithSelection<String> testeList = new ArrayQListWithSelection<>();
        testeList.add(teste);
        assertEquals(teste, testeList.get(0));
    }

    @Test
    public void toStringTest() {
        StringBuilder str = new StringBuilder();
        str.append("0 " + s1 + "\n");
        str.append("1 " + s2 + "\n");
        assertEquals(qlist.toString(), str.toString());
    }

    @Test
    public void equalsTest1() {
        ArrayQListWithSelection<String> compare = new ArrayQListWithSelection<String>();
        compare.add(s1);
        compare.add(s2);
        assertTrue(qlist.equals(compare));
    }

    @Test
    public void equalsTest2() {
        ArrayQListWithSelection<String> compare = new ArrayQListWithSelection<String>();
        compare.add(s1);
        compare.add(s2);
        compare.add("emplastro");
        assertFalse(qlist.equals(compare));
    }

    @Test
    public void equalsTest3() {
        ArrayQListWithSelection<String> compare = new ArrayQListWithSelection<String>();
        assertFalse(qlist.equals(compare));
    }

    @Test
    public void equalsTest4() {
        ArrayQListWithSelection<String> compare = new ArrayQListWithSelection<String>();
        compare.add(s1);
        compare.add("errado");
        assertFalse(qlist.equals(compare));
    }

    @Test
    public void moveUpTest() {
        qlist.moveUpSelected(0);
        assertEquals(s1, qlist.get(1));
    }
}
