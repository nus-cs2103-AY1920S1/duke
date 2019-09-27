package duke.logic;

import duke.extension.expense.Expense;
import duke.extension.expense.ExpenseCategory;
import duke.extension.expense.ExpenseList;
import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage space that stores a file path.
 */
public class Storage {
    private String filePath;
    private ExpenseList tempExpenseList; //might wanna find a better way to do this

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Rewrites file data with text to be added.
     *
     * @param filePath the representation of the path of the file.
     * @param textToAdd text to be added to file.
     * @throws IOException when I/O operations fail.
     */
    private void rewriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to be added to the end of the file data.
     *
     * @param filePath the representation of the path of the file.
     * @param textToAppend text to be appended to file.
     * @throws IOException when I/O operations fail.
     */
    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rewrites the file data with new data from an ArrayList<Task> </Task>.
     *
     * @param taskList the ArrayList<Task> </Task> to be used.
     * @param expensesList the ArrayList<ExpenseCategory> </ExpenseCategory> to be used.
     */
    public void write(ArrayList<Task> taskList, ArrayList<ExpenseCategory> expensesList) {
        //Store all the latest data from the ArrayList
        try {
            rewriteFile(this.filePath, "");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        for (Task task : taskList) {
            StringBuilder dataBuilder = new StringBuilder();
            dataBuilder.append(task.getTaskType());
            dataBuilder.append(" : ");
            if (task.isDone()) {
                dataBuilder.append(1);
            } else {
                dataBuilder.append(0);
            }
            dataBuilder.append(" : ");
            dataBuilder.append(task.getDescription());
            if ((task instanceof Event) || (task instanceof Deadline)) {
                dataBuilder.append(" : ");
                if (task instanceof Event) {
                    dataBuilder.append(((Event) task).getExactDuration());
                } else {
                    dataBuilder.append(((Deadline) task).getExactBy());
                }
            }
            try {
                appendToFile(this.filePath, dataBuilder.toString() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        try {
            appendToFile(this.filePath, "-----" + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        for (ExpenseCategory expenseCategory : expensesList) {
            StringBuilder dataBuilder = new StringBuilder();
            dataBuilder.append(expenseCategory.getCategoryName());
            try {
                appendToFile(this.filePath, dataBuilder.toString() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
            for(Expense expense : expenseCategory.getExpenses()) {
                StringBuilder dataBuilder1 = new StringBuilder();
                dataBuilder1.append(expense.getAmount());
                dataBuilder1.append(" : ");
                dataBuilder1.append(expense.getDescription());
                try {
                    appendToFile(this.filePath, dataBuilder1.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Loads data from a file into an ArrayList<Task> </Task>.
     *
     * @return an ArrayList<Task> </Task> with data from file.
     * @throws FileNotFoundException If file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskListToBeLoaded = new ArrayList<>();
        ExpenseList expenseListToBeLoaded = new ExpenseList();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        String nextDataLine;
        while(s.hasNextLine()) {
            nextDataLine = s.nextLine();
            if(nextDataLine.equals("-----")) {
                break;
            }
                String[] temp = nextDataLine.split(" : ");
                String task = (String) Array.get(temp, 0);
                for (int i = 0; i < temp.length; i++) {
                    System.out.println((String) Array.get(temp, i));
                }
                Task newTask;
                if (task.equals("T")) {
                    newTask = new Todo((String) Array.get(temp, 2));
                } else if (task.equals("D")) {
                    newTask = new Deadline((String) Array.get(temp, 2),
                            (String) Array.get(temp, 3));
                } else {
                    newTask = new Event((String) Array.get(temp, 2),
                            (String) Array.get(temp, 3));
                }
                if (((String) Array.get(temp, 1)).equals("1")) {
                    newTask.setDone();
                }
                taskListToBeLoaded.add(newTask);
        }
        while(s.hasNextLine()) {
            s.nextLine(); //"Food"
            for (int i = 0; i < expenseListToBeLoaded.getExpenseCategories().size(); i++) {
                ExpenseCategory category = expenseListToBeLoaded.getExpenseCategories().get(i);
                while (s.hasNextLine()) {
                    nextDataLine = s.nextLine();
                    String[] temp = nextDataLine.split(" : ");
                    String nextString = (String) Array.get(temp, 0);
                    if (nextString.equals(expenseListToBeLoaded.getExpenseCategories().get(i + 1).getCategoryName())) {
                        break;
                    } else {
                        double amount = Double.parseDouble(nextString);
                        String description = (String) Array.get(temp, 1);
                        category.add(new Expense(amount, description));
                    }
                }
            }
        }
        tempExpenseList = expenseListToBeLoaded;
        return taskListToBeLoaded;
    }

    public ExpenseList getTempExpenseList() {
        return tempExpenseList;
    }
}
