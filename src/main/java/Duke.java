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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i).toString());
            if (i != taskList.size() - 1) { //last item
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String markTask(int taskNum) {
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        return sb.toString();
    }

    void start() {
        System.out.println(greet());
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] eachWord = input.split(" ");
            String command = eachWord[0];
            if (command.equals("bye")) {
                System.out.println(bye());
                break;
            } else if (command.equals("list")) {
                System.out.println(lineWrap(getList()));
            } else if (command.equals("done")) {
                int taskNumber = Integer.parseInt(eachWord[1]);
                System.out.println(lineWrap(markTask(taskNumber)));
            } else {
                Task t = new Task(input);
                taskList.add(t);
                System.out.println(lineWrap("added: " + t.toString()));
            }
        }
    }

}
