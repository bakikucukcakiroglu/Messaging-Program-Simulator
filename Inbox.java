
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE



package boxes;

import elements.*;

import java.util.*;


/**
 * This class implements an inbox with an user.This class extends box class so it has all properties of the box class.
 *  Each inbox has an unread stack and read queue. This class can receive messages and read messages.
 * @author Hasan Baki Kucukcakiroglu
 * @since 2020-05-13
 *
 */
public class Inbox extends Box {

	
	/**
	 *  the unread messages in the user's inbox
	 */
	private Stack<Message> unread;
	
	/**
	 * the read messages in the user's inbox
	 */
	private Queue<Message> read;

	
	/**
	 * Constructs an inbox with an owner. It also creates an unread stack and read queue.
	 * @param user  the owner of the inbox
	 */
	public Inbox(User user) {

		super(user);

		unread = new Stack<>();
		read = new LinkedList<>();

	}

	
	/**
	 * Provide inbox to takes all messages belongin inbox's owner from server
	 * @param server the server where the messages come from
	 * @param time the time that will be set as messages' receive time
	 */
	public void receiveMessages(Server server, int time) {
		
		
		int serverMessageSize = server.getMsgs().size();
		//size of messages in the server

		for (int a = 0; a < serverMessageSize; a++) { 

			Message temp = server.getMsgs().poll();  

			if (temp.getReceiver().getId() == this.getOwner().getId() && this.getOwner().isFriendsWith(temp.getSender())) {
				//if the receiver of the message equals the owner of the inbox and, sender and owner are friends takes the message

				unread.add(temp);
				temp.setTimeStampReceived(time);

			} else {
				//if they are not friends or the message doesn't belong to owner of this inbox ,adds message to server again

				server.getMsgs().add(temp);
			}

		}

	}

	
	/**
	 * Reads a certain amount of message from unread stack.
	 * @param num the number of message will be read
	 * @param time the time that will be set as messages' read time
	 * @return an integer representing number of messages that is read
	 */
	public int readMessages(int num, int time) {

		int properMessageCounter = 0;
		//counts proper messages to read

		if (unread.size() == 0) {
			//if there is no message in the unread stack. Time increase by one.

			
			return 1;
		

		} else if (num == 0) {
			//if num is 0, read all messages in the unread stack.

			int size = unread.size();
			//size of the unread stack

			for (int a = 0; a < size; a++) {
				//travels the unread stack and sends all messages to read queue and adds a time read stamp to all messages.
				//time increase by the number of messages that is read

				Message temp = unread.pop();

				temp.setTimeStampRead(time + properMessageCounter);

				read.add(temp);

				properMessageCounter++;
			}

			return size;

		} else if (num >= unread.size()) {
			//if number of messages that will be read is bigger than the size of the unread stack, reads all messages in the stack

			int size = unread.size();
			//size of unread stack

			for (int a = 0; a < size; a++) {
				//travels the unread stack and sends all messages to read queue and adds a time read stamp to all messages.
				//time increase by the number of messages that is read

				Message temp = unread.pop();

				temp.setTimeStampRead(time + properMessageCounter);

				read.add(temp);

				properMessageCounter++;
			}

			return size;

		} else { 
			//if the number of the messages that will be read is lower than the size of the unread stack, reads the number of message.

			for (int a = 0; a < num; a++) {
				//travels the unread stack num times and sends messages to read queue and adds a time read stamp to all messages.
				//time increase by the number of messages that is read

				Message temp = unread.pop();

				temp.setTimeStampRead(time + properMessageCounter);

				read.add(temp);

				properMessageCounter++;
			}

			return num;

		}
	}

	
	/**
	 * reads all messages from a specific sender.
	 * @param sender the wanting sender
	 * @param time the time that will be set as read time of the message
	 * @return an integer representing the number of messages that is read
	 */
	public int readMessages(User sender, int time) { 
	


		
		int properMessageCounter = 0;
		//counts proper messages to read
		
		Stack<Message>temp= new Stack<>();
		//a temporary stack in order to make some traveling operations on the unread stack
		
		int size= this.getUnread().size();
		//size of unread stack
		
		for(int a= 0; a<size; a++) {
			//travels unread stack
			
			Message temporary= this.getUnread().pop();
			
			if(temporary.getReceiver()==this.getOwner()&&temporary.getSender()==sender) {
				//if the message's sender equals the wanted sender, adds the message to read queue and increase proper message by one
				
				read.add(temporary);
				temporary.setTimeStampRead(time+properMessageCounter);
				properMessageCounter++;
				
			}else {
				//if the message is not proper to read, add it back to temporary stack
				
				temp.add(temporary);
			}
			
		}
		
		
		//the block of code reverse the temporary stack and add it unread stack one by one to make unread stack o previous state
		int sizeOfTempStack=temp.size();
		
		for(int a=0; a<sizeOfTempStack; a++) {
			
			this.getUnread().add(temp.pop());
			
		}
		
		
		
		if(properMessageCounter!=0) {
			//if the number of messages that is read is not 0, increase time by number of message that is read
			
		return properMessageCounter;
		
		}else {
			//if the number of messages that is read is 0, increase time by one
			
			return 1;
		}
	}

	
	/**
	 * Reads a specific message in the unread stack
	 * @param msgId the id of the wanted message
	 * @param time the time that will be set as read time of the message
	 */
	public void readMessage(int msgId, int time) { 


		
		Stack<Message>tempStack= new Stack<>();
		//a temporary stack in order to make some traveling operations on the unread stack

		
		int size=this.getUnread().size();
		//size of the unread stack
		
		for(int a=0; a<size; a++) {
			//travels unread stack 
			
			Message temp= this.getUnread().pop();
			
			if(temp.getId()==msgId) {
				//if the id of the message equals the id  of the wanted message, adds this message to read queue and sets the message's time read stamp
				
				read.add(temp);
				temp.setTimeStampRead(time);
				
			}else {
				//if the id of message doesn't equal the id of the wanted message, add this message back  to temporary stack
				
				tempStack.add(temp);
				
			}
			
		}
		
		//the block of code reverse the temporary stack and add it unread stack one by one to make unread stack o previous state
		int sizeOfTempStack=tempStack.size();
		for(int a=0; a< sizeOfTempStack; a++) {
			
			this.getUnread().add(tempStack.pop());
			
		}
	}

	
	/**
	 * Returns the unread stack of the inbox
	 * @return the unread stack of the inbox
	 */
	public Stack<Message> getUnread() {
		return unread;
	}

	/**
	 * Sets the unread stack of the inbox as the given stack
	 * @param unread the given stack that will be set as unread stack of the inbox
	 */
	public void setUnread(Stack<Message> unread) {
		this.unread = unread;
	}

	/**
	 * Returns the read queue of the inbox 
	 * @return the read queue of the inbox
	 */
	public Queue<Message> getRead() {
		return read;
	}

	/**
	 * Sets the read queue of the inbox as the given queue
	 * @param read the given queue that will be set as read queue of the inbox
	 */
	public void setRead(Queue<Message> read) {
		this.read = read;
	}

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


