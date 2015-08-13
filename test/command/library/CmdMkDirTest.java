/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import helpers.Path;
import helpers.TestHelper;

import org.junit.Before;
import org.junit.Test;

import filesystem.Directory;

public class CmdMkDirTest extends CmdTest {

	@Before
	public void setUp() {
		super.setUp();

		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		this.commandInvoker.addCommand(new CmdMkDir("mkdir", this.drive));
	}

	@Test
	public void cmdMkDir_CreateNewDirectory_NewDirectoryIsAdded() {
		String testDirName = "test1";
		executeCommand("mkdir " + testDirName);
		Directory testDirectory = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName), testDirName);
		assertSame(this.drive.getRootDirectory(), testDirectory.getParent());
		assertEquals(numbersOfDirectoriesBeforeTest+1, this.drive.getRootDirectory().getNumberOfDirectories());
		TestHelper.assertOutputIsEmpty(this.testOutput);
	}
	
	@Test
	public void cmdMkDir_CreateNewDirectory_NewDirectoryIsAddedToCorrectLocation() {
		String testDirName = "test1";
		executeCommand("mkdir " + testDirName);
		Directory testDirectory = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName), testDirName);
		assertSame(this.drive.getRootDirectory(), testDirectory.getParent());
	}
	
	@Test
	public void cmdMkDir_SingleLetterDirectory_NewDirectoryIsAdded() {
		String testDirName = "a";
		executeCommand("mkdir " + testDirName);
		Directory testDirectory = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName), testDirName);
		assertSame(this.drive.getRootDirectory(), testDirectory.getParent());
		assertEquals(this.numbersOfDirectoriesBeforeTest+1, this.drive.getRootDirectory().getNumberOfDirectories());
		TestHelper.assertOutputIsEmpty(testOutput);
	}
	
	@Test
	public void cmdMkDir_NoParameters_ErrorMessagePrinted() {
		executeCommand("mkdir");
		assertEquals(this.numbersOfDirectoriesBeforeTest, this.drive.getRootDirectory().getNumberOfDirectories());
		TestHelper.assertContains("syntax of the command is incorrect", this.testOutput);
	}
	
	@Test
	public void cmdMkDir_SeveralParameters_SeveralNewDirectoriesCreated() {
		// given
		String testDirName1 = "test1";
		String testDirName2 = "test2";
		String testDirName3 = "test3";

		// when
		executeCommand("mkdir " + testDirName1 + " " + testDirName2 + " " + testDirName3);
		
		// then
		Directory directory1 = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName1), testDirName1);
		Directory directory2 = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName2), testDirName2);
		Directory directory3 = TestHelper.getDirectory(drive, Path.Combine(this.drive.getDriveName(), testDirName3), testDirName3);
		assertSame(directory1.getParent(), this.drive.getRootDirectory());
		assertSame(directory2.getParent(), this.drive.getRootDirectory());
		assertSame(directory3.getParent(), this.drive.getRootDirectory());
		assertEquals(this.numbersOfDirectoriesBeforeTest+3, this.drive.getRootDirectory().getNumberOfDirectories());
		TestHelper.assertOutputIsEmpty(testOutput);
	}
}
