import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int book = 1;
        Scanner sc = new Scanner(System.in);
        AddList adl = new AddList();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        System.out.println();
        int counter = 0;
        while(book == 1) {
            String in = sc.nextLine();
            String subin1 = in.split(" ")[0];
            System.out.println("____________________________________________________________");
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
                adl.printEvent(index - 1);
            } else if(subin1.contentEquals("event") ||  subin1.contentEquals("deadline") || subin1.contentEquals("todo")) {
                counter += 1;
                System.out.println(subin1);
                if(in.split("/").length == 1) {
                    adl.addEventWithoutTime(in.split(" ")[1], subin1);
                } else {
                    adl.addEventWithTime(in.split(" ")[1].split("/")[0], subin1, in.split("/")[1].split(" ")[1], in.split("/")[1].split(" ")[0]);
                }
                System.out.println("Got it. I have added this task:");
                adl.printEvent(counter - 1);
                System.out.println("Now you have " + counter + " tasks in the list.");
            }
            else {
                System.out.println("error 001");
            }
            System.out.println("____________________________________________________________");
        }
    }
}
