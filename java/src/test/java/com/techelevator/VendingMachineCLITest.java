package com.techelevator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;

public class VendingMachineCLITest {
	//We are store the default sys.in and sys.out
	 private final InputStream systemIn = System.in;
	 private final PrintStream systemOut = System.out;
	 //Declaring the stream of inputs and outputs so we can have the test hook in
	 private ByteArrayInputStream testIn;
	 private ByteArrayOutputStream testOut;
	 
	 @Before
	 public void setUpOutput() {
		 //Here we are replacing the regular system.out with one the test can read so we can verify the output
		 testOut = new ByteArrayOutputStream();
		 System.setOut(new PrintStream(testOut));
	 }
	 
	 private void provideInput(String data) {
		// here we are setting up our replacement input stream so we can instead of typing
		// just pass in a string of inputs
		 testIn = new ByteArrayInputStream(data.getBytes());
		 System.setIn(testIn);
	 }
	  
	 private String getOutput() {
		 //reads the output stream and return it as a string
		 return testOut.toString();
	 }
	 @After
	 public void restoreSystemInputOutput() {
		 //resets the system in and out to default
		 System.setIn(systemIn);
		 System.setOut(systemOut);
	 }
	 @Test
	 public void test_Exit_Menu() {
		 //Tells the test to effectively type 3 as an an input
		 provideInput("3");
		 //make new vendingMachineCli with a menu for it using the overrided System
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));		
		 //run the menu
		 vMenu.run();
		 //Make sure the output for exiting the menu is correct!
		 Assert.assertTrue(getOutput().contains("Thank you for choosing Vend-O-Matic have a great day!"));		 
	 }
	 @Test
	 public void test_Display_Menu() {
		 //When we need to provide multiple inputs us \n to create a new line between
		 //as the input stream is read in a way similar to file, by that I mean
		 //it is read line by line.
		 provideInput("1\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains("A1 Potato Crisps $3.05 stock: 5"));	
		 Assert.assertTrue(getOutput().contains("Thank you for choosing Vend-O-Matic have a great day!"));	
	 } 
	 @Test 
	 public void test_Purchase_Menu() {
		 provideInput("2\n3\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains("Feed Money"));		 
	 }
	 @Test 
	 public void test_Feed_Money() {
		 provideInput("2\n1\n5\n3\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains("Current Money Provided: $5.00"));		 
	 }
	 @Test 
	 public void test_Select_Product() {
		 provideInput("2\n2\nA1\n3\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains("Insufficiant Funds you need $3.05 and only have $0.00"));	
		 Assert.assertTrue(getOutput().contains("Which product slot do you want to buy? >>>"));		 
	 }
	 @Test 
	 public void test_Finalize_Money() {
		 provideInput("2\n3\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains("Your change is"));		 
	 }
	 @Test
	 public void test_Sales_Report() {
		 provideInput("4\n3");
		 VendingMachineCLI vMenu = new VendingMachineCLI(new Menu(System.in, System.out));			 
		 vMenu.run();
		 Assert.assertTrue(getOutput().contains(" has been created."));		 
	 }
}
