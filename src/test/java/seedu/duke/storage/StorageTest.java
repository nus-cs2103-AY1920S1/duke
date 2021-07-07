package seedu.duke.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void dummyToString() {
        Assertions.assertEquals("dir1/dir2", Storage.getDirAsString("dir1/dir2/dummyFile.txt/"));
    }

}
