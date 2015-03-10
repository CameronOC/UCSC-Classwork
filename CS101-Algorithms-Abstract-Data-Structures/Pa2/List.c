//Cameron Johnson
// id:1384841
// pa5
// List.c
// Cmps 101 Winter 2015
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"
// private nodeObj type
typedef struct nodeObj{
   int item;
   struct nodeObj* next;
   struct nodeObj* prev;
} nodeObj;


// private node type
typedef nodeObj* node;

// node constructor
node newNode(int data){
	node temp = malloc(sizeof(nodeObj));
	temp->item = data;
	temp->prev = NULL;
	temp->next = NULL;
	return temp;
}

// free node function
void freeNode(node* clean){
	if(*clean != NULL && clean != NULL){
		node* temp = clean;
		free(*temp);
		*clean = NULL;
	}
}
// private ListObj type
typedef struct ListObj{
   node first;
   node back;
   node cursor;
   int index;
   int length;
} ListObj;

 // Constructor
    // List() // Creates a new empty list.
    // Access functions
List newList(void){
	List L;
    L = malloc(sizeof(ListObj));
    L->first = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->index = -1;
	L->length = 0;
    return(L);
}
	// frees memory of list
void freeList(List* pL){
	if(pL!=NULL && *pL!=NULL){
		clear(*pL);
		free(*pL);
		*pL = NULL;
		}
}
	// Returns number of elements in this list.
int length(List L) {
    if ( L->first == NULL){
		return 0;
    }else{
		return (L->length);
	}

} 
	// Returns the index of the cursor element in this list, or
   // returns -1 if the cursor element is undefined.
int getIndex(List L) {
	if(L->cursor == NULL){
		return (-1);
	}else{
    	return (L->index);
	}
} 
	// Returns back element in this List.
int front(List L){
    if(length(L)>0){
        return (L->first->item); 
    }else {
		printf("Stack Error: calling front() on NULL Stack reference\n");
		exit(1);
    }
} 
	// Returns back element in this List.   
int back(List L){
    if(length(L)>0){
        return L->back->item;
    }else {
        printf("Stack Error: calling back() on NULL Stack reference\n");
		exit(1); 
    }
}

     
	// Returns cursor element in this list.
int getElement(List L){
    if(length(L)>0 && getIndex(L)>=0 ){
		if(L->cursor == NULL){
			return(-1);
		}else{
			return (L->cursor->item);
		}
    }else{
		return (-1) ;
    }
}
	// Returns true if List B and A are the same List
int equals(List A, List B){
    node temp = A->first;
    node temp2 = B->first;
    for(;;){
    	if(temp == NULL&& temp2 == NULL){
    	return (1);
    	}
    	if(temp2->item == temp->item){
    		temp = temp->next;
        	temp2 = temp2->next;
    	}else{
    		return (0);
    		
    	}
    }
    
}

// Manipulation procedures
	// Re-sets this List to the empty state.
void clear(List L){	
	node temp = L->first;
	while(temp != NULL){
		node temp2 = temp;
		temp = temp->next;
		freeNode(&temp2);
	}
    L->first = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->index = -1;// Re-sets this List to the empty state.
}
	/* moves the cursor to the element at index i,
	otherwise the cursor becomes undefined.*/
void moveTo(List L,int i){
    if(0<=i && i<=length(L)-1){
    	L->cursor = L->first;
    	L->index = i;
    	for( ;i>0;i--){
    		L->cursor = L->cursor->next;
    		}
    }else{
    	L->cursor = NULL;
    	L->index = -1;
    }
}
    /*moves the cursor one step toward the
	 front of the list. If getIndex()==0, cursor becomes undefined.
     If getIndex()==-1, cursor remains undefined. This operation is
     equivalent to moveTo(getIndex()-1). */
void movePrev(List L){
    if(0<getIndex(L)&& getIndex(L)<=length(L)-1){
			L->cursor = L->cursor->prev;
			L->index--;
    }else{
			L->cursor = NULL;
			L->index = -1;
    }
}
    /* If 0<=getIndex()<length()-1, moves the cursor one step toward the
	 back of the list. If getIndex()==length()-1, cursor becomes
     undefined. If getIndex()==-1, cursor remains undefined. This
     operation is equivalent to moveTo(getIndex()+1). */
void moveNext(List L){
    if(0<=getIndex(L) && getIndex(L)<length(L)-1){
		L->cursor = L->cursor->next;
		L->index++;
    }else{
    	L->cursor = NULL;
    	L->index = -1;
    }
}
    // Inserts new element before front element in this List.
void prepend(List L,int data){
    if(L->first == NULL){
    	node temp = newNode(data);
        L->first = temp;
        L->back = temp;
    }else{
		node temp = newNode(data);
    	temp->next = L->first;
    	L->first->prev = temp;
    	L->first = temp;	
    }
	L->length++;
}
	// Inserts new element after back element in this List.
void append(List L,int data){
    if(L->back == NULL){
    	node temp = newNode(data);
        L->first = temp;
        L->back = temp;
    }else{
    	node temp = newNode(data);
    	temp->prev = L->back;
    	L->back->next = temp;
    	L->back = temp;	
    }
	L->length++;
}
    // Inserts new element before cursor element in this List
void insertBefore(List L,int data){
    if(length(L)>0 && getIndex(L)>=0){
    	node temp = newNode(data);
    	if (L->cursor->prev != NULL) {
    		L->cursor->prev->next = temp;
    		temp->prev = L->cursor->prev;
    		temp->next = L->cursor;		    
    	} else {
    		L->first = temp;
			temp->next = L->cursor;	
    	}
    	L->cursor->prev = temp;
		L->length++;
    }else{
    		printf("Stack Error: calling insertBefore() on NULL Stack reference\n");
			exit(1);
    }
}
    // Inserts new element after cursor element in this List
void insertAfter(List L,int data){
    if(length(L)>0 && getIndex(L)>=0){
    	node temp = newNode(data);
     	if (L->cursor->next != NULL) {
     		L->cursor->next->prev = temp;
     		temp->next = L->cursor->next;
     		temp->prev = L->cursor;
     	} else {
     		L->back = temp;
     	}
     	L->cursor->next = temp;
		L->length++;
    }else{
    	return;
    }
}
    // Deletes the front element in this List.
void deleteFront(List L){
    if(length(L)>0){
    	if(L->cursor == L->first){
    		node erase = L->cursor;
    		freeNode(&erase);
    	}
    	L->first = L->first->next;
		node temp = L->first->prev;
    	freeNode(&temp);
		L->length--;
    }else{
    	printf("Stack Error: calling deleteFront(L) on NULL Stack reference\n");
		exit(1);
    }
}
	// Deletes the back element in this List.
void deleteBack(List L){
    if(length(L)>0){
    	if(L->cursor == L->back){
			node erase = L->cursor;
    		freeNode(&erase);
	  	}
    	L->back = L->back->next;
		node temp = L->back->prev;
    	freeNode(&temp);
		L->length--;
    }else{ 
    	printf("Stack Error: calling deleteBack(L) on NULL Stack reference\n");
		exit(1);
    }
}
	// Deletes cursor element in this List. Cursor is undefined after this operation.
void delete(List L){
    if(length(L)>0 && getIndex(L)>=0){
    	if (L->cursor == L->first){
			deleteFront(L);
    	} else if (L->cursor == L->back){
    		deleteBack(L);
    	}
    	if (length(L) > 0 && getIndex(L) > 0){
			L->cursor->next->prev = L->cursor->prev;
    		L->cursor->prev->next = L->cursor->next;
			node erase = L->cursor;
    		freeNode(&erase);
			L->length--;
    	}
 
    }else{
    		printf("Stack Error: calling delete(L) on NULL Stack reference\n");
			exit(1);
    }
}
    
// Other methods
void printList(FILE* out, List L){
    node temp = NULL;
    for(temp = L->first; temp != NULL; temp = temp->next){
		fprintf(out,"%d ", temp->item);
    }
}
	// Returns a new list representing the same integer sequence as this
	// list. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.
List copyList(List L){
    List copy = newList();
    node temp = L->first;
    while(temp != NULL){
    	append(copy,temp->item); 
    	temp = temp->next;
    }
    return (copy);   	
}
