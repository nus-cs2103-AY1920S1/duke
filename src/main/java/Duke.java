import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        greet();
        readInput();
        goodbye();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList(taskList);
                userInput = input.nextLine();
            } else if (userInput.startsWith("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Task selectedTask = taskList.get(taskNumber - 1);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + selectedTask);
                userInput = input.nextLine();
            } else {
                if (userInput.startsWith("todo")) {
                    addTaskToTaskList(taskList, new ToDo(userInput.substring(5)));
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(9, userInput.indexOf('/') - 1);
                    String by = userInput.substring(14 + description.length());
                    addTaskToTaskList(taskList, new Deadline(description, by));
                } else if (userInput.startsWith("event")) {
                    String description = userInput.substring(6, userInput.indexOf('/') - 1);
                    String at = userInput.substring(11 + description.length());
                    addTaskToTaskList(taskList, new Event(description, at));
                }
                userInput = input.nextLine();
            }
        }
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("task list is empty");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }

    public static void addTaskToTaskList(ArrayList<Task> tasklist, Task task) {
        tasklist.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }
}
