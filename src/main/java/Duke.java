import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /* Adding some lines of 
         * comments to this to practice 
         * java coding practices.
         */
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
            if ((Objects.equals(inputString, BYE))) {
                System.out.println("\t\tBye. Hope to see you again soon!\n");
                return;
            }
            else if ((Objects.equals(wordSplit[0], "done") && (Integer.parseInt(wordSplit[1]) <= numOfItems))){
                int numbering = Integer.parseInt((TempInputString.split(" "))[1]) - 1;
                listOfItems[numbering].setDone();
                System.out.println("\t\t" + listOfItems[numbering].toStringDone() + "\n");
            }
            else if (Objects.equals(inputString, "list")) {
                for (int i=0; i<numOfItems; i++ ) {
                    System.out.println("\t\t" + listOfItems[i].toString() + "\n" );
                }
            }
            else if (Objects.equals(wordSplit[0], "delete")) {
                int numbering = Integer.parseInt((TempInputString.split(" "))[1]) - 1;
                Task tempTask = listOfItems[numbering];
                listOfItems = removeElements(listOfItems, listOfItems[numbering]);
                numOfItems--;
                System.out.println("\t\tNoted I have removed this task:\n\t\t" + tempTask.toString() + "\n");
                System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
            }
            else {
                if (Objects.equals(wordSplit[0], "Deadline")){
//                    if (Objects.equals(wordSplit[0], "Todo") || inputString == "Deadline ") {
//                        System.out.println("Please add an argument after the word!");
//                        break;
//                    }
                    try {
                        String newInputString = inputString.substring(9);
                        String splitString[] = newInputString.split("/by");
                        Task item = new Deadline(splitString[0], splitString[1]);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t" + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    }
                    catch (Exception StringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                    }
                }
                else if (Objects.equals(wordSplit[0], "Todo")){
                    try {
                        String newInputString = inputString.substring(5);
                        Task item = new Todo(newInputString);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t" + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    }
                    catch (Exception StringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                    }
                }
                else if (Objects.equals(wordSplit[0], "Event")){
                    try {
                        String newInputString = inputString.substring(6);
                        String splitString[] = newInputString.split("/at");
                        Task item = new Event(splitString[0], splitString[1]);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t" + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    }
                    catch (Exception StringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                    }
                }
                else {
                    System.out.println("\t\tHey Heyyyy, sorry but I have no idea what are you saying, please try again!");
                }
            }
        }
    }
    public static Task[] removeElements(Task[] input, Task deleteMe) {
        List result = new LinkedList();

        for(Task item : input)
            if(!deleteMe.equals(item))
                result.add(item);

        return (Task[]) result.toArray(input);
    }
}

