import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String TABS = "     ";

    /*
    public static void formatDisplayedMessages(String command) {
        System.out.println(LINE);
        String[] parsedCommands = command.split("\n");
        for (String line : parsedCommands) {
            System.out.println(TABS + line);
        }
        System.out.println(LINE);
    }
*/
    public static void welcomeMessage() {
        System.out.println(LINE);
        System.out.println(TABS + "Hello! I'm Duke\n"+ TABS +"What can I do for you?");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner sc = new Scanner(System.in); //gets commands from user
        String userCommand = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        //while loop that takes in user inputs
        while (true) {
            System.out.println(LINE);
            //program runs this command when userCommand is bye
            if (userCommand.equals("bye")) {
                System.out.println(TABS + "Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (userCommand.equals("list")) {
                System.out.println(TABS + "Here are the tasks in your list:");
                //program displays all Tasks in taskList
                String displayedMessage = "";
                Integer index = 1;
                for (Task taskItem : taskList) {
                    displayedMessage += TABS + index.toString() + "." + taskItem + "\n";
                    index++;
                }
                System.out.print(displayedMessage);
            } else if (userCommand.startsWith("done")) {
                System.out.println(TABS + "Nice! I've marked this task as done: ");
                int taskNumber = Character.getNumericValue(userCommand.charAt(5));
                Task taskCompleted = taskList.get(taskNumber - 1);
                taskCompleted.markAsDone();
                System.out.println(TABS + "  " + taskCompleted.toString());
            } else {
                //program creates new Task and adds Task to taskList
                Task userTask = new Task(userCommand);
                taskList.add(userTask);
                System.out.println(TABS + "added: " + userCommand);
            }
            System.out.println(LINE);
            userCommand = sc.nextLine();
        }


    }
}
