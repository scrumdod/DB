/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package invoker;

import interfaces.IDrive;

import java.util.ArrayList;

import command.framework.TestOutput;
import filesystem.Drive;

import junit.framework.TestCase;

public class CommandInvokerTest extends TestCase {

	private CommandInvoker commandInvoker;
	private TestOutput output;
	private TestCommand testcmd;

	protected void setUp() throws Exception {
		super.setUp();
		
		IDrive drive = new Drive("C");
		this.commandInvoker = new CommandInvoker();
		this.testcmd = new TestCommand("dIR", drive);
		commandInvoker.addCommand(this.testcmd);
		
		this.output = new TestOutput();
	}
	
	public void testParseCommandName() {
		TestCase.assertEquals(commandInvoker.parseCommandName(""), "");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir"), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("DIR"), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir param1"), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir,param1, param2"), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir,"), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir   "), "dir");
		TestCase.assertEquals(commandInvoker.parseCommandName("dir o"), "dir");
	}
	
	public void testParseParameters() {
		ArrayList<String> params;
		
		params = commandInvoker.parseCommandParameters("dir");
		TestCase.assertTrue(params.isEmpty() == true);
		
		params = commandInvoker.parseCommandParameters("dir /p");
		TestCase.assertTrue(params.size() == 1);
		TestCase.assertTrue(params.get(0).compareTo("/p") == 0);
		
		params = commandInvoker.parseCommandParameters("dir /p param2");
		TestCase.assertTrue(params.size() == 2);
		TestCase.assertTrue(params.get(0).compareTo("/p") == 0);
		TestCase.assertTrue(params.get(1).compareTo("param2") == 0);
		
		params = commandInvoker.parseCommandParameters("dir    /p");
		TestCase.assertTrue(params.size() == 1);
		TestCase.assertTrue(params.get(0).compareTo("/p") == 0);
		
		params = commandInvoker.parseCommandParameters("dir  param1  param2   ");
		TestCase.assertTrue(params.size() == 2);
		TestCase.assertTrue(params.get(0).compareTo("param1") == 0);
		TestCase.assertTrue(params.get(1).compareTo("param2") == 0);

		params = commandInvoker.parseCommandParameters("d 1 2");
		TestCase.assertTrue(params.size() == 2);
		TestCase.assertTrue(params.get(0).compareTo("1") == 0);
		TestCase.assertTrue(params.get(1).compareTo("2") == 0);
	}
	
	public void testCommandExecuteSimple() {
		this.commandInvoker.executeCommand("DIR", output);
		
		TestCase.assertTrue(this.testcmd.executed == true);
	}
	
	public void testCommandExecuteWithLeadingSpace() {
		this.commandInvoker.executeCommand("   DIR", output);
		
		TestCase.assertTrue(this.testcmd.executed == true);
	}
	
	public void testCommandExecuteWithEndingSpace() {
		this.commandInvoker.executeCommand("DIR   ", output);
		
		TestCase.assertTrue(this.testcmd.executed == true);
	}
	
	public void testCommandExecuteWIthDifferentCase() {
		this.commandInvoker.executeCommand("dir", output);
		
		TestCase.assertTrue(this.testcmd.executed == true);
	}
	
	public void testCommandExecuteWithParameters()
	{
		this.commandInvoker.executeCommand("dir param1 param2", output);
		
		TestCase.assertTrue(this.testcmd.executed == true);
		TestCase.assertTrue(this.testcmd.getParams().size() == 2);
		TestCase.assertTrue(this.testcmd.numberOfParamsPassed == 2);
	}
	
	public void testCommandExecuteWithWrongParameters1() {
		this.testcmd.checkNumberOfParametersReturnValue = false;
		this.commandInvoker.executeCommand("dir param1 param2", output);
		
		TestCase.assertTrue(this.testcmd.executed == false);
		TestCase.assertTrue(this.testcmd.getParams().size() == 2);
		TestCase.assertTrue(this.testcmd.numberOfParamsPassed == 2);
		TestCase.assertTrue(this.output.toString().toLowerCase().contains("wrong") == true);
		TestCase.assertTrue(this.output.toString().toLowerCase().contains("parameter") == true);
	}
}
