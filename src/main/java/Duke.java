import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        String indent = "    ";
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Duke.\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);

        Scanner sc = new Scanner(System.in);
        boolean chat = true;
        while(chat) {
            String input = sc.nextLine();
            input = input.trim();
            String[] inputArr = input.split("\\s+");
            String command = inputArr[0];

            System.out.println(indent + line);
            switch (command) {
                case "bye":
                    System.out.println(indent + "Bye! Hope to see you again soon.");
                    chat = false;
                    break;
                case "list":
                    if (list.size() == 0) {
                        System.out.println(indent + "You have not added any tasks to the list!");
                    }
                    for (int i = 0; i < list.size(); i++) {
                        int k = i + 1;
                        Task task = list.get(i);
                        System.out.println(indent + k + ". " + task);
                    }
                    break;
                case "done":
                    if (inputArr.length == 2) {
                        try {
                            int num = Integer.parseInt(inputArr[1]);
                            Task task = list.get(num - 1);
                            task.markAsDone();
                            System.out.println(indent + "Nice! I've marked this task as done:");
                            System.out.println(indent + indent + task);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(indent + "Oops! This task number does not exist.");
                        } catch (NumberFormatException e) {
                            System.out.println(indent + "Oops! Please specify the task number that you want to mark as done.");
                        }
                    } else {
                        System.out.println(indent + "Oops! Invalid command. Please specify only one task number that you want to mark as done.");
                    }
                    break;
                case "todo":
                    Task task = new ToDo(input);
                    list.add(task);
                    printAddedTask(task, list);
                    break;
                case "event":
                    try {
                        String[] eventCommand = input.split("/");
                        String eventDescript = eventCommand[0].substring(6);
                        String at = eventCommand[1].substring(3);
                        Task eventTask = new Event(eventDescript, at);
                        list.add(eventTask);
                        printAddedTask(eventTask, list);
                    } catch (Exception e) {
                        System.out.println(indent + "Oops! You did not specify when the event is.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineCommand = input.split("/");
                        String deadlineDescript = deadlineCommand[0].substring(9);
                        String by = deadlineCommand[1].substring(3);
                        Task deadlineTask = new Deadline(deadlineDescript, by);
                        list.add(deadlineTask);
                        printAddedTask(deadlineTask, list);
                    } catch (Exception e) {
                        System.out.println(indent + "Oops! You did not specify when the deadline is.");
                    }
                    break;
                default:
                    System.out.println(indent + "Sorry! I don't know what that means.");
            }
            System.out.println(indent + line);
        }

    }

    public static void printAddedTask(Task task, ArrayList list) {
        String indent = "    ";
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + indent + task);
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }
}
