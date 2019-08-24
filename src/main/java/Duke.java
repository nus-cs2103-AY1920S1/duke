import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> ls = new ArrayList<>();

    public Duke() {
        this.ls.add(null);
    }

    public void addTask(Task t){
        this.ls.add(t);
    }

    public void rewriteFile(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < this.ls.size(); i++){
            sb.append(this.ls.get(i).toFileString())
                    .append((char) 30);
        }
        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);
        try{
            FileWriter fw = new FileWriter("./data/duke.txt", false);
            fw.write(sb.toString());
            fw.close();
        }catch(Exception e){
            this.cout("Oops. Something went wrong with file writing.");
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        try {
            for(String cell : new BufferedReader(new FileReader("./data/duke.txt")).readLine().split("\\x1e")){
                d.addTask(Task.parseFileTask(cell));
            }
        }catch(Exception e){
            d.cout("duke.txt not found.\nIt will be automatically created once you have a list.");
        };
        Scanner in = new Scanner(System.in);
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
                        continue;
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
                rewriteFile();
            } catch (DukeException e) {
                this.cout(e.getMessage());
            }
        }
    }
}