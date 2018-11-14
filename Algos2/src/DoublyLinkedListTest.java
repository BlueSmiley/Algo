import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue("Expect true empty",testDLL.isEmpty());
    	testDLL.insertBefore(0,1);
    	assertFalse("Expect false empty",testDLL.isEmpty());
    }
    
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertNull("Test get with empty",testDLL.get(0));
    	testDLL.insertBefore(0, 0);
    	assertNull("Test get > index with non empty",testDLL.get(1));
    	assertNull("Test get < 0 with non empty",testDLL.get(-1));
    	testDLL.insertBefore(2, 1);
    	testDLL.insertBefore(3, 2);
    	testDLL.insertBefore(4, 3);
    	assertEquals("Test get second element from first half",new Integer(1),testDLL.get(1));
    	assertEquals("Test get element from second half",new Integer(3),testDLL.get(3));
    	assertEquals("Test get second last element from second half",new Integer(2),testDLL.get(2));
    	
    }
    
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertFalse("Test deleteAt with empty",testDLL.deleteAt(0));
    	testDLL.insertBefore(0, 0);
    	assertFalse("Test deleteAt > index with non empty",testDLL.deleteAt(1));
    	assertFalse("Test deleteAt < 0 with non empty",testDLL.deleteAt(-1));
    	
    	testDLL.insertBefore(2, 1);
    	testDLL.insertBefore(3, 2);
    	testDLL.insertBefore(4, 3);
    	testDLL.deleteAt(1);
    	assertEquals("Test deleteAt second element from first half","0,2,3",testDLL.toString());
    	testDLL.insertBefore(1, 1);
    	testDLL.deleteAt(3);
    	assertEquals("Test deleteAt last element from second half","0,1,2",testDLL.toString());
    	testDLL.insertBefore(4, 3);
    	testDLL.deleteAt(2);
    	assertEquals("Test deleteAt second last element from second half","0,1,3",testDLL.toString());
    }
    
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.reverse();
    	assertEquals("Test reverse with empty","",testDLL.toString());
    	testDLL.insertBefore(0, 0);
    	testDLL.insertBefore(2, 1);
    	testDLL.reverse();
    	assertEquals("Test reverse with 2 elems","1,0",testDLL.toString());	
    }
    
    @Test
    public void testPushAndPop()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(0);
    	testDLL.push(1);
    	assertEquals("Test push with 2 elems","0,1",testDLL.toString());
    	assertEquals("Test pop with 2 elems",new Integer(1),testDLL.pop());
    	assertEquals("Test pop with 2 elems deleted correct","0",testDLL.toString());
    }
    
    @Test
    public void testEnqueueAndDequeue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertNull("Test dequeue with no elems enqued",testDLL.dequeue());
    	testDLL.enqueue(1);
    	assertEquals("Test enque worked","1",testDLL.toString());
    	assertEquals("Test dequeue with one element",new Integer(1),testDLL.dequeue());
    	
    }
    
}

