import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> ls = new ArrayList<>();

    public Duke() {
        this.ls.add(null);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Duke d = new Duke();
        d.cout("Hello! I'm Duke\nWhat can I do for you?");
        d.run(in);
        d.cout("Bye. Hope to see you again soon!");
    }

    private void cout(String str) {
        if (!str.endsWith("\n"))
            str += '\n';
        System.out.println("    ____________________________________________________________\n     " +
                str.replaceAll("\n(?!$)", "\n     ") +
                "    ____________________________________________________________\n");
    }

    private void run(Scanner in) {
        while (true) {
            String instr = in.nextLine().trim();
            switch (instr) {
                case "bye":
                    return;
                case "list":
                    StringBuilder sb = new StringBuilder();
                    sb.append("Here are the tasks in your list:\n");
                    for (int i = 1; i < this.ls.size(); i++) {
                        sb.append(i)
                                .append(".")
                                .append(this.ls.get(i));
                    }
                    sb.setLength(sb.length() - 1);
                    this.cout(sb.toString());
                    break;
                default:
                    Task t = null;
                    if (instr.startsWith("deadline ")) {
                        String[] temp = instr.substring(9).split(" /by ");
                        if(temp.length == 2)
                            t = new Deadline(temp[0], temp[1]);
                    } else if (instr.startsWith("event ")) {
                        String[] temp = instr.substring(6).split(" /at ");
                        if(temp.length == 2)
                            t = new Event(temp[0], temp[1]);
                    } else if (instr.startsWith("todo ")) {
                        t = new Todo(instr.substring(5));
                    }
                    if (t != null) {
                        this.ls.add(t);
                        this.cout("Got it. I've added this task:\n  " +
                                t +
                                "Now you have " + (this.ls.size() - 1) + " tasks in the list.");
                    }

                    if (instr.startsWith("done ")) {
                        t = this.ls.get(Integer.parseInt(instr.substring(5)));
                        t.setDone();
                        this.cout("Nice! I've marked this task as done:\n" + t);
                    }
                    break;
            }
        }
    }
}