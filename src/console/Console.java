/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package console;

import java.io.IOException;
import interfaces.IDrive;
import interfaces.IExecuteCommand;
import interfaces.IOutputter;

/**Implements a console. The user is able to input command strings
 * and receives the output directly on that console.
 * Configures the Invoker, the Commands and the Filesystem.
 */
public class Console {
	private IExecuteCommand invoker;
	private IDrive drive;
	private IOutputter outputter;

	/**Constructor.
	 * 
	 * @param invoker reference to the invoker used.
	 * @param drive reference to the drive from which the prompt is taken.
	 */
	public Console(IExecuteCommand invoker, IDrive drive) {
		this.invoker = invoker;
		this.drive = drive;
		this.outputter = new ConsoleOutputter();
	}

	/**Processes input from the console and invokes the invoker until 'exit' is typed.
	 */
	public void processInput() {
		String line = new String();
		this.outputter.printLn("MAS Study HSR [Version 20.4.2007]");
		this.outputter.printLn("(C) Copyright 2006-2007 Rainer Grau and Daniel Tobler.");

		while(line.trim().compareToIgnoreCase("exit") != 0) {
			int readChar = 0;
			StringBuilder input = new StringBuilder();

			this.outputter.newline();
			this.outputter.print(this.drive.getPrompt());
			try {
				while (readChar != '\n') {
					readChar = System.in.read();
					input.append((char)readChar);
				}
				line = input.toString();
			} catch (IOException e) {
				// do nothing by intention
			}
			
			invoker.executeCommand(line, this.outputter);
		}
		this.outputter.printLn("\nGoodbye!");
		this.drive.save();
	}
}
