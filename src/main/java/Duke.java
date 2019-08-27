import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadSavedList());
        } catch (IOException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printHello();
        while (true) {
            try {
                String input = scanner.nextLine();
                String instruction = input.split(" ", 2)[0];
                if (instruction.equals("bye")) { // First, check if 'bye' is called
                    ui.printBye();
                    break;
                } else if (instruction.equals("list")) { // Then, check if 'list' is called
                    taskList.printList();
                } else if (instruction.equals("done")) { // Then, check if task is marked done
                    int index = Integer.parseInt(input.split(" ", 2)[1]);
                    taskList.markTask(index);
                    storage.writeSavedList(taskList.getList());
                } else if (instruction.equals("delete")) { // Then, check if task is marked delete
                    int index = Integer.parseInt(input.split(" ", 2)[1]);
                    taskList.deleteTask(index);
                    storage.writeSavedList(taskList.getList());
                } else if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")) {
                    try {
                        if (instruction.equals("todo")) {
                            String taskContent = input.split(" ", 2)[1]; // Remaining content of task
                            if (taskContent.matches("\\s*")) { // if the task's description is only whitespace
                                throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                            }
                            taskList.createToDo(taskContent);
                            storage.writeSavedList(taskList.getList());
                        } else {
                            String taskDescription = input.split("deadline|event", 2)[1];
                            if (!(taskDescription.contains(" /by ") || taskDescription.contains(" /at "))) { // if '/by' and '/at' are absent
                                throw new IncorrectTaskTimeFormatException("OOPS!!! No ' /by ' or ' /at ' detected! Please use the correct format!");
                            }
                            String taskContent = taskDescription.split("/by|/at", 2)[0].strip();
                            String taskTimeBeforeParse = taskDescription.split("/by|/at", 2)[1].strip(); // time must be parsed via '/by' or '/at'
                            String[] taskTimeParsed = taskTimeBeforeParse.split("[ /]");
                            LocalDateTime taskTime = LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]),
                                    Integer.parseInt(taskTimeParsed[1]), Integer.parseInt(taskTimeParsed[0]),
                                    Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                                    Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
                            if (taskContent.matches("\\s*")) { // if the task's description is only whitespace
                                throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                            }
                            if (instruction.equals("deadline")) {
                                taskList.createDeadline(taskContent, taskTime);
                            } else {
                                taskList.createEvent(taskContent, taskTime);
                            }
                            storage.writeSavedList(taskList.getList());
                        }
                    } catch (IndexOutOfBoundsException e) { // if the task description is empty
                        throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                    }
                    ui.printSize(taskList.getSize());
                } else { // if an invalid instruction is entered
                    throw new InvalidInstructionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IOException | DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("CurrentTaskList.txt").run();
    }
}
