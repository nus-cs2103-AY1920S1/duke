import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            try {
                String instr = in.nextLine().trim();
                switch (instr) {
                    case "bye":
                        return;
                    case "list":
                        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
                        for (int i = 1; i < this.ls.size(); i++) {
                            sb.append(i)
                                    .append(".")
                                    .append(this.ls.get(i));
                        }
                        this.cout(sb.toString());
                        break;
                    default:
                        Task t = Task.parseTask(instr);
                        if (t != null) {
                            this.ls.add(t);
                            this.cout("Got it. I've added this task:\n  " +
                                    t +
                                    "Now you have " + (this.ls.size() - 1) + " tasks in the list.");
                            break;
                        }
                        if (instr.startsWith("done")) {
                            try {
                                int i = Integer.parseInt(instr.substring(4).trim());
                                if (i <= 0 || i > (this.ls.size() - 1))
                                    throw new IndexOutOfBoundsDukeException();
                                t = this.ls.get(i);
                            } catch (NumberFormatException e) {
                                throw new IndexFormatDukeException();
                            }
                            t.setDone();
                            this.cout("Nice! I've marked this task as done:\n" + t);
                            break;
                        }
                        if (instr.startsWith("delete")) {
                            try {
                                int i = Integer.parseInt(instr.substring(6).trim());
                                if (i <= 0 || i > (this.ls.size() - 1))
                                    throw new IndexOutOfBoundsDukeException();
                                t = this.ls.remove(i);
                            } catch (NumberFormatException e) {
                                throw new IndexFormatDukeException();
                            }
                            this.cout("Noted. I've removed this task:\n  " +
                                    t +
                                    "Now you have " + (this.ls.size() - 1) + " tasks in the list.");
                            break;
                        }
                        throw new InvalidCommandDukeException();
                }
            } catch (DukeException e) {
                this.cout(e.getMessage());
            }
        }
    }
}