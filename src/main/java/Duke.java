import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        Scanner sc = new Scanner(System.in);
        Task[] listArr = new Task[100]; // max list length: 100
        int listSize = 0;
        int listPointer;
        String listString;

        printIndentedString("Hello! I'm Duke\n" +
                indent + " " + "What can I do for you?", indent);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listString = "";
                for (int i = 0; i < listSize; i++) {
                    listString += listArr[i];
                    if (i != (listSize - 1)) {
                        listString += '\n' + indent + ' ';
                    }
                }
                printIndentedString(listString, indent);
            } else if(input.split(" ")[0].equals("done")){
                listPointer = Integer.parseInt(input.split(" ")[1]);
                listArr[listPointer - 1].markAsDone();
                printIndentedString("Nice! I've marked this task as done: \n"
                        + indent + "   " + listArr[listPointer - 1], indent);
            } else {
                listArr[listSize] = new Task(input, (listSize + 1));
                listSize++;
                printIndentedString("added: " + input, indent);
            }
            input = sc.nextLine();
        }
        printIndentedString("Bye. Hope to see you again soon!", indent);
    }

    public static void printIndentedString(String string, String indent) {
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " " + string);
        System.out.println(indent + "____________________________________________________________");
        System.out.println();
    }
}
