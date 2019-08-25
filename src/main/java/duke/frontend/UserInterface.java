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
    private boolean acceptingInput;
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

        acceptingInput = true;
    }

    public void start() throws DukeException {
        Output logo = new Output(System.out);
        logo.addLine(" ____        _        ")
                .addLine("|  _ \\ _   _| | _____ ")
                .addLine("| | | | | | | |/ / _ \\")
                .addLine("| |_| | |_| |   <  __/")
                .addLine("|____/ \\__,_|_|\\_\\___|");
        logo.print();

        standardOutput
                .addLine("Hi! I'm Duke :)")
                .addLine("What can I do for you?");
        displayOutput();

        taskList = storage.load();
    }

    public boolean isAcceptingInput() {
        return acceptingInput;
    }

    public void displayOutput() {
        standardOutput.print();
    }

    public void displayError(DukeException ex) {
        //flushes standard output otherwise future output may be wrong
        standardOutput.flush();

        // displays dukeexception error messages
        errorOutput.addLine(ex.getMessage());
        errorOutput.print();
    }

    public Command readInput() throws DukeException {
        return Parser.parseAsCommand(input.get());
    }

    // methods that enable parsed commands to instruct duke
    public void executeCommand(Command c) throws DukeException {
        // all commands passed to this method have all required parameter non-empty
        switch (c.getClass().getSimpleName()) {
            case "AddTaskCommand":
                executeCommand((AddTaskCommand) c);
                break;
            case "CompleteTaskCommand":
                executeCommand((CompleteTaskCommand) c);
                break;
            case "DeleteTaskCommand":
                executeCommand((DeleteTaskCommand) c);
                break;
            case "ShowListCommand":
                executeCommand((ShowListCommand) c);
                break;
            case "ExitCommand":
                executeCommand((ExitCommand) c);
                break;
            default:
                break;
                // throw new DukeException("This command is not supported");
        }
    }

    public void executeCommand(AddTaskCommand command) throws DukeException {
        Task task;
        switch (command.type) {
            case ADD_TODO:
                task = new ToDo(command.parameters[0]);
                break;
            case ADD_DEADLINE:
                try {
                    task = new Deadline(command.parameters[0], Parser.parseDateTime(command.parameters[1]));
                } catch (DukeException ex) {
                    task = new Deadline(command.parameters[0], command.parameters[1]);
                }
                break;
            case ADD_EVENT:
                try {
                    task = new Event(command.parameters[0], Parser.parseDateTime(command.parameters[1]));
                } catch (DukeException e) {
                    task = new Event(command.parameters[0], command.parameters[1]);
                }
                break;
            default:
                throw new DukeException("This task type is not supported yet");
        }
        taskList.add(task);
        standardOutput
                .addLine("Got it! I've added this task to the list:")
                .addLine(task.toString())
                .addLine("Now you have ", Integer.toString(taskList.size()), " task(s) in your list.");
        storage.save(taskList);
    }
    
    public void executeCommand(CompleteTaskCommand command) throws DukeException {
        Task task = taskList.complete(command.parameters[0]);
        standardOutput
                .addLine("Got it! I've marked this task as done:")
                .addLine(task.toString());
        storage.save(taskList);
    }

    public void executeCommand(DeleteTaskCommand command) throws DukeException {
        Task task = taskList.delete(command.parameters[0]);
        standardOutput
                .addLine("Got it! I've removed this task from the list:")
                .addLine(task.toString())
                .addLine("Now you have ", Integer.toString(taskList.size()), " task(s) in your list.");
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
                standardOutput.addLine(String.format("%0" + width(taskList.size()) + "d", count), ". ", task.toString());
            }
        }
    }

    private static int width(int number) {
        return Integer.toString(number).length();
    }

    public void executeCommand(ExitCommand command) throws DukeException {
        storage.save(taskList);
        acceptingInput = false;
        standardOutput.addLine("Goodbye! Hope to see you again!");
    }
}
