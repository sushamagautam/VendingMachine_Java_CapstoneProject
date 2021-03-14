package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.*;

public class LogMakerTest {

	LogMaker loggy;
	

	@Test
	public void Test_Log_Change() {
		loggy = new LogMaker();
		loggy.LogChange(new BigDecimal(1.50), new BigDecimal(1.45));
		Assert.assertTrue(compareLog("GIVE CHANGE: $1.50 $1.45"));
	}
	@Test
	public void Test_Log_Vend() {
		loggy = new LogMaker();
		loggy.LogVend("FF","Testy-O's",new BigDecimal(1.50), new BigDecimal(1.45));
		Assert.assertTrue(compareLog("Testy-O's FF $1.50 $1.45"));
	}
	@Test
	public void Test_Log_Feed() {
		loggy = new LogMaker();
		loggy.LogFeed(new BigDecimal(1.50), new BigDecimal(1.45));
		Assert.assertTrue(compareLog("FEED MONEY: $1.50 $1.45"));
	}
	
	//This is the only way I could thing of to double check the log we are writing is correct
	public boolean compareLog(String expectedLog) {
		try(Scanner logScanner = new Scanner(new File("log.txt"))){
			String currentLine = "";
			while(logScanner.hasNextLine()){
				currentLine = logScanner.nextLine();		
			};
			return currentLine.contains(expectedLog);
		}catch(FileNotFoundException fnfEx) {
			Assert.assertTrue(false);
		}
		return false;
	}
}
