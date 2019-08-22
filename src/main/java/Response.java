import java.util.ArrayList;

public class Response {
    static ArrayList<String> commandList = new ArrayList<>();
    private String command;

    Response(String command) {
        this.command = command;
    }

    String line = "    ____________________________________________________________\n";

    public static void enlist(String s) { //add to list method
        if (!s.equals("list")) commandList.add(s);
    }

    @Override
    public String toString() {
        if (command.equals("bye")) {
            return line + "     Bye. Hope to see you again soon!\n" + line;
        } else if (command.equals("list")) {
            String str = line;
            for (int i = 1; i < commandList.size() + 1; i++) {
                str += "    " + i + ". " + commandList.get(i-1) + "\n";
            }
            str += line;
            return str;
        } else {
            return String.format(line
                    + "     added: %s\n%s", command, line);
        }
    }
}