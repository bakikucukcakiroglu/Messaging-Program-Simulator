
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE




package boxes;

import elements.*;

/**
 * This class implements a box with an owner. This class is parent of the inbox and outbox classes. 
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since  2020-05-13
 *
 */

public abstract class Box {

	/**
	 * the owner of the box
	 */
	private User owner;

	
	/**
	 * Constructs a box with its'owner
	 * @param owner the owner of the box
	 */
	public Box(User owner) {

		this.owner = owner;
	}

	
	/**
	 * Returns the owner of the box
	 * @return the owner of the box
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of the box as the given user
	 * @param owner the given user that will be set as the owner of the box
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
}




//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

