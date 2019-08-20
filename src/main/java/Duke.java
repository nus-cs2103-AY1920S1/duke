import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    // print out all the commands as a numbered list
    private void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (Task task : list) {
            System.out.println("\t" + task);
        }
    }

    // print hello message
    private void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String openingMessage = "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n";
        System.out.println(openingMessage);
    }

    // execute command given depending on what command it is
    private void execCommand(String command) {
        String[] commandStringArray = command.split(" "); //split by words
        if (command.equals("list")) {
            printList();
        } else if (command.contains("done") && commandStringArray.length == 2) {
            // command is done and then followed by task number
            int taskNo = Integer.parseInt(commandStringArray[1]);
            Task taskDone = this.list.get(taskNo - 1);
            taskDone.markAsDone();
            System.out.println("\tNice! I've marked this task as done:\n\t\t" + taskDone.toString());
        } else {
            // add new task to the list
            System.out.println("\tGot it. I've added this task:");
            String taskType = commandStringArray[0];
            switch (taskType) {
                case ("todo"):
                    Todo newTodo = new Todo(command.substring(5)); // add newTodo to tasks list with only the description
                    this.list.add(newTodo);
                    System.out.println("\t  " + newTodo.toString());
                    break;
                case ("deadline"):
                    String deadline = command.substring(command.indexOf("/by") + 4);
                    Deadline newDeadline = new Deadline(
                            command.substring(9, command.indexOf("/by")).trim(),
                            // remove trailing white spaces for the description, exclude the deadline
                            deadline);
                    // add new deadline task to the tasks list
                    this.list.add(newDeadline);
                    System.out.println("\t  " + newDeadline.toString());
                    break;
                case ("event"):
                    String when = command.substring(command.indexOf("/at") + 4);
                    Event newEvent = new Event(
                            command.substring(6, command.indexOf("/at")).trim(),
                            // remove trailing white spaces for the description, exclude event day and time
                            when);
                    // add new event task to the tasks list
                    this.list.add(newEvent);
                    System.out.println("\t  " + newEvent.toString());
                    break;
                default:
                    System.out.println("task not recognisable");
                    break;
            }
            System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
        }
    }

    private void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();

        //take in user input and print according to user command
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            command = command.trim();
            if (command.equals("bye")) { //user wants to exit
                duke.printExitMessage(); //program terminates
                return;
            } else {
                duke.execCommand(command);
            }
        }
    }

}
