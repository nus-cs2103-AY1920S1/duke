import java.util.Scanner;

public class Duke {

    private static final String DUKE_HELLO = "Hello! I'm Duke\n    What can I do for you?\n";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!\n";
    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:";
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:\n";
    private static final String DUKE_DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    private static final String DUKE_LINE = "    ____________________________________________________________\n";
    private static final String DUKE_TAB4 = "    ";
    private static final String DUKE_TAB2 = "  ";
    protected TaskList taskList;

    protected Duke() {
        this.taskList = new TaskList();
    }

    private void listTasks() throws DukeIllegalIndexException {
        System.out.println(DUKE_TAB4 + DUKE_LIST_TASKS);
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            System.out.println(String.format("%s%d.%s",
                                             DUKE_TAB4,
                                             i,
                                             taskList.getTaskAt(i).getStatus()));
        }
    }

    private void addTask(Task task) {
        System.out.println(DUKE_TAB4 + DUKE_ADD_TASK
                           + DUKE_TAB4 + DUKE_TAB2 + taskList.addTask(task).getStatus()
                           + String.format("\n" + DUKE_TAB4 + DUKE_NUMBER_OF_TASKS,
                                   taskList.getSize()));
    }

    protected void run() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        // Greet the user
        System.out.print(DUKE_LINE
                           + DUKE_TAB4 + DUKE_HELLO
                           + DUKE_LINE);

        // Handle user input
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                System.out.print(DUKE_LINE);
                if (command.equals("list")) {
                    // List all the commands entered by user
                    this.listTasks();
                } else if (command.startsWith("done")) {
                    // Mark Task at index specified as done
                    try {
                        int index = Integer.parseInt(command.substring(5));
                        taskList.markAsDoneTaskAt(index);
                        System.out.println(DUKE_TAB4 + DUKE_MARK_AS_DONE
                                + DUKE_TAB4 + DUKE_TAB2
                                + taskList.getTaskAt(index).getStatus());
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeIllegalArgumentException("☹ OOPS!!! You must include an index.");
                    }
                } else if (command.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(command.substring(7));
                        System.out.println(DUKE_TAB4 + DUKE_DELETE_TASK
                                + DUKE_TAB4 + DUKE_TAB2 + taskList.deleteTaskAt(index).getStatus()
                                + String.format("\n" + DUKE_TAB4 + DUKE_NUMBER_OF_TASKS,
                                taskList.getSize()));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeIllegalArgumentException("☹ OOPS!!! You must include an index.");
                    }
                } else if (command.startsWith("todo")) {
                    try {
                        command = command.substring(5);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeIllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    this.addTask(new TodoTask(command));
                } else if (command.startsWith("deadline")) {
                    try {
                        String[] cmd = command.substring(9).split(" /by ");

                        if (cmd.length == 1) {
                            // If deadline task does not have a due date
                            throw new DukeIllegalArgumentException("☹ OOPS!!! Deadline dates must be "
                                                                   + "specified with /by.");
                        }
                        if (cmd[0].trim().length() == 0) {
                            // If description of deadline is empty
                            throw new DukeIllegalArgumentException("☹ OOPS!!! The description of a deadline "
                                    + "cannot be empty.");
                        }
                        this.addTask(new DeadlineTask(cmd[0], cmd[1]));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeIllegalArgumentException("☹ OOPS!!! The description of a deadline "
                                + "cannot be empty.");
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String[] cmd = command.substring(6).split(" /at ");
                        if (cmd.length == 1) {
                            // If deadline task does not have a due date
                            throw new DukeIllegalArgumentException("☹ OOPS!!! Event dates must be "
                                                                   + "specified with /at.");
                        }
                        if (cmd[0].trim().length() == 0) {
                            // If description of deadline is empty
                            throw new DukeIllegalArgumentException("☹ OOPS!!! The description of an event "
                                    + "cannot be empty.");
                        }
                        this.addTask(new EventTask(cmd[0], cmd[1]));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeIllegalArgumentException("☹ OOPS!!! The description of an event "
                                + "cannot be empty.");
                    }
                } else {
                    throw new DukeIllegalCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeIllegalIndexException | DukeIllegalCommandException | DukeIllegalArgumentException e) {
                System.out.println(DUKE_TAB4 + e);
            } finally {
                System.out.print(DUKE_LINE);
            }
        }

        // Greet the user and quit program
        System.out.println(DUKE_LINE
                           + DUKE_TAB4 + DUKE_BYE
                           + DUKE_LINE);
    }
    
}