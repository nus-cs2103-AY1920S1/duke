import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage, ui);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = Parser.parseCommand(input);
            String command = inputArr[0];
            try {
                switch (command) {
                case "bye":
                    ui.print("Bye! Hope to see you again soon!");
                    return;
                case "list":
                    taskList.printList();
                    break;
                case "done":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    taskList.doTask(Parser.parseInt(inputArr[1]));
                    break;
                case "delete":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Task number missing!");
                    }
                    taskList.deleteTask(Parser.parseInt(inputArr[1]));
                    break;
                case "todo":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Todo description missing!");
                    }
                    taskList.addNewTask(new ToDo(inputArr[1]));
                    break;
                case "deadline":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Deadline description missing!");
                    }
                    String[] deadlineInputArr = Parser.parseDeadline(inputArr[1]);
                    taskList.addNewTask(new Deadline(deadlineInputArr[0], deadlineInputArr[1]));
                    break;
                case "event":
                    if (inputArr.length <= 1) {
                        throw new DukeException("☹ OOPS! Event description missing!");
                    }
                    String[] eventInputArr = Parser.parseEvent(inputArr[1]);
                    taskList.addNewTask(new Event(eventInputArr[0], eventInputArr[1]));
                    break;
                default:
                    throw new DukeException("☹ OOPS! I can't do it!");
                }
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
