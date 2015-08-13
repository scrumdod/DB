package filesystem;

import java.util.ArrayList;
import java.util.Iterator;

/**Responsibility: 
 * - Is able to query a file system item from any given path
 *   (e.g. Directory-Object "Temp" from "C:\temp")
 */
class FileSystemItemQuerier {
	IDrive currentDrive;
	
	public FileSystemItemQuerier(IDrive currentDrive) {
		this.currentDrive = currentDrive;
	}

	public FileSystemItem getItemFromPath(String givenItemPath) {
		String givenItemPathPatched = cleanItemPathString(givenItemPath);
		if(isSpecialDirectory(givenItemPathPatched))
			return getItemFromSpecialDirectory(givenItemPathPatched);
		givenItemPathPatched = makeAbsolutePathFromRelative(givenItemPathPatched);
		return findMoreComplexPathsRecursive(givenItemPathPatched, currentDrive.getCurrentDirectory());
	}

	private String cleanItemPathString(String givenItemPath) {
		String givenItemPathPatched = replaceForwardWithBackwardSlash(givenItemPath);
		givenItemPathPatched = removeEndingBacklash(givenItemPathPatched);
		givenItemPathPatched = expandPointBacklash(givenItemPathPatched);       // ".\"
		givenItemPathPatched = expandPointPointBacklash(givenItemPathPatched);  // "..\"
		
		return givenItemPathPatched;
	}

	private String replaceForwardWithBackwardSlash(String givenItemPath) {
		return givenItemPath.replace('/', '\\');
	}
	
	private String removeEndingBacklash(String givenItemPath) {
		givenItemPath = givenItemPath.trim();
		if(   givenItemPath.charAt(givenItemPath.length()-1) == '\\'
		   && givenItemPath.length() >= 2) {
				givenItemPath = givenItemPath.substring(0, givenItemPath.length()-1);
		}
		return givenItemPath;
	}

	private String expandPointBacklash(String givenItemPath) {
		if(givenItemPath.length() >= 2) {
			if(givenItemPath.substring(0, 2).compareTo(".\\") == 0) {
				givenItemPath = givenItemPath.substring(2, givenItemPath.length());
			}
		}
		return givenItemPath;
	}

	private String expandPointPointBacklash(String givenItemPath) {
		if(givenItemPath.length() >= 3) {
			if(givenItemPath.substring(0, 3).compareTo("..\\") == 0) {
				StringBuilder temp = new StringBuilder();
				temp.append(getParentDirectorysAbsolutePath());
				temp.append("\\");
				temp.append(givenItemPath.substring(3, givenItemPath.length()));
				givenItemPath = temp.toString();
			}
		}
		return givenItemPath;
	}

	private String getParentDirectorysAbsolutePath() {
		return currentDrive.getCurrentDirectory().getParent().getPath();
	}

	private String makeAbsolutePathFromRelative(String givenItemPath) {
		if(givenItemPath.charAt(0) == '\\') {
			return currentDrive.getDriveName() + givenItemPath;
		}
		if(givenItemPath.length() == 1 || givenItemPath.charAt(1) != ':') {
			return currentDrive.getCurrentDirectory() + "\\" + givenItemPath;
		}
		return givenItemPath;
	}

	private boolean isSpecialDirectory(String givenItemPath) {
		if(isRootPath(givenItemPath))
			return true;
		if(isCurrentDirectory(givenItemPath))
			return true;
		if(isParentDirectory(givenItemPath))
			return true;
		return false;
	}

	private FileSystemItem getItemFromSpecialDirectory(String givenItemPath) {
		if(isRootPath(givenItemPath)) {
			return currentDrive.getRootDirectory();
		}
		if(isCurrentDirectory(givenItemPath)) {
			return currentDrive.getCurrentDirectory();
		}
		if(isParentDirectory(givenItemPath)) {
			Directory parent = currentDrive.getCurrentDirectory().getParent();
			if(parent == null) {  // Case if current directory is already root
				parent = currentDrive.getRootDirectory();
			}
			return parent;
		}
		// Neither Root or Parent directory: Call isSpecialDirectory() before
		throw new IllegalStateException();
	}
	
	private boolean isRootPath(String givenItemPath) {
		if(givenItemPath.compareTo("\\") == 0) return true;
		if(givenItemPath.compareTo("C:") == 0) return true;
		if(givenItemPath.compareTo("C:\\") == 0) return true;
		
		return false;
	}

	private boolean isCurrentDirectory(String givenItemPath) {
		return givenItemPath.compareTo(".") == 0 ? true : false;
	}
	
	private boolean isParentDirectory(String givenItemPath) {
		return givenItemPath.compareTo("..") == 0 ? true : false;		
	}

	private FileSystemItem findMoreComplexPathsRecursive(String givenItemName, Directory directoryToLookup) {
		ArrayList<FileSystemItem> content = directoryToLookup.getContent();
		Iterator<FileSystemItem> it = content.iterator();
		while(it.hasNext()) {
			FileSystemItem item = it.next();
			String pathName = item.getPath();
			if(pathName.equalsIgnoreCase(givenItemName)) {
				return item;
			}
			if(item.isDirectory() == true) {
				FileSystemItem retVal = this.findMoreComplexPathsRecursive(givenItemName, (Directory)item);
				if(retVal != null) {
					return retVal;
				}
			}
		}
		return null;
	}
}
