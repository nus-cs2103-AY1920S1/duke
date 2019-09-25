//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
//import java.util.Scanner;

public class DataForDuke {
    
    private static String bye = "bye";
    private static Task[] listOfItems = new Task[100];
    private static int numOfItems = 0;
    private static String userOutput;
    
    public static String DukeData(String inputString) {

        try {
            String tempInputString = inputString;
            System.out.println(inputString);
            String[] wordSplit = tempInputString.split(" ");
            if ((Objects.equals(inputString, bye))) {
                userOutput = "Bye. Hope to see you again soon!";
                // System.out.println("\t\tBye. Hope to see you again soon!\n");
                return userOutput;
            } else if ((Objects.equals(wordSplit[0], "done") && (Integer.parseInt(wordSplit[1]) <= numOfItems))) {
                int numbering = Integer.parseInt((tempInputString.split(" "))[1]) - 1;
                listOfItems[numbering].setDone();
                userOutput = listOfItems[numbering].toStringDone();
                return userOutput;
                //System.out.println("\t\t" + listOfItems[numbering].toStringDone() + "\n");
            } else if (Objects.equals(inputString, "list")) {
                userOutput = "";
                for (int i = 0; i < numOfItems; i++) {
                    userOutput += listOfItems[i].toString() + "\n";
                    //System.out.println("\t\t" + listOfItems[i].toString() + "\n");
                }
                return userOutput;
            } else if (Objects.equals(wordSplit[0], "delete")) {
                int numbering = Integer.parseInt((tempInputString.split(" "))[1]) - 1;
                Task tempTask = listOfItems[numbering];
                listOfItems = removeElement(listOfItems, listOfItems[numbering]);
                numOfItems--;
                userOutput = "Noted I have removed this task:\n" + tempTask.toString()
                        + "\nYou have " + numOfItems + " items in the list now.";
                //System.out.println("\t\tNoted I have removed this task:\n\t\t" + tempTask.toString() + "\n");
                //System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                return userOutput;
            } else if (Objects.equals(wordSplit[0], "find")) {
                String findWord = wordSplit[1];
                userOutput = "";
                for (int i = 0; i < numOfItems; i++) {
                    if (listOfItems[i].description.contains(findWord)) {
                        userOutput += listOfItems[i].toString() + "\n";
                        //System.out.println("\t\t" + listOfItems[i].toString() + "\n");
                    }
                }
                return userOutput;
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
                        userOutput = String.format("Got it. I've added this task: \n%s\nYou have %d items in the list now", 
                                listOfItems[numOfItems - 1].toString(), numOfItems);
                        return userOutput;
                        //System.out.println("\t\tGot it. I've added this task: \n\t\t"
                        // + listOfItems[numOfItems - 1].toString() + "\n");
                        //System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                    } catch (StringIndexOutOfBoundsException ex) {
                        System.out.println(ex.getMessage());
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                        userOutput = "Please use date time format as such: dd/MM/yyyy HHmm";
                        //System.out.println("Please use date time format as such: dd/MM/yyyy HHmm");
                        return userOutput;
                    }
                } else if (Objects.equals(wordSplit[0], "Todo")) {
                    try {
                        String newInputString = inputString.substring(5);
                        Task item = new Todo(newInputString);
                        listOfItems[numOfItems++] = item;
                        System.out.println("\t\tGot it. I've added this task: \n\t\t"
                                + listOfItems[numOfItems - 1].toString() + "\n");
                        System.out.println("\t\tYou have " + numOfItems + " items in the list now.");
                        userOutput = String.format("Got it. I've added this task: \n" +
                                "%s\n You have %d items in the list now.", listOfItems[numOfItems - 1], numOfItems);
                        return userOutput;
                    } catch (Exception stringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format! Todo");
                        userOutput = "Please follow the proper format! Todo";
                        stringIndexOutOfBoundsException.getMessage();
                        return userOutput;
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
                        userOutput = String.format("Got it. I've added this task: \n" +
                                "%s\n You have %d items in the list now.", listOfItems[numOfItems - 1], numOfItems);
                        return userOutput;
                    } catch (Exception stringIndexOutOfBoundsException) {
                        System.out.println("Please follow the proper format!");
                        userOutput = "Please follow the proper format!";
                        return userOutput;
                    }
                } else if (Objects.equals(wordSplit[0], "Undo")){
                    //pass;
                } else {
                    System.out.println("\t\tHey Heyyyy, "
                            + "sorry but I have no idea what are you saying, please try again!!!");
                }
                assert wordSplit != null;

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return HelpCommand.Invalid();
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
}
