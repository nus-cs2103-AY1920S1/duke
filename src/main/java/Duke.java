import java.util.Objects;
import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {
        String BYE = "bye";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String inputString;
        Task listOfItems[] = new Task[100];
        int numOfItems = 0;
        
        while (true) {
            inputString = scanner.nextLine();
            String TempInputString = inputString;
            String wordSplit[] = TempInputString.split(" ");
            //System.out.println("\n" + TempInputString +"\n" + wordSplit[0] + "\n" + wordSplit[1]);
            if ((Objects.equals(inputString, BYE))) {
                System.out.println("\t\tBye. Hope to see you again soon!\n");
                return;
            }
            else if ((Objects.equals(wordSplit[0], "done") && (Integer.parseInt(wordSplit[1]) <= numOfItems))){
                int numbering = Integer.parseInt((TempInputString.split(" "))[1]) - 1;
                listOfItems[numbering].setDone();
                System.out.println("\t\tNice, I've marked this item as done\n\t\t["
                        + listOfItems[numbering].getStatusIcon() + "] " + listOfItems[numbering].description + "\n");
            }
            else if (Objects.equals(inputString, "list")) {
                for (int i=0; i<numOfItems; i++ ) {
                    System.out.println("\t\t[" + listOfItems[i].getStatusIcon() + "] " + listOfItems[i].description + "\n" );
                }
            }
            else {
                Task item = new Task(inputString);
                listOfItems[numOfItems++] = item;
                System.out.println("\t\t" + inputString + "\n");
            }
        }
    }
}

