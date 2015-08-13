/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package filesystem;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestsForCmdDir extends FileSystemTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testContent() {
		ArrayList<FileSystemItem> content;
		
		content = this.rootDir.getContent();
		TestCase.assertTrue(content.isEmpty() == false);
		TestCase.assertTrue(content.size() == 4);  // subDir1, subDir2, file1InRoot, file2InRoot
		TestCase.assertTrue(content.contains(this.subDir1) == true);
		TestCase.assertTrue(content.contains(this.subDir2) == true);
		TestCase.assertTrue(content.contains(this.fileInRoot1) == true);
		TestCase.assertTrue(content.contains(this.fileInRoot2) == true);
		TestCase.assertTrue(content.contains(this.file1InDir1) == false);

		content = this.subDir1.getContent();
		TestCase.assertTrue(content.isEmpty() == false);
		TestCase.assertTrue(content.size() == 2);  // file1InDir1, file2InDir1
		TestCase.assertTrue(content.contains(this.file1InDir1) == true);
		TestCase.assertTrue(content.contains(this.file2InDir1) == true);
		TestCase.assertTrue(content.contains(this.fileInRoot2) == false);
		
		content = this.subDir2.getContent();
		TestCase.assertTrue(content.isEmpty() == true);
	}
}
