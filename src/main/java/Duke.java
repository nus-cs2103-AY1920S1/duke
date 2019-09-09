import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    /**
     * Todo: store/read filePath and print list or something.
     */
    public Duke(String filePath) {
        /* Adding some lines of
         * comments to this to practice
         * java coding practices.
         */
        String bye = "bye";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String inputString;
        Task[] listOfItems = new Task[100];
        int numOfItems = 0;

        while (true) {
            inputString = scanner.nextLine();
            String tempInputString = inputString;
            String[] wordSplit = tempInputString.split(" ");
            if ((Objects.equals(inputString, bye))) {
                System.out.println("\t\tBye. Hope to see you again soon!\n");
                return;
            } else if ((Objects.equals(wordSplit[0], "done") && (Integer.parseInt(wordSplit[1]) <= numOfItems))) {
                int numbering = Integer.parseInt((tempInputString.split(" "))[1]) - 1;
                listOfItems[numbering].setDone();
                System.out.println("\t\t" + listOfItems[numbering].toStringDone() + "\n");
            } else if (Objects.equals(inputString, "list")) {
                for (int i = 0; i < numOfItems; i++) {
                    System.out.println("\t\t" + listOfItems[i].toString() + "\n");
                }
            } else if (Objects.equals(wordSplit[0], "delete")) {
                int numbering = Integer.parseInt((tempInputString.split(" "))[1]) - 1;
                Task tempTask = listOfItems[numbering];
                listOfItems = removeElement(listOfItems, listOfItems[numbering]);
                numOfItems--;
                System.out.println("\t\tNoted I have removed this task:\n\t\t" + tempTask.toString() + "\n");
                System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
            } else if (Objects.equals(wordSplit[0], "find")) {
                String findWord = wordSplit[1];
                for (int i = 0; i < numOfItems; i++) {
                    if (listOfItems[i].description.contains(findWord)) {
                        System.out.println("\t\t" + listOfItems[i].toString() + "\n");
                    }
                }
            } else {
                if (Objects.equals(wordSplit[0], "Deadline")) {
                    try {
                        String newInputString = inputString.substring(9);
                        if (!(newInputString.contains("/by"))) {
                            throw new StringIndexOutOfBoundsException("Please use the proper format!!!");
                        }
                        String[] splitString = newInputString.split("/by");
                        Task item = new Deadline(splitString[0], splitString[1]);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t"  
                                + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    } catch (StringIndexOutOfBoundsException ex) {
                        System.out.println(ex.getMessage());
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("Please use date time format as such: dd/MM/yyyy HHmm");
                    }
                } else if (Objects.equals(wordSplit[0], "Todo")) {
                    try {
                        String newInputString = inputString.substring(5);
                        Task item = new Todo(newInputString);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t"
                               + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    } catch (Exception stringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                        stringIndexOutOfBoundsException.getMessage();
                    }
                } else if (Objects.equals(wordSplit[0], "Event")) {
                    try {
                        String newInputString = inputString.substring(6);
                        String[] splitString = newInputString.split("/at");
                        Task item = new Event(splitString[0], splitString[1]);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t" 
                                + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    } catch (Exception stringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                    }
                } else {
                    System.out.println("\t\tHey Heyyyy, " 
                            + "sorry but I have no idea what are you saying, please try again!");
                }
            }
        }
    }

    private static Task[] removeElement(Task[] listOfItems, Task listOfItem) {
        Task[] result = new Task[100];
        int iteration = 0;
        for (Task item : listOfItems) {
            if (!listOfItem.equals(item)) {
                result[iteration++] = item;
            }
        }
        return result;
    }
    
    /**
     * Todo: do try and except here to see if duke file is empty.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Function to run the file upon opening.
     */
    public void run() {
        //...
    }
}

