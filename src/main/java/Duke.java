public class Duke {
    static void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    static void printnewline(){
        System.out.println("\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String name = "Duke";
        printline();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printline();
        printnewline();
        System.out.println("list");
        printline();
        System.out.println("\tlist");
        printline();
        printnewline();
        System.out.println("blah");
        printline();
        System.out.println("\tblah");
        printline();
        printnewline();
        System.out.println("bye");
        printline();
        System.out.println("\tBye. Hope to see you again soon!");
        printline();

    }
}
