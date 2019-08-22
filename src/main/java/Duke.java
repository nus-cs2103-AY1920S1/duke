import java.util.Scanner;

public class Duke {
    private static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) {
        ChatLike cl = new ChatLike(); //ChatLike object to call methods
        cl.greet();

        while(true) {
            String comm = S.nextLine();
            if (comm.equals("bye")) {
                cl.byeUser();
                break;
            } else if (comm.equals("list")){
                cl.list();
            } else if (comm.length() >= 4 && comm.substring(0,4).equals("done")){
                try {
                    cl.done(Integer.valueOf(comm.substring(5))); //Sends the list number to be marked as done
                }
                catch(DukeException exc) {
                    System.out.println(exc.getMessage());
                }
            } else if (comm.length() >= 6 && comm.substring(0,6).equals("delete")) {
                try {
                    cl.delete(Integer.valueOf(comm.substring(7))); //Sends the list number to be marked as done
                } catch (DukeException exc) {
                    System.out.println(exc.getMessage());
                }
            } else {
                try {
                    cl.add(comm);
                }
                catch(DukeException exc) {
                    System.out.println(exc.getMessage());
                }
            }
        }

<<<<<<< HEAD
=======
        Scanner S = new Scanner(System.in);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String inp = S.next();
        while(!(inp.equals("bye"))) {
            System.out.println("    ____________________________________________________________\n     " + inp +
                    "\n    ____________________________________________________________");
            inp = S.next();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
>>>>>>> origin/master
    }
}
