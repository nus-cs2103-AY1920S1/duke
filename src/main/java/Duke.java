import java.util.*;
public class Duke {
    private static ArrayList<String> todolist=new ArrayList<String>();
    private static void print(String message){
        System.out.println(">>"+message);
    }
    private static void list(){
        StringBuilder outputmsg=new StringBuilder("List:");
        int i=todolist.size();
        for(int x=1; x<=i;x++) {
            outputmsg.append("\n  "+ x + "." + todolist.get(x - 1));
        }
        String output=outputmsg.toString();
        print(output);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc= new Scanner(System.in);
        while(sc.hasNext()){
            String input=sc.nextLine();
            if(input.equals("bye")) {
                break;
            }else if(input.equals("list")){
                list();
            }else{
                todolist.add(input);
            }
        }
        String exitmsg="Bye. Hope to see you again.";
        print(exitmsg);
    }
}
