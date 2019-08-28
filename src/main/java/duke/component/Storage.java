package duke.component;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for storing and retrieving tasks from hard disk
 */
public class Storage {
    private String filePath;
    private File inputFile;

    /**
     * Constructor for storage object
     * @param filePath path to the location of the file in hard disk
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.inputFile = new File(filePath);
    }

    /**
     * Loads tasks from storage at hard disk into the program
     * @return list of tasks loaded with task from hard disk
     * @throws FileNotFoundException
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        Scanner txtSC = new Scanner(inputFile);

        while (txtSC.hasNext()) {
            String[] historicalInputs = Parser.breakDownString(txtSC.nextLine(),"\\|");
            boolean taskIsCompleted;
            Task oldTask = null;

            if (historicalInputs[1].charAt(1) == '1') {
                taskIsCompleted = true;
            } else {
                taskIsCompleted = false;
            }

            switch (historicalInputs[0].charAt(0)) {
                case 'T':
                    oldTask = new Todo(historicalInputs[2].substring(1), taskIsCompleted);
                    break;
                case 'D':
                    String[] time = historicalInputs[3].substring(1).split(" ");
                    oldTask = new Deadline(
                                            historicalInputs[2].substring(1, historicalInputs[2].length() - 1),
                                            Parser.getDateAndTimeFromString(historicalInputs[3].substring(1)),
                                            taskIsCompleted);
                    break;
                case 'E':
                    oldTask = new Event(
                                        historicalInputs[2].substring(1, historicalInputs[2].length() - 1),
                                        Parser.getDateAndTimeFromString(historicalInputs[3].substring(1)),
                                        taskIsCompleted);
                    break;
            }

            taskList.add(oldTask);
        }

        txtSC.close();

        return taskList;
    }

    /**
     * Saves tasks from the program into hard disk
     * @param taskList list of tasks to be stored into hard disk
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void save(TaskList taskList) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileWriter = new PrintWriter(filePath, "UTF-8");

        for(int i = 0; i < taskList.getSize(); i++) {
            fileWriter.println(taskList.getAtIndex(i).toIndicationInsideFile());
        }

        fileWriter.close();
    }
}
