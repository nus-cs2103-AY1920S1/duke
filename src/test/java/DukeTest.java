import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

class DukeTest {
    @Test
    void main() throws Exception {
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("data/duke.txt"));
        System.setIn(fips);
        System.setIn(original);
    }
}
