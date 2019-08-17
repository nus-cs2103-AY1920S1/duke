import java.util.Scanner;

public class Duke {
    public static String horizontalLine = "____________________________________________________________\n";
    
    public static void main(String[] args) {
        String greetingMsg = Duke.horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + Duke.horizontalLine;
        System.out.println(greetingMsg);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        String[] inputs;
        int nextTaskIndex = 0;

        mainLoop:
            while (true) {
                inputs = scanner.nextLine().split("\\s+");

                System.out.print(Duke.horizontalLine);

                if (inputs.length == 1) {
                    switch (inputs[0]) {
                        case "list":
                            printTaskArray(tasks);
                            break;
                        case "bye":
                            System.out.println(" Bye. Hope to see you again soon!");
                            System.out.println(Duke.horizontalLine);
                            break mainLoop;
                        default:
                            Task task = new Task(inputs[0]);
                            tasks[nextTaskIndex] = task;
                            nextTaskIndex++;
                            System.out.println(" added: " + inputs[0]);
                            System.out.println(Duke.horizontalLine);
                            break;
                    }
                } else if (inputs.length == 2) {
                    if (inputs[0].equals("done")) {
                        try {
                            Integer taskIndex = Integer.parseInt(inputs[1]);
                            if (taskIndex == null) {
                                throw new NullPointerException();
                            }

                            tasks[--taskIndex].setDone(true);
                            printTaskDone(tasks[taskIndex]);

                        } catch (NumberFormatException e) {
                            System.out.println("Specified task number is invalid.");
                            System.out.println(Duke.horizontalLine);
                        } catch (IndexOutOfBoundsException | NullPointerException e) {
                            System.out.println("Task is not within outputs printed by list!");
                        }
                    }
                }


            }

    }

    private static void printTaskDone(Task task) {
        if (task.isDone) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.printf("   %s\n", task.getStatusText());
            System.out.println(Duke.horizontalLine);
        }
    }

    private static void printTaskArray(Task[] tasks) {
        System.out.println(" Here are the tasks in your list:");

        int taskIndex = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.printf(" %d.%s\n",
                    taskIndex,
                    task.getStatusText());
            taskIndex++;
        }

        System.out.println(Duke.horizontalLine);
    }
}