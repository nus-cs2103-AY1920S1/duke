import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int book = 1;
        Scanner sc = new Scanner(System.in);
        AddList adl = new AddList();
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
        System.out.println();
        while(book == 1) {
            String in = sc.nextLine();
            String subin1 = in.split(" ")[0];
            System.out.println("--------------------------------------");
            System.out.print("");
            if(in.contentEquals("bye")) {
                System.out.print("Bye. ");
                System.out.println("Hope to see you again soon!");
                System.out.println();
                book = 0;
            } else if(in.contentEquals("list")) {
                System.out.println("Here are the tasks in your list:");
                adl.printAllEvent();
                System.out.println();
            } else if(subin1.contentEquals("done")) {
                String subin2 = in.split(" ")[1];
                System.out.println("Nice! I have marked this task as done:");
                int index = Integer.parseInt(subin2);
                adl.changeEvent(index - 1);
                // System.out.print("\t");
                adl.printEvent(index);
            }
            else {
                adl.addEvent(in);
                System.out.println("added: " + in);
                System.out.println();
            }
            System.out.println("--------------------------------------");
        }
    }
}
