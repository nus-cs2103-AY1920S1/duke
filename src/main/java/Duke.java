import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        boolean flag = true;
        drawline();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        drawline();
        while(flag){
            String s = sc.nextLine();
            String [] a = s.split(" ");
            switch (a[0]) {
                case "bye" :
                    bye();
                    flag = false;
                    break;
                case "list" :
                    list(list);
                    break;
                case "done":
                    int n = Integer.parseInt(a[1]);
                    done(list, n-1);
                    break;
                case "todo":
                    try{
                        todo(list,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "event":
                    try{
                        event(list,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "deadline":

                    try{
                        deadline(list,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int m = Integer.parseInt(a[1]);
                    delete(list, m-1);
                    break;


                default:
                    try{
                        other(s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }
        }
    }

    public static void drawline(){
        System.out.println("    ____________________________________________________________");
    }
    public static void bye(){
        drawline();
        System.out.println("     Bye. Hope to see you again soon!");
        drawline();

    }
    public static void list(ArrayList<Task>list){
        drawline();
        System.out.println("     Here are the tasks in your list:");
        for(int n=0; n<list.size();n++) {
            int m = n+1;
            System.out.println("     "+m+"."+list.get(n));
        }
        drawline();
    }
    public static void echo(ArrayList<Task>list,Task t){
        list.add(t);
        drawline();
        System.out.println("     added: "+t.getName());
        drawline();
    }
    public static void done(ArrayList<Task>list,int k){
        drawline();
        list.get(k).setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [✓]"+list.get(k).getName());
        drawline();
    }
    public static void todo(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 4) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            String td = s.substring(5);
            Task t = new Todo(td);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline(); 
        }
    }
    public static void deadline(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 8) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String deadlineDescription = s.substring(9, firstIndex);
            String deadlineBy = s.substring(secondIndex);
            Task t = new Deadline(deadlineDescription, deadlineBy);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline();
        }
    }
    public static void event(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 5) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String eventDescription = s.substring(6, firstIndex);
            String eventAt = s.substring(secondIndex);
            Task t = new Event(eventDescription, eventAt);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline();
        }
    }
    public static void other(String s) throws ErrorException{
        throw new ErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void delete(ArrayList<Task>list,int p){
        Task rt = list.get(p);
        drawline();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     "+rt);
        list.remove(p);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        drawline();
    }

}
