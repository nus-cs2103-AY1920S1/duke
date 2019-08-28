import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke, a personal assistant ChatBot that helps a person to keep track of various things.
 */
public class Duke {

    public static Scanner sc = new Scanner(System.in);

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    /**
     * Initialises a new Duke object.
     *
     * @throws FileNotFoundException if file is not found.
     * @throws IOException if there is an issue reading the file.
     */
    public Duke() throws FileNotFoundException, IOException {

        this.ui = new UI();
        this.storage = new Storage("../duke.txt");
        ArrayList<Task> existing = storage.readFileContents();
        this.taskList = new TaskList(existing);
        this.storage.writeToFile("");

    }

    /**
     * Runs the program. It will start from here.
     *
     * @param args
     * @throws IOException if there is an issue reading the file.
     */
    public static void main(String[] args) throws IOException  {

       new Duke().run();

    }

    /**
     * Runs the program by prompting user to enter the command. The program will then carry out the program if the command
     * is valid. If not, it will throw a Duke exception.
     *
     * @throws IOException if there is an issue reading the .txt file to recover the previous list.
     */
    public void run() throws IOException {

        this.ui.welcome();
        String command = ui.promptEntry();
        ui.handleCommand(command, this.taskList);
        //after all commands are done, we will save the updated list into the txt file.
        ArrayList<Task> updated = this.taskList.getList();

        if(!updated.isEmpty()) {
            for (Task task : updated) {
                if(storage.isFileEmpty()) {
                        storage.writeToFile(task.toTextFile());
                } else {
                    storage.appendToFile(task.toTextFile());
                }
            }
        }
        this.ui.goodbye();
    }


}
