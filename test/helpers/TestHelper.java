/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package helpers;

import static org.junit.Assert.*;
import interfaces.IDrive;
import interfaces.IOutputter;
import command.framework.TestOutput;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystemItem;

public final class TestHelper {
	public static void assertOutputIsEmpty(TestOutput testOutput) {
		assertNotNull(testOutput);
		assertEquals(0, testOutput.toString().length());
	}

	public static File getFile(IDrive drive, String filePath, String fileName) {
		FileSystemItem fileSystemItem = drive.getItemFromPath(filePath + "\\" + fileName);
		assertNotNull(fileSystemItem);
		assertEquals(fileName, fileSystemItem.getName());
		assertFalse(fileSystemItem.isDirectory());
		return (File)fileSystemItem;
	}

	public static Directory getDirectory(IDrive drive, String directoryPath, String directoryName) {
		FileSystemItem fileSystemItem = drive.getItemFromPath(directoryPath);
		assertNotNull(fileSystemItem);
		assertEquals(directoryName, fileSystemItem.getName());
		assertTrue(fileSystemItem.isDirectory());
		return (Directory)fileSystemItem;
	}

	public static void assertContains(String expectedToContain, String actualShouldContain) {
		assertTrue(actualShouldContain.contains(expectedToContain));
	}

	public static void assertContains(String expectedToContain, IOutputter actualShouldContain) {
		assertContains(expectedToContain, actualShouldContain.toString());
	}

	public static void assertCurrentDirectoryIs(IDrive drive, Directory expectedDirectory) {
		assertSame(expectedDirectory, drive.getCurrentDirectory());
		assertEquals(expectedDirectory.getPath(), drive.getCurrentDirectory().getPath());
	}
}
