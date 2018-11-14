import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
          if(prev!=null)
        	  prev.next = this;
          if(next!=null)
        	  next.prev = this;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int size;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      size = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     *  Checking a variable value is a constant time operation.Regardless of the size N of the list
     *  we only check one element
     */
    public boolean isEmpty()
    {
      if(size == 0)
    	  return true;
      return false;
    }
    
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  The worst case is when the element is precisely in the middle
     *  Hence we search N/2 elements for a reference to the next element.
     */
    public void insertBefore( int pos, T data ) 
    {
    	if(size == 0)
    	{
    		DLLNode newElement = new DLLNode(data,null,null);
    		head = newElement;
    		tail = newElement;
    	}
    	else if(pos<=0)
    	{
    		DLLNode newElement = new DLLNode(data,null,head);
    		head = newElement;
    	}
    	else if(pos>=size)
    	{
    		DLLNode newElement = new DLLNode(data,tail,null);
    		tail = newElement;
    	}
    	else if(pos<size/2)
		{
			DLLNode currentElem = head;
			for(int i=0;i<pos;i++)
			{
				currentElem = currentElem.next;
			}
			new DLLNode(data,currentElem.prev,currentElem);
		}
		else
		{
			DLLNode currentElem = tail;
			for(int i=size-1;i>pos;i--)
			{
				currentElem = currentElem.prev;
			}
			new DLLNode(data,currentElem.prev,currentElem);
		}
    	size++;
    	return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  Again the worst case if we have to get the data at the very middle
     *  Hence O(N)
     *
     * Worst-case precise runtime cost: 
     *
     * Justification:
     *  Vaseilos said not to do this.
     */
    public T get(int pos) 
    {
    	if(pos>=0 && pos < size)
    	{
    		DLLNode currentElem = null;
    		if(pos<size/2)
    		{
    			currentElem = head;
    			for(int i=0;i<pos;i++)
    			{
    				currentElem = currentElem.next;
    			}
    		}
    		else
    		{
    			currentElem = tail;
    			for(int i=size-1;i>pos;i--)
    			{
    				currentElem = currentElem.prev;
    			}
    		}
    		return currentElem.data;
    	}
    	return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  Again has to traverse through N/2 elements to get reference to element.
     *  We assume getting a reference to the element before and after takes constant time
     *  (or more importantly doesn't vary with the size of the linked list).Hence linear running
     *  time.
     */
    public boolean deleteAt(int pos) 
    {
    	if(pos>=0 && pos < size)
    	{
    		DLLNode currentElem;
    		if(pos<size/2)
    		{
    			currentElem = head;
    			for(int i=0;i<pos;i++)
    			{
    				currentElem = currentElem.next;
    			}
    		}
    		else
    		{
    			currentElem = tail;
    			for(int i=size-1;i>pos;i--)
    			{
    				currentElem = currentElem.prev;
    			}
    		}
    		DLLNode prevElement = currentElem.prev;
    		DLLNode nextElement = currentElem.next;
    		if(prevElement!=null)
    			prevElement.next = nextElement;
    		else
    			head = nextElement;
    		
    		if(nextElement!=null)
    			nextElement.prev = prevElement;
    		else
    			tail = prevElement;
    		size--;
    		return true;
    	}
    	return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  We have to access basically N elements to change the references and we always perform a
     *  constant number of operations that doesnt depend on N on the N elements.
     */
    public void reverse()
    {
    	DLLNode prevElem = null;
    	DLLNode currentElem = head;
    	DLLNode nextElem=null;
    	if(currentElem!=null)
    		nextElem = currentElem.next;
    	
    	for(int i=0;i<size;i++)
    	{
    		currentElem.prev = nextElem;
    		currentElem.next = prevElem;
    		prevElem = currentElem;
    		currentElem = nextElem;
    		if(nextElem!=null)
    			nextElem = nextElem.next;
    	}
    	DLLNode currentHead = head;
    	DLLNode currentTail = tail;
    	tail = currentHead;
    	head = currentTail;
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     *  Its simply appended to the end of the list
     */
    public void push(T item) 
    {
    	insertBefore(size,item);
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * Because its implemented that the push just appends to end of list.Hence push just pops last
     * elem
     */
    public T pop() 
    {
      T item = null;
      item = get(size-1);
      deleteAt(size-1);
      return item;
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: O(1) 
     *
     * Justification:
     *  add to head
     */
    public void enqueue(T item) 
    {
    	insertBefore(0,item);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     *  Because I just remove from tail
     */
    public T dequeue() 
    {
    	T data = get(size-1);
    	deleteAt(size-1);
    	return data;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



