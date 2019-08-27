package duke.frontend;

import duke.io.Input;
import duke.io.Output;
import duke.io.Parser;
import duke.io.Storage;

import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ShowListCommand;
import duke.command.ExitCommand;

import duke.DukeException;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;

public class UserInterface {
    private boolean isAcceptingInput;
    private TaskList taskList;
    private Storage storage;
    private Input input;
    private Output standardOutput;
    private Output errorOutput;

    public UserInterface() {
        // setup input and output
        input = new Input(System.in);

        String lineBreak1 = "___________________________________________________________";
        String lineBreak2 = "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";

        errorOutput = new Output(System.out);
        errorOutput.setHeader(lineBreak1, " ☹ OOPS!!!");
        errorOutput.setFooter(lineBreak2);
        errorOutput.setLeftBorder("ERROR ");
        errorOutput.setLeftIndent(1);
        errorOutput.setWrapOn(true);

        standardOutput = new Output(System.out);
        standardOutput.setHeader(lineBreak1);
        standardOutput.setFooter(lineBreak2);
        standardOutput.setLeftBorder("      ");
        standardOutput.setLeftIndent(1);
        standardOutput.setWrapOn(true);

        // storage = new Storage("SaveFiles");
        storage = new Storage("DukeSave01.txt");

        // load default exisiting task list if any
        taskList = new TaskList();

        isAcceptingInput = true;
    }

    private static int getWidth(int number) {
        return Integer.toString(number).length();
    }

    public boolean canAcceptInput() {
        return isAcceptingInput;
    }

    public void start() throws DukeException {
        Output logo = new Output(System.out);

        logo.addLine(" ____        _        ")
                .addLine("|  _ \\ _   _| | _____ ")
                .addLine("| | | | | | | |/ / _ \\")
                .addLine("| |_| | |_| |   <  __/")
                .addLine("|____/ \\__,_|_|\\_\\___|");
        logo.print();

        standardOutput.addLine("Hi! I'm Duke :)")
                .addLine("What can I do for you?");
        displayOutput();

        taskList = storage.loadTaskList();
    }

    public void displayOutput() {
        standardOutput.print();
    }

    public void displayError(DukeException ex) {
        //flushes standard output otherwise future output may be wrong
        standardOutput.clear();

        // displays dukeexception error messages
        errorOutput.addLine(ex.getMessage());
        errorOutput.print();
    }

    public Command readInput() throws DukeException {
        return Parser.parseAsCommand(input.get());
    }

    // methods that enable parsed commands to instruct duke
    public void executeCommand(Command command) throws DukeException {
        // all commands passed to this method have all required parameter non-empty
        switch (command.getClass().getSimpleName()) {
        case "AddTaskCommand":
            executeCommand((AddTaskCommand) command);
            break;
        case "CompleteTaskCommand":
            executeCommand((CompleteTaskCommand) command);
            break;
        case "DeleteTaskCommand":
            executeCommand((DeleteTaskCommand) command);
            break;
        case "ShowListCommand":
            executeCommand((ShowListCommand) command);
            break;
        case "ExitCommand":
            executeCommand((ExitCommand) command);
            break;
        default:
            break;
        }
    }

    public void executeCommand(AddTaskCommand command) throws DukeException {
        String[] parameters = Command.getParametersUsed(command);
        Task task;

        switch (Command.getTypeOf(command)) {
        case ADD_TODO:
            task = new ToDo(parameters[0]);
            break;
        case ADD_DEADLINE:
            try {
                task = new Deadline(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException ex) {
                task = new Deadline(parameters[0], parameters[1]);
            }
            break;
        case ADD_EVENT:
            try {
                task = new Event(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException e) {
                task = new Event(parameters[0], parameters[1]);
            }
            break;
        default:
            throw new DukeException("This task type is not supported yet");
        }

        taskList.add(task);

        standardOutput.addLine("Got it! I've added this task to the list:")
                .addLine(task.toString())
                .addLine("Now you have ",
                        Integer.toString(taskList.size()),
                        " task(s) in your list.");

        storage.save(taskList);
    }

    public void executeCommand(CompleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getParametersUsed(command);
        Task task = taskList.complete(parameters[0]);

        standardOutput.addLine("Got it! I've marked this task as done:")
                .addLine(task.toString());

        storage.save(taskList);
    }

    public void executeCommand(DeleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getParametersUsed(command);
        Task task = taskList.delete(parameters[0]);

        standardOutput.addLine("Got it! I've removed this task from the list:")
                .addLine(task.toString())
                .addLine("Now you have ",
                        Integer.toString(taskList.size()),
                        " task(s) in your list.");

        storage.save(taskList);
    }

    public void executeCommand(ShowListCommand command) throws DukeException {
        if (taskList.size() == 0) {
            standardOutput.addLine("Your list is empty!");
        } else {
            int count = 0;
            standardOutput.addLine("Here are the task(s) in your list:");

            for (Task task : taskList.list()) {
                count++;
                standardOutput.addLine(
                        String.format("%0" + getWidth(taskList.size()) + "d", count), ". ", task.toString());
            }
        }
    }

    public void executeCommand(ExitCommand command) throws DukeException {
        isAcceptingInput = false;
        storage.save(taskList);
        standardOutput.addLine("Goodbye! Hope to see you again!");
    }
}
