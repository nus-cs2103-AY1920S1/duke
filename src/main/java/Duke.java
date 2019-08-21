import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] data = new String[100];
        int x = 0;

        while (!input.equals("bye")) {

            switch(input) {
                case "list":
                    printlist(data);
                    input = sc.nextLine();
                    break;
                default:
                    data[x] = input;
                    x++;
                    echo("Added: " + input);
                    input = sc.nextLine();
                    break;
            }
        }

        exit();
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String s) {
        String[] arr = s.split("\n");
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        for (String str : arr) {
            System.out.println("     " + str);
        }
        System.out.println(indentedline);
    }

    public static void printlist(String[] array) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        int y = 0;
        while (array[y] != null) {
            System.out.println("     " + (y+1) + ". " + array[y]);
            y++;
        }
        System.out.println(indentedline);
    }
}
