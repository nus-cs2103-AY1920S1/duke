import command.ByeCommand;
import command.Command;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    private Duke() {
        storage = new Storage();
        ArrayList<Task> list = storage.readFromFile();
        this.taskList = new TaskList(list);
    }

    private void run() throws IOException {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();

            Command c = parser.parse(nextLine);
            c.execute(taskList, storage);

            if (c instanceof ByeCommand) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        UI.start();
        try {
            duke.run();
        } catch (IOException ioE) {
            System.err.println(ioE);
        }
    }
}
