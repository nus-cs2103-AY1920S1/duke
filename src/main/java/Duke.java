import java.util.Scanner;

public class Duke {
    void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    static void greeting(){
        String output = "    ____________________________________________________________\n" +
        "     Hello! I'm Duke\n" + 
        "     What can I do for you?\n" +
        "    ____________________________________________________________\n";
        System.out.println(output);
    }
    static String addDoubleLine(String str){
        String line = "    ____________________________________________________________";
        String newstr = "     " + str;
        return line + "\n" + newstr + "\n" + line;
    }
    static void handleInput(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String check = sc.nextLine();
            if (check.equals("bye")) {
                System.out.println(
                    addDoubleLine(
                    "Bye. Hope to see you again soon!"
                    )
                );
                System.exit(0);
            }else{
                System.out.println(addDoubleLine(check));
            }
        }
    }
    public static void main(String[] args) {
        greeting();
        handleInput();
    }
}
