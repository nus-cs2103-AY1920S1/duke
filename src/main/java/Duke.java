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
                //program marks a Task as done
                System.out.println(TABS + "Nice! I've marked this task as done: ");
                int taskNumber = Character.getNumericValue(userCommand.charAt(5));
                Task taskCompleted = taskList.get(taskNumber - 1);
                taskCompleted.markAsDone();
                System.out.println(TABS + "  " + taskCompleted.toString());
            } else {
                //program checks what type of task it is
                Task userTask;
                if (userCommand.startsWith("todo")) {
                    String task = userCommand.substring(5);
                    userTask = new ToDo(task);
                } else if (userCommand.startsWith("deadline")) {
                    String task = userCommand.replaceFirst("deadline ", "");
                    String[] taskInformation = task.split(" /by ");
                    userTask = new Deadline(taskInformation[0], taskInformation[1]);
                } else {
                    String task = userCommand.replaceFirst("event ", "");
                    String[] taskInformation = task.split(" /at ");
                    userTask = new Event(taskInformation[0], taskInformation[1]);
                }
                taskList.add(userTask);
                System.out.println(TABS + "Got it. I've added this task: ");
                System.out.println(TABS + "  " + userTask.toString());

                if (taskList.size() == 1) {
                    System.out.printf("%sNow you have %d task in the list.\n", TABS, taskList.size());
                } else {
                    System.out.printf("%sNow you have %d tasks in the list.\n", TABS, taskList.size());
                }
            }
            System.out.println(LINE);
            userCommand = sc.nextLine();
        }


    }
}
