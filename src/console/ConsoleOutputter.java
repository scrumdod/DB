/*
 * Course Agile Software Development
 * 
 * (c) 2007 by Zuehlke Engineering AG, Rainer Grau and Daniel Tobler
 */ 

package console;

import interfaces.IOutputter;

import java.io.IOException;


/**Implements the IOutputter interface that all text is sent to
 * the console (System.out).
 */
public class ConsoleOutputter implements IOutputter {

	public void newline() {
		System.out.println("");
	}

	public void print(String text) {
		System.out.print(text);
	}

	public void printLn(String line) {
		System.out.println(line);
	}

	public char readSingleCharacter() {
		int in = 0;
		int readChar = 0;

		try {
			while (in != '\n') {
				if(in != '\n' && in != '\r')  // do not consider \r and \n
					readChar = in;
				in = System.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
			readChar = 0;
		}
		return (char)readChar;
	}
}
