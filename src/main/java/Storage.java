import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    protected static String file = "todo.txt";

    /**
     * Constructor for Storage.
     *
     * @param file File that the list of tasks to be.
     */
    public Storage(String file) {

    }

    /**
     * Adds tasks to the file.
     *
     * @param filepath File that the task is added to.
     * @param textToAdd Tasks that needs to be added.
     * @throws IOException If the named file exists but
     *     is a directory rather than a regular file,
     *     does not exist but cannot be created, or
     *     cannot be opened for any other reason.
     */
    public void addToFile(String filepath, String textToAdd) throws IOException {
        assert filepath != null;
        assert textToAdd != null;
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
     * @throws IOException If the named file exists but
     *     is a directory rather than a regular file,
     *     does not exist but cannot be created, or
     *     cannot be opened for any other reason.
     */
    public void writeToFile(String filepath, String textToAdd) throws IOException {
        assert filepath != null;
        assert textToAdd != null;
        FileWriter typer = new FileWriter(filepath);
        typer.write(textToAdd);
        typer.close();
    }

    /**
     * Counts the number of tasks in the list.
     *
     * @param filename File that the tasks are in.
     * @return Returns the number of tasks.
     * @throws IOException If the named file exists but
     *     is a directory rather than a regular file,
     *     does not exist but cannot be created, or
     *     cannot be opened for any other reason.
     */
    public static int countLines(String filename) throws IOException {
        assert filename != null;
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

    /**
     * Loads the task into the task list
     * in TaskList from the file.
     *
     * @return ArrayList that has been copied from the file.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     * @throws DukeException If there is nothing in the file to be loaded,
     *     this exception will be thrown.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File f = new File(file);
        assert f != null;
        Scanner sc = new Scanner(f);
        ArrayList<Task> tempList;
        if (countLines(file) == 0) {
            throw new DukeException("Woahsies wavy! There is nothing in this file!");
        } else {
            while (sc.hasNext()) {
                String task = sc.nextLine();
                int index = task.indexOf("[");
                String taskType = task.substring(index + 1, index + 2);
                int spaceIndex = task.indexOf(" ");
                switch (taskType) {
                case "T":
                    Task toDo = new Todo(task.substring(spaceIndex));
                    taskList.add(toDo);
                    break;
                case "D":
                    int byIndex = task.indexOf("(");
                    Task deadline = new Deadline(task.substring(spaceIndex, byIndex - 1),
                            task.substring(byIndex + 4));
                    taskList.add(deadline);
                    break;
                case "E":
                    int atIndex = task.indexOf("(");
                    Task event = new Event(task.substring(spaceIndex, atIndex - 1),
                            task.substring(atIndex + 4));
                    taskList.add(event);
                    break;
                default:
                    break;
                }
            }
            Ui.printLine();
            Ui.printIndent();
            System.out.println("Your file has been loaded! :)");
            tempList = new ArrayList<Task>(taskList);
            return tempList;
        }
    }
}
