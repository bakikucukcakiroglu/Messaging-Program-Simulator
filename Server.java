
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE




package elements;

import java.io.*;
import java.util.*;

/**
 * This class implements a server with a capacity,current size, queue of messages and server load.
 * This server can print warnings and delete all messages.
 * This server keeps current size of the server and server load. Saves messages in a queue named as msgs.
 * 
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since  2020-05-13
 *
 */
public class Server{
	
	/**
	 * 

	 * @param currentSize	total number of the characters of messages' bodies in the msgs queue
	 * @param msgs			a queue where non received messages are stored
	 * @param serverLoad	current load of server. Keeps currentSize/capacity rate.
	 * 
	 */
	

	/** 
	 * capacity of the server
	 */
	private long capacity;
	
	/**
	 * total number of the characters of messages' bodies in the msgs queue
	 */
	private long currentSize;
	
	/**
	 * a queue where non received messages are stored
	 */
	private Queue<Message>msgs;

	/**
	 * current load of server. Keeps currentSize/capacity rate.
	 */
	public double serverLoad=0;
	
	
	/**
	 * Construct a server with given capacity. Creates a queue to provide server to save messages.
	 * 
	 * @param capacity  	capacity of the server
	 */
	public Server(long capacity) {
		
		this.capacity=capacity;
		
		this.msgs=new LinkedList<>();
		
	}
	
	
	/**
	 * checks the server load and if there is a situation requires printing a warning prints a warning. 
	 * This method also can delete all messages from server if it is full.
	 * 
	 * @param printer 		a PrintStream that provide us printing on the output file
	 */
	public void checkServerLoad(PrintStream printer) {
		
		double oldServerLoad= this.serverLoad;
		//keeps the server load before update(before sending a message or receiving a message)
		
		
		serverLoad= (double)this.currentSize/this.capacity;
		//current load of server. keeps the ratio of current size and capacity
		
		
		if (oldServerLoad < 0.5 && serverLoad >= 0.5 && serverLoad < 0.8) {
			//if servers load exceeds %50 but does not exceed %80 and if it comes from below %50			
			
			printer.println("Warning! Server is 50% full.");

		}
		if (oldServerLoad < 0.8 && serverLoad >= 0.8 && serverLoad < 1) {
			//if servers load exceeds %80 but does not exceed %100 and if it comes from below %80			

			printer.println("Warning! Server is 80% full.");
		}
		if (serverLoad >= 1) {
			//if servers load exceeds %100			

			
			this.flush();
		

			printer.println("Server is full. Deleting all messages...");
			
		}
		if (oldServerLoad >= 0.8 && oldServerLoad < 1 && serverLoad >= 0.5 && serverLoad < 0.8) {
			//if servers drop below %80 but does not drop below %50 and if it comes from above %80		


			printer.println("Warning! Server is 50% full.");
		}
	
	}
	
	
	/**
	 * Deletes all messages in the server.
	 */
	public void flush() {
		
		this.msgs.clear();
		this.currentSize=0;
		this.serverLoad=0;
	}

	/**
	 * Returns the capacity of the server
	 * 
	 * @return the capacity of the server
	 */
	public long getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity of the server as the given capacity
	 * 
	 * @param capacity the new capacity of the server
	 */
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	/**
	 * Returns the msgs queue of the server.
	 * 
	 * @return the queue keeping messages coming to server
	 */
	public Queue<Message> getMsgs() {
		return msgs;
	}

	/**
	 * Sets the msgs as the given queue
	 * 
	 * @param msgs the new queue keeping messages coming to server
	 */
	public void setMsgs(Queue<Message> msgs) {
		this.msgs = msgs;
	}

	/**
	 * Returns the current size of the server
	 * 
	 * @return the current size of the server
	 */
	public long getCurrentSize() {
		return currentSize;
	}

	/**
	 * Sets the current size of the server as the given size
	 * 
	 * @param currentSize the new current size of the server
	 */
	public void setCurrentSize(long currentSize) {
		this.currentSize = currentSize;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

