/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import interfaces.IDrive;
import interfaces.IOutputter;
import command.framework.Command;

import filesystem.File;

/** The implementation is not complete:
 * 2 Unit Tests for this class fail. See Task Card #002.
 */
class CmdMkFile extends Command {

	public CmdMkFile(String cmdName, IDrive drive) {
		super(cmdName, drive);
	}

	@Override
	public void execute(IOutputter outputter) {
		String fileName = this.getParameters().get(0);
		String fileContent = this.getParameters().get(1);
		
		File newFile = new File(fileName, fileContent);
		this.getDrive().getCurrentDirectory().add(newFile);
	}
}
