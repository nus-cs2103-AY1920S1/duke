import java.util.Scanner;

public class Parser {
    Ui myUi;
    TaskList myList;


    public Parser(Ui x, TaskList y) {
        myUi = x;
        myList = y;
    }

    public void parse() {
        Scanner myInputReader = new Scanner(System.in);
        String userInput = myInputReader.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    myUi.printTasks(myList.getList());
                } else if (userInput.contains("done")) {
                    String[] numTasks = userInput.split(" ");
                    String numberAsString = numTasks[1];
                    int number = Integer.parseInt(numberAsString);
                    myUi.printDone(myList.taskDone(number - 1));
                } else if (userInput.contains("delete")) {
                    String[] numTasks = userInput.split(" ");
                    String numberAsString = numTasks[1];
                    int number = Integer.parseInt(numberAsString);
                    myUi.printDelete(myList.deleteTask(number - 1), myList);
                } else {
                    //Adding is handled in tasklist
                    Task current = myList.addTasks(userInput);
                    myUi.printAdd(current, myList);
                }
            } catch (DukeException err) {
                String separator = "    ____________________________________________________________";
                System.out.println(separator);
                System.out.println("    " + err.getMessage());
                System.out.println(separator + "\n");
            } catch (incompleteInputException otherErr) {
                String separator = "    ____________________________________________________________";
                System.out.println(separator);
                System.out.println("    " + otherErr.getMessage());
                System.out.println(separator + "\n");
            } finally {
                userInput = myInputReader.nextLine();
            }

        }
    }
}

