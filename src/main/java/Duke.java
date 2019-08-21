import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks;

    public static void printGreeting() {
        String greet_msg = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(greet_msg);
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int list_num = i + 1;
            Task task = tasks.get(i);
            if (task.getType().equals("todo")) {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString());
            } else if (task.getType().equals("event")) {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString() + " (at: " + task.getDate() + ")");
            } else {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString() + " (by: " + task.getDate() + ")");
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printDone(String task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       [" + '+' + "] " + task + '\n' +
                "    ____________________________________________________________\n");
    }

    public static void printTodo(Todo t) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [T]" + "[ ]" + ' ' + t.toString() + '\n' +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void printDeadline(Deadline d) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [D][ ] " + d.toString() + " (by: " + d.getDate() + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void printEvent(Event e) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [E][ ] " + e.toString() + " (at: " + e.getDate() + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void printDelete(Task t) {
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: ");
        if (t.getType().equals("todo")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString());
        } else if (t.getType().equals("event")) {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString() + " (at: " + t.getDate() + ")");
        } else {
            System.out.println("       " + t.getTypeIcon() + '[' + t.getStatusIcon() + "] " + t.toString() + " (by: " + t.getDate() + ")");
        }
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) throws DukeException {
            printGreeting();
            tasks = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String user_input = sc.nextLine();
                String[] input_string = user_input.split(" ");
                if (input_string[0].equals("bye")) {
                    printBye();
                    System.exit(0);
                } else if (input_string[0].equals("list")) {
                    try {
                        if (input_string.length > 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The list command should not be followed by other text!\n" +
                                    "    ____________________________________________________________\n");
                        } else {
                            printList();
                        }
                    } catch(DukeException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else if (input_string[0].equals("done")) {
                    try {
                        if (input_string.length > 1) {
                            int task_num = Integer.parseInt(input_string[1]);
                            int total_tasks = tasks.size();
                            if (task_num < 1 || task_num > total_tasks) {
                                throw new DukeException("    ____________________________________________________________\n" +
                                        "     ☹ OOPS!!! The number provided is not within the range of the list.\n" +
                                        "    ____________________________________________________________\n");
                            } else {
                                Task task = tasks.get(task_num - 1);
                                boolean isDone = task.getStatus();
                                if (isDone) {
                                    throw new DukeException("    ____________________________________________________________\n" +
                                            "     ☹ OOPS!!! The task has already been marked as completed.\n" +
                                            "    ____________________________________________________________\n");
                                } else {
                                    task.setDone();
                                    printDone(task.toString());
                                }
                            }
                        } else {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Please specify the completed task's number.\n" +
                                    "    ____________________________________________________________\n");
                        }
                    } catch(DukeException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else if (input_string[0].equals("todo")) {
                    try {
                        if (input_string.length > 1) {
                            String task_name = ((user_input.split(" ", 2))[1]).strip();
                            Todo t = new Todo(task_name);
                            tasks.add(t);
                            printTodo(t);
                        } else {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                    "    ____________________________________________________________\n");
                        }
                    } catch(DukeException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else if (input_string[0].equals("deadline")) {
                    String[] separate_task_date = user_input.split("/");
                    String task_name = ((separate_task_date[0].split(" ", 2))[1]).strip();
                    String date = ((separate_task_date[1].split(" ", 2))[1]).strip();
                    Deadline d = new Deadline(task_name, date);
                    tasks.add(d);
                    printDeadline(d);
                } else if (input_string[0].equals("event")) {
                    String[] separate_task_date = user_input.split("/");
                    String task_name = ((separate_task_date[0].split(" ", 2))[1]).strip();
                    String date = ((separate_task_date[1].split(" ", 2))[1]).strip();
                    Event e = new Event(task_name, date);
                    tasks.add(e);
                    printEvent(e);
                } else if (input_string[0].equals("delete")) {
                    int task_num = Integer.parseInt(input_string[1]);
                    int new_total_tasks = tasks.size() - 1;
                    Task t = tasks.get(task_num - 1);
                    tasks.remove(task_num - 1);
                    printDelete(t);
                } else {
                    try {
                        throw new DukeException("    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "    ____________________________________________________________\n");
                    } catch(DukeException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
    }
}
