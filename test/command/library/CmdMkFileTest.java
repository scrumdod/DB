/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import helpers.TestHelper;
import filesystem.File;

public class CmdMkFileTest extends CmdTest {

	@Before
	public void setUp() {
		super.setUp();
		
		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		this.commandInvoker.addCommand(new CmdMkFile("mkfile", this.drive));
	}

	@Test
	public void cmdMkFile_CreatesNewFile() {
		// given
		String newFileName = "testFile";
		
		// when
		this.commandInvoker.executeCommand("mkfile " + newFileName, this.testOutput);
		
		// then
		assertEquals(this.numbersOfFilesBeforeTest+1, this.drive.getCurrentDirectory().getNumberOfFiles());
		TestHelper.assertOutputIsEmpty(this.testOutput);
		File createdFile = TestHelper.getFile(this.drive, this.drive.getCurrentDirectory().getPath(), newFileName);
		assertNotNull(createdFile);
	}

	@Test
	public void cmdMkFile_WithoutContent_CreatesEmptyFile() {
		// given
		String newFileName = "testFile";
		
		// when
		this.commandInvoker.executeCommand("mkfile " + newFileName, this.testOutput);
		
		// then
		File createdFile = TestHelper.getFile(this.drive, this.drive.getCurrentDirectory().getPath(), newFileName);
		assertEquals("", createdFile.getFileContent());
	}

	@Test
	public void cmdMkFile_WithContent_CreatesFileWithContent() {
		// given
		String newFileName = "testFile";
		String newFileContent = "ThisIsTheContent";
		
		// when
		this.commandInvoker.executeCommand("mkfile " + newFileName + " " + newFileContent, this.testOutput);
		
		// then
		assertEquals(this.numbersOfFilesBeforeTest+1, this.drive.getCurrentDirectory().getNumberOfFiles());
		TestHelper.assertOutputIsEmpty(this.testOutput);
		File createdFile = TestHelper.getFile(this.drive, this.drive.getCurrentDirectory().getPath(), newFileName);
		assertEquals(newFileContent, createdFile.getFileContent());
	}

	@Test
	public void cmdMkFile_NoParameters_ReportsError() {
		this.commandInvoker.executeCommand("mkfile", this.testOutput);
		assertEquals(this.numbersOfFilesBeforeTest, this.drive.getCurrentDirectory().getNumberOfFiles());
		TestHelper.assertContains("syntax of the command is incorrect", this.testOutput.toString());
	}
}
