import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("     ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "     ____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> done = new ArrayList<String>();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> details = new ArrayList<String>();

        do{
            String input = sc.nextLine();

            if(input.equals("bye")){
                System.out.println("     ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "     ____________________________________________________________");
                break;
            } else if(input.equals("list")) {
                int i;
                System.out.println("     ____________________________________________________________\n");
                for(i = 0; i < data.size(); i++){
                    System.out.print("     "); // + (i + 1) + ". [" + type.get(i) + "][" + done.get(i) + "] " + data.get(i));
                    System.out.print((i + 1) + ". ");
                    System.out.print("[" + type.get(i) + "]");
                    System.out.print("[" + done.get(i) + "]");
                    System.out.print(" " + data.get(i));
                    if(type.get(i).equals("D")){
                        System.out.print(" (by: " + details.get(i) + ")");
                    } else if(type.get(i).equals("E")){
                        System.out.print(" (at: " + details.get(i) + ")");
                    }

                    System.out.println();
                }
                System.out.println("     ____________________________________________________________");

            } else if(input.startsWith("done")) {
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                done.set(index - 1, "✓");

                System.out.println("     ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("     [" + done.get(index - 1) + "] " + data.get(index - 1));
                System.out.println("     ____________________________________________________________");

            } else if(input.startsWith("delete")){
                String[] sp = input.split(" ", 2);
                int index = Integer.parseInt(sp[1]);

                System.out.println("     ____________________________________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.print("       [" + type.get(index - 1) + "][" + done.get(index - 1) + "] " + data.get(index - 1));
                if(type.get(index - 1).equals("D")){
                    System.out.print(" (by: " + details.get(index - 1) + ")");
                } else if (type.get(index - 1).equals("E")){
                    System.out.print(" (at: " + details.get(index - 1) + ")");
                }
                System.out.println();

                data.remove(index - 1);
                type.remove(index - 1);
                done.remove(index - 1);
                details.remove(index - 1);

                System.out.println("     You now have " + data.size() + " tasks in this list");
                System.out.println("     ____________________________________________________________");



            } else if(input.startsWith("todo")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     OOPS! The description of a todo cannot be empty.");
                    System.out.println("     ____________________________________________________________");
                    continue;
                }

                data.add(sp[1]);
                details.add("NULL");
                done.add("✗");
                type.add("T");

                System.out.println("     ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("        [T][✗] " + sp[1]);
                System.out.println("     Now you have " + data.size() + " tasks in this list");
                System.out.println("     ____________________________________________________________");



            } else if(input.startsWith("deadline")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("     ____________________________________________________________");
                    continue;
                }

                String[] sp2 = sp[1].split(" /by ", 2);

                String[] date = sp2[1].split(" ");
                String time = date[1];
                String[] date2 = date[0].split("/");

                String month;
                if(date2[1].equals(1)){
                    month = "January";
                }
                if(date2[1].equals(2)){
                    month = "February";
                }
                if(date2[1].equals(3)){
                    month = "March";
                }
                if(date2[1].equals(4)){
                    month = "April";
                }
                if(date2[1].equals(5)){
                    month = "May";
                }
                if(date2[1].equals(6)){
                    month = "June";
                }
                if(date2[1].equals(7)){
                    month = "July";
                }
                if(date2[1].equals(8)){
                    month = "August";
                }
                if(date2[1].equals(9)){
                    month = "September";
                }
                if(date2[1].equals(10)){
                    month = "October";
                }
                if(date2[1].equals(1)){
                    month = "November";
                }
                if(date2[1].equals(1)){
                    month = "December";
                }


                data.add(sp2[0]);
                details.add(sp2[1]);
                done.add("✗");
                type.add("D");

                System.out.println("     ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("        [D][✗] " + sp2[0] + " (by: " + sp2[1] + ")");
                System.out.println("     Now you have " + data.size() + " tasks in this list");
                System.out.println("     ____________________________________________________________");

            } else if(input.startsWith("event")) {
                String[] sp = input.split(" ", 2);

                if(sp.length < 2){
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     OOPS!!! The description of a event cannot be empty.");
                    System.out.println("     ____________________________________________________________");
                    continue;
                }

                String[] sp2 = sp[1].split(" /at ", 2);

                data.add(sp2[0]);
                details.add(sp2[1]);
                done.add("✗");
                type.add("E");

                System.out.println("     ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("        [E][✗] " + sp2[0] + " (at: " + sp2[1] + ")");
                System.out.println("     Now you have " + data.size() + " tasks in this list");
                System.out.println("     ____________________________________________________________");
            } else {
                    System.out.println("     ____________________________________________________________\n" +
                            "     OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "     ____________________________________________________________");

            }

        } while(true);
    }
}
