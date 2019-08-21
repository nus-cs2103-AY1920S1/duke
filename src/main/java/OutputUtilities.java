public class OutputUtilities {

    public static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

   public static void sayHi() {
        printLine();
        System.out.println(
               "\tHello! I'm Duke\n" +
               "\tWhat can I do for you?");
        printLine();
   }

   public static void printLine() {
        System.out.println("\t_______________________________");
    }

}
