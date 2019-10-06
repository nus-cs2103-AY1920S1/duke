package duke;

import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Executor {
    private Ui ui;
    private Storage storageHandler;
    private TaskList tasks;
    private Parser parser;

    public Executor(Ui ui, Storage storageHandler, TaskList tasks, Parser parser) {
        this.ui = ui;
        this.storageHandler = storageHandler;
        this.tasks = tasks;
        this.parser = parser;
    }

    /**
     * Responds to user input by determining which subsequent methods to call.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            try {
                Command userCommand = parser.parse(userInput);
                switch (userCommand.getType()) {
                case LIST:
                    ui.displayList(tasks.getTasks());
                    break;
                case DONE:
                    Integer index = ((DoneCommand) userCommand).getIndex();
                    Task doneTask = tasks.markTaskAsDone(index);
                    ui.doneReply(doneTask);
                    break;
                case TODO:
                    Task addedTodo = tasks.addTodo(((TodoCommand) userCommand).getDescription());
                    ui.displayAddedTask(addedTodo, tasks.getTasks());
                    break;
                case DEADLINE:
                    Task addedDeadline = tasks.addDeadline(
                            ((DeadlineCommand) userCommand).getDescription(),
                            ((DeadlineCommand) userCommand).getDueDate());
                    ui.displayAddedTask(addedDeadline, tasks.getTasks());
                    break;
                case EVENT:
                    Task addedEvent = tasks.addEvent(
                            ((EventCommand) userCommand).getDescription(),
                            ((EventCommand) userCommand).getStartDateTime(),
                            ((EventCommand) userCommand).getEndDateTime()
                    );
                    ui.displayAddedTask(addedEvent, tasks.getTasks());
                    break;
                case DELETE:
                    Task deletedTask = tasks.deleteTask(((DeleteCommand) userCommand).getIndex());
                    ui.displayDeletedTask(deletedTask, tasks.getTasks());
                    break;
                case FIND:
                    ArrayList<Task> matchingTasks = tasks.findMatching(((FindCommand) userCommand).getSearchParams());
                    ui.displayList(matchingTasks);
                    break;
                default:
                    // TODO: have this string be based on enum values
                    String commands = "BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, FIND";
                    throw new InvalidTaskException("I don't know what that means! The available commands are: " + commands);
                }
            }
            catch (InvalidTaskException e) {
                ui.displayErrors(e);
            } finally {
                userInput = sc.nextLine();
            }
        }
        ui.sayGoodbye();
        sc.close();
    }
}
