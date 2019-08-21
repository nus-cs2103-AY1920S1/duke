import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        String input = "";
        ArrayList<Task> lst = new ArrayList();
        while(!input.equals("bye")) {
            input = scanner.nextLine();

            if(input.equals("list")){
                for(int i = 0; i < lst.size(); i++){
                    System.out.println(i+1 + ". " + lst.get(i));
                }
            } else {
                String[] inputArr = input.split(" ");
                if (inputArr[0].equals("done")) {
                    int i = Integer.parseInt(inputArr[1]) - 1;
                    lst.get(i).done();
                    String nice = "Nice! I've marked this task as done:";
                    System.out.println(nice + "\n\t" + lst.get(i));
                } else {
                    Task task = new Task(input);
                    lst.add(task);
                    System.out.println("added: " + input);
                }
            }
        }


        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
