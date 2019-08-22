import java.util.ArrayList;

public class Response {
    static ArrayList<Task> commandList = new ArrayList<>();
    private String command;

    Response(String command) {
        this.command = command;
    }

    String line = "    ____________________________________________________________\n";

    public static void enlist(String s) { //add to list method
        String words[] = s.split(" ");
        if (words.length < 2) {
            if (!words[0].equals("list")) commandList.add(new Task(s));
        } else {
            if (!words[0].equals("list") && !words[0].equals("done")) commandList.add(new Task(s));
        }
    }

    @Override
    public String toString() {
        String words[] = command.split(" ");
        if (command.equals("bye")) {
            return line + "     Bye. Hope to see you again soon!\n" + line;
        } else if (command.equals("list")) {
            String str = line + "     Here are the tasks in your list:\n";
            for (int i = 0; i < commandList.size(); i++) {
                String num = "" + (i + 1);
                str += "     " + num + ".[" + commandList.get(i).getStatusIcon() + "] "
                              + commandList.get(i).toString() + "\n";
            }
            str += line;
            return str;
        } else if (words.length > 1 && words[0].equals("done")) {
            int taskNum = Integer.parseInt(words[1]);
            commandList.get(taskNum-1).markAsDone();
            return line + "     Nice! I've marked this task as done:\n"
                        + "     [" + commandList.get(taskNum-1).getStatusIcon() + "]"
                        + commandList.get(taskNum-1).toString() +"\n" + line;
        } else {
            return String.format(line
                    + "     added: %s\n%s", command, line);
        }
    }
}