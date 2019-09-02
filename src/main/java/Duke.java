import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    static String listString = "";
    static ArrayList<Task> listArr = new ArrayList<Task>();
    static String INDENT = "    ";

    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;
    private static String FILENAME = "../../../data/duke.txt";

    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
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
        Scanner sc = new Scanner(System.in);
        int listSize = 0;
        int listPointer;

        UIprintWelcome();
        String input = sc.nextLine();
        String[] inputArr = input.split(" ", 2);

        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                printIndentedString("Here are the tasks in your list:\n" + listString, INDENT);
            } else if (inputArr[0].equals("done")) {
                listPointer = Integer.parseInt(inputArr[1]);
                listArr.get(listPointer - 1).markAsDone();
                printIndentedString("Nice! I've marked this task as done: \n"
                        + INDENT + "   " + listArr.get(listPointer - 1), INDENT);
                updateTodoString();
                storage.updateTodoFile(listString);
            } else if(inputArr[0].equals("delete")) {
                listPointer = Integer.parseInt(inputArr[1]);
                Task deletedTask = listArr.get(listPointer - 1);
                listArr.remove(listPointer - 1);
                printIndentedString("Noted. I've removed this task: \n"
                        + INDENT + "   " + deletedTask + "\n" + INDENT +
                        "Now you have " + listArr.size() + " tasks in the list.", INDENT);
                updateTodoString();
                storage.updateTodoFile(listString);
            } else if(inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")){
                String befTaskAddMessage = "Got it. I've added this task: \n" + INDENT + "   ";
                String aftTaskAddMessage = "Now you have " + (listSize + 1) + " tasks in the list.";
                if(inputArr.length > 1) {
                    if(inputArr[0].equals("todo")) {
                        listArr.add(new Task(inputArr[1], "todo"));
                        printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + INDENT + aftTaskAddMessage, INDENT);
                        listSize++;
                        updateTodoString();
                        storage.updateTodoFile(listString);
                    } else if(inputArr[0].equals("deadline")) {
                        try {
                            String deadline = input.split(" /by ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                            listArr.add(new Task(inputArr[1].split(" /by ")[0], "deadline",
                                    deadlineByDateTime));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + INDENT
                                    + aftTaskAddMessage, INDENT);
                            listSize++;
                            updateTodoString();
                            storage.updateTodoFile(listString);
                        } catch(Exception ex) {
                            printIndentedString("☹ OOPS!!! Deadlines require a specific datetime after /by, "
                                            + "in format 'dd/MM/yyyy HHmm'",
                                    INDENT);
                        }
                    } else if(inputArr[0].equals("event")) {
                        try {
                            String eventDateTime = input.split(" /at ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                            listArr.add(new Task(inputArr[1].split(" /at ")[0], "event"
                                    , eventDateTimeByDateTime));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + INDENT
                                    + aftTaskAddMessage, INDENT);
                            listSize++;
                            updateTodoString();
                            storage.updateTodoFile(listString);
                        } catch(Exception ex) {
                            UIprintError("☹ OOPS!!! Events require a specific datetime after /at, "
                                    + "in format 'dd/MM/yyyy HHmm'");
                        }
                    }
                } else {
                    UIprintError("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.");
                }
            } else {
                printIndentedString("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Try todo, " +
                        "event and deadline", INDENT);
            }
            input = sc.nextLine();
            inputArr = input.split(" ", 2);
        }
        printIndentedString("Bye. Hope to see you again soon!", INDENT);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        new Duke(FILENAME).run();
    }

    public static void printIndentedString(String string, String INDENT) {
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + " " + string);
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println();
    }

    public static void updateTodoString() {
        listString = "" + INDENT + " ";
        for (int i = 0; i < listArr.size(); i++) {
            listString += (i + 1) + ". " + listArr.get(i);
            if (i != (listArr.size() - 1)) {
                listString += '\n' + INDENT + ' ';
            }
        }
    }

    public static void UIprintWelcome() {
        printIndentedString("Hello! I'm Duke\n" +
                INDENT + " " + "What can I do for you?", INDENT);
    }

    public static void UIprintError(String errorMessage) {
        printIndentedString(errorMessage, INDENT);
    }
}
