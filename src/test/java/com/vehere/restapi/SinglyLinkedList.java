package com.vehere.restapi;


class Node{
	Node next;
	int data;
	
}

public class SinglyLinkedList {

	Node head;
	public void insertFirst(int data) {
		Node newNode=new Node();
		newNode.data=data;
		newNode.next=head;
		head=newNode;
	}
	
	public void insertLast(int data) {
		Node current=head;
		while(current.next!=null) {
			current=current.next;
		}
		Node newNode=new Node();
		newNode.data=data;
		current.next=newNode;
	}
	public void deleteFirst() {
		Node temp=head;
		head=temp.next;
		System.out.println("Node deleted at first: "+temp.data);
		
		
	}
	
	public void deleteLast() {
		Node current=head;
		while(current.next.next!=null) {
			current=current.next;
		}
		current.next=null;
		System.out.println("Delete last node");
		
	}
	 public void insertNth(int data, int position) {
	       
		  Node node = new Node();
	        node.data = data;
	        node.next = null;


	        if (this.head == null) {
	            //if head is null and position is zero then exit.
	            if (position != 0) {
	                return;
	            } else { //node set to the head.
	                this.head = node;
	            }
	        }

	        if (head != null && position == 0) {
	            node.next = this.head;
	            this.head = node;
	            return;
	        }

	        Node current = this.head;
	        Node previous = null;

	        int i = 0;

	        while (i < position) {
	            previous = current;
	            current = current.next;

	            if (current == null) {
	                break;
	            }

	            i++;
	        }

	        node.next = current;
	        previous.next = node;
	    }

	 public void deleteByPosition(int position) {
		 if (head == null) 
	            return; 
	  
	        // Store head node 
	        Node temp = head; 
	  
	        // If head needs to be removed 
	        if (position == 0) 
	        { 
	            head = temp.next;   // Change head 
	            return; 
	        } 
	  
	        // Find previous node of the node to be deleted 
	        for (int i=0; temp!=null && i<position-1; i++) 
	            temp = temp.next; 
	  
	        // If position is more than number of nodes 
	        if (temp == null || temp.next == null) 
	            return; 
	  
	        // Node temp->next is the node to be deleted 
	        // Store pointer to the next of node to be deleted 
	        Node next = temp.next.next; 
	  
	        temp.next = next;  // Unl
		 
	 }
	   
	public void printList() {
		System.out.println("Printing LinkedList (head --> last) ");
		Node current=head;
		while(current!=null) {
			System.out.println(current.data);
			current=current.next;
		}
	}
	public static void main(String args[]) {
		SinglyLinkedList ss=new SinglyLinkedList();
		ss.insertFirst(10);
		ss.insertLast(20);
		ss.insertFirst(5);
		ss.insertLast(20);
		ss.insertLast(21);
		ss.insertLast(22);
		ss.insertLast(23);
		ss.insertLast(87);
		ss.insertLast(99);
		ss.printList();
		ss.deleteFirst();
		ss.printList();
		ss.deleteLast();
		ss.printList();
		ss.insertNth(47,4);
		ss.printList();
		ss.deleteFirst();
		ss.deleteFirst();
		ss.deleteFirst();
		ss.deleteFirst();
		ss.insertNth(300,1);
		ss.printList();
		ss.deleteByPosition(0);
		ss.printList();
		

	}
	
}
