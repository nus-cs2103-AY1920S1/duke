import java.util.Scanner;

public class MainManager {
    public MainManager() {};

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String toPrint = sc.nextLine();
            if(toPrint.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(toPrint);
            }
        }
    }


}
