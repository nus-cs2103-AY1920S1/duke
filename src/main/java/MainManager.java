import java.util.Scanner;

public class MainManager {
    private Task[] list;
    private int counter;

    public MainManager() {
        this.list = new Task[100];
        counter = 0;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String toPrint = sc.nextLine();
            String[] command = toPrint.split(" ");
            switch(command[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for(int i = 0; i < list.length; i++) {
                        if(list[i] != null) {
                            System.out.println(i + 1 + "." + list[i]);
                        }
                    }
                    break;

                case "done" :
                    System.out.println("Nice! I've marked this task as done:");
                    Task currTask = list[Integer.parseInt(command[1]) - 1];
                    currTask.setDone();
                    System.out.println(" " + currTask);
                    break;


                default:
                    Task curr = new Task(toPrint);
                    list[counter] = curr;
                    counter++;
                    System.out.println("added: " + toPrint);
                    break;
            }

            if(toPrint.equals("bye")) {
                break;
            }
        }
    }


}
