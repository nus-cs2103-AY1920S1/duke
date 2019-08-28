import java.util.Scanner;
import java.util.ArrayList; 

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
        return line + "\n" + str + "\n" + line;
    }
    static void handleList(ArrayList<Task> ls){
        String output = ""; 
        for(int i = 0; i < ls.size(); i++){
            output = output + "     " +  (i+1) + ": " + ls.get(i).getListItem();
            if(i < ls.size() - 1){
                output = output + "\n";
            }
        }
        System.out.println(addDoubleLine(output));
    }
    static void handleInput(){
        ArrayList <Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] splited = line.split(" ");
            String check = splited[0];

            if (check.equals("bye")) {
                System.out.println(
                    addDoubleLine(
                    "     Bye. Hope to see you again soon!"
                    )
                );
                System.exit(0);
            }else if(check.equals("list")){
                handleList(list);
            }else if(check.equals("done")){
                String taskNum = splited[1];
                Task current = list.get(Integer.parseInt(taskNum) - 1);
                current.markAsDone();
                System.out.println(addDoubleLine("     Nice! I've marked this task as done:\n" + "    " + current.getListItem()));
            }else{
                list.add(new Task(line));
                System.out.println(addDoubleLine("     added: " + line));
            }
        }
    }
    public static void main(String[] args) {
        greeting();
        handleInput();
    }
}
