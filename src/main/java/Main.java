import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //start Duke program
        Scanner sc = new Scanner(System.in);
        String horizontal_line = "\t__________________________________________________";
        System.out.println(horizontal_line);
        System.out.println("\t  Hello! I'm Duke");
        System.out.println("\t  What can I do for you?");
        System.out.println(horizontal_line);

        //store the list
        ArrayList<Task> strArr = new ArrayList<>();

        //take in inputs
        while (sc.hasNext()) {
            String inputStr = sc.nextLine();
            String[] inputArr = inputStr.split(" ");

            if (inputArr[0].equals("bye")) {
                System.out.println(horizontal_line);
                System.out.println("\t  Bye. Hope to see you again soon!");
                System.out.println(horizontal_line);
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println(horizontal_line);
                System.out.println("\t  Here are the tasks in your list:");
                for (int i = 0; i < strArr.size(); i++) {
                    System.out.println("\t  "+ (i + 1) +". " + strArr.get(i));
                }
                System.out.println(horizontal_line);
            } else if (inputArr[0].equals("done")) {
                Integer indexDone = Integer.valueOf(inputArr[1]);
                strArr.get(indexDone - 1).markAsDone();

                System.out.println(horizontal_line);
                System.out.println("\t  Nice! I've marked this task as done:");
                System.out.println("\t    " + strArr.get(indexDone - 1));
                System.out.println(horizontal_line);
            } else {
                Task newTask = new Task(inputStr);
                strArr.add(newTask);
                System.out.println(horizontal_line);
                System.out.println("\t  added: " + inputStr);
                System.out.println(horizontal_line);
            }
        }
    }
}