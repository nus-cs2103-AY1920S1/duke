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
                    return;
                case "list" :
                    list(list);
                    break;
                case "done":
                    done(list, a[1].charAt(0));
                    break;

                default:
                    Task task = new Task(s);
                    echo(list, task);
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
        for(int n=0; n<list.size();n++) {
            int m = n+1;
            if(list.get(n).isDone()== true)
                System.out.println("     [✓]" + m + ". " + list.get(n).getName());
            else
                System.out.println("     [✗]" + m + ". " + list.get(n).getName());
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
        list.get(k-49).setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [✓]"+list.get(k-49).getName());
        drawline();
    }

}