import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> myList;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            //remove in future
            myList = tasks.getTasks();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean canEnd = false;
        while (!canEnd) {
            String fullCommand = ui.readCommand();
            Command command = new Parser().parse(fullCommand);
            command.execute(tasks, ui, storage);
            canEnd = command.canEnd();
        }
        TaskFileWriter writer = new TaskFileWriter();
        try {
            writer.writeToFile("data/duke.txt", myList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
