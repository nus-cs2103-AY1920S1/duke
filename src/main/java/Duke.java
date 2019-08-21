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
            switch (command) {
                case "bye":
                    System.out.println(indent + line);
                    System.out.println(indent + "Bye! Hope to see you again soon.");
                    System.out.println(indent + line);
                    chat = false;
                    break;
                case "list":
                    System.out.println(indent + line);
                    for (int i = 0; i < list.size(); i++) {
                        int k = i + 1;
                        Task task = list.get(i);
                        System.out.println(indent + k + ".[" + task.getStatusIcon() + "] " + task.description);
                    }
                    System.out.println(indent + line);
                    break;
                case "done":
                    if (inputArr.length == 2) {
                        try {
                            int num = Integer.parseInt(inputArr[1]);
                            Task task = list.get(num - 1);
                            task.markAsDone();
                            System.out.println(indent + line);
                            System.out.println(indent + "Nice! I've marked this task as done:");
                            System.out.println(indent + indent + "[" + task.getStatusIcon() + "] " + task.description);
                            System.out.println(indent + line);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(indent + line);
                            System.out.println(indent + "Oops! This task number does not exist.");
                            System.out.println(indent + line);
                        } catch (NumberFormatException e) {
                            System.out.println(indent + line);
                            System.out.println(indent + "Oops! Please specify the task number that you want to mark as done.");
                            System.out.println(indent + line);
                        }
                    } else {
                        System.out.println(indent + line);
                        System.out.println(indent + "Oops! Invalid command. Please specify only one task number that you want to mark as done.");
                        System.out.println(indent + line);
                    }
                    break;
                default:
                    if (!input.equals("")) {
                        Task task = new Task(input);
                        list.add(task);
                        System.out.println(indent + line);
                        System.out.println(indent + "added: " + input);
                        System.out.println(indent + line);
                    }
            }
        }

    }
}
