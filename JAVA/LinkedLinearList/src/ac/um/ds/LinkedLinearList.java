package ac.um.ds;

import ac.um.ds.LinearList.LinearListBackwardIterator;
import ac.um.ds.LinearList.LinearListForwardIterator;
import ac.um.ds.LinkedLinearList.LinkedListBackwardIterator;
import ac.um.ds.LinkedLinearList.LinkedListForwardIterator;


public class LinkedLinearList<T> implements LinearList<T>{
    private class Node<T> {
        public T data;
        public Node prev;
        public Node next;
    }

    public class LinkedListForwardIterator<T> implements LinearList.LinearListForwardIterator<T> {
        private Node<T> currNode;

        //constructors:
        public LinkedListForwardIterator() {
            this.currNode = null;
        }

        public LinkedListForwardIterator(Node<T> node) {
            this.currNode = node;
        }
        public Node<T> getcurr(){return this.currNode;}
        @Override
        public T next() {
            // Write your code here
        	if(this.hasNext()){	this.currNode=this.currNode.next;}
        	return (T) this.currNode.data;
        }

        @Override
        public boolean hasNext() {
            // Write your code here
        	  if(	this.currNode.next==headerNode){return false;}else{return true;}
        }
    }

    public class LinkedListBackwardIterator<T> implements LinearList.LinearListBackwardIterator<T> {
        private Node<T> currNode;
        
        public LinkedListBackwardIterator() {
            this.currNode = null;
        }

        public LinkedListBackwardIterator(Node<T> node) {
            this.currNode = node;
        }
        public Node<T> getcurr(){return this.currNode;}
        @Override
        public T next() {
            // Write your code here
        if(this.hasNext()){	this.currNode=this.currNode.prev;}
        	return (T) this.currNode.data;
        }

        @Override
        public boolean hasNext() {
           // Write your code here		
        	if(	this.currNode.prev==headerNode){return false;}else{return true;}
        }
    }

    public LinkedLinearList() {
        mSize = 0;
        headerNode = new Node<T>();
        headerNode.next = headerNode;
        headerNode.prev = headerNode;
    }

    public LinearListForwardIterator<T> forwardIterator() {
        return new LinkedListForwardIterator<T>(headerNode);
    }

    public LinearListBackwardIterator<T> backwardIterator() {
        return new LinkedListBackwardIterator<T>(headerNode);
    }

    @Override
    public LinearListForwardIterator<T> insert(LinearListForwardIterator<T> it, T data) throws IndexOutOfBoundsException {
        // Write your code here
    	this.mSize++;
    	Node<T> newnode= new Node<T>();
    	newnode.data=data;
    	Node<T> itnode=  ((LinkedLinearList<T>.LinkedListForwardIterator<T>) it).getcurr();
    	newnode.prev=itnode;
    	newnode.next=itnode.next;
    	itnode.next.prev=newnode;
    	itnode.next=newnode;
        return it;
    }

    @Override
    public LinearListBackwardIterator<T> insert(LinearListBackwardIterator<T> it, T data) throws IndexOutOfBoundsException {
		// Write your code here		
this.mSize++;
Node<T> newnode= new Node<T>();
newnode.data=data;
Node<T> itnode= ((LinkedLinearList<T>.LinkedListBackwardIterator<T>) it).getcurr();
newnode.next=itnode;
newnode.prev=itnode.prev;
itnode.prev.next=newnode;
itnode.prev=newnode;
    	return it;
    }

    @Override
    public LinearListForwardIterator<T> remove(LinearListForwardIterator<T> it) throws IndexOutOfBoundsException {
		// Write your code here			
    	
this.mSize--;
Node<T> itnode= ((LinkedLinearList<T>.LinkedListForwardIterator<T>) it).getcurr();
itnode.prev.next=itnode.next;
itnode.next.prev=itnode.prev;
it.next();
    	return it;
    }

    @Override
    public LinearListBackwardIterator<T> remove(LinearListBackwardIterator<T> it) throws IndexOutOfBoundsException {
		// Write your code here		
this.mSize--;
Node<T> itnode=  ((LinkedLinearList<T>.LinkedListBackwardIterator<T>) it).getcurr();
itnode.prev.next=itnode.next;
itnode.next.prev=itnode.prev;
it.next();
    	return it;
	}


    public int size() {
        return mSize;
    }

    @Override
    public String toString() {
        String str = "";
        Node currNode = headerNode;
        for (int i = 0; i < mSize; i++) {
            currNode = currNode.next;
            str += (" " + currNode.data);
        }
        return str;
    }

    private Node<T> headerNode;
    private int mSize;
}
