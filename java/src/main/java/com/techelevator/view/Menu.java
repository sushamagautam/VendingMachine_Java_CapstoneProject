package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	
	/*
	 * PrintWriter out is the menu version of system.out 
	 * Scanner in is also using the equivalent of system.in
	 * I believe the reason it is done this way is so that 
	 * if we were to for example hook this into a non-commandline
	 * program it might be able to hook into whatever that external
	 * program is using to get input and display to the screen.
	 */
	private PrintWriter out;
	private Scanner in;
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	 
	/*
	 * All this is doing is taking in a list of objects
	 * then calling display functions and getting the user input
	 * loops over until a valid choice is made
	 * By using an Object instead of a string this allows the list
	 * of options to be any type while really not losing any of the 
	 * functionality of string that this class cares about
	 * I will go into this later
	 */ 
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println(); //create blank line
		for (int i = 0; i < options.length; i++) { //loop through options
			/*
			 *put in an if to check if the object starts with a special character
			 *if so do not display option
			 */
			if(options[i].toString().charAt(0)!='&') {
				int optionNum = i + 1; //convert the index number to an option number since arrays start at 0
				
				/*
				 * This is something kind of neat that I had not thought about
				 * since String overrides the built in toString() method that 
				 * all object have printing it as though it was an object
				 * act identically to if you were to print a string
				 * Cool Stuff!
				 */
				
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.print("\n Please choose an option >>> ");
		//Flush commits the printed lines to the output
		out.flush();
	}
	
	//grab the menu's next line so that only the menu has access to system.in
	//not strictly required but allow the test to override all inputs
	public String getInput() {
		return in.nextLine();
	}
}
