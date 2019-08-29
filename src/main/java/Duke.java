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
            output = output + "     " +  (i+1) + ": " + ls.get(i).toString();
            if(i < ls.size() - 1){
                output = output + "\n";
            }
        }
        System.out.println(addDoubleLine(output));
    }
    static void printMsg(Task t, int size){
        System.out.println(addDoubleLine("     Got it. I've added this task: \n" + "      " + t.toString() + "\n     Now you have " + size + " tasks in the list."));
    }
    static void handleInput(){
        ArrayList <Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String line = sc.nextLine().toLowerCase();
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
                System.out.println(addDoubleLine("     Nice! I've marked this task as done:\n" + "    " + current.toString()));
            }else if(check.equals("todo")){
                list.add(new Todo(line.replace("todo ", "")));
                Task current = list.get(list.size()-1);
                printMsg(current, list.size());
            }else if(check.equals("event")){
                //removes the command and splits it into 2
                String [] splitDate = line.replace("event ", "").split("/at");
                list.add(new Event(splitDate[0], splitDate[1]));
                Task current = list.get(list.size()-1);
                printMsg(current, list.size());
            }else if(check.equals("deadline")){
                String [] splitDate = line.replace("deadline ", "").split("/by");
                list.add(new Deadline(splitDate[0], splitDate[1]));
                Task current = list.get(list.size()-1);
                printMsg(current, list.size());
            }
            // }else{
            //     list.add(new Task(line, Type.E));
            //     System.out.println(addDoubleLine("     added: " + line));
            // }
        }
    }
    public static void main(String[] args) {
        greeting();
        handleInput();
    }
}
