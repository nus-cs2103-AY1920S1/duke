public class OutputHandler {



    public static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

   public static void sayHi() {
        printLine();
        System.out.println(
               "\tHello! I'm Duke\n" +
               "\tWhat can I do for you?\n");
        printLine();

   }
    public static void printLine() {
        System.out.println("\t_______________________________");
    }


}
