/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import command.framework.TestOutput;

import interfaces.IDrive;
import invoker.CommandInvoker;
import filesystem.Directory;
import filesystem.File;
import filesystem.Drive;

/**Sets up a directory structure as follows:
 * C:\
 * |---FileInRoot1
 * |---FileInRoot2
 * |---subDir1
 * |   |---File1InDir1
 * |   |---File2InDir1
 * |---subdir2
 * 
 */
public abstract class CmdTest {

	protected CommandInvoker commandInvoker;
	protected TestOutput testOutput;
	protected IDrive drive;
	protected Directory rootDir;
	protected File fileInRoot1;
	protected File fileInRoot2;
	protected Directory subDir1;
	protected File file1InDir1;
	protected File file2InDir1;
	protected Directory subDir2;
	protected int numbersOfDirectoriesBeforeTest;
	protected int numbersOfFilesBeforeTest;

	public CmdTest() {
		super();
	}

	protected void setUp() {
		this.drive = new Drive("C");
		this.rootDir = this.drive.getRootDirectory();
		this.fileInRoot1 = new File("FileInRoot1", "an entry");
		this.rootDir.add(this.fileInRoot1);
		this.fileInRoot2 = new File("FileInRoot2", "a long entry in a file");
		this.rootDir.add(this.fileInRoot2);
		
		this.subDir1 = new Directory("subDir1");
		this.rootDir.add(subDir1);
		this.file1InDir1 = new File("File1InDir1", "");
		this.subDir1.add(this.file1InDir1);
		this.file2InDir1 = new File("File2InDir1", "");
		this.subDir1.add(this.file2InDir1);
		
		this.subDir2 = new Directory("subDir2");
		this.rootDir.add(subDir2);
		
		this.commandInvoker = new CommandInvoker();
		this.testOutput = new TestOutput();
		
		this.numbersOfDirectoriesBeforeTest = this.drive.getRootDirectory().getNumberOfDirectories();
		this.numbersOfFilesBeforeTest = this.drive.getRootDirectory().getNumberOfFiles();
	}
	
	protected void executeCommand(String commandLine) {
		this.commandInvoker.executeCommand(commandLine, this.testOutput);
	}
}