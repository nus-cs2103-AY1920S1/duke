import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listArr = new ArrayList<Task>();
        int listSize = 0;
        int listPointer;
        String listString;

        printIndentedString("Hello! I'm Duke\n" +
                indent + " " + "What can I do for you?", indent);
        String input = sc.nextLine();
        String[] inputArr = input.split(" ", 2);

        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                listString = "Here are the tasks in your list:\n" + indent + " ";
                for (int i = 0; i < listArr.size(); i++) {
                    listString += (i + 1) + ". " + listArr.get(i);
                    if (i != (listArr.size() - 1)) {
                        listString += '\n' + indent + ' ';
                    }
                }
                printIndentedString(listString, indent);
            } else if (inputArr[0].equals("done")) {
                listPointer = Integer.parseInt(inputArr[1]);
                listArr.get(listPointer - 1).markAsDone();
                printIndentedString("Nice! I've marked this task as done: \n"
                        + indent + "   " + listArr.get(listPointer - 1), indent);
            } else if(inputArr[0].equals("delete")) {
                listPointer = Integer.parseInt(inputArr[1]);
                printIndentedString("Noted. I've removed this task: \n"
                        + indent + "   " + listArr.get(listPointer - 1) + "\n" + indent +
                        "Now you have " + listArr.size() + " tasks in the list.", indent);
                listArr.remove(listPointer - 1);
            } else if(inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")){
                String befTaskAddMessage = "Got it. I've added this task: \n" + indent + "   ";
                String aftTaskAddMessage = "Now you have " + (listSize + 1) + " tasks in the list.";
                if(inputArr.length > 1) {
                    if(inputArr[0].equals("todo")) {
                            listArr.add(new Task(inputArr[1], "todo"));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent
                                    + aftTaskAddMessage, indent);
                            listSize++;
                    } else if(inputArr[0].equals("deadline")) {
                        try {
                            String deadline = input.split(" /by ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                            listArr.add(new Task(inputArr[1].split(" /by ")[0], "deadline",
                                    deadlineByDateTime));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent
                                    + aftTaskAddMessage, indent);
                            listSize++;
                        } catch(Exception ex) {
                            printIndentedString("☹ OOPS!!! Deadlines require a specific datetime after /by, "
                                            + "in format 'dd/MM/yyyy HHmm'",
                                    indent);
                        }
                    } else if(inputArr[0].equals("event")) {
                        try {
                            String eventDateTime = input.split(" /at ")[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                            listArr.add(new Task(inputArr[1].split(" /at ")[0], "event"
                                    , eventDateTimeByDateTime));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent
                                    + aftTaskAddMessage, indent);
                            listSize++;
                        } catch(Exception ex) {
                            printIndentedString("☹ OOPS!!! Events require a specific datetime after /at, "
                                            + "in format 'dd/MM/yyyy HHmm'"
                                    , indent);
                        }
                    }
                } else {
                    printIndentedString("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty."
                            , indent);
                }
            } else {
                printIndentedString("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", indent);
            }
            input = sc.nextLine();
            inputArr = input.split(" ", 2);
        }
        printIndentedString("Bye. Hope to see you again soon!", indent);
    }

    public static void printIndentedString(String string, String indent) {
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " " + string);
        System.out.println(indent + "____________________________________________________________");
        System.out.println();
    }
}
