import java.util.ArrayList;

public class Action {
    public static void welcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void byeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void doTask(String command, ArrayList<Task> list) {
        if (command.equals("list")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("     " + i + "." + list.get(i - 1));
            }
            System.out.println("    ____________________________________________________________\n");
        } else {
            String[] commandStrArray = command.split(" ", 2);
            if (commandStrArray[0].equals("done")) {
                list.get(Integer.parseInt(commandStrArray[1]) - 1).markAsDone();
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task: ");
                if (commandStrArray[0].equals("todo")) {
                    Task task = new ToDo(commandStrArray[1]);
                    System.out.println("       " + task);
                    list.add(task);
                } else if (commandStrArray[0].equals("deadline")) {
                    String[] descriptionStrArray = commandStrArray[1].split("/by");
                    Task task = new Deadline(descriptionStrArray[0], descriptionStrArray[1]);
                    System.out.println("       " + task);
                    list.add(task);
                } else {
                    String[] descriptionStrArray = commandStrArray[1].split("/at");
                    Task task = new Event(descriptionStrArray[0], descriptionStrArray[1]);
                    System.out.println("       " + task);
                    list.add(task);
                }
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }
}
