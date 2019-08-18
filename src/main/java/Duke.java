import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        String separationLine = "    ____________________________________________________________";
        System.out.println(separationLine + "\n" + logo + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + separationLine + "\n");

        ArrayList<Task> taskStore = new ArrayList<>(100);
        String userInput = scan.nextLine();
        while(!"bye".equals(userInput)) {
            switch (userInput.split(" ")[0]) {
                case "list":
                    System.out.println(separationLine + "\n     Here are the tasks in your list:");
                    for (Task task : taskStore) {
                        System.out.println("     " + (taskStore.indexOf(task) + 1) + "." + task.toString());
                    }
                    System.out.println(separationLine + "\n");
                    break;
                case "done":
                    Task doneTask = taskStore.get(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    doneTask.setDone();
                    System.out.println(separationLine + "\n     Nice! I've marked this task as done:\n       "
                            + doneTask + "\n" + separationLine);
                    break;
                default:
                    taskStore.add(new Task(userInput));
                    System.out.println(separationLine + "\n     added: " + userInput + "\n" + separationLine + "\n");
                    break;
            }
            userInput = scan.nextLine();
        }
        System.out.println(separationLine + "\n     Bye. Hope to see you again soon!\n" + separationLine);
    }
}
