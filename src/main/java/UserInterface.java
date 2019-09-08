import java.util.Scanner;

public class UserInterface {
    static TaskList todoList;
    UserInterface(TaskList todoList) {
        this.todoList = todoList;
        todoList.read();
    }

    static void read () {
        Scanner consoleScanner = new Scanner(System.in);
        String userCommand = "";
        String description = "";
        String date = "";

        String userInput;
        while (!userCommand.equals("exit")) {
            userInput = consoleScanner.nextLine();
            userCommand = userInput.split(" ",2)[0];
            description = userInput.split("/")[0];
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
                        todoList.removeFromTodo(userInput.split(" ", 2)[1]);
                    }
                    catch (Exception e) {
                        dukePrint("Nothing to delete");
                    }
                    break;
                default:
                    dukePrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            todoList.save();

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
