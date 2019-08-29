import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String taskListPath = "./src/main/data/taskList.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        storage = new Storage(taskListPath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    private Task createTask(String str, String command) throws DukeException { //command is either todo, deadline or event
        String[] splitStr;
        if (command.equals("todo")) {
            return new ToDo(false, str);
        } else if (command.equals("deadline")) { //eg input: deadline return book /by 2/12/2019 1800
            splitStr = str.split("/by");
            if (splitStr.length == 1) {
                throw new DukeException("Invalid format. Please use 'by:' to state your deadline");
            }
            LocalDateTime dt = Parser.parseDateTime(splitStr[1].trim());
            return new Deadlines(false, splitStr[0].trim(), dt);
        } else if (command.equals("event")) { //eg input: event proj meeting /at 2/12/2019 1800 - 2/12/2019 1800
            splitStr = str.split("/at");
            if (splitStr.length == 1) {
                throw new DukeException("Invalid format. Please use 'at:' to state your deadline");
            }
            String[] dateString = splitStr[1].trim().split(" - "); //e.g. 2/12/2019 1800 - 2/12/2019 1800
            LocalDateTime start = Parser.parseDateTime(dateString[0]);
            LocalDateTime end = Parser.parseDateTime(dateString[1]);
            return new Events(false, splitStr[0].trim(), start, end);
        } else {
            return null;
        }
    }

    void executeCommand(String command, String[] inputSplit) throws DukeException {
        if (command.equals("list")) {
            Ui.printStr(ui.strList(tasks.getTaskList()));
        } else if (command.equals("done")) {
            String taskNumStr = inputSplit[1];
            tasks.markTask(taskNumStr);
            storage.updateFile(tasks);
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (inputSplit.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            }
            Task task = createTask(inputSplit[1], command);
            tasks.addTask(task);
            storage.updateFile(tasks);
        } else if (command.equals("delete")) {
            String taskNumStr = inputSplit[1];
            tasks.deleteTask(taskNumStr);
            storage.updateFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    void start() {
        ui.greet();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ", 2); //split into command and remaining sentence
            String command = inputSplit[0];

            try {
                if (command.equals("bye")) {
                    ui.bye();
                    break;
                } else {
                    executeCommand(command, inputSplit);
                }
            } catch (DukeException e) {
                ui.printStr(e.getMessage());
            }
        }
    }

}