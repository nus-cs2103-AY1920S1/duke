import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private enum Commands {
        bye,
        list,
        done,
        delete,
        todo,
        event,
        deadline,
    }
    /*
        prints out a series of dashes that result in a straight line
     */
    private static void straightLine() {
        System.out.println("\n--------------------------------------");
    }

    /*
        adds a new task newTask to the arraylist tasks
        and also prints out statememnts stating that it has been completed
     */
    private static void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + newTask.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        straightLine();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you?\n");
        straightLine();

        boolean shouldStop = false;
        while (!shouldStop) {
            try {
                String input = sc.nextLine().trim();
                straightLine();
                String command = input.split(" ")[0]; //the first word of the user input
                Commands commandEnum;
                try {
                    commandEnum = Commands.valueOf(command.toLowerCase());
                } catch(IllegalArgumentException err) {
                    throw new InvalidCommandDukeException(command);
                }

                String description = input.substring(command.length()).trim();
                switch (commandEnum) {
                    case bye:
                        System.out.println("    Bye! See you again soon!!");
                        straightLine();
                        shouldStop = true;
                        break;
                    case list:
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("      " + (i + 1) + "." + tasks.get(i).toString());
                        }
                        straightLine();
                        break;
                    case done:
                        {
                            int taskNumber;
                            try {
                                taskNumber = Integer.parseInt(description) - 1;
                                tasks.get(taskNumber).complete();
                                System.out.println("    Nice! I've marked this task as done:");
                                System.out.println("      " + tasks.get(taskNumber).toString());
                                straightLine();
                            } catch (IndexOutOfBoundsException | NumberFormatException err) {
                                throw new InvalidDescriptionDukeException(command, description);
                            }
                        }
                        break;
                    case delete:
                        {
                            int taskNumber;
                            try {
                                taskNumber = Integer.parseInt(description) - 1;
                                System.out.println("    Noted. I've removed this task:");
                                System.out.println("      " + tasks.get(taskNumber).toString());
                                tasks.remove(taskNumber);
                                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                                straightLine();
                            } catch (IndexOutOfBoundsException | NumberFormatException err) {
                                throw new InvalidDescriptionDukeException(command, description);
                            }
                        }
                        break;
                    case todo:
                        addTask(new ToDoTask(description));
                        break;
                    case event:
                        addTask(new EventsTask(description));
                        break;
                    case deadline:
                        addTask(new DeadlinesTask(description));
                        break;
                    default:
                        throw new InvalidCommandDukeException(command);
                }
            } catch(DukeException err) {
                System.out.println("    " + err.toString());
                straightLine();
            }
        }
    }
}
