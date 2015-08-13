/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package filesystem;

import junit.framework.TestCase;

public class FileTest extends TestCase {

	private File testfile;

	protected void setUp() throws Exception {
		testfile = new File("test", "test content");
	}
	
	public void testConstructor() {
		String name = "hello.txt";
		String content = "This is the content";
		
		testfile = new File(name, content);
		TestCase.assertTrue(testfile.getName().compareTo(name) == 0);
		TestCase.assertTrue(testfile.getFileContent().compareTo(content) == 0);
	}
	
	public void testForDirectory() {
		TestCase.assertTrue(testfile.isDirectory() == false);
	}

	public void testRename() {
		this.testfile.setName("NewName");
		TestCase.assertTrue(this.testfile.getName().compareTo("NewName") == 0);
	}
	
	public void testRenameWithIllegalNames() {
		String defaultName = new String("default");
		this.testfile.setName(defaultName);
		TestCase.assertTrue(this.testfile.getName().compareTo(defaultName) == 0);
		
		try {
			this.testfile.setName("Illegal\\Name");
			TestCase.fail();  // must throw an exception
		}
		catch(IllegalArgumentException e) {
			TestCase.assertTrue(this.testfile.getName().compareTo(defaultName) == 0);
		}
	}
	
	public void testFileSize() {
		//TODO
	}
}
