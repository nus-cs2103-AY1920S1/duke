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
        ArrayList<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        drawline();
        System.out.println("     Hello! I'm Duke\n" +
                           "     What can I do for you?");
        drawline();
        while(flag){
            String s = sc.nextLine();
            switch (s) {
                case "bye" :
                    bye();
                    flag = false;
                    break;
                case "list" :
                    list(list);
                    break;
                default:
                    echo(list, s);
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
    public static void list(ArrayList<String>list){
        drawline();
        for(int n=0; n<list.size();n++) {
            int m = n+1;
            System.out.println("     " + m + ". " + list.get(n));
        }
        drawline();
    }
    public static void echo(ArrayList<String>list,String s){
        list.add(s);
        drawline();
        System.out.println("     added: "+s);
        drawline();
    }

}
