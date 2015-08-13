package filesystem;

public interface IDrive {

	/**Returns the object of the root directory.
	 * @return root directory
	 */
	public abstract Directory getRootDirectory();

	/**Returns the object of the current directory.
	 * @return current directory
	 */
	public abstract Directory getCurrentDirectory();

	/**Changes the current directory. The given directory must be part of the drive's directory structure,
	 * otherwise the current directory remains unchanged.
	 * @param dir directory which should become the current directory
	 */
	public abstract boolean setCurrentDirectory(Directory dir);

	/**Returns the drive name with a ending ':'.
	 * E.g. "C:"
	 */
	public abstract String getDriveName();

	/**Returns the DOS-Prompt, drive name with an ending '>' and a space.
	 * E.g. "C:> "
	 */
	public abstract String getPrompt();

	/**Changes the drive label of the drive
	 * @param label new drive label
	 */
	public abstract void setLabel(String label);

	/**Returns the drive label
	 * @return drive label
	 */
	public abstract String getLabel();

	/**Returns the object of a given path name.<br>
	 * <br>
	 * Example:<br>
	 * getItemFromPath("C:\\temp\\aFile.txt");<br>
	 * Returns the FileSystemItem-object which abstracts aFile.txt in the temp directory.<br>
	 * <br>
	 * Remarks:<br>
	 * <li> Always use "\\" for backslashes since the backslash is used as escape character for Java strings.
	 * <li> This operation works for relative paths (temp\\aFile.txt) too. The lookup starts at the current directory.
	 * <li> This operation works for forward slashes '/' too.
	 * <li> ".." and "." are supported too.
	 * <br>
	 * @param givenItemPath Path for which the item shall be returned
	 * @return FileSystemObject or null if no path found.
	 */
	public abstract FileSystemItem getItemFromPath(String givenItemPath);

	/**Builds up a directory structure from the given path on a real drive.
	 * Subdirectories become directories and subdirectories
	 * Files in that directory and the subdirectories become files, content is set to
	 * full path, filename and size of that file.
	 * 
	 * Example:<br>
	 * C:\temp<br>
	 * +-- MyFile1.txt (size 112000 Bytes)<br>
	 * +-- MyFile2.txt (50000)<br>
	 * +-- SubDir1 (Dir)<br>
	 * ....+-- AnExecutable.exe (1234000)<br>
	 * ....+-- ConfigFiles (Dir)<br>
	 * <br>
	 * Results in<br>
	 * <li> All files and subdirectories of the root directory deleted
	 * <li> Current directory set to root directory
	 * <li> File MyFile1.txt added to root directory with content "C:\temp\MyFile1.txt, size 112000 Bytes"
	 * <li> File MyFile2.txt added to root directory with content "C:\temp\MyFile2.txt, size 50000 Bytes"
	 * <li> Directory SubDir1 added to root directory
	 * <li> File AnExecutable.exe added to SubDir1 with content "C:\temp\SubDir1\AnExecutable.exe, size 1234000 Bytes"
	 * <li> Directory ConfigFiles added to SubDir1
	 * <br>
	 * @param path Path which points to a directory 
	 */
	public abstract void createFromRealDirectory(String path);

	/**Stores a directory structure persistently.
	 * Is called when "exit" is called.
	 * Hint: Stores the object stream in a fixed file.  
	 */
	public abstract void save();

	/**Creates a directory structure from an object stream.
	 * Is called at startup of the application.
	 */
	public abstract void restore();

}