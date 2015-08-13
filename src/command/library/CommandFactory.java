/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import interfaces.IDrive;

import java.util.ArrayList;
import command.framework.Command;

/**The factory is responsible to create an object of every command supported
 * and to add it to the list of known commands.
 * New commands must be added to the list of known commands here.
 */
public class CommandFactory {
	
	private ArrayList<Command> commands;

	/**Constructor; creates all known commands and adds them to the list of supported commands. 
	 * 
	 * @param drive reference to the drive, the commands operate on.
	 */
	public CommandFactory(IDrive drive) {
		this.commands = new ArrayList<Command>();
		
		// Add commands here
		this.commands.add(new CmdDir("dir", drive));
		this.commands.add(new CmdCd("cd", drive));
		this.commands.add(new CmdCd("chdir", drive));
		this.commands.add(new CmdMkDir("mkdir", drive));
		this.commands.add(new CmdMkDir("md", drive));
		this.commands.add(new CmdMkFile("mf", drive));
		this.commands.add(new CmdMkFile("mkfile", drive));
	}
	
	/**Returns the list of known commands.
	 * Is called at configuration time to transfer the supported commands to the invoker. 
     * @return list of known commands.
	 */
	public ArrayList<Command> getCommandList() {
		return commands;
	}

}
