import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String TABS = "     ";

    /**
     * Prints out system messages after formatting
     * @param command
     */
    public static void formatDisplayedMessages(String command) {
        System.out.println(LINE);
        String[] parsedCommands = command.split("\n");
        for (String line : parsedCommands) {
            System.out.println(TABS + line);
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        formatDisplayedMessages(greeting);
        Scanner sc = new Scanner(System.in); //gets commands from user
        String userCommand = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        //while loop that takes in user inputs
        while (true) {
            //program runs this command when userCommand is bye
            if (userCommand.equals("bye")) {
                formatDisplayedMessages("Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals("list")) {
                //program displays all Tasks in taskList
                String displayedMessage = "";
                Integer index = 1;
                for (Task taskItem : taskList) {
                    displayedMessage += index.toString() + ". " + taskItem.toString() + "\n";
                    index++;
                }
                formatDisplayedMessages(displayedMessage);
            } else {
                //program creates new Task and adds Task to taskList
                Task userTask = new Task(userCommand);
                taskList.add(userTask);
                formatDisplayedMessages("added: " + userCommand);
            }
            userCommand = sc.nextLine();
        }


    }
}
