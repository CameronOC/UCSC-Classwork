/*-----------------------------------------------------
Cameron Johnson
List.java
Pa1
id: 1384841
Computer Science 101
*/-----------------------------------------------------
public class List {
	//private node class
    private class node {
        // node fields
		int item;
        node prev;
        node next;
		// node constructor
        node(int item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
		// 2nd node constructor
        node(int item, node before, node after) {
            this.item = item;
            this.prev = before;
            this.next = after;
        }
		// overrides ToString method
        public String toString() {
            return String.valueOf(this.item);
        }
    }
	
	// private variables for List class
    private node first;
    private node back;
    private node cursor;
    private int index;
	private int length;


    // Constructor
    // List() // Creates a new empty list.
    // Access functions
    List() {
        this.first = null;
        this.back = null;
        this.cursor = null;
        this.index = -1;
		this.length = 0
    }
// Returns number of elements in this list.
    public int length() {

        if ( first == null){
            return 0;
        }else{
            return length;
        }

    } 

	// Returns the index of the cursor element in this list, or
    // returns -1 if the cursor element is undefined.
    int getIndex() {
		return index; 
    } 
	
	// Returns front element in this list.
     int front(){
         if(length()>0){
        	return first.item; 
         }else {
            throw new RuntimeException("Function Front; there is no first item in the list"); 
         }
     } 
  
	// Returns back element in this List.
    int back(){
    	 if(length()>0){
         	return this.back.item;
          }else {
            throw new RuntimeException("Function back; there is no back item in the list");  
          }
     }

     
	// Returns cursor element in this list.
     int getElement(){
    	 if(length()>0 && getIndex()>=0 ){
    		 return cursor.item; 
    	 }else{
    		 return -1;
    	 }
     }

	// Returns true if this List and L are the same integer
    boolean equals(List L){
    	node temp = L.first;
    	node temp2 = first;
    	for(;;){
    		if(temp == null && temp2 == null){
    			return true;
    		}
    		if(temp2.item == temp.item){
    			temp = temp.next;
        		temp2 = temp2.next;
    		}else{
    			return false;
    		
    		}
    	}
    
    }
   

// Manipulation procedures
// Re-sets this List to the empty state.
    void clear(){
    	 this.first = null;
         this.back = null;
         this.cursor = null;
         this.index = -1;
		 this.length = 0;
    }
    /* moves the cursor to the element at index i,
	otherwise the cursor becomes undefined.*/
	void moveTo(int i){
    	if(0<=i && i<=length()-1){
    		cursor = first;
    		index = 0;
    		for( ;i>0;i--){
    			cursor = cursor.next;
    			index++;
    				
    		}
    	}else{
    		cursor = null;
    		index = -1;
    	}
     }
	/*moves the cursor one step toward the
	 front of the list. If getIndex()==0, cursor becomes undefined.
     If getIndex()==-1, cursor remains undefined. This operation is
     equivalent to moveTo(getIndex()-1). */
    void movePrev(){
    	 if(0<getIndex() && getIndex()<=length()-1){
    		 cursor = cursor.prev;
    		 index--;
    	 }else{
    		 cursor = null;
    		 index = -1;
    	 }
     }
    /* If 0<=getIndex()<length()-1, moves the cursor one step toward the
	 back of the list. If getIndex()==length()-1, cursor becomes
     undefined. If getIndex()==-1, cursor remains undefined. This
     operation is equivalent to moveTo(getIndex()+1). */
    void moveNext(){
    	if(0<=getIndex() && getIndex()<length()-1){
    		cursor = cursor.next;
    		index++;
    		
    	}else{
    		cursor = null;
    		index = -1;
    	}
    }
    
	// Inserts new element before front element in this List.
    void prepend(int data){
    	if(first == null){
    		node temp = new node(data);
        	first = temp;
        	back = temp;
    	}else{
    		node temp = new node(data);
    		temp.next = first;
    		first.prev = temp;
    		first = temp;
    		
    	}
		length++;
    }
	 // Inserts new element after back element in this List.
    void append(int data){
    	if(back == null){
    		node temp = new node(data);
        	first = temp;
        	back = temp;
    	}else{
    		node temp = new node(data);
    		temp.prev = back;
    		back.next = temp;
    		back = temp;
    	}
		length++;
    }
    // Inserts new element before cursor element in this List
    void insertBefore(int data){
    	if(length()>0 && getIndex()>=0){
    		node temp = new node(data);
    		if (cursor.prev != null) {
    		    cursor.prev.next = temp;
    		    temp.prev = cursor.prev;
    		    temp.next = cursor;		    
    		} else {
    		    first = temp;
    		}
    		cursor.prev = temp;
			length++;
    	}else{
    		throw new RuntimeException("Function insertBefore; the length of the list isn't long enough");
    	}
    
    }
    // Inserts new element after cursor element in this List
    void insertAfter(int data){
    	 if(length()>0 && getIndex()>=0){
    		node temp = new node(data);
     		if (cursor.next != null) {
     		    cursor.next.prev = temp;
     		    temp.next = cursor.next;
     		    temp.prev = cursor;
     		} else {
     		    back = temp;
     		}
     		cursor.next = temp;
			length++;
    	 }else{
    		 throw new RuntimeException("Function insertAfter; the length of the list isn't long enough");
    	 }
     }
    // Deletes the front element in this List.
    void deleteFront(){
    	if(length()>0){
    		if(cursor == first){
    			cursor = null;
    		}
    		first = first.next;
    		first.prev = null;
			length--;
    	}else{
    		throw new RuntimeException("Function deleteBack; List isn't long enough");
    	}
    	
    }
	// Deletes the back element in this List.
    void deleteBack(){
    	if(length()>0){
    		if(cursor == back){
    			cursor = null;
    		}
    		back = back.next;
    		back.prev = null;
			length--;
    	}else{
    		throw new RuntimeException("Function deleteBack; List isn't long enough");
    	} 
    }
	// Deletes cursor element in this List. Cursor is undefined after this operation.
    void delete(){
    	 if(length()>0 && getIndex()>=0){
    		 if (cursor == first){
    			    deleteFront();
    			} else if (cursor == back){
    			    deleteBack();
    			}
    			if (length() > 0 && getIndex() > 0){
    			    cursor.next.prev = cursor.prev;
    			    cursor.prev.next = cursor.next;
    			    cursor = null;
					length--;
    			}
    		 
    	 }else{
    		 throw new RuntimeException("Function deleteg; List isn't long enough");
    	 }
     }
  
// Other methods
	
	/* Overrides Object's toString method. Creates a string
	// consisting of a space separated sequence of integers
    // with front on the left and back on the right. The
	// cursor is ignored. */
    public String toString(){
    	node temp = this.first;
    	String indices = "";
    	int i;
    	String addon;
    	while(temp != null){
    		i = temp.item;
    		addon =String.valueOf(i);
    		indices = indices+" "+addon;
    		temp = temp.next;
    	}
    	return indices;
    	
    }
    
	// Returns a new list representing the same integer sequence as this
	// list. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.
    List copy(){
    	List copy = new List();
    	node temp = first;
    	while(temp != null){
    		copy.append(temp.item);
    		temp = temp.next;
    	}
    	return copy;	
    }
}
