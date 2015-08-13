/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import filesystem.Directory;
import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

public class CmdCdTest extends CmdTest {

	@Before
	public void setUp() {
		super.setUp();
		
		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		this.commandInvoker.addCommand(new CmdCd("cd", this.drive));
	}

	@Test
	public void cmdCd_ChangeToSubdirectory_ChangesDirectory() {
		String dirName = this.subDir1.getPath();
		executeCommand("cd " + dirName);
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir1);
	}
	
	@Test
	public void cmdCd_ChangeToSubDirectoryWithEndingBacklash_ChangesDirectory() {
		String dirName = this.subDir1.getPath();
		executeCommand("cd " + dirName + "\\");
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir1);
	}
	
	@Test
	public void cmdCd_WithBacklash_ChangesToRoot() {
		// given
		Directory subSubDir1 = new Directory("subSubDir1");
		this.subDir1.add(subSubDir1);
		this.drive.setCurrentDirectory(subSubDir1);
		
		// when
		executeCommand("cd \\");
		
		// then
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.rootDir);
	}
	
	@Test
	public void cmdCd_WithPointPoint_ChangesToParent() {
		this.drive.setCurrentDirectory(this.subDir1);
		executeCommand("cd ..");
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.rootDir);
	}
		
	@Test
	public void cmdCd_WithPointPointInRootDir_RemainsInRootDir() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("cd ..");
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.rootDir);
	}
	
	@Test
	public void cmdCd_WithPoint_RemainsInCurrentDirectory() {
		this.drive.setCurrentDirectory(this.subDir1);
		executeCommand("cd .");
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir1);
	}		

	@Test
	public void cmdCd_WithPointInRootDir_RemainsInCurrentDirectory() {
		executeCommand("cd .");
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.rootDir);
	}		

	@Test
	public void cmdCd_WithoutParameter_PrintsCurrentDirectory() {
		this.drive.setCurrentDirectory(this.subDir1);
		executeCommand("cd");
		TestHelper.assertContains(this.subDir1.getPath(), this.testOutput.toString());
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir1);
	}
	
	@Test
	public void cmdCd_WithInvalidAbsolutePath_RemainsInCurrentDirectory() {
		this.drive.setCurrentDirectory(this.subDir2);
		executeCommand("cd c:\\gaga\\gugus");
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir2);
		TestHelper.assertContains("system cannot find the path specified", this.testOutput.toString());
	}
	
	@Test
	public void cmdCd_WithFileAsPath_RemainsInCurrentDirectory() {
		this.drive.setCurrentDirectory(this.subDir2);
		executeCommand("cd " + this.file1InDir1.getPath());
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir2);
		TestHelper.assertContains("system cannot find the path specified", this.testOutput.toString());
	}
	
	@Test
	public void cmdCd_WithRelativePath_ChangesDirectory() {
		executeCommand("cd " + this.subDir1.getName());
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, this.subDir1);
	}
	
	@Test
	public void cmdCd_WithSingleLetterDirectory_ChangesDirectory() {
		// given
		Directory directoryWithSingleLetter = new Directory("a");
		this.rootDir.add(directoryWithSingleLetter);
		this.drive.setCurrentDirectory(this.rootDir);
		
		// when
		executeCommand("cd " + directoryWithSingleLetter.getName());
		
		// then
		TestHelper.assertOutputIsEmpty(this.testOutput);
		TestHelper.assertCurrentDirectoryIs(this.drive, directoryWithSingleLetter);
	}
}
