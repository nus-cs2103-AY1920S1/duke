package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private Path path;

    Storage(String location) {
        path = Paths.get(location);
    }

    /**
     * Saves the given list of lines.
     *
     * @param lines List of lines to save.
     * @throws IOException If the tasks cannot be saved.
     */
    public void store(List<String> lines) throws IOException {
        Files.write(path, lines);
    }

    /**
     * Loads and returns the list of lines.
     *
     * @return List of lines loaded.
     * @throws IOException If the lines cannot be loaded.
     */
    List<String> load() throws IOException {
        return Files.readAllLines(path);
    }
}
