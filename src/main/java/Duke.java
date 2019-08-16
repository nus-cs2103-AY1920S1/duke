import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum TaskType { TODO, DEADLINE, EVENT; }
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        String s;
        whileloop: while (true) {
            s = sc.nextLine();
            switch (s) {
                case "bye"://exit
                    break whileloop;
                case "list"://list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        StringBuilder sb = new StringBuilder();
                        Task t = taskList.get(i);
                        sb.append(i+1).append(". ").append(t);
                        System.out.println(sb);
                    }
                    break;
                default:
                    if (s.indexOf("done ") == 0) {//Set task to done
                        try {
                            int index = Integer.parseInt(s.replace("done ", ""));
                            index--;//0 Bounded
                            Task t = taskList.get(index);
                            t.markAsDone(true);
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(t.getStatusWithDescription());
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println(e);
                        }
                    } else if (s.indexOf("todo ") == 0) {
                        insertByCommand(taskList, TaskType.TODO, s);
                    } else if (s.indexOf("event ") == 0) {
                        insertByCommand(taskList, TaskType.EVENT, s);
                    } else if (s.indexOf("deadline ") == 0) {
                        insertByCommand(taskList, TaskType.DEADLINE, s);
                    } else {//Add item
                        taskList.add(new Task(s));
                        System.out.println("added: " + s);
                    }
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void insertByCommand(List<Task> taskList, TaskType taskType, String line) {
        String data = line.replaceFirst("^.*?\\s","");
        switch (taskType) {
            case TODO:
                insertNewTask(taskList, new Todo(data));
                break;
            case EVENT:
                insertNewTask(taskList, new Event(data));
                break;
            case DEADLINE:
                insertNewTask(taskList, new Deadline(data));
                break;
        }
    }

    public static void insertNewTask(List<Task> taskList, Task taskToInsert) {
        System.out.println("Got it. I've added this task: ");
        taskList.add(taskToInsert);
        System.out.println("  " + taskToInsert.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
