import java.util.Scanner;

public class MainManager {
    private String[] list;
    private int counter;

    public MainManager() {
        this.list = new String[100];
        counter = 0;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String toPrint = sc.nextLine();
            switch(toPrint) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for(String curr : list) {
                        if(curr != null) {
                            System.out.println(curr);
                        }
                    }
                    break;

                default:
                    list[counter] = ++counter + ". " + toPrint;
                    System.out.println("added: " + toPrint);
                    break;
            }

            if(toPrint.equals("bye")) {
                break;
            }
        }
    }


}
