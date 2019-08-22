import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");

        while(sc.hasNext()) {
            String inputString = sc.nextLine();
            String inputCommand  = inputString.split(" ")[0];
            if (inputCommand.equals("bye")) {
                System.out.println("___________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________________________");
                break;
            } else if (inputCommand.equals("list")) {
                System.out.println("__________________________________");
                System.out.println("Here are the tasks in your list:");
                if(taskList.size() == 0) {
                    System.out.println("List is Empty");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + "." + taskList.get(i));
                    }
                }
                System.out.println("___________________________________");
            } else if (inputCommand.equals("done")) {
                String[] taskNumString = inputString.split(" ");
                int taskNum = Integer.parseInt(taskNumString[1]);
                Task currentTask = taskList.get(taskNum - 1);
                currentTask.markAsDone();
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(currentTask);
                System.out.println("___________________________________");
            }
            else {
                System.out.println("___________________________________");
                System.out.println("added: " + inputString);
                System.out.println("___________________________________");
                Task newTask = new Task(inputString);
                taskList.add(newTask);
            }
        }
    }
}
