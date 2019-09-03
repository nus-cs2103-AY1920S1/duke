import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke{

    private static ArrayList<ListItem> todoList = new ArrayList<>() {
        @Override
        public String toString() {
            String toReturn = "";
            for (int i = 0; i < this.size(); i++) {
                toReturn = toReturn.concat((i + 1) + ". " + this.get(i).toString() + "\n    ");
            }

            return toReturn.substring(0, toReturn.length() - 5);
        }
    };

    /**
     * Main driver class for Duke.
     *
     */
    public static void main(String[] args) {
        dukePrint("Hello! I'm Duke", "What can I do for you?");
        Scanner consoleScanner = new Scanner(System.in);
        String userCommand = "";
        String userInput;
        while (!userCommand.equals("exit")) {
            userInput = consoleScanner.next();
            userCommand = userInput.split(" ",2)[0];
            switch(userCommand) {
                case "list":
                    dukePrint(todoList);
                    break;
                default:
                    addToTodo(userInput);

            }

        }

        dukePrint("Bye. Hope to see you again soon!");
    }

    /**
     * Echos string.
     * @param echoedString targeted String to be echoed
     */
    private static void echo(String echoedString) {
        dukePrint(echoedString);
    }

    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */

    private static void dukePrint(Object... texts) {
        System.out.println("    _____________________________");
        for (Object text : texts) {
            System.out.println("    " + text);
        }
        System.out.println("    _____________________________");

    }


    private static void addToTodo(String description) {
        todoList.add(new ListItem(description));
        dukePrint("added: " + description);
    }


}
