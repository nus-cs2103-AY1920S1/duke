package utilities;


import expense.Expense;
import expense.randomExpense;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Utilities.Storage enables data to be retrieved and stored in the text file.
 */
public class Storage {

    private String filename;
    private String Line_Break = "_____EXPENSES_____";

    /**
     * constructor.
     *
     * @param filename is the name of hard drive file to output to
     */
    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * to load the list from output.
     *
     * @return ArrayList of tasks to create Utilities.TaskList
     *
     * @throws Exception in case BufferedReader is unable to read the filename
     */
    public ArrayList<Task> load() throws Exception  {
        ArrayList<Task> list = new ArrayList<>();

        BufferedReader br = Files.newBufferedReader(Paths.get(filename));
        String lineToRead;

        while (!(lineToRead = br.readLine()).equals(Line_Break)) {
            if ((!lineToRead.equals("")) && (lineToRead.charAt(0) == 'T')) {
                Task newTask = ToDo.outputAsToDo(lineToRead);
                list.add(newTask);
            } else if ((!lineToRead.equals("")) && (lineToRead.charAt(0) == 'D')) {
                Task newTask = Deadline.outputAsDeadline(lineToRead);
                list.add(newTask);
            } else if ((!lineToRead.equals("")) && (lineToRead.charAt(0) == 'E')) {
                Task newTask = Event.outputAsEvent(lineToRead);
                list.add(newTask);
            }
        }
        return list;

    }

    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> list = new ArrayList<>();

        try {
        BufferedReader br = Files.newBufferedReader(Paths.get(filename));
        String lineToRead;
        boolean isExpense = false;

            while ((lineToRead = br.readLine()) != null){
                if(isExpense){
                    String name = lineToRead.substring(3).split("\\|", 3)[0].trim();
                    String cost = lineToRead.substring(3).split("\\|", 3)[1].trim();
                    String tagName = lineToRead.substring(3).split("\\|", 3)[2].trim();
                    String command = name + "/for" + cost + " #" + tagName;
                    list.add(new randomExpense(command));
                }else if(lineToRead.equals(Line_Break)){
                    isExpense = true;
                }
            }
        } catch (IOException e) {
            System.out.println("File not read");
        } catch (Exception ignored) {}

        return list;
    }

    /**
     * prints list on the output text file.
     *
     * @param tasks is the list of tasks to be printed
     *
     * @param eList is the list of expenses
     * @throws FileNotFoundException in case filename is not found
     */
    public void updateFile(TaskList tasks, ExpenseList eList) throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println(tasks.printForOutput());
        outputTo.println(Line_Break);
        outputTo.println(eList.toString());
        outputTo.close();
    }

}
