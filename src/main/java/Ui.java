import java.util.Scanner;

public class Ui {
    public void initiate(Storage storage, TaskList taskList) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("\n%s\n%s\n", "Hello! I'm Duke", "What can I do for you?");
        System.out.println(logo + greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            String[] parseInfo = Parser.parseCommand(input);
            int currentNum = taskList.getNumTask();
            switch (parseInfo[0]) {
            case "DONE":
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.doneTask(parseInfo[1]) + "\n");
                break;
            case "DELETE":
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.deleteTask(parseInfo[1]));
                break;
            case "TODO":
                taskList.todoTask(parseInfo[1]);
                printTaskAddition(taskList);
                break;
            case "DEADLINE":
                taskList.deadlineTask(Parser.parseDetails(parseInfo[1]));
                printTaskAddition(taskList);
                break;
            case "EVENT":
                taskList.eventTask(Parser.parseDetails(parseInfo[1]));
                printTaskAddition(taskList);
                break;
            case "LIST":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.getNumTask(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, taskList.getTask(i)));
                }
                System.out.println();
                break;
            default:
                try {
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                            || input.equals("done")) {
                        String message = String.format("The description of a %s cannot be empty.", input);
                        throw new InsufficientArgumentException(message);
                    } else {
                        throw new InvalidArgumentException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException exception) {

                }
            }

            if (currentNum != taskList.getNumTask()) {
                System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.getNumTask()));
            }

            if (sc.hasNext()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }

        storage.overWrite(taskList.getList());

        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    public void printTaskAddition(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask());
    }

    public void showLoadingError() {
        System.out.println("No existing task list can be loaded. New task list will be created.");
    }
}