import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Duke {
    static String listString = "";
    static ArrayList<Task> listArr = new ArrayList<Task>();
    static String indent = "    ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int listSize = 0;
        int listPointer;

        printIndentedString("Hello! I'm Duke\n" +
                indent + " " + "What can I do for you?", indent);
        String input = sc.nextLine();
        String[] inputArr = input.split(" ", 2);

        while (!inputArr[0].equals("bye")) {
            if (inputArr[0].equals("list")) {
                printIndentedString("Here are the tasks in your list:\n" + listString, indent);
            } else if (inputArr[0].equals("done")) {
                listPointer = Integer.parseInt(inputArr[1]);
                listArr.get(listPointer - 1).markAsDone();
                printIndentedString("Nice! I've marked this task as done: \n"
                        + indent + "   " + listArr.get(listPointer - 1), indent);
                updateTodoString();
                updateTodoFile(listString);
            } else if(inputArr[0].equals("delete")) {
                listPointer = Integer.parseInt(inputArr[1]);
                Task deletedTask = listArr.get(listPointer - 1);
                listArr.remove(listPointer - 1);
                printIndentedString("Noted. I've removed this task: \n"
                        + indent + "   " + deletedTask + "\n" + indent +
                        "Now you have " + listArr.size() + " tasks in the list.", indent);
                updateTodoString();
                updateTodoFile(listString);
            } else if(inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")){
                String befTaskAddMessage = "Got it. I've added this task: \n" + indent + "   ";
                String aftTaskAddMessage = "Now you have " + (listSize + 1) + " tasks in the list.";
                if(inputArr.length > 1) {
                    if(inputArr[0].equals("todo")) {
                            listArr.add(new Task(inputArr[1], "todo"));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent + aftTaskAddMessage, indent);
                            listSize++;
                            updateTodoString();
                            updateTodoFile(listString);
                    } else if(inputArr[0].equals("deadline")) {
                        if(input.contains(" /by ")) {
                            String deadline = input.split(" /by ")[1];
                            listArr.add(new Task(inputArr[1].split(" /by ")[0], "deadline", deadline));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent + aftTaskAddMessage, indent);
                            listSize++;
                            updateTodoString();
                            updateTodoFile(listString);
                        } else {
                            printIndentedString("☹ OOPS!!! Deadlines require a specific datetime after /by.", indent);
                        }
                    } else if(inputArr[0].equals("event")) {
                        if(input.contains(" /at ")) {
                            String eventDateTime = input.split(" /at ")[1];
                            listArr.add(new Task(inputArr[1].split(" /at ")[0], "event", eventDateTime));
                            printIndentedString(befTaskAddMessage + listArr.get(listSize) + "\n " + indent + aftTaskAddMessage, indent);
                            listSize++;
                            updateTodoString();
                            updateTodoFile(listString);
                        } else {
                            printIndentedString("☹ OOPS!!! Events require a specific datetime after /at.", indent);
                        }
                    }
                } else {
                    printIndentedString("☹ OOPS!!! The description of a " + inputArr[0] + " cannot be empty.", indent);
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

    public static void updateTodoString() {
        listString = "" + indent + " ";
        for (int i = 0; i < listArr.size(); i++) {
            listString += (i + 1) + ". " + listArr.get(i);
            if (i != (listArr.size() - 1)) {
                listString += '\n' + indent + ' ';
            }
        }
    }

    public static void updateTodoFile(String todoString) {
        try {
            PrintWriter writer = new PrintWriter("../../../data/duke.txt", "UTF-8");
            writer.printf(todoString);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
