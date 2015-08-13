/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package interfaces;

import filesystem.Directory;
import filesystem.FileSystemItem;

public interface IDrive {
	Directory getRootDirectory();
	Directory getCurrentDirectory();
	boolean setCurrentDirectory(Directory dir);
	String getDriveName();
	String getPrompt();
	void setLabel(String label);
	String getLabel();
	FileSystemItem getItemFromPath(String givenItemPath);
	void save();
	void restore();
}