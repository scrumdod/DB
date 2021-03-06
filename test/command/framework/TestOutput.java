/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package command.framework;

import interfaces.IOutputter;


/**Implements Outputter interface for testing purpose.<br>
 * Offers:
 * <li> buffering output for later investigation
 * <li> simulating reading characters from console
 */
public class TestOutput implements IOutputter {

	private StringBuffer output;
	private char characterThatIsRead = 0;
	
	public TestOutput() {
		this.output = new StringBuffer();
	}

	public void newline() {
		output.append("\n");
	}

	public void print(String text) {
		output.append(text);
	}

	public void printLn(String line) {
		output.append(line);
		newline();
	}
	
	/**Empties the buffered output. Important to call
	 * before starting a new test.
	 */
	public void empty() {
		this.output = new StringBuffer();
	}

	/** Returns the buffered output
	 */
	@Override
	public String toString() {
		return output.toString();
	}

	/**Checks length of the buffered output
	 * @return true if no character is stored in buffered output,
	 *         else otherwise
	 */
	public boolean isEmpty() {
		if(this.output.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**Simulates reading a character from console.
	 * Returns 0 if setCharacterThatIsRead() is not called previously.
	 * <br>
	 * Usage in Unit Tests:<br>
	 * &nbsp;&nbsp;&nbsp; TestOutput testOutput.setCharacterThatIsRead('Y');<br>
	 * &nbsp;&nbsp;&nbsp; this.commandInvoker.executeCommand("copy C:\\WinWord.exe C:\\ProgramFiles\\", testOutput);<br>
	 * &nbsp;&nbsp;&nbsp; TestCase.assertTrue(testOutput.toString().toLowerCase().contains("overwrite") == true);<br>
	 * &nbsp;&nbsp;&nbsp; TestCase.assertTrue(testOutput.characterWasRead() == true);<br>
	 */
	public char readSingleCharacter() {
		return this.characterThatIsRead;
	}
	
	/**Sets the character that is read when calling readSingleCharacter().
	 * @param character Character that is read.
	 */
	public void setCharacterThatIsRead(char character) {
		this.characterThatIsRead = character;
	}

}
