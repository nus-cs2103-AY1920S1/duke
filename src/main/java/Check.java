import java.util.Scanner;

public class Check {
        public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

            Scanner S = new Scanner(System.in);
            String listOfAct[] = new String[100];
            int i = 0;
            System.out.println("    ____________________________________________________________\n" +
                    "     Hello! I'm Duke\n" +
                    "     What can I do for you?\n" +
                    "    ____________________________________________________________\n");
            String inp = S.nextLine();
            while(!(inp.equals("bye"))) {
                if(inp.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    for (int j = 0; j < i; j++) {
                        System.out.println("     " + (j + 1) + ". " + listOfAct[j]);
                    }
                    System.out.println("    ____________________________________________________________\n");
                    inp = S.nextLine();
                    continue;
                }

                System.out.println("    ____________________________________________________________\n" +
                        "     added: " + inp + "\n" +
                        "    ____________________________________________________________\n");
                listOfAct[i++] = inp;
                inp = S.nextLine();
            }
            System.out.println("    ____________________________________________________________\n" +
                    "     Bye. Hope to see you again soon!\n" +
                    "    ____________________________________________________________\n");
        }
    }
