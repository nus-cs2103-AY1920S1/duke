import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum TaskType {todo, deadline, event; }
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
                    if (taskList.size() > 0) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            StringBuilder sb = new StringBuilder();
                            Task t = taskList.get(i);
                            sb.append(i + 1).append(" ").append(t);
                            System.out.println(sb);
                        }
                    } else {
                        System.out.println("There are no tasks in your list.");
                    }
                    break;
                default:
                    if (s.indexOf("done") == 0) {//Set task to done
                        try {
                            int index = Integer.parseInt(s.replace("done", "").trim());
                            index--;//0 Bounded
                            Task t = taskList.get(index);
                            t.markAsDone(true);
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("  " + t);
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The index is invalid.");
                        }
                    } else if (s.indexOf("todo") == 0) {
                        insertByCommand(taskList, TaskType.todo, s);
                    } else if (s.indexOf("event") == 0) {
                        insertByCommand(taskList, TaskType.event, s);
                    } else if (s.indexOf("deadline") == 0) {
                        insertByCommand(taskList, TaskType.deadline, s);
                    } else {
                        //taskList.add(new Task(s));
                        //System.out.println("added: " + s);
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void insertByCommand(List<Task> taskList, TaskType taskType, String line) {
        String data = line.replaceFirst("^.*?\\s","");
        //If no change, then it's either empty, or invalid command
        if (line.equals(data)) {
            if (line.equals("todo") || line.equals("event") || line.equals("deadline")) {
                data = "";
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return;
            }
        }
        String[] splitData;
        switch (taskType) {
            case todo:
                if (Task.validateData(data, "todo"))
                    insertNewTask(taskList, new Todo(data));
                break;
            case event:
                splitData = TaskWithDate.extractDataFromLine(data, " /at ");
                if (TaskWithDate.validateData(splitData, "event"))
                    insertNewTask(taskList, new Event(splitData[0], splitData[1]));
                break;
            case deadline:
                splitData = TaskWithDate.extractDataFromLine(data, " /by ");
                if (TaskWithDate.validateData(splitData, "deadline"))
                insertNewTask(taskList, new Deadline(splitData[0], splitData[1]));
                break;
        }
    }

    public static void insertNewTask(List<Task> taskList, Task taskToInsert) {
        if(taskToInsert != null) {
            System.out.println("Got it. I've added this task: ");
            taskList.add(taskToInsert);
            System.out.println("  " + taskToInsert.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
}
