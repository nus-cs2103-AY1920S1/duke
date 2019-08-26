import java.util.Scanner;
import java.util.ArrayList;

public class dukeBot {
    public void start() {
        Scanner sc =  new Scanner(System.in);

        // create storage for tasks
        ArrayList<Task> list = new ArrayList<>();
        int cnt = 0;
        String firstWord = "added: ";


        while(true) {
            String input = sc.nextLine();

            if(input.startsWith("done")) {
                int index = Integer.valueOf(input.substring(5));
                list.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index - 1).toString());
                continue;
            }

            switch(input) {
                case "bye":
                case "Bye":
                    System.out.println("Bye. Hope to see you again soon!"); return;
                case "list": for(int i = 0; i < cnt; i++) System.out.println(((i + 1) + ".").concat(list.get(i).toString())); break;
                default:
                    if(!input.equals("")) {
                        list.add(cnt++, new Task(input));
                        System.out.println(firstWord.concat(input));
                    }
                }
            }
        }
    }
