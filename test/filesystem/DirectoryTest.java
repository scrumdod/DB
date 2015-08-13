/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package filesystem;

import java.util.ArrayList;

import junit.framework.TestCase;

public class DirectoryTest extends TestCase {

	private Directory rootDir;
	private Directory subDir1;
	private Directory subDir2;

	protected void setUp() throws Exception {
		super.setUp();
		
		this.rootDir = new Directory("root");
		this.subDir1 = new Directory("subDir1");
		this.rootDir.add(subDir1);
		this.subDir2 = new Directory("subDir2");
		this.rootDir.add(subDir2);
	}
	
	public void testConstructor() {
		String name = "root";
		Directory testdir = new Directory(name);
		TestCase.assertTrue(testdir.getName().compareTo(name) == 0);
		TestCase.assertTrue(testdir.getContent().isEmpty() == true);
		TestCase.assertTrue(testdir.getParent() == null);
	}
	
	public void testSubDirectory() {
		ArrayList<FileSystemItem> content = this.rootDir.getContent();
		TestCase.assertTrue(content != null);
		TestCase.assertTrue(content.size() == 2);
		
		FileSystemItem item = content.get(0);
		TestCase.assertTrue(item.isDirectory() == true);
		TestCase.assertTrue(item.getName().compareTo(subDir1.getName()) == 0);
		
		FileSystemItem parent = item.getParent();
		TestCase.assertTrue(parent.isDirectory() == true);
		TestCase.assertEquals(parent, this.rootDir);
		TestCase.assertTrue(parent.getParent() == null);
		
		String path = item.getPath();
		TestCase.assertTrue(path.compareTo(rootDir.getName() + "\\" + this.subDir1.getName()) == 0);
		
	}
	
	public void testContainingFiles() {
		
	}
	
	public void testForDirectory() {
		TestCase.assertTrue(this.rootDir.isDirectory() == true);
		TestCase.assertTrue(this.subDir2.isDirectory() == true);
	}
	
	public void testRename() {
		this.subDir1.setName("NewName");
		TestCase.assertTrue(this.subDir1.getName().compareTo("NewName") == 0);
	}
	
	public void testNumberOfFilesAndDirectories() {
		//TODO
	}
}
