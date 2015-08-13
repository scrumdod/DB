package filesystem;

/** Factory to create a Drive-Object. Required to be independant on the different
 * IDrive-Realizations. 
 */
public class FileSystem {
	private static IDriveFactory driveFactory = new DefaultDriveFactory();

	public static IDrive CreateDrive() {
			return driveFactory.CreateDrive();
	}
	
	public static void ChangeDriveFactory(IDriveFactory newDriveFactory) {
		assert (newDriveFactory != null);
		driveFactory = newDriveFactory;
	}
}

interface IDriveFactory {
	public IDrive CreateDrive();
}

class DefaultDriveFactory implements IDriveFactory {
	public IDrive CreateDrive() {
		return (IDrive) new Drive("C");
	}
}
