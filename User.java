
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE



package elements;

import java.util.*;

import boxes.*;

/**
 * This class implements a basic user. Each user has an id, inbox, outbox,and a friends list.
 * This class can add a friend, remove a friend, and check if two user are friends or not.
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since 2020-05-13
 */
public class User {
	
	/**
	 * the id of the user
	 */
	private int id;
	
	/**
	 * the inbox of the user where all coming messages will be kept 
	 */
	private Inbox inbox;
	
	/**
	 * the outbox of the user where all going messages will be kept
	 */
	private Outbox outbox;
	
	/**
	 * the list of friends of the user
	 */
	private ArrayList<User> friends;

	
	/**
	 * Constructs a user with an id. Also creates their inbox, outbox and friends lists.
	 * @param id the id that will be set as user's id
	 */
	public User(int id) {

		this.id = id;
		inbox=new Inbox(this);
		outbox=new Outbox(this);
		this.friends=new ArrayList<>();

	}

	
	/**
	 * a method for adding a friend to the friend list. It also add the user to the other user's friends list.
	 * @param other the given user to add friend
	 */
	public void addFriend(User other) { 

		if (!this.isFriendsWith(other)) {
			//checks if they are already friends or not

			this.friends.add(other);
			other.friends.add(this);
			
		
		}
	}
		
		
	/**
	 *  a method for removing a friend from the friends list. It also removes the user from the other user’s friends list.
	 * @param other the given user to remove from friend list
	 */
	public void removeFriend(User other) { 

		if (this.isFriendsWith(other)) {
			//checks if they are friends or not

			this.friends.remove(other);
			
			other.friends.remove(this);
			
		        
		}
	}

	
	/**
	 * Returns true if the user and the other user are friends, false otherwise.
	 * @param other the given user to check if they are friends or not
	 * @return a boolean value. if they are friends returns true, otherwise returns false.
	 */
	public boolean isFriendsWith(User other) {

		return friends.contains(other);

	}

	
	/**
	 * 
	 * @param receiver the receiver of the message
	 * @param body	   the text body of the message
	 * @param time	   the time pointing to the sending time of the message
	 * @param server   the server which the message will be sent
	 */
	public void sendMessage(User receiver, String body, int time, Server server) {
		
		
		
		Message newMessage= new Message(this,receiver,body,server,time);
		//creates a new message according to given parameters
		
		
		this.outbox.getSent().add(newMessage);
		//adds the new message to user's sent box
		server.getMsgs().add(newMessage);
		//adds the new message to server
		server.setCurrentSize(server.getCurrentSize()+body.length());
		//sets server's new size after the new message added
		
		
		

	}

	
	/**
	 * Returns the id of the user
	 * @return the id of the user
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the user as the given id
	 * @param id the given id that will be set as id of the user
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the inbox of the user
	 * @return the inbox of the user
	 */
	public Inbox getInbox() {
		return inbox;
	}

	/**
	 * Sets the inbox of the user as the given inbox
	 * @param inbox the given inbox that will be set as inbox of the user
	 */
	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	/**
	 * Returns the outbox of the user
	 * @return the outbox of the user
	 */
	public Outbox getOutbox() {
		return outbox;
	}

	/**
	 * Sets the outbox of the user as the given outbox
	 * @param outbox the given outbox that will be set as outbox of the user
	 */
	public void setOutbox(Outbox outbox) {
		this.outbox = outbox;
	}

	/**
	 * Returns the friends list of the user
	 * @return the friends list of the user
	 */
	public ArrayList<User> getFriends() {
		return friends;
	}

	/**
	 * Sets the friends list of the user as the given list
	 * @param friends the given list that will be set as the friends list of the user
	 */
	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

