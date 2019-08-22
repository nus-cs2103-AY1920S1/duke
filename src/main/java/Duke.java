import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        Scanner sc = new Scanner(System.in);
        String[] listArr = new String[100]; // max list length: 100
        int listIndex = 1;
        String listString;

        printIndentedString("Hello! I'm Duke\n" +
                indent + " " + "What can I do for you?", indent);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                listString = "";
                for(int i = 0; i < (listIndex - 1); i++) {
                    listString += listArr[i];
                }
                printIndentedString(listString, indent);
            } else {
                listArr[listIndex - 1] = listIndex + ". " + input;
                listIndex++;
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
