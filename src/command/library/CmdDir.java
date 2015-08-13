/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import interfaces.IDrive;
import interfaces.IOutputter;

import java.util.ArrayList;
import java.util.Iterator;

import command.framework.Command;

import filesystem.Directory;
import filesystem.FileSystemItem;

class CmdDir extends Command {

	private Directory dirToPrint;

	public CmdDir(String name, IDrive drive) {
		super(name, drive);
	}
	
	@Override
	public boolean checkNumberOfParameters(int number) {
		if(number == 0 || number == 1) 
			return true;
		else
			return false;
	}
	
	@Override
	public boolean checkParameterValues(IOutputter outputter) {
		if(this.getParameters().size() > 0) {
			String dirPath = this.getParameters().get(0);
			FileSystemItem fs = this.getDrive().getItemFromPath(dirPath);
			if(fs == null) {
				outputter.printLn("File Not Found");
				return false;
			}
			if(fs.isDirectory() == false) {
				this.dirToPrint = fs.getParent();
			}
			else {
				this.dirToPrint = (Directory)fs;
			}
		}
		else {
			this.dirToPrint = this.getDrive().getCurrentDirectory();
		}
		
		return true;
	}
	
	@Override
	public void execute(IOutputter outputter) {
		ArrayList<FileSystemItem> content = this.dirToPrint.getContent();
		
		outputter.printLn("Directory of " + this.dirToPrint.getPath());
		outputter.newline();
		
		Iterator<FileSystemItem> it = content.iterator();
		FileSystemItem item;
		while(it.hasNext()) {
			item = it.next();
			if(item.isDirectory() == true) {
				outputter.print("<DIR>");
			}
			else {
				outputter.print("" + item.getSize());
			}
			outputter.print("\t" + item.getName());
			outputter.newline();
		}
		outputter.printLn("\t" + this.dirToPrint.getNumberOfFiles() + " File(s)");
		outputter.printLn("\t" + this.dirToPrint.getNumberOfDirectories() + " Dir(s)");
	}

}
