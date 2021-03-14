package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMaker {

	private File logFile = new File("log.txt");


	public LogMaker() {
		//if log.txt does not exist make it!
		if(!logFile.exists()) {
			try {				
				logFile.createNewFile();
			}
			catch(IOException ie) {
				System.out.println("Couldn't create a log File");
			}
		}
	}
 
	public void LogVend(String slot, String name, BigDecimal prevChange, BigDecimal currentChange) {
		//Create a logAction in the form of "Name Slot $prevChange $currentChange"
		String logAction = String.format(name + " " + slot + " $%,.2f $%,.2f", prevChange, currentChange);
		//send action to log to print to log.txt	//%.2f returns value with 2 decimal places, f: floating number
		Log(logAction);
		
	}

	public void LogFeed(BigDecimal prevChange, BigDecimal currentChange) {
		//Create a logAction in the form of "FEED MONEY: $prevChange $currentChange
		String logAction = String.format("FEED MONEY: $%,.2f $%,.2f", prevChange, currentChange);
		//send action to log to print to log.txt
		Log(logAction);
		
	}
	
	public void LogChange(BigDecimal prevChange, BigDecimal currentChange) {
		//Create a logAction in the form of "GIVE CHANGE: $prevChange $currentChange
		String logAction = String.format("GIVE CHANGE: $%,.2f $%,.2f", prevChange, currentChange);
		//send action to log to print to log.txt
		Log(logAction);
	}
	
	private void Log(String action) {
		//Create a writer
		try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(this.logFile,true))){
			//print the action with a timestamp
			logWriter.println(getFormatedDate() + " " + action);		
		}
		catch(FileNotFoundException ex) {
			//call this if someone deleted log.txt while the program was running!
			System.out.println(logFile.getAbsolutePath() + " not found");
		}
	}

	private String getFormatedDate() {
		//This is far less cursed then what we were using
		
		//formatter basically creates the pattern we want the date displayed in
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//Get when it is right now and format it for output ez
		return LocalDateTime.now().format(formatter);
 
	}

}



