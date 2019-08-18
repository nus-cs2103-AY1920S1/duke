import java.util.Scanner;

public class Duke {
    private void draw_line() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    private void repeat() {
        Scanner sc = new Scanner(System.in);
        String next_string = sc.next();
        draw_line();
        System.out.println(next_string);
        draw_line();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
