package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import com.techelevator.view.Menu;

public class VendingMachineCLI {
	//Create all the main menu Options these will be used to create the menu
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "&Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT,MAIN_MENU_OPTION_SALES_REPORT};
	//Create all the products menu Options these will be used to create the menu
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	
	 
	private VendingMachine vendMachine;
	
	private Menu menu;
	


	public VendingMachineCLI(Menu menu) {
		//initialize Vending Machine'
		vendMachine = new VendingMachine();
		this.menu = menu;
	} 
	
	public void run() {
		while (true) {
			//GetChoiceFromOptions() asks the user for what menu option they want to select
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				DisplayItems();			
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//Opens the Purchase Menu
				PurchaseMenu();	
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				//Leaves the program
				System.out.println("\nThank you for choosing Vend-O-Matic have a great day!");
				break;
			}
			else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				//Leaves the program
				System.out.println(vendMachine.printSalesReport());
				
			}
		
		}
	} 
   
	public void DisplayItems() {
		
		//Get a map of items from the machine
		Map<String, VendableItem> stock = vendMachine.getCurrentStock();
		//Get all of the keys from the map so we can sort them
		List<String> itemSlots = new ArrayList<>(stock.keySet());
		//sort the list of keys
		Collections.sort(itemSlots);
		//loop though the sorted key
		for(String slot : itemSlots) {
			//get the item that the current key represents
			VendableItem item = stock.get(slot);
			//Print item in the form of "Slot ItemName $Amount stock: Amount"
			System.out.printf(slot + " " + item.getName() + " $%,.2f stock: " + ((item.getStock() > 0)?item.getStock():"SOLD OUT!")+"\n", item.getCost());		
		}																			// ? is short for if else statement, called as a conditional operator
																					// if item.getStock()>0 is true, item.getStock() is called
	}																				// if it's false, "SOLD OUT!" +....... is called
		
	public void PurchaseMenu() {
		
		//provides space between this menu and last
		System.out.println();
		while (true) {
			System.out.printf("Current Money Provided: $%,.2f\n", vendMachine.getCurrencyHeld());
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			
			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				// init feedAmount as null so we can use that as a loop condition
				
				BigDecimal feedAmount = null;
				
				do {								
					System.out.print("\nHow much money do you want to put in? >>> ");				
					try {
						//get the amount added by the user
						feedAmount = new BigDecimal(menu.getInput());
						//Feed the Machine!!! if the feed succeeds returns true
						if(vendMachine.FeedMoney(feedAmount.stripTrailingZeros())) {
							System.out.printf("\n$%,.2f added to the machine.\n",feedAmount);	
						}else {
							System.out.println("\nPlease input a valid, positive value.");	
							feedAmount = null;
						}						
					}	 
					catch(NumberFormatException ne) {	
						//If the user inputs an invalid number feedAmount remains null
						//Ask them again
						System.out.println("Please input a valid number");
					}
				}while(feedAmount == null); //While feedAmount remains null keep asking for input
			
			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				DisplayItems();
				//Ask user what they want to buy
				System.out.print("Which product slot do you want to buy? >>> ");	
				String selection = menu.getInput();
				//vend the selected item and display the vend message (or error)
				String message = vendMachine.Vend(selection.toUpperCase());
				//print message with \n before and after to create newlines before and after
				System.out.println("\n"+message+"\n");

				
			}
			else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				//Close out the Vending Machine sale and print the change message
				System.out.println("\n"+vendMachine.FinalizeTransaction());
				System.out.println("Thank you!");
				break;
			}						
		}						
	}	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
