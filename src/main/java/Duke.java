import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private List<Task> taskList = new ArrayList<>();

    String lineWrap(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(input + "\n");
        sb.append("____________________________________________________________\n");
        return sb.toString();
    }

    private String greet() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    private String bye() {
        return lineWrap("Bye. Hope to see you again soon!");
    }

    private String getList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i).toString());
            if (i != taskList.size() - 1) { //last item
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private Task createTask(String str, String command) throws DukeException { //command is either todo, deadline or event
        String[] splitStr;
        if (command.equals("todo")) {
            return new ToDo(str);
        } else if (command.equals("deadline")) {
            splitStr = str.split("/by");
            if (splitStr.length == 1) {
                throw new DukeException("Invalid format. Please use 'by:' to state your deadline");
            }
            return new Deadlines(splitStr[0].trim(), splitStr[1].trim());
        } else if (command.equals("event")) {
            splitStr = str.split("/at");
            if (splitStr.length == 1) {
                throw new DukeException("Invalid format. Please use 'at:' to state your deadline");
            }
            return new Events(splitStr[0].trim(), splitStr[1].trim());
        } else {
            return null;
        }
    }

    private String deleteTask(int taskNum) {
        StringBuilder sb = new StringBuilder();
        Task t = taskList.get(taskNum - 1);
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + t + "\n");
        taskList.remove(taskNum - 1);
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        return sb.toString();
    }

    private String addTask(Task task) {
        taskList.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        return sb.toString();
    }

    private String markTask(int taskNum) throws DukeException { //mark as done. Need to check if already done, and if so throw exception
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        return sb.toString();
    }

    private int parseTaskInt(String str) throws DukeException {
        int taskInt;
        try {
            taskInt = Integer.parseInt(str);
            if (taskInt > taskList.size() || taskInt < 0) {
                throw new DukeException("That task number does not exist, please try again");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please state a valid task number");
        }

        return taskInt;
    }

    void executeCommand(String command, String[] inputSplit) throws DukeException {
        if (command.equals("list")) {
            System.out.println(lineWrap(getList()));
        } else if (command.equals("done")) {
            int taskInt = parseTaskInt(inputSplit[1]);
            System.out.println(lineWrap(markTask(taskInt)));
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (inputSplit.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            }
            Task task = createTask(inputSplit[1], command);
            System.out.println(lineWrap(addTask(task)));
        } else if (command.equals("delete")) {
            int taskInt = parseTaskInt(inputSplit[1]);
            System.out.println(lineWrap(deleteTask(taskInt)));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    void start() {
        System.out.println(greet());
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ", 2); //split into command and remaining sentence
            String command = inputSplit[0];

            try {
                if (command.equals("bye")) {
                    System.out.println(bye());
                    break;
                } else {
                    executeCommand(command, inputSplit);
                }
            } catch (DukeException e) {
                System.out.println(lineWrap(e.getMessage()));
            }
        }
    }
}