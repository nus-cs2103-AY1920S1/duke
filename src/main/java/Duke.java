import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    private ArrayList<Task> list;

    private Duke() {
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
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, this.list.get(i)));
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
    private void execCommand(String command) throws InvalidTaskException {
        String[] commandStringArray = command.split(" "); //split by words
        String taskType = commandStringArray[0];
        if (command.equals("list")) {
            printList();
        } else if (taskType.equals("done") && commandStringArray.length == 2) {
            // when the command is done and followed by task number
            int taskNo = Integer.parseInt(commandStringArray[1]);
            Task taskDone = this.list.get(taskNo - 1);
            taskDone.markAsDone();
            System.out.println("\tNice! I've marked this task as done:\n\t\t" + taskDone.toString());
        } else if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
            // check if the taskType is a valid command before adding the task to the tasks list
            try {
                addTask(command, taskType);
            } catch (MissingDescriptionException | MissingInputException e) {
                // taskType is valid but missing arguments
                System.out.println("\t" + e.getMessage());
            }
        } else {
            // taskType is not a valid command, throw IllegalArgumentException
            throw new InvalidTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    // adds a certain type of task to the tasks list depending on user's input
    private void addTask(String command, String taskType) throws MissingDescriptionException, MissingInputException {
        String desc = command.substring(taskType.length()).trim();
        Task task = new Task();
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("☹ OOPS!!! The description of " + taskType + " cannot be empty.");
        }
        switch (taskType) {
            case ("todo"):
                task = new Todo(desc);
                break;
            case ("deadline"):
                if (!desc.contains("/by")) {
                    throw new MissingInputException("☹ OOPS!!! The deadline cannot be found because /by is missing");
                }
                String[] splitDeadlineDesc = desc.split("/by");
                desc = splitDeadlineDesc[0].trim(); // first element in string array is solely the task description
                // second element in string array is the deadline of the task, unless it is not found
                String deadline;
                try {
                    deadline = splitDeadlineDesc[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // above exception will be thrown when the splitDeadlineDesc only has one element
                    // this means that there is nothing after /by
                    throw new MissingInputException("☹ OOPS!!! The deadline cannot be found after /by");
                }
                task = new Deadline(desc, deadline);
                break;
            case ("event"):
                if (!desc.contains("/at")) {
                    throw new MissingInputException("☹ OOPS!!! The event date and time cannot be found because /at is missing");
                }
                String[] splitEventDesc = desc.split("/at"); // first element is simply the string description
                // second element would be the event time/day, unless it is not found
                desc = splitEventDesc[0].trim();
                String when;
                try {
                    when = splitEventDesc[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    // above exception will be thrown when the splitEventDesc only has one element
                    // this means that there is nothing after /at
                    throw new MissingInputException("☹ OOPS!!! The event date and time cannot be found after /at");
                }
                task = new Event(desc, when);
                break;
        }
        this.list.add(task);
        printAddedTask(task);
    }

    // prints necessary lines when adding a task
    private void printAddedTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.list.size()));
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
            if (command.equals("bye")) { // user wants to exit
                duke.printExitMessage();
                return; // program terminates
            } else {
                try {
                    duke.execCommand(command);
                } catch (InvalidTaskException e) {
                    System.out.println("\t" + e.getMessage());
                }
            }
        }
    }

}
