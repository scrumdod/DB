/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package configuration;

import filesystem.Drive;
import interfaces.IDrive;
import interfaces.IExecuteCommand;
import invoker.CommandInvoker;

import command.library.CommandFactory;

import console.Console;

/**Configures the system. Contains the method main().
 */
public class Configurator {

	/**Method main(). Called initially.
	 */
	public static void main(String[] args) {
		Configurator config = new Configurator();
		config.configurateSystem();
	}


	/**Configures the system for a console application.
	 */
	public void configurateSystem() {
		
		// Create file system with initial root directory
		// and read any persistent information.
		IDrive drive = new Drive("C");
		drive.restore();

		// Create all commands and invoker
		CommandFactory factory = new CommandFactory(drive);
		CommandInvoker commandInvoker = new CommandInvoker();
		commandInvoker.setCommands(factory.getCommandList());
		IExecuteCommand invoker = commandInvoker;
		
		// Setup console for input and output
		Console console = new Console(invoker, drive);
		
		// Start console
		console.processInput();
	}
}
