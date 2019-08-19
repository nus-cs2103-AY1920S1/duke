public class OutputHandler {

    public static void echo(String cmd) {
        prettify(cmd);
    }

    public static void sayBye() {
        prettify("Bye. Hope to see you again soon!");
    }

   public static void sayHi() {
       System.out.println("\t_______________________________\n" +
               "\tHello! I'm Duke\n" +
               "\tWhat can I do for you?\n" +
               "\t_______________________________");
   }
    private static void prettify(String text) {
        System.out.println("\t_______________________________");
        System.out.println("\t" + text);
        System.out.println("\t_______________________________");
    }
}
