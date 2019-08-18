import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> ls = new ArrayList<>();

    public Duke(){
        this.ls.add("");
    }

    private void cout(String str){
        System.out.println("    ____________________________________________________________\n     " +
                str.replaceAll("\n", "\n     ") +
                "\n    ____________________________________________________________\n");
    }

    private void run(Scanner in){
        while(true){
            String instr = in.nextLine().trim();
            switch(instr) {
                case "bye":
                    return;
                case "list":
                    StringBuilder sb = new StringBuilder();
                    for(int i = 1; i < this.ls.size(); i++){
                        sb.append(i)
                                .append(". ")
                                .append(this.ls.get(i))
                                .append('\n');
                    }
                    sb.setLength(sb.length() - 1);
                    cout(sb.toString());
                    break;
                default:
                    this.ls.add(instr);
                    cout("added: " + instr);
                    break;
            }
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Duke d = new Duke();
        d.cout("Hello! I'm Duke\nWhat can I do for you?");
        d.run(in);
        d.cout("Bye. Hope to see you again soon!");
    }
}