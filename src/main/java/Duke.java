import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static String INDENT = "    ";
    private static String FILENAME = "../../../data/duke.txt";

    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(INDENT);
        try {
            storage = new Storage(filePath);
        } catch(FileNotFoundException | UnsupportedEncodingException err) {
            ui.showFileMissingError();
        }
        try {
            tasks = new TaskList(storage.load());
        } catch(Exception err) {
            ui.printError(err.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = Parser.parse(parser.getCommand(), parser.getDetail(), INDENT);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void runOld() throws FileNotFoundException, UnsupportedEncodingException {
        int listPointer;

        ui.showWelcome();
        String fullCommand = ui.readCommand();
        Parser parser = new Parser(fullCommand);

        while (!parser.getCommand().equals("bye")) {
            if (parser.getCommand().equals("list")) {
                ui.printResponse("Here are the tasks in your list:\n" + tasks.getListString());
            } else if (parser.getCommand().equals("done")) {
                listPointer = parser.getPointer();
                tasks.markTaskAsDone(listPointer - 1);
                ui.printResponse("Nice! I've marked this task as done: \n"
                        + INDENT + "   " + tasks.getList().get(listPointer - 1));
                storage.updateTodoFile(tasks.getListString());
            } else if(parser.getCommand().equals("delete")) {
                listPointer = parser.getPointer();
                Task deletedTask = tasks.getList().get(listPointer - 1);
                tasks.deleteTask(listPointer - 1);
                ui.printResponse("Noted. I've removed this task: \n"
                        + INDENT + "   " + deletedTask + "\n" + INDENT +
                        "Now you have " + tasks.getList().size() + " tasks in the list.");
                storage.updateTodoFile(tasks.getListString());
            } else if(parser.getCommand().equals("todo") || parser.getCommand().equals("deadline") || parser.getCommand().equals("event")){
                String befTaskAddMessage = "Got it. I've added this task: \n" + INDENT + "   ";
                String aftTaskAddMessage = "Now you have " + (tasks.getList().size() + 1) + " tasks in the list.";
                if(parser.getInputArr().length > 1) {
                    if(parser.getCommand().equals("todo")) {
                        tasks.addTask(new Task(parser.getDetail(), "todo", false));
                        ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT + aftTaskAddMessage);
                        storage.updateTodoFile(tasks.getListString());
                    } else if(parser.getCommand().equals("deadline")) {
                        try {
                            tasks.addDateTask(parser.getDetail().split(" /by ")[0], parser.getDetail().split(" /by ")[1], "deadline");
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch(Exception ex) {
                            ui.printResponse("☹ OOPS!!! Deadlines require a specific datetime after /by, "
                                            + "in format 'dd/MM/yyyy HHmm'");
                            System.out.println(ex);
                        }
                    } else if(parser.getCommand().equals("event")) {
                        try {
                            tasks.addDateTask(parser.getDetail().split(" /at ")[0], parser.getDetail().split( " /at ")[1], "event");
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(tasks.getList().size() - 1) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            storage.updateTodoFile(tasks.getListString());
                        } catch(Exception ex) {
                            ui.printError("☹ OOPS!!! Events require a specific datetime after /at, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                        }
                    }
                } else {
                    ui.printError("☹ OOPS!!! The description of a " + parser.getCommand() + " cannot be empty.");
                }
            } else {
                ui.printResponse("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Try todo, " +
                        "event and deadline");
            }
            fullCommand = ui.readCommand();
            parser = new Parser(fullCommand);
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke(FILENAME).run();
    }
}
