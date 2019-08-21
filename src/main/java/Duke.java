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

    private Task createTask(String str, String command) { //command is either todo, deadline or event
        String[] splitStr;
        if (command.equals("todo")) {
            return new ToDo(str);
        } else if (command.equals("deadline")) {
            splitStr = str.split("by");
            return new Deadlines(splitStr[0].trim(), splitStr[1].trim());
        } else if (command.equals("event")) {
            splitStr = str.split("at");
            return new Events(splitStr[0].trim(), splitStr[1].trim());
        } else {
            return null;
        }
    }

    private String addTask(Task task) {
        taskList.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        return sb.toString();
    }

    private String markTask(int taskNum) { //mark as done
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        return sb.toString();
    }

    void executeCommand(String command, String[] inputSplit){
        if (command.equals("list")) {
            System.out.println(lineWrap(getList()));
        } else if (command.equals("done")) {
            int taskNumber = Integer.parseInt(inputSplit[1]);
            System.out.println(lineWrap(markTask(taskNumber)));
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task task = createTask(inputSplit[1], command);
            System.out.println(lineWrap(addTask(task)));
        }
    }

    void start() {
        System.out.println(greet());
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0];
            if (command.equals("bye")) {
                System.out.println(bye());
                break;
            } else {
                executeCommand(command, inputSplit);
            }
        }
    }

}