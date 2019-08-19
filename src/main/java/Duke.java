import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        runUI(arrayList);
    }

    public static void runUI(ArrayList<String> arrayList){
        String str;
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);
        displayWelcome();
        while(isContinue) {
            str = scanner.nextLine();
            switch (str) {
                case "bye":
                    displayQuit();
                    isContinue = false;
                    break;
                case "list":
                    list(arrayList);
                    break;
                default:
                    addTaskIn(str,arrayList);

            }
        }
    }

    public static void displayWelcome(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void displayQuit(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /*public static void echo(String str) {
        System.out.println("    ____________________________________________________________");
        System.out.println(str);
        System.out.println("    ____________________________________________________________");
    }*/

    //Add new task
    public static void addTaskIn(String str,ArrayList<String> arrayList) {
        System.out.println("    ____________________________________________________________");
        arrayList.add(str);
        System.out.println("added: "+str);
        System.out.println("    ____________________________________________________________");
    }

    public static void list (ArrayList<String> arrayList){
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("     "+(i+1)+". "+arrayList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
}
