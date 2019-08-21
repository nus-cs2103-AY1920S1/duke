import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println(greet);
        Texts texts = new Texts();
        while(sc.hasNext()) {
            String next = sc.next();
            try {
                switch (Command.valueOf(next)) {
                    case list:
                        String tasks = "Here are the tasks in your list:";
                        System.out.println(tasks);
                        texts.print();
                        break;
                    case bye:
                        String Exit = "Bye. Hope to see you again soon!";
                        System.out.println(Exit);
                        sc.close();
                        break;
                    case done:
                        int n = sc.nextInt();
                        texts.get(n - 1).setDone();
                        String done = "Nice! I've marked this task as done:\n";
                        System.out.println(done + "  " + texts.get(n - 1));
                        break;
                    case todo:
                        String descriptionT = sc.nextLine();
                        texts.add(descriptionT,"T");
                        System.out.println("Got it. I've added this task:\n" + "  "
                                + "[T]" + texts.getLast() + "\nNow you have " +
                                texts.getNumber() + " tasks in the list.");
                        break;
                    case deadline:
                        String allD = sc.nextLine();
                        int place = allD.indexOf("/");
                        String descriptionD = allD.substring(place - 1);
                        String date = allD.substring(place + 3, allD.length());
                        
                        break;
                    case event:
                        break;
                }
            } catch(IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
        }
    }

}

enum Command {
    list,bye,done,todo,deadline,event;
        }
