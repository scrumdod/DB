/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.library;

import org.junit.Before;
import org.junit.Test;
import helpers.TestHelper;
import command.library.CmdDir;

public class CmdDirTest extends CmdTest {
	
	@Before
	public void setUp() {
		super.setUp();

		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		this.commandInvoker.addCommand(new CmdDir("dir", this.drive));
	}

	@Test
	public void cmdDir_WithoutParameter_PrintPathOfCurrentDirectory() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir");
		TestHelper.assertContains(this.rootDir.getPath(), this.testOutput);
	}
	
	@Test
	public void cmdDir_WithoutParameter_PrintFiles() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir");
		TestHelper.assertContains(this.fileInRoot1.getName(), this.testOutput);
		TestHelper.assertContains(this.fileInRoot2.getName(), this.testOutput);
	}
	
	@Test
	public void cmdDir_WithoutParameter_PrintDirectories() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir");
		TestHelper.assertContains(this.subDir1.getName(), this.testOutput);
		TestHelper.assertContains(this.subDir2.getName(), this.testOutput);
	}
	
	@Test
	public void cmdDir_WithoutParameter_PrintsFooter() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir");
		TestHelper.assertContains("2 File(s)", this.testOutput);
		TestHelper.assertContains("2 Dir(s)", this.testOutput);
	}

	@Test
	public void cmdDir_PathAsParameter_PrintGivenPath() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir c:\\subDir1");
		TestHelper.assertContains(this.subDir1.getPath(), this.testOutput);
	}

	@Test
	public void cmdDir_PathAsParameter_PrintFilesInGivenPath() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir c:\\subDir1");
		TestHelper.assertContains(this.file1InDir1.getName(), this.testOutput);
		TestHelper.assertContains(this.file2InDir1.getName(), this.testOutput);
	}

	@Test
	public void cmdDir_PathAsParameter_PrintsFooter() {
		this.drive.setCurrentDirectory(this.rootDir);
		executeCommand("dir c:\\subDir1");
		TestHelper.assertContains("2 File(s)", this.testOutput);
		TestHelper.assertContains("0 Dir(s)", this.testOutput);
	}
}
