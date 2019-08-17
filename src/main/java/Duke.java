import java.util.Scanner;

public class Duke {
    private static void cout(String str){
        System.out.println("    ____________________________________________________________\n     " +
                str.replaceAll("\n", "\n     ") +
                "\n    ____________________________________________________________\n");
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        cout("Hello! I'm Duke\nWhat can I do for you?");

        while(true){
            String instr = in.nextLine().trim();
            if(instr.equals("bye"))
                break;
            cout(instr);
        }

        cout("Bye. Hope to see you again soon!");
    }
}
