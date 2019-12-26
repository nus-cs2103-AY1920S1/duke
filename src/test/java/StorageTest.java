import org.junit.jupiter.api.Test;
import SerSnapsalot.storage.Storage;
import java.io.File;

public class StorageTest{

	@Test
	void storageTest() {
		Storage storage = new Storage();
		String path = storage.getPath();
		File tempFile = new File(path);
		boolean fileExists = tempFile.exists();
		if (fileExists) {
			System.out.println("---- Test: Storage file exists.");
		}
	}
}
