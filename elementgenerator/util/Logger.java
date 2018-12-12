/**
 * 
 */
package elementgenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.JTextArea;

/**
 * A class to log everything that happens in the generator
 * @author Jonah Pierce (Fellow Hashbrown)
 *
 */
public class Logger {
	
	/**
	 * An encapsulated class to ease the output log file and its layout
	 * @author Jonah Pierce (Fellow Hashbrown)
	 *
	 */
	private class LogMessage {
		
		// Instance Fields
		private String time;
		private String origin;
		private String message;
		
		/**
		 * Creates a new LogMessage object with specified origin and message variables
		 * @param origin the origin of the message
		 * @param message the message itself
		 */
		public LogMessage(String time, String origin, String message) {
			this.time = time;
			this.origin = origin;
			this.message = message;
		}
		
		/**
		 * Returns the String of this message and keeps a specific layout
		 * @param longestOriginLength the longest origin name's length to provide a fixed layout
		 * @return String
		 */
		public String toString(int longestOriginLength) {
			for(int i = this.origin.length(); i < longestOriginLength; i++)
				this.origin += " ";
			return this.time + " " + this.origin + " > " + message;
		}
		
	}
	
	// Static Fields
	public static final int ERROR = 0;
	public static final int ATTEMPT = 1;
	public static final int SUCCESS = 2;
	public static final int FAILED = 3;
	public static final int WARNING = 4;
	public static final int INFO = 5;
	
	// Instance Fields
	private boolean open;
	private boolean toFile;
	
	private LogMessage[] messages;
	private Calendar date;
	
	private JTextArea output;
	
	private String longestOriginName;
	
	/**
	 * Creates a new Logger object
	 * @param open whether or not the Logger is open
	 * @param toFile whether or not to save the messages to a file (default is {@code false})
	 */
	public Logger(boolean open, boolean toFile) {
		this.open = open;
		this.toFile = toFile;
		this.date = Calendar.getInstance();
		this.messages = new LogMessage[0];
		this.longestOriginName = "";
	}
	
	/**
	 * Creates a new Logger object
	 * @param open whether or not the Logger is open (default is {@code false})
	 */
	public Logger(boolean open) {
		this(open, false);
	}
	
	/**
	 * Creates a new Logger object
	 */
	public Logger() {
		this(false);
	}
	
	/**
	 * Sets whether or not the Logger will save to a file
	 * @param toFile whether or not the Logger will save to a file
	 */
	public void setFileLog(boolean toFile) {
		this.toFile = toFile;
		
		// Add a log message if toFile is deactivated or activated
		log("Logger", "********** THE LOGGER HAS BEEN " + ((toFile)? "ACTIVATED ": "DEACTIVATED ") + "**********");
	}
	
	/**
	 * Sets an output JTextArea to show log information there
	 * @param output the JTextArea object to show information from
	 */
	public void setOutput(JTextArea output) {
		this.output = output;
	}
	
	/**
	 * Displays the message given and places the text in the JTextArea (only if open)
	 * @param message the message to add to the output
	 * @param messageType the type of message ({@code ERROR, ATTEMPT, SUCCESS, FAILED, INFO})
	 */
	public void log(String origin, String message, int messageType) {
		
		if(origin.length() > this.longestOriginName.length())
			this.longestOriginName = origin;
		
		// Get current time
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DATE);
		int year = date.get(Calendar.YEAR);
		int hour = date.get(Calendar.HOUR);
		int minute = date.get(Calendar.MINUTE);
		int second = date.get(Calendar.SECOND);
		String dateString = String.format(
				"[%s/%s/%s (%s:%s:%s)]",
				month, day, year, 
				hour, (minute < 10)? "0" + minute: minute, (second < 10)? "0" + second: second
		);
		
		// Create full display message
		LogMessage logMessage;
		switch(messageType) {
			case ERROR:
				logMessage = new LogMessage(dateString + " [ERROR]   ", origin, message);
				break;
			case ATTEMPT:
				logMessage = new LogMessage(dateString + " [ATTEMPT] ", origin, message);
				break;
			case SUCCESS:
				logMessage = new LogMessage(dateString + " [SUCCESS] ", origin, message);
				break;
			case FAILED:
				logMessage = new LogMessage(dateString + " [FAILED]  ", origin, message);
				break;
			case WARNING:
				logMessage = new LogMessage(dateString + " [WARNING] ", origin, message);
				break;
			default:
				logMessage = new LogMessage(dateString + " [INFO]    ", origin, message);
				break;
		}
		
		// Only do actions if logger is open
		if(open) {
			
			// Print message
			System.out.println(message);
			// Add message to output component if not null
			if(output != null) {
				output.append(message + "\n");
				output.revalidate();
				output.repaint();
			}
			
			// Add to messages array if toFile
			LogMessage[] newArray = new LogMessage[messages.length + 1];
			for(int i = 0; i < messages.length; i++)
				newArray[i] = messages[i];
			newArray[newArray.length - 1] = logMessage;
			messages = newArray.clone();
			
		}
	}
	
	/**
	 * Displays the message given (if the logger is open)
	 * @param message the message to display
	 */
	public void log(String origin, String message) {
		log(origin, message, INFO);
	}
	
	/**
	 * Closes the logger and saves the messages to a file (if the logger allows it)
	 */
	public void close() {
		
		// Only do actions if logger is open
		if(open) {
			
			// Only create log directory and save file if "toFile" is true
			if(toFile) {
				
				// Create log directory
				File logDirectory = new File(System.getProperty("user.dir") + "/Logs/");
				logDirectory.mkdir();
				
				// Try to save messages to log file
				try {
					
					// Create log file
					int month = date.get(Calendar.MONTH);
					int day = date.get(Calendar.DATE);
					int year = date.get(Calendar.YEAR);
					int hour = date.get(Calendar.HOUR);
					int minute = date.get(Calendar.MINUTE);
					int second = date.get(Calendar.SECOND);
					File logFile = new File(System.getProperty("user.dir") + "/logs/" + String.format(
							"%s-%s-%s (%s-%s-%s) log.elelog",
							month, day, year,
							hour, (minute < 10)? "0" + minute: minute, (second < 10)? "0" + second: second
					));
					// Open BufferedWriter
					BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
					
					// Write lines to writer
					for(LogMessage line: messages)
						writer.write(line.toString(longestOriginName.length()) + "\n");
					
					// Close writer
					writer.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}

}
