
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package executable;

import java.io.*;
import java.util.*;

import boxes.*;
import elements.*;

/**
 * This is a CMPE 160 project. The main objective of this project is implementing a basic messaging program.
 *  This program contains users, the server, messages, mailboxes and users' actions such that
 * adding friend, sending message. This project reads a input file and
 * executes according to data in this file. Prints the output to an output file via print stream.
 * 
 * @author Hasan Baki Kucukcakiroglu
 * @since 2020-05-13
 */

public class Main {
	
	/**
	 * This method executes code according to given arguments. It contains for
	 * loops, variables, scanners, print streams, array lists and if else
	 * statements.
	 * 
	 * This main method executes the related code blocks according to given argument. 
	 * 
	 * @param args args
	 * @throws FileNotFoundException if there is not an input file throws a file nott found exception
	 */

	public static void main(String[] args) throws FileNotFoundException {

		
		Scanner input = new Scanner(new File(args[0]));
		// reads the input from input file.

		PrintStream output = new PrintStream(new File(args[1]));
		// provides us to write output in a blank file.


		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Message> messages = new ArrayList<Message>();
		// Array lists represent all users and all messages
		// respectively. They are sorted by ID.

		int currentTime = 0;
		
		//keeps the current time according to the elapsed time during the operations

		int numOfUsers = input.nextInt();
		//this integer takes from input file and keeps number of users
		int numOfOperations = input.nextInt();
		//this integer takes from input file and keeps number of operations
		int capacityOfServer = input.nextInt();
		//this integer takes from input file and keeps capacity of server

		
		//this for loop creates new users according to number of users coming from input. Add them users array list.
		for (int n = 0; n < numOfUsers; n++) {

			users.add(new User(n));

		}

		Server server = new Server(capacityOfServer);
		//the server of message program. 

		input.nextLine();
		

	
		
		
		for (int i = 0; i < numOfOperations; i++) {
			//this for loop reads all lines of input file one by one and directs the code according to operation number.

			String line = input.nextLine();
			//takes the following line of input file

			Scanner liner = new Scanner(line);
			//creates a scanner that use the following line of the input file

			int operationCode = liner.nextInt();
			//the beginning of every input line includes an operation number. This integer keeps this operation number.

			
			

			
			
			if (operationCode == 0) {//send message
				//if operation code coming from input is 0, this part of code is executed and the given sender send a message
				//to the given receiver.

				int senderId = liner.nextInt();
				//the id of the sender coming from input file
				
				int receiverId = liner.nextInt();
				//the id of the receiver coming from input file
				
				String body = liner.next();
				//the text of the message coming from the input file

				while (liner.hasNext()) {
					//this loop adds proper lines of input to body of the message 

					body += " " + liner.next();
				}

				users.get(senderId).sendMessage(users.get(receiverId), body, currentTime, server);
				

				Iterator<Message> itr = users.get(senderId).getOutbox().getSent().iterator();
				//this iterator travels the sent message box of the user

				Message lastSended = null;

				while (itr.hasNext()) {
				//this while loop travels until the end of the sent box. And determines last message.

					lastSended = itr.next();

				}
				
				messages.add(lastSended);
				//adds the last sent message to messages array list.

				messages.get(Message.numOfMessages).setId(Message.numOfMessages);
				//determines the id of the last sent message.

				Message.numOfMessages++;
				//increase the number of the messages by one

				currentTime++;
				//increase the current time by one since sending message operation takes one time

				server.checkServerLoad(output);
				//checks the server load to determine if there is a situation requiring to print a warning or not.
				//if there is, this method prints a warning. 

			
			
			
			
			
			} else if (operationCode == 1) {// receive messages
				//if operation code coming from input is 1, this part of code is executed and the given receiver takes all messages
				//belonging him/her from the server.
				
				int receiverId = liner.nextInt();
				//the id of the receiver coming from input

				users.get(receiverId).getInbox().receiveMessages(server, currentTime);

				int serverSize = server.getMsgs().size();
				//keeps the size of the server(total message lengths)

				long currentSizeCounter = 0;
				//keeps the total lengths of messages on the server
				

				for (int a = 0; a < serverSize; a++) {
				//this for loop travels on the server and sums up lengths of all messages

					Message temp = server.getMsgs().poll();

					currentSizeCounter += temp.getBody().length();

					server.getMsgs().add(temp);
				}

				server.setCurrentSize(currentSizeCounter);
				//sets the current size of the server as the value of the current size counter.

				server.checkServerLoad(output);
				//checks the server load to determine if there is a situation requiring to print a warning or not.
				//if there is, this method prints a warning. 

				currentTime++;
				//increase the current time by one since receiving message operation takes one time

				

			
			
			
			} else if (operationCode == 2) {//read a certain amount of messages
				//if operation code coming from input is 2, this part of code  read a certain amount of messages from the inbox of the user.

				
				int receiverId = liner.nextInt();
				//the id of receiver coming from input
				
				int numberOfMessages = liner.nextInt();
				//the number of messages the will be read

				int incrementOfTime = users.get(receiverId).getInbox().readMessages(numberOfMessages, currentTime);
				//keeps the number of reading message from user's inbox.

				currentTime += incrementOfTime;
				//increase the current time by increment of time .Increment of time
				// keeps the number of read message.



				
					
				
			} else if (operationCode == 21) {//read all messages from a specific sender
				// if the operation code coming from input is 21, this part of code read all the messages coming  from a sender. 

				int receiverId = liner.nextInt();
				//the id of receiver coming from input

				int senderId = liner.nextInt();
				//the id of sender coming from input


				int incrementOfTime = users.get(receiverId).getInbox().readMessages(users.get(senderId), currentTime);
				//keeps the number of reading message from user's inbox.
			
				currentTime += incrementOfTime;
				//increase the current time by increment of time .Increment of time
				// keeps the number of read message.
				
				
				
				
				

			} else if (operationCode == 22) {//read a specific message 
				// if the operation code coming from input is 22, this part of code read the specific message in the inbox.
				//if there is no such a message does nothing.

				int receiverId = liner.nextInt();
				//the id of receiver coming from input
				
				int messageId = liner.nextInt();
				//the id of message coming from input

				users.get(receiverId).getInbox().readMessage(messageId, currentTime);

				currentTime++;
				//increase the current time by one since reading a message takes one time

				
				
				
				

			} else if (operationCode == 3) {//add friend
				//if the operation code coming from input is 3, this part of code provide user to add a friend.
				//if they are already friends, does nothing.

				int id1 = liner.nextInt();
				//the id of first user will be added as a friend. 
				
				int id2 = liner.nextInt();
				//the id of second user will be added as a friend. 


				users.get(id1).addFriend(users.get(id2));

				currentTime++;
				//increase the current time by one since adding friend operation takes one time

				
				
				
				

			} else if (operationCode == 4) {//remove friend
				// if the operation code coming from input is 4, this part of code removes the given friend
				//from user's friend list. If they are not friend, does nothing.

				int id1 = liner.nextInt();
				//the id of first user will be removed from friend list.

				int id2 = liner.nextInt();
				//the id of second user will be removed from friend list.


				users.get(id1).removeFriend(users.get(id2));

				currentTime++;
				//increase the current time by one since removing friend operation takes one time

				
				
				
				

			} else if (operationCode == 5) {//flush the server
				// if the operation code coming from input is 5, this part of code deletes all messages on the server.

				server.flush();

				currentTime++;
				//increase the current time by one since flushing operation takes one time
				


				
				
				
			} else if (operationCode == 6) {// print the current size of the server
				//if the operation code coming from input is 6, this part of code prints the current character size
				//of the server

				output.println("Current load of the server is " + server.getCurrentSize() + " characters.");

				currentTime++;
				//increase the current time by one since printing the current size of the server operation takes one time


				
				
				
				
			} else if (operationCode == 61) {// print the last message a user read
				//if the operation code coming from input is 61, this part of code prints the last message read from the user.

				int userId = liner.nextInt();
				//the is of user coming from input

				int sizeOfRead = users.get(userId).getInbox().getRead().size();
				//the size of read box.

				Message last = null;

				for (int a = 0; a < sizeOfRead; a++) {
				//this loop is created in order to determine the last message on the read box.

					last = users.get(userId).getInbox().getRead().poll();

					users.get(userId).getInbox().getRead().add(last);

				}

				if (last != null) {
				//if there is a message read from the user prints this message.

					output.println(last.toString());

				}

				currentTime++;
				//increase the current time by one since printing the last message operation takes one time


			} else {
				//if the given operation code isn't proper

			}

		}

	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


