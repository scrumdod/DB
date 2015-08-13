/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package filesystem;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestsForMove extends FileSystemTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testFileMove() {
		ArrayList<FileSystemItem> content;
		
		// Check Preconditions
		TestCase.assertTrue(this.file1InDir1.getPath().compareTo("C:\\subDir1\\File1InDir1") == 0);
		TestCase.assertSame(this.file1InDir1.getParent(), this.subDir1);
		content = subDir2.getContent();
		TestCase.assertTrue(content.contains(this.file1InDir1) == false);
		content = subDir1.getContent();
		TestCase.assertTrue(content.contains(this.file1InDir1) == true);
		
		// Do move
		this.subDir2.add(this.file1InDir1);
		
		// Check Postconditions
		TestCase.assertTrue(this.file1InDir1.getPath().compareTo("C:\\subDir2\\File1InDir1") == 0);
		TestCase.assertSame(this.file1InDir1.getParent(), this.subDir2);
		content = subDir2.getContent();
		TestCase.assertTrue(content.contains(this.file1InDir1) == true);
		content = subDir1.getContent();
		TestCase.assertTrue(content.contains(this.file1InDir1) == false);
	}
}
