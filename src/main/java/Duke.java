import java.util.*;

public class Duke {
    private static Integer totalTask = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        List<String> availableCommands = Arrays.asList("bye", "list", "done", "todo", "event", "deadline");

        greet();

        while(scanner.hasNext()) {
            String command = scanner.nextLine();

            try {
                if (!availableCommands.contains(command.split(" ")[0])) {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                } else if (command.equals("bye")) {
                    exit();
                    break;
                } else if (command.equals("list")) {
                    printList(taskManager);
                } else if (command.startsWith("done")) {
                    String[] words = command.split("\\s");
                    int index = Integer.parseInt(words[1]);
                    doneTask(taskManager, index);
                } else {
                    totalTask++;
                    addTask(taskManager, command);
                }
            } catch (DukeException e) {
                horizontalLine();
                System.out.println(e.getMessage());
                horizontalLine();
                System.out.println();
            }

        }

    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
        System.out.println();
    }

    public static void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void addTask(TaskManager taskManager, String command) throws DukeException{
        horizontalLine();
        List<String> commandList = new ArrayList<>(Arrays.asList(command.split(" ")));
        String stringHolder = (commandList.remove(0));
        List<String> listHolder = new ArrayList<>(commandList);
        if (stringHolder.startsWith("todo")) {
            stringHolder = (String.join(" ", commandList));
            if (!stringHolder.isEmpty()) {
                taskManager.addTask(new ToDo(stringHolder));
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][\u2718] " + stringHolder);
                System.out.println("Now you have " + totalTask + " tasks in the list.");
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else if (stringHolder.startsWith("deadline")) {
            stringHolder = commandList.remove(0);
            listHolder.remove(0);
            for (String i : listHolder) {
                if (i.equals("/by")) {
                    commandList.remove(0);
                    break;
                } else {
                    stringHolder = stringHolder + " " + commandList.remove(0);
                }
            }

            String date = commandList.remove(0);
            listHolder.clear();
            listHolder.addAll(commandList);
            for (String i : listHolder) {
                date = date + " " + commandList.remove(0);
            }

            taskManager.addTask(new Deadline(stringHolder, date));

            System.out.println("Got it. I've added this task:");
            System.out.println("  [D][\u2718] " + stringHolder + " (by: " + date + ")");
            System.out.println("Now you have " + totalTask + " tasks in the list.");
        } else {
            stringHolder = commandList.remove(0);
            listHolder.remove(0);
            for (String i : listHolder) {
                if (i.equals("/at")) {
                    commandList.remove(0);
                    break;
                } else {
                    stringHolder = stringHolder + " " + commandList.remove(0);
                }
            }

            String date = commandList.remove(0);
            listHolder.clear();
            listHolder.addAll(commandList);
            for (String i : listHolder) {
                date = date + " " + commandList.remove(0);
            }

            taskManager.addTask(new Event(stringHolder, date));
            System.out.println("Got it. I've added this task:");
            System.out.println("  [E][\u2718] " + stringHolder + " (at: " + date + ")");
            System.out.println("Now you have " + totalTask + " tasks in the list.");
        }
        horizontalLine();
        System.out.println();
    }

    public static void printList(TaskManager taskManager) {
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + ".[" + taskManager.getTask(i).getType() + "]"+ "[" + taskManager.getTask(i).getStatusIcon() + "] " + taskManager.getTask(i).getDescription() + taskManager.getTask(i).getDate());
        }
        horizontalLine();
        System.out.println();
    }

    public static void doneTask(TaskManager taskManager, int index) {
        index = index - 1;
        taskManager.doneTask(index);
        horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskManager.getTask(index).getStatusIcon() + "] " + taskManager.getTask(index).getDescription() + taskManager.getTask(index).getDate());
        horizontalLine();
        System.out.println();
    }

}
