
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE




package elements;

import java.util.*;

/**
 * This class implements a simple message. Each message has an id, a body, a sender, a receiver and various time stamps.
 * This class can compare messages and convert message objects to strings.
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since 2020-05-13
 *
 */


public class Message {
	
    /** 
	  * number of total messages in the program
	 */
	public static int numOfMessages = 0;
	
	/** 
	 * the id of message
	 */
	private int id;
	
	/** 
	  * the body of the message composing of text
	 */
	private String body;
	
	/** 
	 * the sender of the message
	 */
	private User sender;
	
	/**  
	 * the receiver of he message
	 */
	
	private User receiver;
	
	/**  
	 * the time indicating when the message was sent
	 */
	private int timeStampSent;
	
	/** the time indicating when the message was read
	 */
	
	private int timeStampRead = -1;
	
	/** the time indicating when the message was received
	 */
	private int timeStampReceived = -1;

	
	
	/**
	 * Constructs a message with a given sender, receiver, body, server and time.
	 * 
	 * @param sender 	the sender of the message
	 * @param receiver	the receiver of the message
	 * @param body		the text body of the message
	 * @param server	the server which the message will sent
	 * @param time		the time when the message will
	 */
	public Message(User sender, User receiver, String body, Server server, int time) {

		this.body = body;
		this.sender = sender;
		this.receiver = receiver;
		this.timeStampSent=time; 

	}

	
	/**
	 * Compares two messages according to their body length.
	 * @param o other message that will be compared
	 * @return  1 if the first message is longer, -1 if the second message is longer or 0 if their length is equal
	 */
	public int compareTo(Message o) { 

		if (this.body.length() > o.body.length()) {

			return 1;
		
		} else if (this.body.length() < o.body.length()) {

			return -1;
		
		} else {

			return 0;
		}
	}
	
	/**
	 *  Returns true if two messages have same id, else returns false.
	 *  @return a boolean that determines if they are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(this.id==((Message)obj).id) {
		
			return true;
		
		}else {
			
			return false;
		
		}

	}
	
	/**
	 * Converts message object to a string with a proper form.
	 * 
	 */
	@Override
	public String toString() {

		if (this.timeStampReceived == -1) {
			//if the message is not received

			String messageLabel = "\tFrom: " + this.sender.getId() + " To: " + this.receiver.getId() + "\n"
					+ "\tReceived:  Read: \n\t" + this.body;

			return messageLabel;
		
		} else if (this.timeStampRead == -1) {
			//if the message is not read

			String messageLabel = "\tFrom: " + this.sender.getId() + " To: " + this.receiver.getId() + "\n"
					+ "\tReceived: " + this.timeStampReceived + " Read: \n\t" + this.body;

			return messageLabel;
		
		} else {
			//if the message is received and  is read

			String messageLabel = "\tFrom: " + this.sender.getId() + " To: " + this.receiver.getId() + "\n"
					+ "\tReceived: " + this.timeStampReceived + " Read: " + this.timeStampRead + "\n\t" + this.body;

			return messageLabel;
		}

	}
	
	/**
	 * Returns the id of the message
	 * @return the id of the message
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the message as given id
	 * @param id  the given id that will be set as id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the body of the message
	 * @return the id of the message
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Sets the body of the message as given body.
	 * @param body the given body that will be set as body
	 */ 
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Returns the sender of the message
	 * @return the sender of the message
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Sets the sender of the message as the given sender
	 * @param sender the given sender that will be set as sender
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * Returns the receiver of the message
	 * @return the receiver of the message
	 */
	public User getReceiver() {
		return receiver;
	}

	/**
	 * Sets the receiver of the message as the given receiver
	 * @param receiver the given receiver that will be set as receiver
	 */
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	/**
	 * Returns the sent time of the message
	 * @return the sent time of the message
	 */
	public int getTimeStampSent() {
		return timeStampSent;
	}
	
	/**
	 * Sets the sent time of the message as the given time
	 * @param timeStampSent the time that will be set as the sent time of the message
	 */
	public void setTimeStampSent(int timeStampSent) {
		this.timeStampSent = timeStampSent;
	}

	/**
	 * Returns the read time of the message
	 * @return the read time of the message
	 */
	public int getTimeStampRead() {
		return timeStampRead;
	}

	/**
	 * Sets the read time of the message as the given time
	 * @param timeStampRead the time that will be set as the read time of the message
	 */
	public void setTimeStampRead(int timeStampRead) {
		this.timeStampRead = timeStampRead;
	}

	/**
	 * Returns the receive time of the message
	 * @return the receive time of the message
	 */
	public int getTimeStampReceived() {
		return timeStampReceived;
	}

	/**
	 * Sets the receive time of the message as the given time
	 * @param timeStampReceived the time that will be set as the receive time of the message
	 */
	public void setTimeStampReceived(int timeStampReceived) {
		this.timeStampReceived = timeStampReceived;
	}

}




//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

