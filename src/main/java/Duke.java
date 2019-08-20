import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static Task[] myList = new Task[100];
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";
    public static int size;

    public static String getDescription(String cmd){
     String[] curr = cmd.split(" ");
     return curr[1] + " " + curr[2];
    }

    public static void childFeature(String cmd){
        System.out.print(upperLine);
        System.out.println("Got it. I've added this task: ");
        String firstWord = cmd.split(" ")[0];
        if(firstWord.equals("todo")){
            myList[idx] = new Todo(getDescription(cmd));
        }else if(firstWord.equals("deadline")){
            String by = cmd.split("/by ")[1];
            myList[idx] = new Deadline(getDescription(cmd), by);
        }else{
            String at = cmd.split("/at ")[1];
            myList[idx] = new Event(getDescription(cmd), at);
        }
        System.out.println(myList[idx]);
        idx++;
        size++;
        System.out.println("Now you have "+ size + " tasks in the list.");
        System.out.println(lowerLine);
    }
    public static void addFeature(String cmd){

        myList[idx] = new Task(cmd);
        idx++;
        size++;
        message = upperLine + "add: " + cmd + "\n" + lowerLine;
        System.out.println(message);
    }

    public static void tryFeature(String cmd) {
        String firstWord = cmd.split(" ")[0];
        if(firstWord.equals("todo") || firstWord.equals("deadline")
                ||firstWord.equals("event")){
            childFeature(cmd);
        }else{
            addFeature(cmd);
        }
    }
        public static void doneFeature(String num_string){
        int i = Integer.parseInt(num_string);
        System.out.print(upperLine);
        System.out.println("Nice! I've marked this task as done: ");
        Task current = myList[i - 1];
        current.markAsDone();
        System.out.println(current);
        System.out.println(lowerLine);
        size++;

    }
    public static void listFeature(){
        System.out.print(upperLine);
        String title = "Here are the tasks in your list:\n";
        System.out.print(title);
        for(int i = 0; i < idx; i++){
            System.out.println((i+1) + ". " + myList[i]);
        }
        System.out.println(lowerLine);
    }

    public static void byeFeature(){
        message = upperLine + "Bye. Hope to see you again soon!\n" + lowerLine;
        System.out.println(message);
    }
    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n"
        + "What can I do for you?\n";
        greet = upperLine + greet + lowerLine;
        System.out.println(greet);

        while(true){
            String cmd = sc.nextLine();
            if(cmd.equals("bye")){
                byeFeature();
                break;
            }else{
                if(cmd.equals("list")){
                    listFeature();
                }else if(cmd.split(" ").length == 2 &&
                        cmd.split(" ")[0].equals("done")){
                    doneFeature(cmd.split(" ")[1]);
                }else{
                    tryFeature(cmd);
                }
            }
        }
    }
}