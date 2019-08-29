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
            //bye
            if (check.equals("bye")) {
                System.out.println(addDoubleLine("     Bye. Hope to see you again soon!"));
                System.exit(0);
            //list
            }else if(check.equals("list")){
                handleList(list);
            //done
            }else if(check.equals("done")){
                String taskNum = splited[1];
                Task current = list.get(Integer.parseInt(taskNum) - 1);
                current.markAsDone();
                System.out.println(addDoubleLine("     Nice! I've marked this task as done:\n" + "    " + current.toString()));
            //make a todo task
            }else if(check.equals("delete")){
                String taskNum = splited[1];
                Task current = list.get(Integer.parseInt(taskNum) - 1);
                list.remove(Integer.parseInt(taskNum) - 1);
                System.out.println(addDoubleLine("     Noted. I've removed this task: \n" + "    " + current.toString() + "\n     Now you have " + list.size() + " tasks in the list."));
            }else if(check.equals("todo")){
                String description = line.replace("todo", "").trim();
                //error handling
                if(description.equals("")){
                    System.out.println(addDoubleLine("    ☹ OOPS!!! The description of a todo cannot be empty."));
                
                }else{ //successful addition 
                    list.add(new Todo(description));
                    Task current = list.get(list.size()-1);
                    printMsg(current, list.size());
                }
            //make an event task
            }else if(check.equals("event")){
                //removes the command and splits it into 2
                String [] splitDate = line.replace("event", "").split("/at");
                if(splitDate.length < 2){
                    System.out.println(addDoubleLine("    ☹ OOPS!!! Events require both a description and a date \n    (e.g. event go to concert /at 13 Feb)"));
                }else{
                    //if it reaches here it is successful
                    list.add(new Event(splitDate[0].trim(), splitDate[1].trim()));
                    Task current = list.get(list.size()-1);
                    printMsg(current, list.size());
                
                }
            //make deadline task
            }else if(check.equals("deadline")){
                String [] splitDate = line.replace("deadline", "").split("/by");
                if(splitDate.length < 2){
                    System.out.println(addDoubleLine("    ☹ OOPS!!! Deadlines require both a description and a date by \n    (e.g. deadline homework3 /by tomorrow)"));
                }else{
                    list.add(new Deadline(splitDate[0].trim(), splitDate[1].trim()));
                    Task current = list.get(list.size()-1);
                    printMsg(current, list.size());
                }
            //error handling
            }else{
                System.out.println(addDoubleLine("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }
    public static void main(String[] args) {
        greeting();
        handleInput();
    }
}
