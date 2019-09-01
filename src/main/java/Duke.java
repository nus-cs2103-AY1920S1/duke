import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke duke = new Duke();
//        duke.level1();
//        duke.level2();
//        duke.level3();
//        duke.level4();
        duke.level6();
    }

    private void level1() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.next();
            if (s == null) {
                return;
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println(s);
            }
        }
    }

    private void level2() {
        List<String> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s == null) {
                return;
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (s.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + "." + taskList.get(i));
                }
            } else {
                taskList.add(s);
                System.out.println("added: " + s);
            }
        }
    }

    private void level3() {
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s == null) {
                return;
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    System.out.println(i + 1 + "." + "[" + t.getStatus() + "]" + t.getDesc());
                }
            } else if (s.contains("done")) {
                int index = Integer.parseInt(s.substring(5));
                if (index < 1 || index >= taskList.size()) {
                    throw new ArrayIndexOutOfBoundsException("Out of range, the task does not exist");
                } else {
                    System.out.println("Nice! I've marked this task as done: ");
                    taskList.get(index - 1).markAsDone();
                    System.out.println("[" + "\u2713" + "] " + taskList.get(index - 1).getDesc());
                }
            } else {
                taskList.add(new Task(s));
                System.out.println("added: " + s);
            }
        }
    }

    private void level4() {
        Task[] tasks = new Task[100];
        Scanner sc = new Scanner(System.in);
        int taskIndex = 0;
        while (true) {
            String s = sc.nextLine();
            if (s == null) {
                return;
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    Task t = tasks[i];
                    System.out.println(i + 1 + "." + t.toString());
                }
            } else if (s.contains("done")) {
                int index = Integer.parseInt(s.substring(5));
                if (index < 1 || index >= tasks.length) {
                    throw new ArrayIndexOutOfBoundsException("Out of range, the task does not exist");
                } else {
                    System.out.println("Nice! I've marked this task as done: ");
                    tasks[index - 1].markAsDone();
                    System.out.println(tasks[index - 1].toString());
                }
            } else if (s.contains("todo")) {
                if (s.substring(4).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    return;
                }
                Task toDo = new ToDo(s.substring(5));
                tasks[taskIndex] = toDo;
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                taskIndex++;
                System.out.println("Now you have " + taskIndex + ((taskIndex == 1) ? " task in the list" : " tasks in the list."));
            } else if (s.contains("event")) {
                if (s.substring(5).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    return;
                }
                int dateStartIndex = s.indexOf("/at") + 4;
                String event = s.substring(dateStartIndex);
                Task toDo = new Event(s.substring(6, dateStartIndex - 4), event);
                tasks[taskIndex] = toDo;
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                taskIndex++;
                System.out.println("Now you have " + taskIndex + ((taskIndex == 1) ? " task in the list" : " tasks in the list."));
            } else if (s.contains("deadline")) {
                if (s.substring(8).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    return;
                }
                int dateStartIndex = s.indexOf("/by") + 4;
                String deadline = s.substring(dateStartIndex);
                Task toDo = new Deadline(s.substring(9, dateStartIndex - 4), deadline);
                tasks[taskIndex] = toDo;
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                taskIndex++;
                System.out.println("Now you have " + taskIndex + ((taskIndex == 1) ? " task in the list" : " tasks in the list."));
            } else {
                printInvalidCommand();
                return;
            }
        }
    }

    private void level6() {
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s == null) {
                return;
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    System.out.println(i + 1 + "." + t.toString());
                }
            } else if (s.contains("done")) {
                int index;
                try {
                    index = Integer.parseInt(s.substring(5));
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! You have entered an invalid number");
                    continue;
                }
                if (index < 1 || index > taskList.size()) {
                    System.out.println("☹ OOPS!!! Out of range, the task does not exist");
                    continue;
                } else {
                    System.out.println("Nice! I've marked this task as done: ");
                    taskList.get(index - 1).markAsDone();
                    System.out.println(taskList.get(index - 1).toString());
                }
            } else if (s.contains("todo")) {
                if (s.substring(4).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                Task toDo = new ToDo(s.substring(5));
                taskList.add(toDo);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                System.out.println("Now you have " + taskList.size() + ((taskList.size() == 1) ? " task in the list" : " tasks in the list."));
            } else if (s.contains("event")) {
                if (s.substring(5).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    continue;
                }
                int dateStartIndex = s.indexOf("/at") + 4;
                String event = s.substring(dateStartIndex);
                Task toDo = new Event(s.substring(6, dateStartIndex - 4), event);
                taskList.add(toDo);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                System.out.println("Now you have " + taskList.size() + ((taskList.size() == 1) ? " task in the list" : " tasks in the list."));
            } else if (s.contains("deadline")) {
                if (s.substring(8).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }
                int dateStartIndex = s.indexOf("/by") + 4;
                String deadline = s.substring(dateStartIndex);
                Task toDo = new Deadline(s.substring(9, dateStartIndex - 4), deadline);
                taskList.add(toDo);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + toDo.toString());
                System.out.println("Now you have " + taskList.size() + ((taskList.size() == 1) ? " task in the list" : " tasks in the list."));
            } else if (s.contains("delete")) {
                if (s.substring(6).isEmpty()) {
                    System.out.println("☹ OOPS!!! You cannot delete an empty entry.");
                    continue;
                }
                Task task;
                try {
                    task = taskList.get(Integer.parseInt(s.substring(7)) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! You have entered an invalid number");
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Out of range, the task does not exist");
                    continue;
                }
                taskList.remove(task);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(task.toString());
                System.out.println("Now you have " + taskList.size() + ((taskList.size() == 1) ? " task in the list" : " tasks in the list."));
            } else {
                printInvalidCommand();
            }
        }
    }
    
    private void printInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

class Task {
    private String desc;
    private Boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return ("[" + getStatus() + "]" + " " + getDesc());
    }

}

class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

class Deadline extends Task {
    private String ddl;

    public Deadline(String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl + ")";
    }

}

class Event extends Task {
    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

}

