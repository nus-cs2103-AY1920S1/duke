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
        String[] commandStringArray = command.split(" ");
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
            Task task = new Task(command,this.list.size() + 1);
            this.list.add(task);
            System.out.println("\tadded: " + command);
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
