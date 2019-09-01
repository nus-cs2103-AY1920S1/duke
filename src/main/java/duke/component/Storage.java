package duke.component;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for storing and retrieving tasks from hard disk.
 */
public class Storage {
    private String filePath;
    private String directoryPath;
    private File inputFile;
    private File directory;

    /**
     * Constructor for storage object.
     * @param currentWorkingDirectoryPath path to the current working directory of the program
     */
    public Storage(String currentWorkingDirectoryPath) {
        this.directoryPath = currentWorkingDirectoryPath + "\\data";
        this.filePath = currentWorkingDirectoryPath + "\\data\\Duke.txt";
        this.directory = new File(directoryPath);
        this.inputFile = new File(filePath);
    }

    /**
     * Loads tasks from storage at hard disk into the program.
     *
     * @return list of tasks loaded with task from hard disk.
     * @throws FileNotFoundException when input file cannot be found.
     * @throws IOException when fails to create new file
     */
    public List<Task> load() throws FileNotFoundException, IOException {
        List<Task> taskList = new ArrayList<>();

        if(!this.directory.exists()) {
            this.directory.mkdir();
        }

        if(!this.inputFile.exists()) {
            this.inputFile.createNewFile();
        }
        Scanner txtSC = new Scanner(inputFile);

        while (txtSC.hasNext()) {
            String[] historicalInputs = Parser.breakDownString(txtSC.nextLine(), "\\|");
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
            default:
            }

            taskList.add(oldTask);
        }

        txtSC.close();

        return taskList;
    }

    /**
     * Saves tasks from the program into hard disk.
     * @param taskList list of tasks to be stored into hard disk.
     * @throws FileNotFoundException when input file cannot be found.
     * @throws UnsupportedEncodingException when encoding error occurs.
     */
    public void save(TaskList taskList) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileWriter = new PrintWriter(filePath, "UTF-8");

        for (int i = 0; i < taskList.getSize(); i++) {
            fileWriter.println(taskList.getAtIndex(i).toIndicationInsideFile());
        }

        fileWriter.close();
    }
}
