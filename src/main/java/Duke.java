import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
//    private static String listString = "";
//    private static ArrayList<Task> listArr = new ArrayList<Task>();
    private static String INDENT = "    ";
    private static String FILENAME = "../../../data/duke.txt";

    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            ui = new Ui(INDENT);
            storage = new Storage(filePath);
            tasks = new TaskList();
        } catch(Exception err) {
            System.out.println(err);
        }
//        try {
//            tasks = new TaskList(storage.load());
//        } catch(DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() throws FileNotFoundException, UnsupportedEncodingException {
        int listSize = 0;
        int listPointer;

        ui.start();
        String input = ui.readLine();
        String[] inputArr = input.split(" ", 2);

        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                ui.printResponse("Here are the tasks in your list:\n" + tasks.getListString());
            } else if (inputArr[0].equals("done")) {
                listPointer = Integer.parseInt(inputArr[1]);
                tasks.markTaskAsDone(listPointer - 1);
                ui.printResponse("Nice! I've marked this task as done: \n"
                        + INDENT + "   " + tasks.getList().get(listPointer - 1));
                storage.updateTodoFile(tasks.getListString());
            } else if(inputArr[0].equals("delete")) {
                listPointer = Integer.parseInt(inputArr[1]);
                Task deletedTask = tasks.getList().get(listPointer - 1);
                tasks.deleteTask(listPointer - 1);
                ui.printResponse("Noted. I've removed this task: \n"
                        + INDENT + "   " + deletedTask + "\n" + INDENT +
                        "Now you have " + tasks.getList().size() + " tasks in the list.");
                storage.updateTodoFile(tasks.getListString());
            } else if(inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")){
                String befTaskAddMessage = "Got it. I've added this task: \n" + INDENT + "   ";
                String aftTaskAddMessage = "Now you have " + (listSize + 1) + " tasks in the list.";
                if(inputArr.length > 1) {
                    if(inputArr[0].equals("todo")) {
                        tasks.addTask(new Task(inputArr[1], "todo"));
                        ui.printResponse(befTaskAddMessage + tasks.getList().get(listSize) + "\n " + INDENT + aftTaskAddMessage);
                        listSize++;
                        storage.updateTodoFile(tasks.getListString());
                    } else if(inputArr[0].equals("deadline")) {
                        try {
                            String deadline = input.split(" /by ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                            tasks.addTask(new Task(inputArr[1].split(" /by ")[0], "deadline",
                                    deadlineByDateTime));
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(listSize) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            listSize++;
                            storage.updateTodoFile(tasks.getListString());
                        } catch(Exception ex) {
                            ui.printResponse("☹ OOPS!!! Deadlines require a specific datetime after /by, "
                                            + "in format 'dd/MM/yyyy HHmm'");
                        }
                    } else if(inputArr[0].equals("event")) {
                        try {
                            String eventDateTime = input.split(" /at ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                            tasks.addTask(new Task(inputArr[1].split(" /at ")[0], "event"
                                    , eventDateTimeByDateTime));
                            ui.printResponse(befTaskAddMessage + tasks.getList().get(listSize) + "\n " + INDENT
                                    + aftTaskAddMessage);
                            listSize++;
                            storage.updateTodoFile(tasks.getListString());
                        } catch(Exception ex) {
                            ui.printError("☹ OOPS!!! Events require a specific datetime after /at, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                        }
                    }
                } else {
                    ui.printError("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.");
                }
            } else {
                ui.printResponse("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Try todo, " +
                        "event and deadline");
            }
            input = ui.readLine();
            inputArr = input.split(" ", 2);
        }
        ui.exit();
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        new Duke(FILENAME).run();
    }
}
