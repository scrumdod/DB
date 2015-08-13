/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package invoker;

import interfaces.IDrive;
import interfaces.IOutputter;

import java.util.ArrayList;

import command.framework.Command;

public class TestCommand extends Command {

	public boolean executed = false;
	public int numberOfParamsPassed = -1;
	public boolean checkNumberOfParametersReturnValue = true;
	public boolean checkParameterValuesReturnValue = true;

	protected TestCommand(String cmdName, IDrive drive) {
		super(cmdName, drive);
	}
	
	public ArrayList<String> getParams() {
		return this.getParameters();
	}
	
	@Override
	protected boolean checkNumberOfParameters(int number) {
		this.numberOfParamsPassed  = number;
		return this.checkNumberOfParametersReturnValue;
	}
	
	@Override
	protected boolean checkParameterValues(IOutputter outputter) {
		return this.checkParameterValuesReturnValue;
	}

	@Override
	public void execute(IOutputter outputter) {
		this.executed  = true;
	}
}
