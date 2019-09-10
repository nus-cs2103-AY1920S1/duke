import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    static TaskList todoList;
    static Storage data;
    UserInterface(TaskList todoList, Storage store) {
        this.todoList = todoList;
        this.data = store;
    }

    static void listen () {
        Scanner consoleScanner = new Scanner(System.in);
        String userCommand = "";
        String description = "";
        String date = "";

        String userInput;
        while (!userCommand.equals("exit")) {
            userInput = consoleScanner.nextLine();
            userCommand = userInput.split(" ",2)[0];
            try {
                description = userInput.split(" ")[1].split("/")[0];
            } catch (Exception e) {

            }

            if (userInput.split("/").length > 1) {
                date = userInput.split("/")[1];
            }
            else {
                date = " ";
            }
            switch(userCommand) {
                case "list":
                    dukePrint(todoList);
                    break;
                case "done":
                    ListItem target = todoList.lst.get(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
                    target.done();
                    dukePrint("Nice! I've marked this task as done:", "  " + target);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    try {
                        dukePrint(todoList.addToTodo(description, userCommand, date));
                    }
                    //try to catch more exceptions
                    catch (Exception e) {
                        dukePrint("☹ OOPS!!! The description of a " + userCommand + " cannot be empty.");
                    }
                    break;
                case "delete":
                    try {
                        dukePrint(todoList.removeFromTodo(userInput.split(" ", 2)[1]));
                    }
                    catch (Exception e) {
                        dukePrint("Nothing to delete");
                    }
                    break;
                case "find":
                    String lst = "";
                    int counter = 1;
                    for (ListItem item : TaskList.lst) {
                        if (item.getDescription().contains(description)) {
                            lst += counter + "." + item.toString() + "\n     ";
                            counter++;
                        }
                    }
                    dukePrint("Here are the matching tasks in your list:", lst.substring(0, lst.length() - 6));
                    break;

                default:
                    dukePrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            data.save(todoList.lst);

        }
    }
    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */

    private static void dukePrint(Object... texts) {
        System.out.println("    _____________________________");
        for (Object text : texts) {
            System.out.println("     " + text);
        }
        System.out.println("    _____________________________");

    }
}
