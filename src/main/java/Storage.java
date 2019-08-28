import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Storage {
        private ArrayList<Task> listOfTask = new ArrayList<Task>();

    public Storage(ArrayList<Task> list) {

    }

    /**
     * Adds tasks to the file.
     *
     * @param filepath File that the task is added to.
     * @param textToAdd Tasks that needs to be added.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public static void addToFile(String filepath, String textToAdd) throws IOException {
            FileWriter typer = new FileWriter(filepath, true);
            typer.write(textToAdd + System.lineSeparator());
            typer.close();
    }

    /**
     * Writes task to a file.
     * Can be used as a way to overwrite tasks in the file as well.
     *
     * @param filepath File that the task is added to.
     * @param textToAdd Tasks that needs to be added.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter typer = new FileWriter(filepath);
        typer.write(textToAdd);
        typer.close();
    }

    /**
     * Counts the number of tasks in the list.
     *
     * @param filename File that the tasks are in.
     * @return Returns the number of tasks.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static int countLines(String filename) throws IOException {
        try (InputStream inputs = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] characters = new byte[1024];

            int readCharacters = inputs.read(characters);
            if (readCharacters == -1) {
                // no lines to read
                return 0;
            }

            int count = 0;
            while (readCharacters == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (characters[i++] == '\n') {
                        ++count;
                    }
                }
                readCharacters = inputs.read(characters);
            }

            // count remaining characters
            while (readCharacters != -1) {
                //System.out.println(readCharacters);
                for (int i = 0; i < readCharacters; ++i) {
                    if (characters[i] == '\n') {
                        ++count;
                    }
                }
                readCharacters = inputs.read(characters);
            }

            return count == 0 ? 1 : count;
        }
    }

}
