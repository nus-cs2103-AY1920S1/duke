public class OutputUtilities {

    public static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

   public static void sayHi() {
        printLine();
        System.out.println(
               "\t Hello! I'm Duke\n" +
               "\t What can I do for you?");
        printLine();
   }

   public static void printLine() {
        System.out.println("\t_______________________________");
    }

}
