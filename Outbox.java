
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE




package boxes;

import java.util.*;

import elements.*;

/**
 * 
 * This class implements an outbox with its'owner.It extens box class and has all properties of box class.
 * Every outbox has sent queue.This queue can keep sent messages.
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since 2020-05-13
 *
 */

public class Outbox extends Box {

	
	/**
	 *  a queue which keeps going messages of its' owner
	 */
	private Queue<Message> sent;

	
	/**
	 * Constructs an outbox with its' owner. Also creates a sent list to save going message.
	 * @param owner the owner of the outbox
	 */
	public Outbox(User owner) {

		super(owner);
		this.sent = new LinkedList<>();

	}

	/**
	 * Returns the sent queue of the outbox
	 * @return the sent queue of the outbox
	 */
	public Queue<Message> getSent() {
		return sent;
	}

	/**
	 * Sets the sent queue of the outbox as given queue
	 * @param sent the given queue that will be set as sent queue of the outbox
	 */
	public void setSent(Queue<Message> sent) {
		this.sent = sent;
	}
}




//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

