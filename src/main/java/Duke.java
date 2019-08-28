import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        String fullCommand = ui.readInput(new Scanner(System.in));

        boolean continues = true;

        while (!fullCommand.equals("bye")) {
            try {
                Parser parser = new Parser(fullCommand);
                boolean valid = parser.checkValidity();
                if(valid) {
                    String commandType = parser.getCommandType();
                    switch (commandType) {
                    case "list":
                        tasks.showTasks();
                        break;
                    case "done":
                        tasks.doneTask(parser.getIndex());
                        storage.updateFile(tasks);
                        break;
                    case "delete":
                        tasks.deleteTask(parser.getIndex());
                        storage.updateFile(tasks);
                        break;
                    case "todo":
                        tasks.addTodo(parser.getActivityNameWithoutTime(),
                                false);
                        storage.updateFile(tasks);
                        break;
                    case "deadline":
                        tasks.addDeadline(parser.getActivityNameWithTime(),
                                parser.getDeadline(), false);
                        storage.updateFile(tasks);
                        break;
                    case "event":
                        tasks.addEvent(parser.getActivityNameWithTime(),
                                parser.getTime(), false);
                        storage.updateFile(tasks);
                        break;
                    }
                }
            } catch (DukeException | IOException ex) {
                System.out.println(ex.getMessage());
            }
            fullCommand = ui.readInput(new Scanner(System.in));
        }

        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}